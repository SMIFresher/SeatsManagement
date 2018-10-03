
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
<body >
	
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">
						<div id="systemModify" ng-app="SystemModify"
							ng-controller="SystemModifyController" ng-init="loadInitially()">
							<h2>Modify System</h2>
							<form id="Form" method="post" onsubmit="formSubmit()"
								autocomplete="off">
								<div class="form-group">
								 <input type="hidden" class="form-control" id="sid"  name="systemId">
								  <input type="hidden" class="form-control" id="eid"  name="employeeId">
								   <input type="hidden" class="form-control" id="status"  name="allotmentStatus">
							</div>
								<div class="form-group">
									<label for="pwd">Employee Number</label> <input type="text"
										class="form-control" id="enum" placeholder="Employee Number">
										
								</div>
								<!-- <div class="form-group">
									<label for="pwd">Team Name</label> <input type="text"
										class="form-control" id="tname" placeholder="Team Name">
										
								</div>  -->
								<div class="form-group">
									<label for="pwd">System Name</label> <input type="text"
										class="form-control" id="sysname"
										placeholder="Enter System Name" name="systemName">
								</div>
								<div class="form-group">
									<label for="pwd">System Type</label> <select name="systemType"  class="custom-select mb-3"
										id="systemType" ng-model="systemDefault">
										<option value="">--Select System Type--</option>
										<option value="desktop">Desktop</option>
										<option value="laptop">Laptop</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Network Type</label> <select name="networkType"  class="custom-select mb-3"
										id="networkType" ng-model="networkDefault">
										<option value="">--Select Network Type--</option>
										<option value="lan">Lan</option>
										<option value="wifi">WiFi</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Operating System Type</label> <select name="operatingSystem"  class="custom-select mb-3"
										id="osType" ng-model="osDefault">
										<option value="">--Select OS Type--</option>
										<option value="windows">Windows</option>
										<option value="mac">Mac</option>
										<option value="linux">Linux</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Additional Devices</label><br>
							        <div ng-repeat="ad in getAddDev" class="col-md-6">
										<input type="checkbox" name="additionalDeviceList" value="{{ad.additional_device_id}}"> {{ad.device_name}}
								 	</div>
								</div>

								<button type="submit" class="btn btn-primary">Update</button>
							</form>

						</div>
					</div>
				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id="system" ng-app="System" ng-controller="SystemController">

					<table class="table table-hover">
					
						<thead>
							<td>Employee Number</td>
							<td>Team Name</td>
							<td>System Name</td>
							<td>System Type</td>
							<td>Network Type</td>
							<td>OS Type</td>
							<td>Devices</td>
						<!-- 	<td align="center">Process</td> -->
						</thead>
						<tbody>
							<tr ng-repeat="system in getSystem">
								<td style="display:none;">{{system.systemId}}</td>
								<td>{{system.employee.employeeRoll}}</td>
								<td>{{system.employee.team.teamName}}</td>
								<td>{{system.systemName}}</td>
								<td>{{system.systemType}}</td>
								<td>{{system.networkType}}</td>
								<td>{{system.operatingSystem}}</td>
								<td style="display:none;">{{system.employee.employeeId}}</td>
								<td style="display:none;">{{system.allotmentStatus}}</td>
								<td>
								<select class="custom-select mb-3" name="additionalDevice" >
										<option ng-repeat="system in system.additionalDevice">{{system.device_name}}</option>
									</select>
								</td>
								<!-- <td align="center">
									<button class="btn btn-warning">Update</button></td> -->
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
	var  col7=currow.find('td:eq(6)').text();
	var  col8=currow.find('td:eq(7)').text();
	var  col9=currow.find('td:eq(8)').text();
	var  col10=currow.find('td:eq(9)').text();
	
	  document.getElementById('sid').value=col1;
    document.getElementById('enum').value=col2;
  /*   document.getElementById('tname').value=col3; */
    document.getElementById('sysname').value=col4;
    document.getElementById('systemType').value=col5;
	document.getElementById('networkType').value=col6;
	document.getElementById('osType').value=col7;
 	document.getElementById('eid').value=col8; 
 	document.getElementById('status').value=col9; 
 	/* document.getElementById('adDevice').value=col10;  */
  })

  
 var apps= angular.module('SystemModify',[]);
  

//AJAX Request Type Header to prepare error response for AJAX seperately
apps.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
	});
	
apps.controller('SystemModifyController', function($scope, $http) {
$scope.systemDefault="";
$scope.networkDefault="";
$scope.osDefault="";


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
var app = angular.module('System', ['SystemModify']);


//AJAX Request Type Header to prepare error response for AJAX seperately
app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
	});

app.controller('SystemController', function($scope, $http) {
	 $http.post("/seatmanagement/systems/getAllSystems.do")
     .then(function successCallback(response) {
         $scope.getSystem = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
});

angular.element(document).ready(function() {
    angular.bootstrap(document.getElementById("system"), ['System']);
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
            location.replace("/seatmanagement/systems/getModifySystem");
    }
 	
});
}
	
</script>





</body>
</html>
