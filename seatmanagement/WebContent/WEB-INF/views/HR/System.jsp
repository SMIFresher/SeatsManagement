
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
<body>
	

<div ng-app="workplaceManagement" ng-controller="workplaceManagementController" id="additinal" ng-init="deviceDetails(); systemDetails(); employeeDetails();">
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="content text-center btn btn-primary	" data-toggle="modal" data-target="#AssignEmployee" style="margin-left: 200px;">Assign Employee</div>
				<div class="row">
					<div class="col-md-12">

						<h2>System</h2>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
							<div class="form-group">
								<label for="pwd">System Name:</label> 
								<input type="hidden" class="form-control" id="systemId"  name="systemId">
								<input type="hidden" class="form-control" id="employeeId"  name="employeeId">
		<input name="systemName" class="form-control" id="systemName" type="text" placeholder="System Name"/>						
							</div>

							<div class="form-group">
								<label for="systemType">System Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-6">
											<input type="radio" class="custom-control-input"
												id="desktop" name="systemType" value="desktop"> 
									<label  class="custom-control-label" for="desktop">Desktop</label>
										</div>
										<div class="col-md-6">
					<input type="radio" class="custom-control-input" id="laptop" name="systemType" value="laptop">
					 <label class="custom-control-label" for="laptop">Laptop</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="networkType">Operating System:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="mac" name="operatingSystem" value="mac"> <label
												class="custom-control-label" for="mac">MAC</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="windows"
												name="operatingSystem" value="windows"> <label
												class="custom-control-label" for="windows">WINDOWS</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="linux"
												name="operatingSystem" value="linux"> <label
												class="custom-control-label" for="linux">LINUX</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="networkType">Network Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="lan" name="networkType" value="lan"> <label
												class="custom-control-label" for="lan">LAN</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="wifi"
												name="networkType" value="wifi"> <label
												class="custom-control-label" for="wifi">WIFI</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div >
								<label for="networkType">Additional Device:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<div ng-repeat="ad in getAddDev">
												<input type="checkbox" name="additionalDeviceList" 
												value="{{ad.additional_device_id}}"> {{ad.device_name}}
								 			</div>
										</div>
									</div>
								</div>
								</div>
							</div>
							
					
							<button type="submit" class="btn btn-primary">Submit</button><br><br>
							
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
							<td>System Name</td>
							<td >System Type</td>
							<td>Operating System</td>
							<td>Network Type</td>
							<td>device</td>
							<td>Status</td>
							<td >Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="sys in getsystem">
								<td style="display: none;">{{sys.systemId}}</td>
								<td>{{sys.systemName}}</td>
								<td>{{sys.systemType}}</td>
								<td>{{sys.operatingSystem}}</td>
								<td>{{sys.networkType}}</td>
								<td>
								<select class="custom-select mb-3" name="additionalDevice" >
										<option ng-repeat="system in sys.additionalDevice">{{system.device_name}}</option>
								</select></td>
								<td>{{sys.allotmentStatus}}</td>
								<td align="center">
									<button class="btn btn-danger deleteBtn">Delete</button></td>
							</tr>
						</tbody>
					</table>
			  	</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="AssignEmployee">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Assign System To Employee</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
			<form id="Form1" method="post" onsubmit="formSubmitEmp();" autocomplete="off">
				<div class="form-group">
                   		<label for="systemId">System Id </label>
							<select class="custom-select mb-3" name="systemId" id="systemName">
								<option ng-repeat="sys in getsystem | filter: {allotmentStatus : '!Active'}" value="{{sys.systemId}}">{{sys.systemName}}</option>
							</select>
				</div>
				<div class="form-group">
					<label for="team">Employee Id</label>
						<select class="custom-select mb-3" name="employeeId" id="employeeId">
							<option ng-repeat="emp in getemp" value="{{emp.employeeId}}">{{emp.employeeRoll}}</option>
						</select>
				</div>
				<div class="modal-footer">
		          <button type="submit"  class="btn btn-success" >Submit</button>
		        </div>
		        </form>
		  </div>
		
           
        </div>
      </div>
    </div>
    <div ng-include="'/seatmanagement/models/search.html'"></div>
  </div>

	

<script type="text/javascript">
		$('.table tbody').on('click', 'tr', function() {
			var currow = $(this).closest('tr');
			var col0 = currow.find('td:eq(0)').text();
			var col1 = currow.find('td:eq(1)').text();
			var col2 = currow.find('td:eq(2)').text();
			var col3 = currow.find('td:eq(3)').text();
			var col4 = currow.find('td:eq(4)').text();
			var col5 = currow.find('td:eq(5)').text();
			
			document.getElementById('systemId').value = col0;
			document.getElementById('systemName').value = col1;
			document.getElementById(col2).checked = true;
			document.getElementById(col3).checked = true;
			document.getElementById(col4).checked = true;
			document.getElementById(col5).checked = true;
		})
	</script>


<script type="text/javascript">
		$('.table tbody').on('click', 'tr', function() {
			var currow = $(this).closest('tr');
			var col1 = currow.find('td:eq(0)').text();

			document.getElementById('systemId').value = col1;
		})
</script>




<script type="text/javascript">
function formSubmit(){
	
	console.log("Serialised Form : " + $("#Form").serialize());

 $.ajax({
     url:'/seatmanagement/systems/saveOrUpdateSystem',
     method : 'POST',
     data: $("#Form").serialize(),
     async:false,
     success: function (data) {
   $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("/seatmanagement/systems/addSystem");
    }
 	
});
}

function formSubmitEmp(){
	
	console.log("Serialised Form : " + $("#Form1").serialize());

 $.ajax({
     url:'/seatmanagement/systems/assignEmployee',
     method : 'POST',
     data: $("#Form1").serialize(),
     async:false,
     success: function (data) {
   $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("/seatmanagement/systems/addSystem");
    }
 	
});
}

                   
$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	 systemId = currow.find('td:eq(0)').text();
	console.log(" systemId : " +  systemId);
	
	 $.get("/seatmanagement/systems/deleteById", {
		 systemId: systemId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("/seatmanagement/systems/addSystem");
		});

	}
);

</script>
<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>
