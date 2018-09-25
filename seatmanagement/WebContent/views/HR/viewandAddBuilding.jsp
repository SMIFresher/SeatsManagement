<!DOCTYPE html>
<html>
<head>
	<title>Seats Managament</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/nav.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	
	 <script src="../js/angular.ng-modules.js"></script>
</head>
<body>


<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br>
<div ng-app="Building" ng-controller="BuildingController">
<div class="container">
  <div class="row" ng-repeat="Building in getBuildings">
  
    <div class="col-md-4">
    	<div class="content text-center" data-toggle="modal" data-target="#myModal" >
    	<br><br><br>
    	  <h3>{{Building.Name }}</h3>
	      <p>{{ Building.some}} Building architecture</p>
    	</div>
    </div>    
  </div>
<hr>  
	<div class="col-md-4 ">
    	<div class="content text-center" data-toggle="modal" data-target="#AddBuilding">
    		<br><br>
    	   <h3>+</h3>
	      <h3>Add</h3>
        <p></p>
    	</div>
    </div>
</div>
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
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>


  <div class="modal fade" id="AddBuilding">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      <form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Add Buliding</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="row">
            <div class="col-md-5">
              <h4>Please give a building</h4>
              <ul>
                <li>Enter A Proper Name</li>
                <li>Please Provide a Full Address</li>
                <li>Location EX: - "Chennai Or Madurai"</li>
              </ul>
            </div>
            <div class="col-md-7">
                  <div class="form-group">
                    <label for="buildingName">Building Name :</label> <input
                      type="text" class="form-control" id="buildingName"
                      name="buildingName" required="required"
                      placeholder="Building Name">
                  </div>
                  <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea class="form-control" name="buildingAddress" rows="3" id="address"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="location">Building Name :</label> <input type="text"
                      class="form-control" id="location" name="buildingLocation" name="location"
                      required="required" placeholder="Location">
                  </div>
                  <div class="form-group">
                    <label for="location">Square Feet :</label> <input type="text"
                      class="form-control" id="SqFt" name="squareFeet"
                      required="required" placeholder="Square Feet">
                  </div>
                   <div class="form-group">
                   		<div ng-app="getOrg" ng-controller="getOrganization">
						<label for="location">Organization </label>
							<select class="custom-select mb-3" ng-model="selectedName" ng-options="x.organisationName for x in getOrg">
							</select>
						</div>
					</div>
              
            </div>
          </div>
           
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button  class="btn btn-success" >Add Building</button>
        </div>
		</form>
      </div>
    </div>
  </div>
<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../building/build',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");

    }
 	
});
}
</script>

<script>
		var app = angular.module('getOrg', []);
		app.controller('getOrganization', function($scope, $http) {
		    $http.get("../../organisation/getAllOrganisations")
		    .then(function (response) {$scope.getOrg = response.data.records;});
		});

		var app = angular.module('Building', []);
		app.controller('BuildingController', function($scope, $http) {
		    $http.get("../../building/getAllBuildings")
		    .then(function (response) {$scope.getBuildings = response.data.records;});
		});
</script>
</body>
</html>
