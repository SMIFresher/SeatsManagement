<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
		<h2>Seating Arrangement</h2>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Seating Arrangement</h2>
						<form method="post" onsubmit="formSubmit()" id="Form">
						
							<div ng-app="Building" ng-controller="BuildingController"
								ng-init="loadBuilding()">
								<div class="form-group">
								<select name="building" ng-model="building" class="form-control"
									ng-change="loadFloor()">
									<option value="">Select Building</option>
									<option ng-repeat="building in getBuilding" value="{{building.buildingId}}">{{building.buildingName}}</option>
								</select>
								</div>
								<div class="form-group">
								<select name="floor" ng-model="floor"
									class="form-control">
									<option value="">Select Floor</option>
									<option ng-repeat="floor in getflr" value="{{floor.floorId}}">
										{{floor.floorName}}</option>
								</select>
								</div>
							</div>

							<div class="form-group">
								<label for="Seatoccupied">Seat Occupied:</label>  
								<input type="text" class="form-control" id="seatoccupied" placeholder="Enter Occupied Seat"
									name="seatOccupied">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="myApp" ng-controller="customersCtrl">

					<table class="table table-hover">
						<thead align="center">
							<td>Building Name</td>
							<td>Floor Name</td>
							<td>Block Name</td>
							<td>Seat Occupied</td>
							<td align="center">Process</td>
						</thead>

						<tbody>
							<tr ng-repeat="flr in getflr" align="center">
								<td>{{flr.organisationId}}</td>
								<td>{{flr.organisationName}}</td>
								<td>{{flr.organisationName}}</td>
								<td>{{flr.organisationName}}</td>
								<td><button class="btn btn-danger">Delete</button></td>
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
	
	$scope.loadFloor = function(){ 
	    $http.get("../../floor/getFloorsByBuildingId",{'buildingId':$scope.building})
	        .then(function successCallback(response) {
	            $scope.getflr = response.data;
	            console.log("floor :" + response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
		}
});

/* var app = angular.module('Floor', ['Building']);
app.controller('FloorController', function($scope, $http) {
	$scope.loadFloor = function(){ 
    $http.post("../../floor/getFloorsByBuildingId",{'buildingId':$scope.building})
        .then(function successCallback(response) {
            $scope.getflr = response.data;
            console.log("floor :" + response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
	}
}); */
</script>	
		
		
<script type="text/javascript">
function formSubmit(){
	
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
</body>
</html>
