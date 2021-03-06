
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

<br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Building</h2>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
							<div class="form-group">
								<label for="pwd">Building Id:</label>
								
									 <input type="text" class="form-control" id="bid" placeholder="Enter Building Id" name="buildingId">
									 <input type="hidden" class="form-control" id="oid" placeholder="Enter Building Name" name="organisationId">
							</div>
							<div class="form-group">
								<label for="pwd">Building Name:</label>  <input type="text"
									class="form-control" id="bname" placeholder="Enter Building Name"
									name="buildingName">
							</div>
							<div class="form-group">
								<label for="pwd">Building Address:</label>  <input type="text"
									class="form-control" id="baddress" placeholder="Enter Building Address"
									name="buildingAddress">
							</div>
							<div class="form-group">
								<label for="pwd">Building Location:</label>  <input type="text"
									class="form-control" id="blocation" placeholder="Enter Building Location"
									name="buildingLocation">
							</div>
							<div class="form-group">
								<label for="pwd">Building Location:</label>  <input type="text"
									class="form-control" id="bsqr" placeholder="Sq.Ft"
									name="squareFeet">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id="build" ng-app="Building" ng-controller="BuildingController">

					<table class="table table-hover">
						<thead>
							<td>Building Name</td>
							<td>Building Address</td>
							<td>Building Location</td>
							<td>Squr Feet</td>
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="build in getBuilding">
								<td style="display:none;">{{build.buildingId}}</td>
								<td style="display:none;">{{build.organisation.organisationId}}</td>
								<td>{{build.buildingName}}</td>
								<td>{{build.buildingAddress}}</td>
								<td align="center">{{build.buildingLocation}}</td>
								<td>{{build.squareFeet}}</td>
								<td align="center">
									<button class="btn btn-danger">Delete</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
  $('.table tbody').on('click','tr',function() {
    var currow=$(this).closest('tr');
    var  col1=currow.find('td:eq(0)').text();
    var  col2=currow.find('td:eq(1)').text();
    var  col3=currow.find('td:eq(2)').text();
	var  col4=currow.find('td:eq(3)').text();
	var  col5=currow.find('td:eq(4)').text();
	var  col6=currow.find('td:eq(5)').text();
	
    document.getElementById('bid').value=col1;
    document.getElementById('oid').value=col2;
    document.getElementById('bname').value=col3;
    document.getElementById('baddress').value=col4;
	document.getElementById('blocation').value=col5;
	document.getElementById('bsqr').value=col6;
  })

</script>








<script>
var app = angular.module('Building', []);
app.controller('BuildingController', function($scope, $http) {
	 $http.post("../../building/getAllBuildings")
     .then(function successCallback(response) {
         $scope.getBuilding = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
});
</script>

<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../building/build',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
    	 var status = data.RESPONSE_STATUS;
    	 if(status == "OK"){

         }
         if(status == "ERROR"){
         	 var message = response.data.RESPONSE_MESSAGE;
         	// Business Error scenario
         	// provision to display business error message
         }
    },error: function (response) {
    	var status = response.RESPONSE_STATUS;
    	var message = response.RESPONSE_MESSAGE;
    	var errorCode = response.ERROR_CODE;
    	console.log("Response Status : " + status);
    	console.log("Response Message : " + message);
    	console.log("ErrorCode : " + errorCode);
    }
 	
});
}
	

	
</script>






</body>
</html>
