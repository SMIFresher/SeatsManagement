
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
								<label for="pwd">Floor Name:</label> <input type="text"
									class="form-control" id="fname" placeholder="Enter Floor Name"
									name="floorName"> 
							</div>
							<div class="form-group">
								<label for="pwd">Floor Type:</label>
								<select class="custom-select mb-3" name="floorId">
										<option value="Commercial">Commercial</option>
										<option value="home">Home</option>
									</select>
								
							</div>

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
							<td>Floor Name</td>
							<td>Building Name</td>
							<td>Floor Type</td>
							
							
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="floor in getFloor">
								<td>{{floor.floorName}}</td>
								<td>{{floor.building.buildingName}}</td>
								<td>{{floor.floorType}}</td>
								<td style="display:none;">{{floor.floorId}}</td> 
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
			
		
			document.getElementById('fname').value = col1;
			
			
		})
	</script>




	
	<script>
var app = angular.module('floor', []);
app.controller('FloorController', function($scope, $http) {
	 $http.get("../../floor/getAllFloor")
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
				url : '../../floor/floorsave',
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
	
	
	 $('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	var floorId = currow.find('td:eq(3)').text();
	console.log("floorId : " + floorId);
	
	 $.post("../../floor/deleteFloorById", {
		 floorId:floorId  
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Floor.jsp");
		});

	}
); 
	</script>

</body>
</html>
