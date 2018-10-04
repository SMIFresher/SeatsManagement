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
			<button onclick="window.print();" class="btn btn-primary">PRINT</button>
			<hr>
			<br>
			<div class="row" id="compdetails" ng-app="getCompany" ng-controller="getCompanyController" ng-init="getBuilding();">

				<div class="col-md-12">
				<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Building Details Details</h4>
							<br>
				</div>
					<div class="table-responsive">
						
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
								<tr ng-repeat="compdetails in getCompDetails" ng-init="parentIndex = $index1" ng-click="getFloor(compdetails.buildingId)">
									<td><span class="fa fa-bookmark text-primary"></span></td>
									<td>{{compdetails.BuildingName}}</td>
									<td>{{compdetails.Total_Seating_Capacity}}</td>
									<td>{{compdetails.Total_Seating_Occupied}}{{$index}}</td>
									<td>{{compdetails.Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Floor Details {{getFloorDetails[$index1].BuildingName}}</h4>
							<br>
						</div>
					<div class="table-responsive">
						
						<table class="table border">
							<thead>
								<tr>

									<th>Company</th>
									<th>Floor Nmae</th>
									<th>Total Seats</th>
									<th>Total Seats Occupied</th>
									<th>Available Seats</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="compdetails in getFloorDetails" ng-init="parentIndex = $index2" ng-click="getBlock(compdetails.floorId)">
									
									<td>{{compdetails.BuildingName}}</td>
									<td>{{compdetails.FloorName}}</td>
									<td>{{compdetails.Total_Seating_Capacity}}</td>
									<td>{{compdetails.Total_Seating_Occupied}}</td>
									<td>{{compdetails.Total_Seating_Available}}</td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="table-responsive">
						<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Block Details {{getBlockDetails[$index2].FloorName}}</h4>
							<br>
						</div>
						<table class="table border">
							<thead>
								<tr>

									<th>Floor Name</th>
									<th>Block Name</th>
									<th>Total Seats</th>
									<th>Total Seats Occupied</th>
									<th>Available Seats</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="compdetails in getBlockDetails" >
									
									<td>{{compdetails.FloorName}}</td>
									<td>{{compdetails.BlockName}}</td>
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

		

<script type="text/javascript">


	var app = angular.module('getCompany', []);
	//AJAX Request Type Header to prepare error response for AJAX seperately
	app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
		  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
		});
	app.controller('getCompanyController', function($scope, $http) {

		$scope.getBuilding = function(){ 

		    $http.get("../dashboard/getAllCompanyDetailsCount")
	        .then(function successCallback(response) {
	            $scope.getCompDetails = response.data;
	            console.log(response.data);
				
	        }, function errorCallback(response) {
	           
	            doModal('Some Error',response.data.ERROR_MESSAGE);
	        });

		};

		$scope.getFloor = function(buildingId){ 
		    $http.get("/seatmanagement/dashboard/getAllFloorDetailsCount?buildingId="+buildingId)
		        .then(function successCallback(response) {
		            $scope.getFloorDetails = response.data;
		            console.log(response.data);
		        }, function errorCallback(response) {
		            alert(response.status);
		        });
		}
		
		$scope.getBlock=function(floorId){
			$http.get("/seatmanagement/dashboard/getAllBlockDetailsCount?floorId="+floorId)
	        .then(function successCallback(response) {
	            $scope.getBlockDetails = response.data;
	            console.log(response.data);
				
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
		};

	    
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