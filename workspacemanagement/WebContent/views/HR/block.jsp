

<!DOCTYPE html>
<html lang="en">
<head>
<title>Curd Application</title>
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
</head>
<body>

<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">
						<h2>Blocks</h2>
						<form id="Form" method="post" onsubmit="formSubmit()">
						
							<div ng-app="Building" ng-controller="BuildingController"
								ng-init="loadBuilding()">
								
								<div class="form-group">
								<label for="location">Building:</label>
								<select name="building" ng-model="building" class="form-control"
									ng-change="floorDetails(building)">
									<option value="">--Select Building--</option>
									<option ng-repeat="building in getBuilding" value="{{building.buildingId}}">{{building.buildingName}}</option>
								</select>
								</div>
								
								<div class="form-group">
									<label for="location">Floor:</label>
								<select name="floorId" ng-model="floor"
									class="form-control" ng-change="blockDetails(floor)">
									<option value="">--Select Floor--</option>
									<option ng-repeat="floor in getFloor" value="{{floor.floorId}}">
										{{floor.floorName}}</option>
								</select>
								</div>
								
							</div>
							
							<div class="form-group">
								<label for="blockName">Block Name:</label> 
									<input type="text"
									class="form-control" id="blockName" placeholder="Enter Block Name"
									name="blockName"> 
							</div>
							<div class="form-group">
								<label for="blockType">Block Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Rooms" name="blockType" value="Rooms"> 
												<label class="custom-control-label" for="Rooms">Rooms</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Cabins" name="blockType" value="Cabins">
												 <label class="custom-control-label" for="Cabins">Cabins</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="blockMeasurement">Block Measurement:</label> 
									 <input type="text"
									class="form-control" id="blockMeasurementString" placeholder="Enter Block Measurement"
									name="blockMeasurement">
							</div>
							<div class="form-group">
								<label for="block_capacity">Block Capacity:</label>
								<input type="text"
									class="form-control" id="block_capacity"
									placeholder="Enter Block Capacity" name="blockCapacity">
							</div>
							<div class="form-group">
								<label for="block_desc">Block Description:</label> 
								 <input type="text"
									class="form-control" id="block_desc"
									placeholder="Enter Block Description" name="blockDescription">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id="block" ng-app="block" ng-controller="blockController">

					<table class="table table-hover">
						<thead>
							<td>Building Name</td>
							<td>Floor Name</td>
							<td>Block Id</td>
							<td>Block Name</td>
							<td>Block Measurement</td>
							<td>Block Type</td>
							<td>Block Capacity</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody >
						<tr ng-repeat="blk in getBlock" align="center">
						<td>{{blk.floor.building.buildingName}}</td>
						<td>{{blk.floor.floorName}}</td>
						<td>{{blk.blockId}}</td>
						<td>{{blk.blockName}}</td>
						<td>{{blk.blockMeasurement}}</td>
						<td>{{blk.blockType}}</td>
						<td>{{blk.blockCapacity}}</td>
						<td align="center"><button class="btn btn-danger deleteBtn" value="{{blk.blockId}}">Delete</button></td>
						
						</tr>
						</tbody>
						
					</table>
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
		}
		
		$scope.floorDetails=function(buildingId){
			$http.get("../../floor/getFloorByBuildingId?buildingId="+buildingId)
	        .then(function successCallback(response) {
	            $scope.getFloor = response.data;
	            console.log(response.data);
				
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
		};
	
	});
	var app = angular.module('block', ['Building']);
	app.controller('blockController', function($scope, $http) {
	    $http.post("../../block/getAllBlocks")
	        .then(function successCallback(response) {
	            $scope.getBlock = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
	});

	angular.element(document).ready(function() {
	    angular.bootstrap(document.getElementById("block"), ['block']);
	  });

</script>
	
<script type="text/javascript">
function formSubmit(){
	  console.log("hello");
	 $.ajax({
	     url:'../../block/saveblock',
	     method : 'POST',
	     data: $("#Form").serialize(),
	   
	     success: function (data) {
	            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");

	    }
	 	
	});
	}
		
		
	var blockId = null;
	
	$('.table tbody').on('click', '.deleteBtn', function() {
		var currow = $(this).closest('tr');
		blockId = currow.find('td:eq(2)').text();
		console.log("blockId : " + blockId);
		
		 $.post("../../block/deleteBlockById", {
			 blockId:blockId
			}, function(data) {
				// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("block.jsp");
			});
	
		}
	);
		 
		
	</script>

	
</body>
</html>
