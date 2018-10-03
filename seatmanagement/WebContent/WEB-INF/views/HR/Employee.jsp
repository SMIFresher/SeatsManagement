<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="Form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	 <script src="/seatmanagement/js/ajaxConfig.js"></script>

	 
</head>
<body>

<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br>
	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Employee</h2>
						<form id="Form" autocomplete="on">
							
							<div class="form-group">
								<label for="employeeRoll">Employee Roll Number:</label>  
								<input name="employeeRoll" class="form-control" id="employeeRoll" type="text" placeholder="Employee Roll Number"/>
								<input name="employeeId" class="form-control" id="employeeId" type="hidden" placeholder="Employee Id"/>			
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
								<select class="custom-select mb-3" name="designation" id="designation">
									<option value="PROJECT MANAGER">PROJECT MANAGER</option>
									<option value="TECH LEADER">TECH LEADER</option>
									<option value="TEAM LEADER">TEAM LEADER</option>
									<option value="TRAINEE">TRAINEE</option>
									<option value="HR">HR</option>
									<option value="PROJECT MEMBER">PROJECT MEMBER</option>
								</select>
							</div>
							<div class="form-group">
								<label for="dateOfJoin">Date of Join:</label> 
       							<input class="form-control" id="date" name="doj" placeholder="DD/MM/YYY" type="date"/>
							  </div>
							 <div class="form-group">
		                   		<div ng-app="Organisation" ng-controller="OrganisationController" id="organisation1">
								<label for="organisation">Organisation </label>
									<select class="custom-select mb-3 organisationId" name="organisationId">
										<option ng-repeat="organisation1 in getOrganisations" value="{{organisation1.organisationId}}">{{organisation1.organisationName}}</option>
									</select>
								

								<label for="team">Team Name </label>
									<select class="custom-select mb-3 teamId" name="teamId">
										<option ng-repeat="team1 in getTeam" value="{{team1.teamId}}">{{team1.teamName}}</option>
									</select>
							</div>
							</div>
							<button type="submit" class="btn btn-primary" onClick="formSubmit();" >Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			
			<div class="col-md-8">
				<div id="emp" ng-app="employee" ng-controller="employeeController">
				<!-- <div class="table-resposive" style="overflow-x: auto; height: 700px;"> -->
					<table class="table table-hover">
						<thead align="center">
							<td>Roll Number</td>
							<td>First Name</td>
							<td>Last Name</td>
							<td>Designation</td>
							<td>Date of Join</td>
							<td>Organisation Name</td>
							<td>Team Name</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody align="center">
						<tr ng-repeat="emp in getemployees" align="center">
						<td>{{emp.employeeRoll}}</td>
						<td>{{emp.firstName}}</td>
						<td>{{emp.lastName}}</td>
						<td>{{emp.designation}}</td>
						<td>{{emp.doj}}</td>
						<td>{{emp.organisation.organisationName}}</td>
						<td>{{emp.team.teamName}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="teamId" value="{{emp.team}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form>
						</td>
						<td style="display:none;">{{emp.employeeId}}</td>
						</tr>
						</tbody>
						
					</table>
					<!-- </div> -->
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

	var teamApp = angular.module('Team', []);
	teamApp.controller('TeamController', function($scope, $http) {
	    
	});
	
	var employeeApp = angular.module('employee', ['Team']);
	//AJAX Request Type Header to prepare error response for AJAX seperately
	employeeApp.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
		  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
		});
	employeeApp.controller('employeeController', function($scope, $http) {

	    $http.post("/seatmanagement/employee/getAllEmployees")
	        .then(function successCallback(response) {
	            $scope.getemployees = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
	});

	var organisationApp = angular.module('Organisation', []);
	//AJAX Request Type Header to prepare error response for AJAX seperately
	organisationApp.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
		  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
		});
	organisationApp.controller('OrganisationController', function($scope, $http) {
	    $http.post("/seatmanagement/organisation/getAllOrganisations")
	        .then(function successCallback(response) {
	            $scope.getOrganisations = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	        });
	    
	    $http.post("/seatmanagement/team/getAllTeam")
        .then(function successCallback(response) {
            $scope.getTeam = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
        });
	    
	    
	});

	angular.element(document).ready(function() {
	    angular.bootstrap(document.getElementById("emp"), ['employee']);
	});
	
	</script>
	
	<script type="text/javascript">
	function formSubmit(){
		
	var teamId = $("#Form .teamId").val();		
	if(teamId == null){
		$.ajax({
		     url:'/seatmanagement/employee/saveEmployeeWithoutTeam',
		     method : 'POST',
		     data: $("#Form").serialize(),
		     success: function (data) {
		            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
		            location.replace("/seatmanagement/employee/getEmployeeView");
		    },error: function(data){
		    }
		 	
		});
	}else{
		$.ajax({
		     url:'/seatmanagement/employee/saveEmployeeWithTeam',
		     method : 'POST',
		     data: $("#Form").serialize(),
		     success: function (data) {
		            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
		            location.replace("/seatmanagement/employee/getEmployeeView");
		    },error: function(data){
		    }
		 	
		});
	}
	 
	}
	
		
	var eId = null;

	$('.table tbody').on('click', '.deleteBtn', function() {
		var currow = $(this).closest('tr');
		eId = currow.find('td:eq(8)').text();
		console.log("eId : " + eId);
		
		 $.post("/seatmanagement/employee/deleteEmployeeById", {
			 employeeId:eId
			}, function(data) {
				// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("/seatmanagement/employee/getEmployeeView");
			});
		}
	);
		 
		
	</script>

</body>
</html>
