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
	  <script src="/seatmanagement/js/angular.ng-modules.js"></script>
	  <script src="/seatmanagement/js/ajaxConfig.js"></script>
</head>
<body>

	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
<div id="floor" ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="buildingDetails(); FloorDetails();">
	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Floor</h2>
						<form id="Form" method="post" ng-submit="saveFloors();">
							<div class="form-group">
								<label for="location">Building Name </label>
									<select class="custom-select mb-3" name="buildingId">
										<option ng-repeat="build in getBuilding" value="{{build.buildingId}}">{{build.buildingName}}</option>
									</select>

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
							<button type="submit" class="btn btn-success">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
					<table class="table table-hover">
						<thead align="center">
							<td>Floor Name</td>
							<td>Floor Type</td>
							<td>Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="flr in getFloor" align="center">
								<td>{{flr.floorName}}</td>
								<td>{{flr.floorType}}</td>
								<td style="display:none;">{{flr.floorId}}</td>
								<td><a href="/seatmanagement/seating/getSeatingView?floorId={{flr.floorId}}"><button class="btn btn-primary deleteBtn">View</button></a></td>
							</tr>
						</tbody>
					</table>
				
			</div>
		</div>
	</div>
</div>
<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>
