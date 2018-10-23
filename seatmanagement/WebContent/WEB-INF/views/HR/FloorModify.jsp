
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
	<script src="/seatmanagement/js/ajaxConfig.js"></script>
</head>
<body>
<input type="text" hidden="hidden" value="${buildingId}" id="buildingIdForFloorModify">
<div id="floor" ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="floorDetailsByBuildingFromHidden(); buildingDetails(); ">
	<!-- Nav Bar -->
	<jsp:include page="nav.jsp"></jsp:include>

	<br>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Floor</h2>
						<form id="Form" method="post"  enctype="multipart/form-data" action="/seatmanagement/Floors/save">
							<div class="form-group">
								 <input type="hidden"
									class="form-control" id="fid" placeholder="Enter Floor Id"
									name="floorId"> 
							</div>
							
							<div class="form-group">
								<label for="pwd">Floor Name:</label> <input type="text"
									class="form-control" id="fname" placeholder="Enter Floor Name"
									name="floorName"> 
							</div>
							<div class="form-group">
								<label for="pwd">Floor Type:</label>
								<select class="custom-select mb-3" id="ftype" name="floorType">
										<option value="Commercial">Commercial</option>
										<option value="Home">Home</option>
									</select>
								
							</div>
							<input type="text"
									class="form-control" id="bid"
									name="buildingId" hidden="">
							 <div class="custom-file mb-3">
							 		<label for="ftype">Floor Map:</label>
     							 <input type="file" class="custom-file-input" id="customFile" name="file">
    							  <label class="custom-file-label" for="customFile">Choose file</label>
   							 </div>
							<button type="submit" class="btn btn-success">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
			<div class="col-sm-12 bg-primary text-white">
							<br>
							<h4>Floor Details</h4>
							<br>
						</div>
					<div class="col-sm-12 table-responsive">
				
					<table class="table table-hover">
						<thead>
							
							<td>Floor Name</td>
							<td>Floor Type</td>
							
							
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="floor in getFloor">
								<td style="display:none;">{{floor.floorId}}</td>
								<td>{{floor.floorName}}</td>
								<td>{{floor.floorType}}</td>
								
								<td style="display:none;">{{floor.building.buildingId}}</td>
								
								<td align="center">
									<button class="btn btn-danger" ng-click="deleteFloor(floor.floorId);">Delete</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div ng-include="'/seatmanagement/models/search.html'"></div>
	</div>
	
<script src="/seatmanagement/js/AngulerController.js"></script>
<script type="text/javascript">
	$('.table tbody').on('click', 'tr', function() {
		var currow = $(this).closest('tr');
		
		var col1 = currow.find('td:eq(0)').text();
		var col2 = currow.find('td:eq(1)').text();
		var col3 = currow.find('td:eq(2)').text();
		var col4 = currow.find('td:eq(3)').text();
	
		document.getElementById('fid').value = col1;
		document.getElementById('fname').value = col2;
		document.getElementById('ftype').value = col3;
		document.getElementById('bid').value = col4;
		
		
	})
</script>

<!-- <script type="text/javascript">
		
		function deleteFloor(button){
			var currow = $(button).closest('tr');
			var floorId = currow.find('td:eq(0)').text();
			console.log("floorId : " + floorId);
			
			 $.delete("/seatmanagement/Floors/", {
				 floorId:floorId
				}, function(data) {
					// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
		            //location.replace("/seatmanagement/floor/getFloorView");
				});

			}
	</script> -->

</body>
</html>
