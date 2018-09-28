<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="Form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<div ng-app="organisation" ng-controller="OrganisationController">

	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
    <div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Seating Arrangement</h2>
						<div>
							<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
					  		<div class="form-group">
							<label>Organization Name</label>
					  		<input name="organisationName" class="form-control" id="orgName" type="text" placeholder="Organization Name"/>
					  		</div>
					  		<button type="submit" class="btn btn-primary">Submit</button>
					  		
					  		</form>	
					    </div>
					    
					</div>
					<br><br><br><br>
					<div class="col-md-12" id="result">
					    	
					</div>
				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="organisation" ng-controller="OrganisationController">

					<table class="table table-hover">
						<thead align="center">
							<td>Organization Id</td>
							<td>Organization Name</td>
							<td align="center">Process</td>
						</thead>
						<tbody align="center">
						<tr ng-repeat="org in getOrg">
						<td>{{org.organisationId}}</td>
						<td>{{org.organisationName}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="organisationId" value="{{org.organisationId}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form>
						</td>
						</tr>
						</tbody>
					
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
		$('.table tbody').on('click', 'tr', function() {
			var currow = $(this).closest('tr');
			var col1 = currow.find('td:eq(1)').text();

			document.getElementById('orgName').value = col1;
		})
	</script>





<script>
var app = angular.module('organisation', []);
app.controller('OrganisationController', function($scope, $http) {
    $http.post("../../organisation/getAllOrganisations")
        .then(function successCallback(response) {
            $scope.data = response.data;
            var status = response.data.RESPONSE_STATUS;
            if(status == "OK"){
            	console.log("Success scenario");
            	$scope.getOrg = response.data.organisations;
            }
            if(status == "ERROR"){
            	 var message = response.data.RESPONSE_MESSAGE;
            	// Business Error scenario
            	// provision to display business error message
            }
            console.log(response.data);
        }, function errorCallback(response) {
        	var status = response.data.RESPONSE_STATUS;
        	var message = response.data.RESPONSE_MESSAGE;
        	var errorCode = response.data.ERROR_CODE;
        	console.log("Response Status : " + status);
        	console.log("Response Message : " + message);
        	console.log("ErrorCode : " + errorCode);
        });
});
</script>

<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../organisation/saveOrganisation',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
    	 var status = data.RESPONSE_STATUS;
    	 if(status == "OK"){
         	//console.log("Success scenario");
         	$('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Organisation.jsp");
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
	
	
var orgId = null;

$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	orgId = currow.find('td:eq(0)').text();
	console.log("orgId : " + orgId);
	
	/*  $.post("../../organisation/deleteOrganisationById", {
		 organisationId:orgId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Organisation.jsp");
		}); */
	 
	 $.ajax({
	     url:'../../organisation/deleteOrganisationById',
	     method : 'POST',
	     data: {organisationId:orgId},
	     success: function (data) {
	    	 var status = data.RESPONSE_STATUS;
	    	 if(status == "OK"){
	         	//console.log("Success scenario");
	    		 location.replace("Organisation.jsp");
	         }
	         if(status == "ERROR"){
	         	 var message = data.RESPONSE_MESSAGE;
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
);
	 
	
</script>
</body>
</html>