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
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<jsp:include page="nav.jsp"></jsp:include>
		
		
		<div class="container-fluid ">
			<br><br><br><br>
			
			
			<hr>
			<br>
			<div class="row">
				
				<div class="col-lg-8">
					<div class="table-responsive" id="compdetails" ng-app="Company" ng-controller="getCompanyController" ng-init="getBuilding(); getFloor(); getBlock();">
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
								<tr ng-repeat="compdetails in getCompDetails" >
									<td><span class="fa fa-bookmark text-primary"></span></td>
									<td>{{compdetails.BuildingName}}</td>
									
									<td>{{compdetails.Total_Seating_Capacity}}</td>
									<td>{{compdetails.Total_Seating_Occupied}}</td>
									<td>{{compdetails.Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
					
			
				
				
				
				
			
				<div class="col-md-6">
						<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Floor Details</h4>
							<br>
						</div>
						<table class="table border">
							<thead>
								<tr>
									<th>Batch</th>
									<th>Company</th>
									<th>Floor</th>
									<th>Total Seats</th>
									<th>Total Seats Occupied</th>
									<th>Available Seats</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="floordetails in getFloorDetails" >
									<td><span class="fa fa-bookmark text-primary"></span></td>
									<td>{{floordetails.BuildingName}}</td>
									<td>{{floordetails.FLoorName}}</td>
									<td>{{floordetails.Total_Seating_Capacity}}</td>
									<td>{{floordetails.Total_Seating_Occupied}}</td>
									<td>{{floordetails.Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
				
				</div>
				
				
			
				<div class="col-md-6">
					
						<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Block Details</h4>
							<br>
						</div>
						<table class="table border">
							<thead>
								<tr>
									<th>Batch</th>
									<th>Floor</th>
									<th>Block</th>
									<th>Total Seats</th>
									<th>Total Seats Occupied</th>
									<th>Available Seats</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="blockdetails in getBlockDetails" >
									<td><span class="fa fa-bookmark text-primary"></span></td>
									
									<td>{{blockdetails.FLoorName}}</td>
									<td>{{blockdetails.BlockName}}</td>
									<td>{{blockdetails.Total_Seating_Capacity}}</td>
									<td>{{blockdetails.Total_Seating_Occupied}}</td>
									<td>{{blockdetails.Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
					
				</div>
				
			</div>
			</div>


		

	
	</div>
	<script>
	var apps= angular.module('Company',[]);
	//AJAX Request Type Header to prepare error response for AJAX seperately
	apps.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
		  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
		});
	
	
	var app = angular.module('Company', []);
	app.controller('getCompanyController', function($scope, $http) {
		
		$scope.getBuilding = function(){ 
	    $http.post("/seatmanagement/dashboard/getAllCompanyDetailsCount")
	        .then(function successCallback(response) {
	            $scope.getCompDetails = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
		}
		
		
		  $scope.getFloor = function(){ 
		    $http.get("/seatmanagement/dashboard/getAllFloorDetailsCount??buildingId="+buildingId)
		        .then(function successCallback(response) {
		            $scope.getFloorDetails = response.data;
		            console.log(response.data);
		        }, function errorCallback(response) {
		            alert(response.status);
		        });
		}
		
		$scope.getBlock=function(buildingId){
			$http.get("/seatmanagement/dashboard/getAllBlockDetailsCount?floorId="+floorId)
	        .then(function successCallback(response) {
	            $scope.getBlockDetails = response.data;
	            console.log(response.data);
				
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
		};
	 
	});
	

</body>

</html>