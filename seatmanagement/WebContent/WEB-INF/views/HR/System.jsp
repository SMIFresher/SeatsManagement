
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
	
	
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>System</h2>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
							<div class="form-group">
								<label for="pwd">System Name:</label> 
								<input type="hidden" class="form-control" id="systemId"  name="systemId">
								<input type="hidden" class="form-control" id="employeeId"  name="employeeId">
		<input name="systemName" class="form-control" id="systemName" type="text" placeholder="System Name"/>						
							</div>

							<div class="form-group">
								<label for="systemType">System Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-6">
											<input type="radio" class="custom-control-input"
												id="desktop" name="systemType" value="desktop"> 
									<label  class="custom-control-label" for="desktop">Personal Computer</label>
										</div>
										<div class="col-md-6">
					<input type="radio" class="custom-control-input" id="laptop" name="systemType" value="laptop">
					 <label class="custom-control-label" for="laptop">Laptop</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="networkType">Operating System:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="mac" name="operatingSystem" value="mac"> <label
												class="custom-control-label" for="mac">MAC</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="windows"
												name="operatingSystem" value="windows"> <label
												class="custom-control-label" for="windows">WINDOWS</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="linux"
												name="operatingSystem" value="linux"> <label
												class="custom-control-label" for="linux">LINUX</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="networkType">Network Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="lan" name="networkType" value="lan"> <label
												class="custom-control-label" for="lan">Ethernet</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="wifi"
												name="networkType" value="wifi"> <label
												class="custom-control-label" for="wifi">Wifi</label>
										</div>
									</div>
								</div>
							</div>
								<div class="form-group">
								<div ng-app="AdditinalDevice" ng-controller="AdditinalDeviceController" id="additinal" ng-init="loadInitially()">
								<label for="networkType">additional Device:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<div ng-repeat="ad in getAddDev">
										<input type="checkbox" name="additionalDeviceList" 
										value="{{ad.additional_device_id}}"> {{ad.device_name}}
								 	</div>
										</div>
									</div>
								</div>
								</div>
							</div>
							
					
							<button type="submit" class="btn btn-primary">Submit</button>
					</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id="sys" ng-app="System" ng-controller="SystemController">

					<table class="table table-hover">
						<thead>
					        <td>System id</td>
							<td>System Name</td>
							<td >System Type</td>
							<td>Operating System</td>
							<td>Network Type</td>
							<td>device</td>
							<td>Status</td>
							<td >Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="sys in getsystem">
								<td>{{sys.systemId}}</td>
								<td>{{sys.systemName}}</td>
								<td>{{sys.systemType}}</td>
								<td>{{sys.operatingSystem}}</td>
								<td>{{sys.networkType}}</td>
								<td>
								<select class="custom-select mb-3" name="additionalDevice" >
										<option ng-repeat="system in sys.additionalDevice">{{system.device_name}}</option>
									</select></td>
								<td>{{sys.allotmentStatus}}</td>
								<td align="center">
									<button class="btn btn-danger deleteBtn">Delete</button></td>
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

			document.getElementById('systemId').value = col1;
		})
</script>


<script>
var apps= angular.module('AdditinalDevice',[]);
//AJAX Request Type Header to prepare error response for AJAX seperately
apps.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
	});
apps.controller('AdditinalDeviceController', function($scope, $http) {

$scope.loadInitially = function(){ 
    $http.post("/seatmanagement/Additionaldevice/getAllDevice")
        .then(function successCallback(response) {
            $scope.getAddDev = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
	};
});

var app = angular.module('System', ['AdditinalDevice']);
//AJAX Request Type Header to prepare error response for AJAX seperately
app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
	});
app.controller('SystemController', function($scope, $http) {
	 $http.post("/seatmanagement/systems/getAllSystems.do")
     .then(function successCallback(response) {
         $scope.getsystem = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
});


angular.element(document).ready(function() {
    angular.bootstrap(document.getElementById("sys"), ['System']);
  });

</script>





<script type="text/javascript">
function formSubmit(){
	
	console.log("Serialised Form : " + $("#Form").serialize());

 $.ajax({
     url:'/seatmanagement/systems/saveOrUpdateSystem',
     method : 'POST',
     data: $("#Form").serialize(),
     async:false,
     success: function (data) {
   $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("/seatmanagement/systems/addSystem");
    }
 	
});
}


                    
$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	 systemId = currow.find('td:eq(0)').text();
	console.log(" systemId : " +  systemId);
	
	 $.get("/seatmanagement/systems/deleteById", {
		 systemId: systemId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("/seatmanagement/systems/addSystem");
		});

	}
);

</script>

</body>
</html>
