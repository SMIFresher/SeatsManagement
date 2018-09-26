

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

	<div class="jumbotron text-center bg-primary text-white">
		<h2>View All</h2>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">
						<h2>Blocks</h2>
						<form id="Form" method="post" onsubmit="formSubmit()">
							<div class="form-group">
		                   		<div ng-app="Building" ng-controller="BuildingController" id="build">
								<label for="location">Building Name </label>
									<select class="custom-select mb-3" name="buildingId">
										<option ng-repeat="build in getBuilding" value="{{build.buildingId}}">{{build.buildingName}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="floorName">Floor Name:</label> <select
									name="cars" class="custom-select mb-3" id="FloorName">
									<option selected>Floor Name</option>
									<option value=""></option>
								</select>
							</div>
							<div class="form-group">
								<label for="blockName">Block Name:</label> <input type="hidden" value=""
									name="id" id="blockName"> <input type="text"
									class="form-control" id="blockName" placeholder="Enter Block Name"
									name="block_Name">
							</div>
							<div class="form-group">
								<label for="systemType">Block Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Rooms"
												name="example1" value="Rooms"> <label
												class="custom-control-label" for="Rooms">Rooms</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="Cabins" name="example1" value="Cabins"> <label
												class="custom-control-label" for="Cabins">Cabins</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="pwd">Block Measurement:</label> <input type="hidden" value=""
									name="id" id="blockMeasurement"> <input type="text"
									class="form-control" id="blockMeasurement" placeholder="Enter Block Measurement"
									name="block_measure">
							</div>
							<div class="form-group">
								<label for="pwd">Block Capacity:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="block_capacity"
									placeholder="Enter Block Capacity" name="block_capacity">
							</div>
							<div class="form-group">
								<label for="pwd">Block Description:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="block_desc"
									placeholder="Enter Block Description" name="block_desc">
							</div>
							<div class="form-group">
								<label for="pwd">Square Feet:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="block_sqft"
									placeholder="Enter Block Square Feet" name="block_sqft">
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
							<td>Floor Name</td>
							<td>Block Id</td>
							<td>Block Name</td>
							<td>Block Measurement</td>
							<td>Block Type</td>
							<td>Block Description</td>
							<td>Capacity</td>
							<td>Square Feet</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody align="center">
						<tr ng-repeat="blk in getBlock">
						<td>{{blk.floor}}</td>
						<td>{{blk.blockId}}</td>
						<td>{{blk.blockName}}</td>
						<td>{{blk.blockMeasurement}}</td>
						<td>{{blk.blockType}}</td>
						<td>{{blk.blockDescription}}</td>
						<td>{{blk.blockCapacity}}</td>
						<td>{{blk.squarefeet}}</td>
						<td align="center"><button class="btn btn-danger">Delete</button></td>
						</tr>
						</tbody>
						
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- <script type="text/javascript">
		$('.table tbody').on('click', 'tr', function() {
			var currow = $(this).closest('tr');
			var col1 = currow.find('td:eq(0)').text();
			var col2 = currow.find('td:eq(1)').text();
			var col3 = currow.find('td:eq(2)').text();
			var col4 = currow.find('td:eq(3)').text();
			var col5 = currow.find('td:eq(4)').text();
			var col6 = currow.find('td:eq(5)').text();
			var col7 = currow.find('td:eq(6)').text();

			document.getElementById('buildingName').value = col1;
			document.getElementById('FloorName').value = col2;
			document.getElementById('blockName').value = col3;
			document.getElementById(col4).checked = true;
			document.getElementById('blockMeasurement').value = col5;
			document.getElementById('block_capacity').value = col6;
			document.getElementById('block_desc').value = col7;
			
		})
	</script> -->

	<script>
	var app = angular.module('Block', []);
	app.controller('BlockController', function($scope, $http) {
	    $http.post("../../block/getAllBlocks")
	        .then(function successCallback(response) {
	            $scope.getBuilding = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
	});
	
	var app = angular.module('block', ['Block']);
	app.controller('blockController', function($scope, $http) {
	    $http.post("../../block/getAllBlocks")
	        .then(function successCallback(response) {
	            $scope.getOrg = response.data;
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
	
	 $.ajax({
	     url:'../../block/save',
	     method : 'POST',
	     data: $("#Form").serialize(),
	     success: function (data) {
	            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("Block.jsp");
	    }
	 	
	});
	}
		
		
	var orgId = null;
	
	$('.table tbody').on('click', '.deleteBtn', function() {
		var currow = $(this).closest('tr');
		orgId = currow.find('td:eq(0)').text();
		console.log("orgId : " + orgId);
		
		 $.post("../../block/deleteBlockById", {
			 organisationId:orgId
			}, function(data) {
				// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("Block.jsp");
			});
	
		}
	);
		 
		
	</script>

	
</body>
</html>
