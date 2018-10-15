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
<div id="floor" ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="buildingDetails(); FloorDetails();">
	
	
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
						<form id="Form" method="post"  enctype="multipart/form-data" action="/seatmanagement/Floors/save">
							<div class="form-group">
								<label for="location">Building Name </label>
									<select class="custom-select mb-3"  id="bid" name="buildingId">
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
							 <div class="custom-file mb-3">
							 		<label for="ftype">Floor Map:</label>
     							 <input type="file" class="custom-file-input" id="customFile" name="file">
    							  <label class="custom-file-label" for="customFile">Choose file</label>
   							 </div>
							<button type="submit" class="btn btn-success">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			
			<div class="col-md-8">
					<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Floor Details</h4>
							<br>
						</div>
					<div class="col-sm-12 table-responsive">
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
								<td><a href="#">
								<button class="btn btn-primary">View</button></a></td>
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
