<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="Form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  <script src="../js/angular.ng-modules.js"></script>
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

						<h2>Floor</h2>
						<form method="post" onsubmit="formSubmit()" id="Form">
							<div class="form-group">
		                   		<div ng-app="Building" ng-controller="BuildingController" id="build">
								<label for="location">Building Name </label>
									<select class="custom-select mb-3" name="buildingId">
										<option ng-repeat="build in getBuilding" value="{{build.buildingId}}">{{build.buildingName}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="ftype">Floor Type:</label>  <input type="text"
									class="form-control" id="ftype" placeholder="Enter Floor Type"
									name="floorType">
							</div>
							<div class="form-group">
								<label for="fname">Floor Name:</label>  <input type="text"
									class="form-control" id="fname" placeholder="Enter Floor Name"
									name="floorName">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id="floor" ng-app="floor" ng-controller="floorController">

					<table class="table table-hover">
						<thead align="center">
							<td>Floor Name</td>
							<td>Floor Type</td>
							<td>Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="flr in getflr" align="center">
								<td>{{flr.floorName}}</td>
								<td>{{flr.floorType}}</td>
								<td style="display:none;">{{flr.floorId}}</td>
								<td><button class="btn btn-danger deleteBtn">Delete</button></td>
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
    $http.post("../../building/getAllBuildings")
        .then(function successCallback(response) {
            $scope.getBuilding = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
});

var app = angular.module('floor', ['Building']);
app.controller('floorController', function($scope, $http) {
    $http.post("../../floor/getAllFloor")
        .then(function successCallback(response) {
            $scope.getflr = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
});

$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	var floorId = currow.find('td:eq(2)').text();
	console.log("floorId : " + floorId);
	
	 $.post("../../floor/deleteFloorById", {
		 floorId:floorId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Floor.jsp");
		});

	}
);

angular.element(document).ready(function() {
    angular.bootstrap(document.getElementById("floor"), ['floor']);
  });
  
</script>

<script type="text/javascript">
function formSubmit(){
	
 $.ajax({
     url:'../../floor/floorsave',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");

    }
 	
});
}
</script>
</body>
</html>
