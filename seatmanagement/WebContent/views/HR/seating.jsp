
<!DOCTYPE html>
<html lang="en">
<head>
<title>Seating Assign</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.2/dist/leaflet.css" />
<script type="text/javascript" src="https://unpkg.com/leaflet@1.0.2/dist/leaflet.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="imgViewer2.js"></script>
</head>
<body>

<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Seating Arrangement</h2>
						<hr>
						
						<form method="post" id="Form"  onsubmit="formSubmit()">
						
							<div ng-app="Building" ng-controller="BuildingController"
								ng-init="loadBuilding()">
								<div class="form-group">
									 <label for="emp_id">Building</label>
									<select ng-model="building" class="form-control"
										ng-change="floorDetails(building)">
										<option value="">Select Building</option>
										<option ng-repeat="building in getBuilding" value="{{building.buildingId}}">{{building.buildingName}}</option>
									</select>
								</div>
								
								<div class="form-group">
									<label>Floor</label>
									<select ng-model="floor"
										class="form-control" ng-change="blockDetails(floor)">
										<option value="">Select Floor</option>
										<option ng-repeat="floor in getFloor" value="{{floor.floorId}}">
											{{floor.floorName}}</option>
									</select>
								</div>
								
								<div class="form-group">
									<label for="emp_id">Black</label>
									<select name="blockID" ng-model="block"
										class="form-control">
										<option value="">Select Floor</option>
										<option ng-repeat="block in getBlock" value="{{block.blockId}}">
											{{block.blockName}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
					          <label for="xax">x</label>  
							<input type="text" class="form-control" id="xax" placeholder="X Axis" name="x_axis">
					        </div>
					        <div class="form-group">
					          <label for="yax">y</label>  
								<input type="text" class="form-control" id="yax" placeholder="Y Axix" name="y_axis">
					        </div>
							<div class="form-group">
								<label for="Seatoccupied">Seat Occupied:</label>  
								<input type="text" class="form-control" id="seatoccupied" placeholder="Enter Occupied Seat"
									name="seat_occupied">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div class="borderr text-center">

			      <img  id="image1" src="images/vgs.svg" style="border: 30px solid #555; padding:20px;" width="80%"  />
			  
			     </div>
			</div>
		</div>
	</div>








<script>
var app = angular.module('Building', []);
app.controller('BuildingController', function($scope, $http) {
	
	$scope.loadBuilding = function(){ 
    $http.post("../../building/getAllBuildings")
        .then(function successCallback(response) {
            $scope.getBuilding = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
	};
	
	$scope.floorDetails=function(buildingId){
		$http.get("../../floor/getFloorByBuildingId?buildingId="+buildingId)
        .then(function successCallback(response) {
            $scope.getFloor = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            alert(response.status);
        });
	};
//chages are thare
	$scope.blockDetails=function(floor){
		$http.get("../../block/getBlockByBlockType?block_type=Cabins&floor_id="+floor)
        .then(function successCallback(response) {
            $scope.getBlock = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            alert(response.status);
        });
	};
});

</script>	
		
		
<script type="text/javascript">
function formSubmit(){
	console.log($('#Form').serialize());
 $.ajax({
     url:'../../seating/saveSeating',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> Successfully Inserted</div>");

    }
 	
});
}
</script>


<script type="text/javascript">
			;(function($) {
				$(document).ready(function(){
					var $img = $("#image1").imgViewer2({
								onReady: function() {
									$('.leaflet-grab').css('cursor','crosshair');
								},
								onClick: function( e, pos ) {	
									var imgpos = this.relposToImage(pos);
									$("#xax").val(pos.x);
					                $("#yax").val(pos.y);
								}
					});
				});
			})(jQuery);
		</script>
</body>
</html>
