
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

						<h2>Block</h2>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
						
							<div class="form-group">
								<label for="pwd">Block Id:</label> <input type="text"
									class="form-control" id="bid" placeholder="Enter Block Id"
									name="blockId">									
							</div>
							<div class="form-group">
								<label for="pwd">Block Name:</label>  <input type="text"
									class="form-control" id="bname" placeholder="Enter Block Name"
									name="blockName">
							</div>
							<div class="form-group">
								<label for="pwd">Block Capacity:</label>  <input type="text"
									class="form-control" id="bcap" placeholder="Enter Block Address"
									name="blockCapacity">
							</div>
							<div class="form-group">
								<label for="pwd">Block Description:</label>  <input type="text"
									class="form-control" id="bdes" placeholder="Enter Block Location"
									name="blockDescription">
							</div>
							<div class="form-group">
								<label for="pwd">Block Measurement:</label>  <input type="text"
									class="form-control" id="bmes" placeholder="Enter Block Location""
									name="blockMeasurement">
							</div>
							<!-- <div class="form-group">
								<label for="pwd">Square Feet:</label>  <input type="text"
									class="form-control" id="sqft" placeholder="Enter Square Feet"
									name="squareFeet">
							</div> -->
							<input type="text"
									class="form-control" id="flrId"
									name="floorId" hidden="">
							<input type="text"
									class="form-control" id="blkType"
									name="blockType" hidden="">
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div id=block ng-app="Block" ng-controller="BlockController">

					<table class="table table-hover">
						<thead>
						<td>Block Id</td>
							<td>Block Name</td>
							<td>Block Capacity</td>
							<td>Block Description</td>
							<td>Block Measurement</td>
							<!-- <td>Square Feet</td> -->
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="block in getBlock">
								<td>{{block.blockId}}</td>
								<td>{{block.blockName}}</td>
								<td>{{block.blockCapacity}}</td>
								<td>{{block.blockDescription}}</td>
								<td>{{block.blockMeasurement}}</td>
								<!-- <td>{{block.squareFeet}}</td> -->
								<td style="display:none;">{{block.blockId}}</td>
								<td style="display:none;">{{block.floor.floorId}}</td>
								<td style="display:none;">{{block.blockType}}</td>
								
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
  $('.table tbody').on('click','tr',function() {
    var currow=$(this).closest('tr');
    var  col1=currow.find('td:eq(0)').text();
    var  col2=currow.find('td:eq(1)').text();
    var  col3=currow.find('td:eq(2)').text();
	var  col4=currow.find('td:eq(3)').text();
	var  col5=currow.find('td:eq(4)').text();
	var  col6=currow.find('td:eq(6)').text();
	var  col7=currow.find('td:eq(7)').text();
	
	 document.getElementById('bid').value=col1;
    document.getElementById('bname').value=col2;
    document.getElementById('bcap').value=col3;
    document.getElementById('bdes').value=col4;
    document.getElementById('bmes').value=col5;
	document.getElementById('flrId').value=col6;
	document.getElementById('blkType').value=col7;
	
  })

</script>








<script>
var app = angular.module('Block', []);
app.controller('BlockController', function($scope, $http) {
	 $http.get("../../block/getAllBlocks")
     .then(function successCallback(response) {
         $scope.getBlock= response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
});
</script>

<script type="text/javascript">
function formSubmit(){

 $.ajax({
     url:'../../block/saveblock',
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


 var blockId = null;

$('.table tbody').on('click', '.deleteBtn', function() {
	var currow = $(this).closest('tr');
	blockId = currow.find('td:eq(0)').text();
	console.log("blockId : " + blockId);
	
	 $.post("../../block/deleteBlockById", {
		 blockId:blockId
		}, function(data) {
			// $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
            location.replace("block.jsp");
		});
 
	}
);

	

	
</script>






</body>
</html>
