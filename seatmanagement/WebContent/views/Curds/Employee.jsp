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
		<h2>Employee Details</h2>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Employee</h2>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
							<div class="form-group">
								<label for="employeeId">Employee Id:</label> 
								<input name="employeeId" class="form-control" id="employeeId" type="text" placeholder="Employee Id"/>
							</div>
							<div class="form-group">
								<label for="employeeRoll">Employee Roll Number:</label>  
								<input name="employeeRoll" class="form-control" id="employeeRoll" type="text" placeholder="Employee Roll Number"/>
							</div>
							<div class="form-group">
								<label for="firstName">First Name:</label> 
								<input type="text" class="form-control" id="firstName" placeholder="First Name" name="firstName">
							</div>
							<div class="form-group">
								<label for="lastName">Last Name:</label> 
								<input type="text" class="form-control" id="lastName" placeholder="Last Name" name="lastName">
							</div>
							<div class="form-group">
								<label for="designation">Designation:</label> 
								<input type="text" class="form-control" id="designation" placeholder="Designation" name="designation">
							</div>
							<div class="form-group">
								<label for="dateOfJoin">Date of Join:</label> 
       							<input class="form-control" id="date" name="doj" placeholder="DD/MM/YYY" type="date"/>
							  </div>
							<div class="form-group">
								<label for="teamId">Team Id:</label> 
								<input type="text" class="form-control" id="teamId" placeholder="Team Id" name="team">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="employee" ng-controller="employeeController">
				<div class="table-resposive" style="overflow-x: auto; height: 700px;">
					<table class="table table-hover">
						<thead align="center">
							<td>Employee Roll Number</td>
							<td>First Name</td>
							<td>Last Name</td>
							<td>Designation</td>
							<td>Date of Join</td>
							<td>Team Name</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody align="center">
						<tr ng-repeat="emp in getemployee" align="center">
						<td>{{emp.employeeRoll}}</td>
						<td>{{emp.firstName}}</td>
						<td>{{emp.lastName}}</td>
						<td>{{emp.designation}}</td>
						<td>{{emp.doj}}</td>
						<td>{{emp.team}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="teamId" value="{{emp.teamId}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form>
						</td>
						</tr>
						</tbody>
						
					</table>
					</div>
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

			document.getElementById('employeeRoll').value = col1;
			document.getElementById('"firstName"').value = col2;
			document.getElementById('lastName').value = col3;
			document.getElementById('designation').value = col4;
			document.getElementById('date').value = col5;
			document.getElementById('teamId').value = col6;
		})
	</script> -->
	
	<script>
	var app = angular.module('employee', []);
	app.controller('employeeController', function($scope, $http) {
	    $http.post("../../employee/getAllEmployees")
	        .then(function successCallback(response) {
	            $scope.getemployee = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
	});
	</script>
	
	<script type="text/javascript">
	function formSubmit(){
	
	 $.ajax({
	     url:'../../employee/saveEmployee',
	     method : 'POST',
	     data: $("#Form").serialize(),
	     success: function (data) {
	            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("Employee.jsp");
	    }
		 error: function (jqXHR,exception) {
			alert(exception)
	 	}
	 	
	});
	}
		
	var tId = null;

	$('.table tbody').on('click', '.deleteBtn', function() {
		var currow = $(this).closest('tr');
		tId = currow.find('td:eq(0)').text();
		console.log("tId : " + tId);
		
		 $.post("../../employee/deleteEmployeeById", {
			 teamId:tId
			}, function(data) {
				// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("Employee.jsp");
			});
		}
	);
		 
		
	</script>

</body>
</html>
