<!DOCTYPE html>
<html lang="en">
<head>
<title>ADD Block Seating Details</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<!-- <link rel="stylesheet" href="style.css" type="text/css" media="screen" /> -->
<script type="text/javascript" src="../redips-drag-min.js"></script>
<script type="text/javascript" src="script.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


</head>

<style type="text/css">
td {
	width: 100px;
	height: 100px;
}
</style>


<body >

	
<jsp:include page="nav.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="content" ng-app="getRows" ng-controller="getSeatingDetails" >
		<div class="container-fluid" id="redips-drag" >
			<div class="row" >
				<style type="text/css">
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: 2px solid rgba(0, 0, 0, 0);
}
</style>
				<div class="col-md-3">
					<div class="container border">
						<br>
						<h3>
							<span class="fa fa-th" aria-hidden="true"></span> Block Type
						</h3>
						<p>SOme Name</p>
						<hr>
						<div class="row">
							<div class="col-md-12">
								<p>
									<b>Bulding name :</b> SMI I
								</p>
								<hr>
							</div>

							<div class="col-md-6">
								<p>
									<b>Floar :</b> 1
								</p>
							</div>
							<div class="col-md-6">
								<p>
									<b>Block Capacity :</b> 30
								</p>
							</div>
							<div class="col-md-12">
								<p>
									<b>Mesurments :</b> 209 sq ft
								</p>
							</div>
							<div class="col-md-12">
								<p align="justify">
									<b>Description</b><br> <span>Lorem ipsum dolor sit
										amet, consectetur adipisicing elit, sed do eiusmod tempor
										incididunt ut labore et dolore magna aliqua. Ut enim ad minim
										veniam, quis nostrud exercitation ullamco </span>
								</p>
							</div>

						</div>
						<br>

					</div>
				</div>
				<div class="col-md-8 text-center">

					<table id="block" class="table table-borderless"
						style="border: 5px solid; padding: 30px;">
						<tbody>
							<tr ng-repeat="i in [] | range:6">
								<td ng-repeat="j in [] | range:6">

									<div
										ng-repeat="data in mydatas | filter:{seatingAccessories:'desktop'} ">
										<div ng-if="i==data.seatingRow && j==data.seatingColum ">
											<div id="{{data.seatingAccessories}}" class="redips-drag"
												style="width: 100%;" ng-click="getSysDetails(data.seatingSystemNo)" class="content text-center" data-toggle="modal" data-target="#myModal">
												<span class="fa fa-desktop" style="font-size: 60px;"></span><br>{{data.seatingSystemNo}}
											</div>
										</div>
									</div>

									<div
										ng-repeat="data in mydatas | filter:{seatingAccessories:'laptop'} ">
										<div ng-if="i==data.seatingRow && j==data.seatingColum ">
											<div id="{{data.seatingAccessories}}" class="redips-drag"
												style="width: 100%;" ng-click="getSysDetails(data.seatingSystemNo)" class="content text-center" data-toggle="modal" data-target="#myModal">
												<span class="fa fa-laptop" style="font-size: 60px;"></span><br>{{data.seatingSystemNo}}
											</div>
										</div>
									</div>

									<div
										ng-repeat="data in mydatas | filter:{seatingAccessories:'exit'} " >
										<div ng-if="i==data.seatingRow && j==data.seatingColum ">
											<div id="{{data.seatingAccessories}}" class="redips-drag"
												style="width: 100%;" ng-click="getSysDetails(data.seatingSystemNo)" class="content text-center" data-toggle="modal" data-target="#myModal">
												<span class="fa fa-bars" style="font-size: 60px;"></span><br>{{data.seatingSystemNo}}
											</div>
										</div>
									</div>

									<div
										ng-repeat="data in mydatas | filter:{seatingAccessories:'Emptydesk'} ">
										<div ng-if="i==data.seatingRow && j==data.seatingColum ">
											<div id="{{data.seatingAccessories}}" class="redips-drag"
												style="width: 100%;" ng-click="getSysDetails(data.seatingSystemNo)" class="content text-center" data-toggle="modal" data-target="#myModal">
												<span class="fa fa-bars" style="font-size: 60px;"></span><br>{{data.seatingSystemNo}}
											</div>
										</div>
									</div>

								</td>
							</tr>
						</tbody>
					</table>

				</div>




				<div class="modal fade " id="myModal">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Details</h4>
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
										<div class="row text-center"></div>
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
			</div>
		</div>
	</div>



	<script type="text/javascript">

var app = angular.module('getRows', []);

app.filter('range', function() {
  return function(input, total) {
    total = parseInt(total);
    for (var i=0; i<total; i++)
      input.push(i);
    return input;
  };
});

app.controller('getSeatingDetails', function($scope, $http) {
    $http.get("/seatmanagement/seatingdetails/getAllSeatingDetails")
    .then(function(response) {
        $scope.mydatas = response.data;
        console.log(response.data);
    });
    $scope.getSysDetails=function(sysno){
    	console.log(sysno);
    };
});

</script>
</body>
</html>
