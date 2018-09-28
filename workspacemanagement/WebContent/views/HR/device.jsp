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


<div ng-app="AdditionalDevice" ng-controller="AdditionalDeviceController">

	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
    <div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Additional Device</h2>
						<div>
							<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
					  		<div class="form-group">
							<label>Device Name</label>
					  		<input name="device_name" class="form-control" id="device_name" type="text" placeholder="Device Name"/>
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
				<div id="AdditionalDevice" ng-app="AdditionalDevice" ng-controller="AdditionalDeviceController">

					<table class="table table-hover">
						<thead align="center">
							<td>Device Name</td>
							<td align="center">Process</td>
						</thead>
						<tbody align="center">
						<tr ng-repeat="dev in getdev" align="center">
						<td style="display:none;">{{dev.additional_device_id}}</td>
						<td>{{dev.device_name}}</td>
						<td align="center">
							<form method="post" ><input type="hidden" name="additional_device_id" value="{{dev.additional_device_id}}"><button type="submit" class="btn btn-danger deleteBtn">Delete</button></form>
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

			document.getElementById('device_name').value = col1;
		})
	</script>





<script>
var app = angular.module('AdditionalDevice', []);
app.controller('AdditionalDeviceController', function($scope, $http) {
    $http.post("../../Additionaldevice/getAllDevice")
        .then(function successCallback(response) {
            $scope.getdev = response.data;
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
            location.replace("device.jsp");
    }
 	
});
}
	
	
var devId = null;

$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	devId = currow.find('td:eq(0)').text();
	console.log("devId : " + devId);
	
	 $.post("../../Additionaldevice/deleteByDeviceId", {
		 additional_device_id:devId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("device.jsp");
		});

	}
);
	 
	
</script>
</body>
</html>