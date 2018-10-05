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

	<div class="jumbotron text-center bg-primary text-white">
		<h2>View All</h2>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Details</h2>
						<form action="#">
							<div class="form-group">
								<label for="pwd">Device Id:</label> <input type="hidden"
									value="" name="id" id="id"> 
									<input type="text"
									class="form-control" id="system_id"
									placeholder="Enter Device Id" name="additional_device_id">
							</div>
							<div class="form-group">
								<label for="device">Additional Device:</label> <input type="hidden"
									value="" name="id_device" id="id_device"> 
									<input type="text"
									class="form-control" id="Device_id"
									placeholder="Enter Device Name" name="device_name">
							</div>
							<button type="submit" class="btn btn-primary">Insert</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="additionalDevice" ng-controller="additionalDeviceController">

					<table class="table table-hover">
						<thead align="center">
							<td>Device Id</td>
							<td>Additional Device</td>
							<td align="center">Process</td>
						</thead>
						
						<tbody align="center">
						<tr ng-repeat="device in getDevice">
						<td style="display:none;">{{device.additional_device_id}}</td>
						<td>{{device.device_name}}</td>
						<td align="center"><button class="btn btn-danger">Delete</button></td>
						</tr>
						</tbody>
						
					</table>
				</div>
			</div>
		</div>
	</div>

<script>
var app = angular.module('additionalDevice', []);
app.controller('additionalDeviceController', function($scope, $http) {
    $http.post("../../Additionaldevice/getAllDevice")
        .then(function successCallback(response) {
            $scope.getOrg = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
});
</script>

<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../Additionaldevice/savedevice',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("additional_deviceCURD.jsp");
    }
 	
});
}
	
	
var orgId = null;

$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	orgId = currow.find('td:eq(0)').text();
	console.log("orgId : " + orgId);
	
	 $.post("../../Additionaldevice/deleteByDeviceId", {
		 organisationId:orgId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("additional_deviceCURD.jsp");
		});

	}
);
	 
	
</script>


	
</body>
</html>
