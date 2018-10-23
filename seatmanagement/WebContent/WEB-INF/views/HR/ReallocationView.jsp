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
	<div class="row">
	<div class="col-md-4">
	<h2>Reallocation Status</h2>
		<form id="Form" method="put" ng-submit="updateReallocation();">
			<input type="text" class="form-control" id="reallocationId"  name="reallocationId">
			<input type="text" class="form-control" id="requestedDate"  name="reallocationRequestedDate">
			<input type="text" class="form-control" id="employeeId"  name="employee">
			<div class="form-group">
				<label>Employee Roll Number:</label>
				<input type="text" class="form-control" id="employeeRoll" placeholder="Enter Employee Roll Number"> 
			</div>
			<div class="form-group">
				<label>Employee Name:</label>
				<input type="text" class="form-control" id="employeeName" placeholder="Enter Employee Name"> 
			</div>
			<input type="text"  class="form-control" id="previousBlockId"  name="previousBlock">
			<input type="text"  class="form-control" id="blockId"  name="block">
			<div class="form-group">
				<label for="status">Status:</label>
				<select class="custom-select mb-3" id="status" name="reallocationStatus">
					<option value="Pending">Pending</option>
					<option value="Alloted">Alloted</option>
					<option value="Completed">Completed</option>
				</select>
			</div>
			<button type="submit" class="btn btn-success">Submit</button>
		</form>
	</div>
	<div class="col-md-8">
	<div class="container">
	  
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
							</thead>
							<tbody align="center">
							<tr ng-repeat="reall in getAllReallocation | filter:'Pending'">
							<td style="display:none;">{{reall.reallocationId}}</td>
							<td style="display:none;">{{reall.reallocationRequestedDate}}</td>
							<td style="display:none;">{{reall.employee.employeeId}}</td>
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td value="{{reall.previousblock.blockId}}">{{reall.previousblock.blockName}}</td>
							<td value="{{reall.block.blockId}}">{{reall.block.blockName}}</td>
							<td>{{reall.reallocationStatus}}</td>
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
							</thead>
							<tbody align="center">
							<tr ng-repeat="reall in getAllReallocation | filter:'Alloted'">
							<td style="display:none;">{{reall.reallocationId}}</td>
							<td style="display:none;">{{reall.reallocationRequestedDate}}</td>
							<td style="display:none;">{{reall.employee.employeeId}}</td>
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td value="{{reall.previousblock.blockId}}">{{reall.previousblock.blockName}}</td>
							<td value="{{reall.block.blockId}}">{{reall.block.blockName}}</td>
							<td>{{reall.reallocationStatus}}</td>
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
							<td style="display:none;">{{reall.reallocationRequestedDate}}</td>
							<td style="display:none;">{{reall.employee.employeeId}}</td>
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td value="{{reall.previousblock.blockId}}">{{reall.previousblock.blockName}}</td>
							<td value="{{reall.block.blockId}}">{{reall.block.blockName}}</td>
							<td>{{reall.reallocationStatus}}</td>
							</tr>
							</tbody>
						</table>
					</div><br>
				</div>
			</div>  
	    </div>
	  </div>
	</div>
</div>
</div>
</div>


		<div ng-include="'/seatmanagement/models/search.html'"></div>
	</div>
<script type="text/javascript">
	$('.table tbody').on('click', 'tr', function() {
		var currow = $(this).closest('tr');
		
		var col1 = currow.find('td:eq(0)').text();
		var col2 = currow.find('td:eq(1)').text();
		var col3 = currow.find('td:eq(2)').text();
		var col4 = currow.find('td:eq(3)').text();
		var col5 = currow.find('td:eq(4)').text();
		var col6 = currow.find('td:eq(5)').text();
		var col7 = currow.find('td:eq(6)').text();
		var col8 = currow.find('td:eq(7)').text();
	
		document.getElementById('reallocationId').value = col1;
		document.getElementById('requestedDate').value = col2;
		document.getElementById('employeeId').value = col3;
		document.getElementById('employeeRoll').value = col4;
		document.getElementById('employeeName').value = col5;
		document.getElementById('previousBlockId').value = col6;
		document.getElementById('blockId').value = col7;
		$("#status").val(col8).change();
		
	})
</script>

<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>