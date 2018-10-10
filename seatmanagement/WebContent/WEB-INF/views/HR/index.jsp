<!DOCTYPE html>
<html>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Home</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Our Custom CSS -->
<link href="/seatmanagement/css/HR/nav.css" rel="stylesheet"
	type="text/css" />
	<link href="/seatmanagement/css/HR/style.css" rel="stylesheet"
	type="text/css" />
<!-- Font Awesome JS -->


  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
   <script src="/seatmanagement/js/ajaxConfig.js"></script>
</head>

<body>
<div ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="dashboardCount(); companyDetailsCount(); osDetailsCount();">
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<jsp:include page="nav.jsp"></jsp:include>
		

		
		<div class="container-fluid ">
			<br><br><br><br>
			<div class="row">
				<div class="col-sm-6 col-md-3">
					<div class="content text-center" >
						<h1>
							<span class="fa fa-users" airal="true"></span> {{getCountDatas.Employee_Count}}
						</h1>
						<p>Total Employee</p>
					</div>
				</div>

				<div class="col-sm-6 col-md-3">
					<div class="content text-center" >
						<h1>
							<span class="fa fa-desktop" airal="true"></span> {{getCountDatas.System_Count}}
						</h1>
						<p>Total Systems</p>
					</div>
				</div>

				<div class="col-sm-6 col-md-3">
					<div class="content text-center" >
						<h1>
							<span class="fa fa-bar-chart" airal="true"></span> {{getCountDatas.Seating_Details_Count}}
						</h1>
						<p>Total Seats</p>
					</div>
				</div>

				<div class="col-sm-6 col-md-3">
					<div class="content text-center" >
						<h1>
							<span class="fa fa-tasks" airal="true"></span> {{getCountDatas.Block_Count}}
						</h1>
						<p>Total Blocks</p>
					</div>
				</div>
			</div>

			<hr>
			<br>
			<div class="row">
				<div class="col-lg-4">
					<div class="table-responsive">
						<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Device Details</h4>
							<br>
						</div>
						<div class="col-sm-12">
							<table class="table border">
								<br>
								<p>System OS</p>
								<tbody>
									<tr>
										<td>Windows</td>
										<td><span class="badge badge-pill badge-primary">{{getOsDetails[0].Windows_Count}}</span></td>
									</tr>
									<tr>
										<td>Linux</td>
										<td><span class="badge badge-pill badge-primary">{{getOsDetails[0].Linux_Count}}</span></td>
									</tr>
									<tr>
										<td>Mac</td>
										<td><span class="badge badge-pill badge-primary">{{getOsDetails[0].Mac_Count}}</span></td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>
				</div>
				<div class="col-lg-8">
						<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Company Details</h4>
							<br>
						</div>
						<div class="col-sm-12 table-responsive">
						<table class="table border">
							<thead>
								<tr>
									<th>Batch</th>
									<th>Company</th>
									<th>Total Seats</th>
									<th>Total Seats Occupied</th>
									<th>Available Seats</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="compdetails in getCompDetails" ng-init="parentIndex = $index">
									<td><span class="fa fa-bookmark text-primary"></span></td>
									<td>{{compdetails.BuildingName}}</td>
									<td>{{compdetails.Total_Seating_Capacity}}</td>
									<td>{{compdetails.Total_Seating_Occupied}}</td>
									<td>{{compdetails.Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
						</div>
					</div>
				</div>
			</div>
		</div>
<div ng-include="'/seatmanagement/models/search.html'"></div>

<script src="/seatmanagement/js/AngulerController.js"></script>

  
  </div>
</body>

</html>