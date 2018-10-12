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
<div ng-app="workplaceManagement" ng-controller="workplaceManagementController"  ng-init="buildingDetails(); UtilitiesDetails(); blockDetails(); getAllReallocations();">
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br><br>
	
	<div class="container">
		<div class="row">
			
				<div class="col-md-12">
	

					<table class="table table-hover">
						<thead>
							<td>Employee Roll</td>
							<td>Employee Name</td>  
							<td>Previous Block</td>
							<td>Assigned Block</td>
							<td>Approved Date</td>
							<td>Alloted by</td>
							<td>Status</td>
						</thead>
						
						<tbody >
						<tr ng-repeat="reall in getAllReallocation" align="left">
							<td>{{reall.employee.employeeRoll}}</td>
							<td>{{reall.employee.firstName}}</td>
							<td>{{reall.previousBlockId}}</td>
							<td style="display: none;">{{reall.block.blockId}}</td>
							<td>{{reall.block.blockName}}</td>
							<td>{{reall.reallocatedDate}}</td>
							<td>{{reall.allotedBy}}</td>
							<td>{{reall.reallocationStatus}}</td>
						</tr>
						</tbody>
						
					</table>
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
