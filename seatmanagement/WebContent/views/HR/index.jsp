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
<link href="<c:url value="css/nav.css" />" rel="stylesheet"
	type="text/css" />
	<link href="<c:url value="css/style.css" />" rel="stylesheet"
	type="text/css" />
<!-- Font Awesome JS -->


  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
</head>

<body>
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<jsp:include page="nav.jsp"></jsp:include>
		
		
		<div class="container-fluid ">
			<br><br><br><br>
			
			<div id="comp" ng-app="getComp" ng-controller="getCompController"> 
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
										<td><span class="badge badge-pill badge-primary">50</span></td>
									</tr>
									<tr>
										<td>Linux</td>
										<td><span class="badge badge-pill badge-primary">20</span></td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>
				</div>
				<div class="col-lg-8">
					<div class="table-responsive" id="compdetails" ng-app="getCompany" ng-controller="getCompanyController">
						<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Company Details</h4>
							<br>
						</div>
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
									<td>{{getCompDetails[$index].BuildingName}}</td>
									<td>{{getCompDetails[$index].Total_Seating_Capacity}}</td>
									<td>{{getCompDetails[$index].Total_Seating_Occupied}}</td>
									<td>{{getCompDetails[$index].Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		

<script type="text/javascript">
		var app = angular.module('getComp', []);
		app.controller('getCompController', function($scope, $http) {
		    $http.get("../../dashboard/getAllDashboardCount")
		        .then(function successCallback(response) {
		            $scope.getCountDatas = response.data[0];
		            console.log(response.data);
		        }, function errorCallback(response) {
		            alert(response.status);
		        });
		});


	var app = angular.module('getCompany', ['getComp']);
	app.controller('getCompanyController', function($scope, $http) {
	    $http.get("../../dashboard/getAllCompanyDetailsCount")
	        .then(function successCallback(response) {
	            $scope.getCompDetails = response.data;
	            console.log(response.data);
				
	        }, function errorCallback(response) {
	            alert(response.status);
	        });

	    
	});
	angular.element(document).ready(function() {
	    angular.bootstrap(document.getElementById("compdetails"), ['getCompany']);
	});

	
</script>
		
		
		
		
		
		
	</div>
	
	
	<!-- Models -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Modal Heading</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-4 text-center">
							<h3>SMI I</h3>
							<p>SMI I floar architecture.....</p>
						</div>
						<div class="col-sm-8 cc">
							<div class="row text-center">
								<div class="col-sm-12 flr bg-info">
									<h4>Flr 1</h4>
								</div>
								<div class="col-sm-12 flr bg-info">
									<h4>Flr 1</h4>
								</div>
								<div class="col-sm-12 flr">
									<h4>Flr 1</h4>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

</body>

</html>