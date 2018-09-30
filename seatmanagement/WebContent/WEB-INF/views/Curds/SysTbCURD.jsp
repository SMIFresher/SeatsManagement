<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

	


<!DOCTYPE html>
<html lang="en">
<head>
<title>System Management</title>
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

						<h2>System</h2>
						<form action="#">
							<div class="form-group">
								<label for="pwd">Employee Id:</label> <input
									type="hidden"  name="id" id="id"> <input
									type="text" class="form-control" id="empId"
									placeholder="Enter Employee Id" name="employeeId">
							</div>
							<div class="form-group">
								<label for="pwd">System Number:</label> <input type="hidden"
									value="" name="id" id="Systemnumber"> <input type="text"
									class="form-control" id="systemNo"
									placeholder="Enter System Number" name="systemName">
							</div>

							<div class="form-group">
								<label for="systemType">System Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-6">
											<input type="radio" class="custom-control-input"
												id="Personal Computer" name="systemType" value=""> <label
												class="custom-control-label" for="Personal Computer">Personal Computer</label>
										</div>
										<div class="col-md-6">
											<input type="radio" class="custom-control-input"
												id="Laptop" name="systemType" value="Laptop"> <label
												class="custom-control-label" for="Laptop">Laptop</label>
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
												id="Ethernet" name="networkType" value="Ethernet"> <label
												class="custom-control-label" for="Ethernet">Ethernet</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Wifi"
												name="networkType" value="wifi"> <label
												class="custom-control-label" for="Wifi">Wifi</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="allotmentStatus">Allotment Status:</label>
								<div class="custom-control custom-radio mb-3">
								
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="Allocated" name="allotmentStatus" value="Allocated"> <label
												class="custom-control-label" for="Allocated">Allocated</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Deallocated"
												name="allotmentStatus" value="Deallocated"> <label
												class="custom-control-label" for="Deallocated">Deallocated</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="systemType">Additional Device:</label> <select
									name="cars" class="custom-select mb-3" id="Device">
									<option selected>Additional Device</option>
									<option  value="KeyBoard">KeyBoard</option>
									<option value="Mouse">Mouse</option>
									<option value="HeadSet">HeadSet</option>
								</select>
							</div>


							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="myApp" ng-controller="customersCtrl">

					<table class="table table-hover">
						<thead>
							<td>Employee Id</td>
							<td>System No</td>
							<td align="center">System Type</td>
							<td>Network Type</td>
							<td>Status</td>
							<td>Device</td>
							<td align="center">Process</td>
						</thead>
						
					
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
			var col5 = currow.find('td:eq(4)').text();
			var col6 = currow.find('td:eq(5)').text();

			
			
			document.getElementById('empId').value = col1;
			document.getElementById('systemNo').value = col2;
			document.getElementById(col3).checked = true;
			document.getElementById(col4).checked = true;
			document.getElementById(col5).checked = true;
			$("#Device").val(col6).change();
		})
	</script>
</body>
</html>
