
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
	<script src="/seatmanagement/js/ajaxConfig.js"></script>
</head>
<body >
<div  ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="deviceDetails(); systemDetails();">
	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">
						
							<h2>Modify System</h2>
							<form id="Form" method="post" ng-submit="saveSystems();" autocomplete="off">
								<div class="form-group">
								 <input type="hidden" class="form-control" id="sid"  name="systemId">
								  <input type="hidden" class="form-control" id="eid"  name="employeeId">
								   <input type="hidden" class="form-control" id="status"  name="allotmentStatus">
							</div>
								<div class="form-group">
									<label for="pwd">Employee Number</label> <input type="text"
										class="form-control" id="enum" placeholder="Employee Number">
										
								</div>
								<!-- <div class="form-group">
									<label for="pwd">Team Name</label> <input type="text"
										class="form-control" id="tname" placeholder="Team Name">
										
								</div>  -->
								<div class="form-group">
									<label for="pwd">System Name</label> <input type="text"
										class="form-control" id="sysname"
										placeholder="Enter System Name" name="systemName">
								</div>
								<div class="form-group">
									<label for="pwd">System Type</label> <select name="systemType"  class="custom-select mb-3"
										id="systemType" ng-model="systemDefault">
										<option value="">--Select System Type--</option>
										<option value="desktop">Desktop</option>
										<option value="laptop">Laptop</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Network Type</label> <select name="networkType"  class="custom-select mb-3"
										id="networkType" ng-model="networkDefault">
										<option value="">--Select Network Type--</option>
										<option value="lan">Lan</option>
										<option value="wifi">WiFi</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Operating System Type</label> <select name="operatingSystem"  class="custom-select mb-3"
										id="osType" ng-model="osDefault">
										<option value="">--Select OS Type--</option>
										<option value="windows">Windows</option>
										<option value="mac">Mac</option>
										<option value="linux">Linux</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Additional Devices</label><br>
							        <div ng-repeat="ad in getAddDev" class="col-md-6">
										<input type="checkbox" name="additionalDeviceList" value="{{ad.additional_device_id}}"> {{ad.device_name}}
								 	</div>
								</div>

								<button type="submit" class="btn btn-primary">Update</button>
							</form>
					</div>
				</div>
				<br>
			</div>
			<div class="col-md-8">
					<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>System Details</h4>
							<br>
						</div>
					<div class="col-sm-12 table-responsive">
					<table class="table table-hover">
					
						<thead>
							<td>Employee Number</td>
							<td>Team Name</td>
							<td>System Name</td>
							<td>System Type</td>
							<td>Network Type</td>
							<td>OS Type</td>
							<td>Devices</td>
						<!-- 	<td align="center">Process</td> -->
						</thead>
						<tbody>
							<tr ng-repeat="system in getsystem">
								<td style="display:none;">{{system.systemId}}</td>
								<td>{{system.employee.employeeRoll}}</td>
								<td>{{system.employee.team.teamName}}</td>
								<td>{{system.systemName}}</td>
								<td>{{system.systemType}}</td>
								<td>{{system.networkType}}</td>
								<td>{{system.operatingSystem}}</td>
								<td style="display:none;">{{system.employee.employeeId}}</td>
								<td style="display:none;">{{system.allotmentStatus}}</td>
								<td>
								<select class="custom-select mb-3" name="additionalDevice" >
										<option ng-repeat="system in system.additionalDevice">{{system.device_name}}</option>
									</select>
								</td>
								<!-- <td align="center">
									<button class="btn btn-warning">Update</button></td> -->
							</tr>
						</tbody>
					</table>
				  </div>
				</div>
			</div>
		</div>
		<div ng-include="'/seatmanagement/models/search.html'"></div>
	</div>




<script type="text/javascript">

$('.table tbody').on('click','tr',function() {
    var currow=$(this).closest('tr');
    var  col1=currow.find('td:eq(0)').text();
    var  col2=currow.find('td:eq(1)').text();
    var  col3=currow.find('td:eq(2)').text();
	var  col4=currow.find('td:eq(3)').text();
	var  col5=currow.find('td:eq(4)').text();
	var  col6=currow.find('td:eq(5)').text();
	var  col7=currow.find('td:eq(6)').text();
	var  col8=currow.find('td:eq(7)').text();
	var  col9=currow.find('td:eq(8)').text();
	var  col10=currow.find('td:eq(9)').text();
	
	  document.getElementById('sid').value=col1;
    document.getElementById('enum').value=col2;
  /*   document.getElementById('tname').value=col3; */
    document.getElementById('sysname').value=col4;
    document.getElementById('systemType').value=col5;
	document.getElementById('networkType').value=col6;
	document.getElementById('osType').value=col7;
 	document.getElementById('eid').value=col8; 
 	document.getElementById('status').value=col9; 
 	/* document.getElementById('adDevice').value=col10;  */
  })


</script>

<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>
