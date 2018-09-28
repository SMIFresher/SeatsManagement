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

						<h2>Team</h2>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
							<div class="form-group">
								<label for="teadId">Team Id:</label> 
								<input name="teamId" class="form-control" id="team_id" type="text" placeholder="Organization Name"/>
							</div>
							<div class="form-group">
								<label for="systemType">Team Name:</label> <select
									name="teamName" class="custom-select mb-3" id="teamName">
									<option selected>Team Name</option>
									<option value="Corex">Corex</option>
									<option value="Vision">Vision</option>
									<option value="Credential Management">Credential Management</option>
								</select>
							</div>
							<div class="form-group">
								<label for="teadhead">Team Head:</label>  
								<input name="teamHead" class="form-control" id="team" type="text" placeholder="Team head"/>
							</div>
							<div class="form-group">
								<label for="teamMember">Team Member Count:</label> 
								<input type="text" class="form-control" id="team_member" placeholder="Enter team member count" name="teamMembersCount">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="teamMembers" ng-controller="teamMembersController">

					<table class="table table-hover">
						<thead align="center">
							<td>Team Name</td>
							<td>Team Head</td>
							<td>Team Member Count</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody align="center">
						<tr ng-repeat="team in getteam" align="center">
						<td style="display:none;">{{team.teamId}}</td>
						<td>{{team.teamName}}</td>
						<td>{{team.teamHead}}</td>
						<td>{{team.teamMembersCount}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="teamId" value="{{team.teamId}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form>
						</td>
						</tr>
						</tbody>
						
					</table>
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
		$('.table tbody').on('click', 'tr', function() {
			var currow = $(this).closest('tr');
			var col1 = currow.find('td:eq(0)').text();
			var col2 = currow.find('td:eq(1)').text();
			var col3 = currow.find('td:eq(2)').text();
			var col4 = currow.find('td:eq(3)').text();

			document.getElementById('team_id').value = col1;
			$("#teamName").val(col2).change();
			document.getElementById('team').value = col3;
			document.getElementById('team_member').value = col4;
		})
	</script>
	
	<script>
	var app = angular.module('teamMembers', []);
	app.controller('teamMembersController', function($scope, $http) {
	    $http.post("../../team/getAllTeam")
	        .then(function successCallback(response) {
	            $scope.getteam = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
	});
	</script>
	
	<script type="text/javascript">
	function formSubmit(){
	
	 $.ajax({
	     url:'../../team/saveTeam',
	     method : 'POST',
	     data: $("#Form").serialize(),
	     success: function (data) {
	            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("TeamTbCURD.jsp");
	    }
	 	
	});
	}
		
	var tId = null;

	$('.table tbody').on('click', '.deleteBtn', function() {
		var currow = $(this).closest('tr');
		tId = currow.find('td:eq(0)').text();
		console.log("tId : " + tId);
		
		 $.post("../../team/deleteTeamById", {
			 teamId:tId
			}, function(data) {
				// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("TeamTbCURD.jsp");
			});
		}
	);
		 
		
	</script>

</body>
</html>
