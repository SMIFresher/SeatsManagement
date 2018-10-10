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
	 <script src="/seatmanagement/js/ajaxConfig.js"></script>
</head>
<body>

<div id="build" ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="blockDetailsByFloor('${id}')">

<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
			<div class="col-md-12" >
			    	<div class="content text-center" data-toggle="modal" data-target="#myModal" style="height: 550px;">
		              <br><br>
		              <h3>Map View</h3>
		              <p align="justify">
		                If you want To see the Floor Map View You can View By click below the button. All Blocks of the this floor will display like a map and you can see others Empty Place also Try out this... 
		              </p>
					    	<br><br><br><br>
		
						     <label><b>Floor Map View</b></label><br>
		             <a href="/seatmanagement/seating/getSeatingView?floorId=${id}"><button class="btn btn-primary">Floor View</button></a>
			    	</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="row">
			    <div class="col-md-4" ng-repeat="block in getBlock | unique:'blockId'">
			    	<a href="/seatmanagement/systems/EditView?seatingId={{block.seating.seatingId}}">
			    	<div class="content text-center">
			    	<br><br>
			    	  <h3>{{block.blockName}}</h3>
				      <p>Type - {{block.blockType}}</p>
			    	</div>
			    	</a>
			    </div>
			   

			    <div class="col-md-4 ">
			   		<a href="/seatmanagement/block/getBlockView">
				    	<div class="content text-center" >
				    		<br>
				    	   <h3>+</h3>
					      <h3>Add</h3>
				        <p></p>
				    	</div>
			   		</a>
			    </div>
			</div>
		</div>
	</div>
  
</div>



<!-- Models -->

<div class="modal fade" id="getFloor">
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
          	<div class="col-sm-4">
          		<h3>Building Details</h3>
          		<hr>
          		<p>
          			<b>Building Name : </b> {{getFloor[0].building.buildingName}} <br> <br>
          			<b>Building Address</b><br> {{getFloor[0].building.buildingAddress}}  <br>
          			<b>Location :</b> {{getFloor[0].building.buildingLocation}}  <br>
          		</p>
          		
          		
          		
          	</div>
          	<div class="col-sm-8 cc">
          		<div class="row text-center">
          			
	          			<div class="col-sm-12" ng-repeat="flr in getFloor">
	          				
	          				<div class="container flr">
		          				<div class="erase float-sm-right">
		          					<h5><span onclick="location.replace('/seatmanagement/floor/FloorModify')" class="fa fa-cog"></span></h5>
		          				</div>
		          				<a href="/seatmanagement/seating/getSeatingView?floorId={{flr.floorId}}">
	          					<br>
								<h4>{{flr.floorName}}</h4>
								</a>
	          				</div>
	          				
	          			</div>
	
          			<div class="col-sm-12">
          				<a href="/seatmanagement/floor/FloorView">
          				<div class="container flr">
							<h2>+</h2>
							<p>Add</p>
          				</div>
          				</a>
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
      <form id="Form" method="post" autocomplete="off" ng-submit="saveBuildings();">
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
                      name="buildingName" ng-model="building.buildingName" required="required"
                      placeholder="Building Name">
                  </div>
                  <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea class="form-control" name="buildingAddress" rows="3" id="address"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="location">Building Location :</label> <input type="text"
                      class="form-control" id="location" name="buildingLocation" name="location"
                      required="required" placeholder="Location">
                  </div>
                  <div class="form-group">
                    <label for="location">Square Feet :</label> <input type="text"
                      class="form-control" id="SqFt" name="squareFeet"
                      required="required" placeholder="Square Feet">
                  </div>
                   <div class="form-group">
						<label for="location">Organization </label>
							<select class="custom-select mb-3" name="organisationId">
								<option ng-repeat="org in getOrg" value="{{org.organisationId}}">{{org.organisationName}}</option>
							</select>
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
  
	<div ng-include="'/seatmanagement/models/search.html'"></div>
</div>
<script src="/seatmanagement/js/AngulerController.js"></script>

</body>
</html>
