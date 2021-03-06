
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
	<div ng-app="Systems" ng-controller="SystemController ng-init="loadInitially()">
	
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
		<input name="systemName" class="form-control" id="systemName" type="text" placeholder="System Name"/>						
							</div>

							<div class="form-group">
								<label for="systemType">System Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-6">
											<input type="radio" class="custom-control-input"
												id="Personal Computer" name="type" value="Personal Computer"> 
									<label  class="custom-control-label" for="Personal Computer">Personal Computer</label>
										</div>
										<div class="col-md-6">
					<input type="radio" class="custom-control-input" id="Laptop" name="type" value="Laptop">
					 <label class="custom-control-label" for="Laptop">Laptop</label>
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
												id="MAC" name="os" value="MAC"> <label
												class="custom-control-label" for="MAC">MAC</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="WINDOWS"
												name="os" value="WINDOWS"> <label
												class="custom-control-label" for="WINDOWS">WINDOWS</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="LINUX"
												name="os" value="LINUX"> <label
												class="custom-control-label" for="LINUX">LINUX</label>
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
												id="Ethernet" name="network" value="Ethernet"> <label
												class="custom-control-label" for="Ethernet">Ethernet</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Wifi"
												name="network" value="wifi"> <label
												class="custom-control-label" for="Wifi">Wifi</label>
										</div>
									</div>
								</div>
							</div>
								<div class="form-group">
								<label for="networkType">additional Device:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<div ng-repeat="ad in getAddDev">
										<input type="checkbox" checklist-value="ad.additional_device_id"> {{ad.device_name}}
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
							<tr ng-repeat="sys in getSystem">
								<td>{{sys.systemId}}</td>
								<td>{{sys.systemName}}</td>
								<td>{{sys.systemType}}</td>
								<td>{{sys.operatingSystem}}</td>
								<td>{{sys.networkType}}</td>
								<td>
								<select class="custom-select mb-3" >
										<option ng-repeat="system in system.additionalDevice">{{system.device_name}}</option>
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
			var col1 = currow.find('td:eq(1)').text();

			document.getElementById('systemId').value = col1;
		})
	</script>


<script>
var apps= angular.module('Systems',[]);
apps.controller('SystemController', function($scope, $http) {
$scope.systemDefault="";
$scope.networkDefault="";
$scope.osDefault="";


$scope.loadInitially = function(){ 
    $http.post("../../Additionaldevice/getAllDevice")
        .then(function successCallback(response) {
            $scope.getAddDev = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
	};


});


var app = angular.module('Systems', []);
app.controller('SystemController', function($scope, $http) {
	 $http.post("../../systems/getAllSystems.do")
     .then(function successCallback(response) {
         $scope.getsystem = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
});
</script>
<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../systems/saveOrUpdateSystem',
     method : 'POST',
     data: $("#Form").serialize(),
     success: function (data) {
   $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("System.jsp");
    }
 	
});
}


                    
$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	 systemId = currow.find('td:eq(0)').text();
	console.log(" systemId : " +  systemId);
	
	 $.post("../../systems/deleteById", {
		 systemId: systemId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("System.jsp");
		});

	}
);

</script>

</body>
</html>
