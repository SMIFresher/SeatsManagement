<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<br><br><br><br>
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<div ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="getAllReallocations();">
	<div class="container">
	  <h2>Reallocation Status</h2>
	  <br>
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
	    <li class="nav-item">
	      <a class="nav-link active" data-toggle="tab" href="#pending">Pending</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#alloted">Alloted</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#completed">Completed</a>
	    </li>
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div id="pending" class="container tab-pane active"><br>
	      <br><br>
	    	<div class="container">
			<div class="row">
				<div class="col-md-12">
					
						<table class="table table-hover">
							<thead align="center">
								<td>Employee Id</td>
								<td>Employee Name</td>
								<td>Current Block</td>
								<td>Requested Block</td>
								<td>Status</td>
								<td align="center">Process</td>
							</thead>
							<tbody align="center">
							<tr ng-repeat="reall in getAllReallocation | filter:'Pending'">
							<td style="display:none;">{{reall.reallocationId}}</td>
							<td style="display:none;">{{reall.employee.employeeId}}</td>
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td>{{reall.previousblock.blockName}}</td>
							<td value="{{reall.block.blockId}}">{{reall.block.blockName}}</td>
							<td align="center"> 
								<select>
									<option value="">{{reall.reallocationStatus}}</option>
									<option>Pending</option>
									<option>Alloted</option>
									<option>Completed</option>
								</select>
								<!-- <form method="post" ><input type="hidden" name="reallocation_id" value="{{reall.reallocation_id}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form> -->
							</td>
							<td><div class="col-md-12"><button type="submit" class="btn btn-primary ">Submit</button></div></td>
							</tr>
							</tbody>
						</table>
					</div><br>
				</div>
			</div>
	    </div>
	    <div id="alloted" class="container tab-pane fade"><br>
	      <br><br>
	    	<div class="container">
			<div class="row">
				<div class="col-md-12">
					
						<table class="table table-hover">
							<thead align="center">
								<td>Employee Id</td>
								<td>Employee Name</td>
								<td>Current Block</td>
								<td>Requested Block</td>
								<td>Status</td>
								<td align="center">Process</td>
							</thead>
							<tbody align="center">
							<tr ng-repeat="reall in getAllReallocation | filter:'Alloted'">
							<td style="display:none;">{{reall.reallocationId}}</td>
							<td style="display:none;">{{reall.employee.employeeId}}</td>
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td>{{reall.previousblock.blockName}}</td>
							<td value="{{reall.block.blockId}}">{{reall.block.blockName}}</td>
							<td align="center"> 
								<select>
									<option value="">{{reall.reallocationStatus}}</option>
									<option>Pending</option>
									<option>Alloted</option>
									<option>Completed</option>
								</select>
								<!-- <form method="post" ><input type="hidden" name="reallocation_id" value="{{reall.reallocation_id}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form> -->
							</td>
							<td><div class="col-md-12"><button type="submit" class="btn btn-primary ">Submit</button></div></td>
							</tr>
							</tbody>
						</table>
					</div><br>
				</div>
			</div>
	    </div>
	    <div id="completed" class="container tab-pane fade"><br>
	      <br><br>
	    	<div class="container">
			<div class="row">
				<div class="col-md-12">
					
						<table class="table table-hover">
							<thead align="center">
								<td>Employee Id</td>
								<td>Employee Name</td>
								<td>Previous Block</td>
								<td>Requested Block</td>
								<td>Status</td>
							</thead>
							<tbody align="center">
							<tr ng-repeat="reall in getAllReallocation | filter:'Completed'">
							<td style="display:none;">{{reall.reallocationId}}</td>
							<td style="display:none;">{{reall.employee.employeeId}}</td>
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td>{{reall.previousblock.blockName}}</td>
							<td value="{{reall.block.blockId}}">{{reall.block.blockName}}</td>
							<td >{{reall.reallocationStatus}}</td>
							</tr>
							</tbody>
						</table>
					</div><br>
				</div>
			</div>  
	    </div>
	  </div>
	</div>





		<div ng-include="'/seatmanagement/models/search.html'"></div>
	</div>

<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>