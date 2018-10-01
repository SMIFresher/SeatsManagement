
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
						<form id="Form" method="post" onsubmit="formSubmit();"
							autocomplete="off">
							<div class="form-group">
								<label for="pwd">Floor Id:</label> <input type="text"
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
										<option value="home">Home</option>
									</select>
								
							</div>
							<input type="text"
									class="form-control" id="bid"
									name="buildingId" hidden="">

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id="floor" ng-app="floor" ng-controller="FloorController">

					<table class="table table-hover">
						<thead>
							<td>Floor Id </td>
							<td>Floor Name</td>
							<td>Floor Type</td>
							
							
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="floor in getFloor">
								<td>{{floor.floorId}}</td>
								<td>{{floor.floorName}}</td>
								<td>{{floor.floorType}}</td>
								
								<td style="display:none;">{{floor.building.buildingId}}</td>
								
								<td align="center">
									<button class="btn btn-danger deletBtn" >Delete</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>




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




	
	<script>
var app = angular.module('floor', []);
//AJAX Request Type Header to prepare error response for AJAX seperately
app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
	});
app.controller('FloorController', function($scope, $http) {
	 $http.get("/seatmanagement/floor/getAllFloor")
     .then(function successCallback(response) {
         $scope.getFloor = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
	 
	 
		/* angular.element(document).ready(function() {
		    angular.bootstrap(document.getElementById("floor"), []);
		  }); */

});
</script>
	
	
	
	
	

	<script type="text/javascript">
		function formSubmit() {

			$.ajax({
				url : '/seatmanagement/floor/floorsave',
				method : 'POST',
				data : $("#Form").serialize(),
				success : function(data) {
					var status = data.RESPONSE_STATUS;
					if (status == "OK") {

					}
					if (status == "ERROR") {
						var message = response.data.RESPONSE_MESSAGE;
						// Business Error scenario
						// provision to display business error message
					}
				},
				error : function(response) {
					var status = response.RESPONSE_STATUS;
					var message = response.RESPONSE_MESSAGE;
					var errorCode = response.ERROR_CODE;
					console.log("Response Status : " + status);
					console.log("Response Message : " + message);
					console.log("ErrorCode : " + errorCode);
				}

			});
		}
	
	
	 /* $('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	var floorId = currow.find('td:eq(0)').text();
	console.log("floorId : " + floorId);
	
	 $.post("../../floor/deleteFloorById", {
		 floorId:floorId  
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Floor.jsp");
		}); */

/* 	}
); 

 */
	</script>

</body>
</html>
