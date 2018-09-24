
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

						<h2>Add Organization </h2>
						<form id="Form" method="post">
							<div class="form-group">
								<label for="orgId">Organization Name:</label> 
								<input type="hidden" value="" name="organisationName" id="id"> <input type="text"
									class="form-control" id="orgId" placeholder="Organization Name"
									name="team_id">
							</div>
							<button type="submit" class="btn btn-primary">Add</button>
						</form>

					</div>
					<div class="col-md-12">
						<div id="result">
						</div>
					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="organisation" ng-controller="OrganisationController">
					<div class="col-md-12 bg-primary text-white">
					<br>
					<h3>Organization Details</h3>
					<br>
					</div>
					<table class="table table-hover border">
						<thead>
							<th>Organization Id</th>
							<th>Organization Name</th>
							<th align="center">Process</th>
						</thead>
						<tbody align="center">
						<tr ng-repeat="org in getOrg">
						<td>{{org.organisationId}}</td>
						<td>{{org.organisationName}}</td>
						<td align="center">
							<form action="../../organisation/deleteOrganisationById" method="post"><button type="submit" class="btn btn-danger">Delete</button></form>
						</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
		<script>
		var app = angular.module('organisation', []);
		app.controller('OrganisationController', function($scope, $http) {
		    $http.get("../../organisation/getAllOrganisations")
		    .then(function (response) {$scope.getOrg = response.data.records;});
		});
		</script>
		
		
		
<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../organisation/saveOrganisation',
     data: $("#Form").serialize(),
     success: function (data) {
            $('#result').html("Success");

    }
 	
});
}


</script>
		
</body>
</html>
