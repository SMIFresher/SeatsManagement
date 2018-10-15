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



<div ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="OrganisationDetails();">
	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
    <div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Organisation</h2>
						<div>
							<form id="Form" method="post" ng-submit="saveOrganisations();" autocomplete="off">
					  		<div class="form-group">
							<label>Organization Name</label>
					  		<input name="organisationName" class="form-control" id="orgName" type="text" placeholder="Organization Name"/>
					  		</div>
					  		<button type="submit" class="btn btn-primary">Submit</button>
					  		
					  		</form>	
					    </div>
					    
					</div>
					<br><br><br><br>
					<div class="col-md-12" id="result">
					    	
					</div>
				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Organization Details</h4>
							<br>
						</div>
					<div class="col-sm-12 table-responsive">
					<table class="table table-hover">
						<thead align="center">
						
							<td>Organization Name</td>
							<td align="center">Process</td>
						</thead>
						<tbody align="center">
						<tr ng-repeat="org in getOrg">
						<td style="display:none;">{{org.organisationId}}</td>
						<td>{{org.organisationName}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="organisationId" value="{{org.organisationId}}">
							<button  class="btn btn-danger" ng-click="deleteOrganisation(org.organisationId);">Delete</button></form>
						</td>
						</tr>
						</tbody>
					</table>
				</div>
				</div>
			</div>
		</div>
		<div ng-include="'/seatmanagement/models/search.html'"></div>
	</div>


<script src="/seatmanagement/js/AngulerController.js"></script>





</body>
</html>