<!DOCTYPE html>
<html>
<head>
	<title>Seats Managament</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/nav.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script src="http://mystudy.co.nf/ref/multistep.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="http://mystudy.co.nf/ref/multistep.css">
</head>
<body>

<div ng-app="workplaceManagement" ng-controller="workplaceManagementController"  ng-init="buildingDetails(); employeeDetails();">
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>
<br><br><br><br>




<div class="container">
  
<div class="stepwizard col-md-12">
    <div class="stepwizard-row setup-panel">
      <div class="stepwizard-step">
        <a href="#step-1" type="button" class="btn btn-primary btn-circle">1</a>
        <p>Step 1</p>
      </div>
      <div class="stepwizard-step">
        <a href="#step-2" type="button" class="btn  btn-circle" >2</a>
        <p>Step 2</p>
      </div>
    </div>
  </div>
  
  <form id="Form" ng-submit="leadRequests();">
    <div class="setup-content" id="step-1">
      <div class="col-md-12 ">
        <div class="col-md-12">
          <h3> Current Building ID</h3>
          	<label for="location">Building:</label>
          		<div>
					<select ng-model="previousbuilding" class="form-control"
						ng-change="floorDetailsByBuilding(previousbuilding)">
						<option value="">--Select Building--</option>
						<option ng-repeat="building in getBuilding" value="{{building.buildingId}}">{{building.buildingName}}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="location">Floor:</label>
					<select ng-model="previousfloor"
						class="form-control" ng-change="blockDetailsByFloor(previousfloor)">
						<option value="">--Select Floor--</option>
						<option ng-repeat="floor in getFloor" value="{{floor.floorId}}">
							{{floor.floorName}}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="location">Block:</label>
					<select name="previousblockId" ng-model="previousblock"
						class="form-control" >
						<option value="">--Select Block--</option>
						<option ng-repeat="block in getBlock" value="{{block.blockId}}">
							{{block.blockName}}</option>
					</select>
				</div>
				<div class="form-group">
					<label>Employee Roll :</label><br>
					<input class="form-control" list="browsers" name="employeeId">
						<datalist  id="browsers">
							<option ng-repeat="emp in getemp" value="{{emp.employeeId}}">{{emp.firstName}} ({{emp.employeeRoll}})</option>
						</datalist>
				</div>
          <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
        </div>
      </div>
    </div>
    <div class=" setup-content" id="step-2">
      <div class="col-xs-12 col-md-offset-3">
        <div class="col-md-12">
          <h3>Requested Building ID</h3>
          <label for="location">Building:</label>
          		<div>
					<select ng-model="building" class="form-control"
						ng-change="floorDetailsByBuilding(building)">
						<option value="">--Select Building--</option>
						<option ng-repeat="building in getBuilding" value="{{building.buildingId}}">{{building.buildingName}}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="location">Floor:</label>
					<select ng-model="floor"
						class="form-control" ng-change="blockDetailsByFloor(floor)">
						<option value="">--Select Floor--</option>
						<option ng-repeat="floor in getFloor" value="{{floor.floorId}}">
							{{floor.floorName}}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="location">Block:</label>
					<select name="blockId" ng-model="block"
						class="form-control" >
						<option value="">--Select Block--</option>
						<option ng-repeat="block in getBlock" value="{{block.blockId}}">
							{{block.blockName}}</option>
					</select>
				</div>
          <button class="btn btn-primary nextBtn pull-right" type="submit" >Submit</button>
        </div>
      </div>
    </div>
  </form>
  
</div>
</div>
<script src="/seatmanagement/js/AngulerController.js"></script>
</body>
</html>
