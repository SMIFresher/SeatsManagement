

<!DOCTYPE html>
<html lang="en">
<head>
<title>Curd Application</title>
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
<div ng-app="workplaceManagement" ng-controller="workplaceManagementController"  ng-init="buildingDetails(); UtilitiesDetails(); blockDetails();">
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br><br>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">
						<h2>Blocks</h2>
						<form id="Form" method="post" ng-submit="saveBlocks();">
														
								<div class="form-group">
								<label for="location">Building:</label>
								<select name="building" ng-model="building" class="form-control"
									ng-change="floorDetailsByBuilding(building)">
									<option value="">--Select Building--</option>
									<option ng-repeat="building in getBuilding" value="{{building.buildingId}}">{{building.buildingName}}</option>
								</select>
								</div>
								
								<div class="form-group">
									<label for="location">Floor:</label>
								<select name="floorId" ng-model="floor"
									class="form-control" ng-change="blockDetails()">
									<option value="">--Select Floor--</option>
									<option ng-repeat="floor in getFloor" value="{{floor.floorId}}">
										{{floor.floorName}}</option>
								</select>
								</div>
								
							
					
							
							<div class="form-group">
								<label for="blockName">Block Name:</label> 
									<input type="text"
									class="form-control" id="blockName" placeholder="Enter Block Name"
									name="blockName"> 
							</div>
							<div class="form-group">
								<label for="blockType">Block Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Rooms" name="blockType" value="Rooms"> 
												<label class="custom-control-label" for="Rooms">Rooms</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Cabins" name="blockType" value="Cabins">
												 <label class="custom-control-label" for="Cabins">Cabins</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="blockMeasurement">Block Measurement:</label> 
									 <input type="text"
									class="form-control" id="blockMeasurementString" placeholder="Enter Block Measurement"
									name="blockMeasurement">
							</div>
							<div class="form-group">
								<label for="block_capacity">Block Capacity:</label>
								<input type="text"
									class="form-control" id="block_capacity"
									placeholder="Enter Block Capacity" name="blockCapacity">
							</div>
							<div class="form-group">
								<label for="block_desc">Block Description:</label> 
								 <input type="text"
									class="form-control" id="block_desc"
									placeholder="Enter Block Description" name="blockDescription">
							</div>
							<div >
								<label>Add Utility :</label>
								<div ng-repeat="utilities in getUtilities">
										<input type="checkbox" name="utilityList" 
										value="{{utilities.utilityId}}"> {{utilities.utilityName}}
								 	</div>
							</div>
							<button type="submit" class="btn btn-primary" ng-click="showCheckedOnes()">Submit</button>
						</form>
</div>
					</div>

				</div>
				<br>
			
			<div class="col-md-8">
	
					<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>BLock Details</h4>
							<br>
						</div>
					<div class="col-sm-12 table-responsive">
					<table class="table table-hover">
						<thead>
							<td>Building Name</td>
							<td>Floor Name</td>  
							<td>Block Name</td>
							<td>Block Measurement</td>
							<td>Block Type</td>
							<td>Block Capacity</td>
							<td>Utilities</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody >
						<tr ng-repeat="blk in getBlock" align="center">
						<td>{{blk.floor.building.buildingName}}</td>
						<td>{{blk.floor.floorName}}</td>
						<td style="display: none;">{{blk.blockId}}</td>
						<td>{{blk.blockName}}</td>
						<td>{{blk.blockMeasurement}}</td>
						<td>{{blk.blockType}}</td>
						<td>{{blk.blockCapacity}}</td>
						<td>
							<select class="custom-select mb-3" >
								<option ng-repeat="block in blk.utilities">{{block.utilityName}}</option>
							</select>
						</td>
						<td align="center"><button class="btn btn-danger deleteBtn" value="{{blk.blockId}}">Delete</button></td>
						
						</tr>
						</tbody>
						
					</table>
					</div>
				</div>
			</div>
		</div>
		<div ng-include="'/seatmanagement/models/search.html'"></div>
</div>
	
<script type="text/javascript">
		
	var blockId = null;
	
	$('.table tbody').on('click', '.deleteBtn', function() {
		var currow = $(this).closest('tr');
		blockId = currow.find('td:eq(2)').text();
		console.log("blockId : " + blockId);
		
		 $.post("/seatmanagement/block/deleteBlockById", {
			 blockId:blockId
			}, function(data) {
				// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("/seatmanagement/block/getBlockView");
			});
	
		}
	);
		 
		
</script>
<script src="/seatmanagement/js/AngulerController.js"></script>
	
</body>
</html>
