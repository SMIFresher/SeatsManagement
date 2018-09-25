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

						<h2>Floor</h2>
						<form action="#">
							<div class="form-group">
								<label for="fid">Floor Id:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="fid" placeholder="Enter Floor Id"
									name="fID">
							</div>
							<div class="form-group">
								<label for="ftype">Floor Type:</label>  <input type="text"
									class="form-control" id="ftype" placeholder="Enter Floor Type"
									name="floortype">
							</div>
							<div class="form-group">
								<label for="fname">Floor Name:</label>  <input type="text"
									class="form-control" id="fname" placeholder="Enter Floor Name"
									name="floortype">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="floor" ng-controller="floorController">

					<table class="table table-hover">
						<thead align="center">
							<td>Floor Id</td>
							<td>Floor Type</td>
							<td>Floor Name</td>
							<td>Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="flr in getflr" align="center">
								<td>{{flr.organisationId}}</td>
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
var app = angular.module('floor', []);
app.controller('floorController', function($scope, $http) {
    $http.post("../../floor/getAllFloor")
        .then(function successCallback(response) {
            $scope.getOrg = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
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
