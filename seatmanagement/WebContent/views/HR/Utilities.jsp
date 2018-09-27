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


<div ng-app="Utilities" ng-controller="UtilitiesController">

	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
    <div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Utilities</h2>
						<div>
							<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
					  		<div class="form-group">
							<label>Utility Name</label>
					  		<input name="utilityName" class="form-control" id="utilityName" type="text" placeholder="Utilities Name "/>
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
				<div ng-app="Utilities" ng-controller="UtilitiesController">

					<table class="table table-hover">
						<thead align="center">
							<td>Utilities Id</td>
							<td>Utilities Name</td>
							<td align="center">Process</td>
						</thead>
						<tbody align="center">
						<tr ng-repeat="utilities in getUtilities">
						<td>{{utilities.utilityId}}</td>
						<td>{{utilities.utilityName}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="utilityId" value="{{utilities.utilityId}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form>
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

			document.getElementById('utilityName').value = col1;
		})
</script>

<script>
var app = angular.module('Utilities', []);
app.controller('UtilitiesController', function($scope, $http) {
    $http.post("../../utilities/getAllUtilities")
        .then(function successCallback(response) {
            $scope.getUtilities = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
});
</script>

<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../utilities/saveUtilities',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Utilities.jsp");
    }
 	
});
}

		
var uId = null;

$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	uId  = currow.find('td:eq(0)').text();
	console.log("uId  : " + uId );
	
	 $.post("../../utilities/deleteUtilityById", {
		 utilityId:uId 
		}, function(data) {
			 $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("Utilities.jsp");
		});

	}
);
</script>
</body>
</html>