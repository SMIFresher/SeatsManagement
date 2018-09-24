<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

	<%
	String id = request.getParameter("userid");
	String driver = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/";
	String database = "seatmanagementexample";
	String userid = "root";
	String password = "root";
	try {
	Class.forName(driver);
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	%>



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

						<h2>Team</h2>
						<form action="../../organisation/saveOrganisation" method="post">
							<!-- <div class="form-group">
								<label for="orgId">Organization Id:</label> <input type="hidden" value=""
									name="id" id="id"> <input type="text"
									class="form-control" id="orgId" placeholder="Organization Id"
									name="team_id">
							</div> -->
							<div class="form-group">
								<label for="orgId">Organization Name:</label> <input type="hidden" value=""
									name="id" id="id"> <input type="text"
									class="form-control" id="orgId" placeholder="Organization Name"
									name="team_id">
							</div>
							<button type="submit" class="btn btn-primary">Add</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="organisation" ng-controller="OrganisationController">

					<table class="table table-hover">
						<thead>
							<td>Organization Id</td>
							<td>Organization Name</td>
							<td align="center">Process</td>
						</thead>
						<tbody align="center">
						<tr ng-repeat="org in getOrg">
						<td>{{org.organisationId}}</td>
						<td>{{org.organisationName}}</td>
						<td align="center">
							<form action="../../organisation/deleteOrganisationById" method="post"><button type="submit" class="btn btn-danger">Delete</button></form>
						</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
		<script>
		var app = angular.module('organisation', []);
		app.controller('OrganisationController', function($scope, $http) {
		    $http.get("../../organisation/getAllOrganisation")
		    .then(function (response) {$scope.getOrg = response.data.records;});
		});
		</script>
</body>
</html>
