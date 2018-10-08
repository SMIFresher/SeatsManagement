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
	 <script src="/seatmanagement/js/ajaxConfig.js"></script>
</head>
<body>
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br>
	<div class="container" ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="OrganizationDetails(); TeamDetails();">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Team</h2>
						<form id="Form" method="post" ng-submit="saveTeam();" autocomplete="off">
							<div class="form-group">
		                   		<div>
								<label for="organisation">Organisation </label>
									<select class="custom-select mb-3 organisationId" name="organisationId">
										<option ng-repeat="organisation in getOrg" value="{{organisation.organisationId}}">{{organisation.organisationName}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="systemType">Team Name:</label> 
								<input name="teamName" class="form-control" id="teamName" type="text" placeholder="Team Name"/>
							</div>
							<div class="form-group">
								<label for="teamHeadDesignation">TeamHead Designation </label>
								<select class="custom-select mb-3" ng-model="designation" ng-change="onDesignationChange()" name="teamHeadDesignation">
									<option value="PROJECT MANAGER">PROJECT MAMANGER</option>
									<option value="TECH LEADER">TECH LEADER</option>
									<option value="TEAM LEADER">TEAM LEADER</option>
								</select>
							</div>
							<div class="form-group">
		                   		<div>
								<label for="teamHead">TeamHead </label>
									<select class="custom-select mb-3 teamHeadId" name="teamHeadEmployeeId" ng-model="confirmed">
										<option ng-repeat="teamHead in getTeamHeads" value="{{teamHead.employeeId}}">{{teamHead.firstName}} ({{teamHead.employeeRoll}})</option>
									</select>
								</div>
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
	

					<table class="table table-hover">
						<thead align="center">
							<td style="display:none;">Team Id</td>
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
							<button class="btn btn-danger deleteBtn" ng-click="deleteTeam(team.teamId)">Delete</button>
						</td>
						</tr>
						</tbody>
						
					</table>
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
	
	
<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>
