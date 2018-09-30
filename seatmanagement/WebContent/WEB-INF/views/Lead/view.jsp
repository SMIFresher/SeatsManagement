<!DOCTYPE html>
<html>
<head>
	<title>Seats Managament</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="/seatmanagement/css/HR/style.css">
	<link rel="stylesheet" type="text/css" href="/seatmanagement/css/HR/nav.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	
	 <script src="/seatmanagement/js/angular.ng-modules.js"></script>
	 
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br>
<div id="build" ng-app="Building" ng-controller="BuildingController">
<div class="container">
  <div class="row" >
  
    <div class="col-md-4" ng-repeat="Building in getBuilding">
    	<div ng-click="floorDetails(Building.buildingId)" class="content text-center" data-toggle="modal" data-target="#myModal" >
    	<br><br><br>
    	  <h3>{{Building.buildingName}}</h3>
	      <p>{{Building.buildingName}} Building architecture</p>
    	</div>
    </div>   
  </div>
<hr>  
	
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
          			<div class="col-sm-12" ng-repeat="flr in getFloor | orderBy : '-floorName'">
          				<a href="/seatmanagement/seating/getSeatingView?floorId={{flr.floorId}}">
          				<div class="container flr">
							<h4>{{flr.floorName}}</h4>
          				</div></a>
          			</div>
          		</div>
          	</div>
          </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>

</div>


<script>

var app = angular.module('getOrg', ['Building']);
app.controller('getOrganization', function($scope, $http) {
    $http.post("/seatmanagement/organisation/getAllOrganisations")
        .then(function successCallback(response) {
            $scope.getOrg = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
});


var app = angular.module('Building', []);
app.controller('BuildingController', function($scope, $http) {
    $http.post("/seatmanagement/building/getAllBuildings")
        .then(function successCallback(response) {
            $scope.getBuilding = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            alert(response.status);
        });
		$scope.floorDetails=function(buildingId){
			$http.get("/seatmanagement/floor/getFloorByBuildingId?buildingId="+buildingId)
	        .then(function successCallback(response) {
	            $scope.getFloor = response.data;
	            console.log(response.data);
				
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
		};
    	
    
});
angular.element(document).ready(function() {
    angular.bootstrap(document.getElementById("orgFiled"), ['getOrg']);
  });
</script>
</body>
</html>
