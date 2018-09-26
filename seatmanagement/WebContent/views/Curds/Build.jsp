
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

	<div class="jumbotron text-center bg-primary text-white">
		<h2>View All</h2>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">

						<h2>Building</h2>
						<form action="#">
							<div class="form-group">
								<label for="pwd">Building Id:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="bid" placeholder="Enter Building Id"
									name="bID">
							</div>
							<div class="form-group">
								<label for="pwd">Building Name:</label>  <input type="text"
									class="form-control" id="bname" placeholder="Enter Building Name"
									name="buildname">
							</div>
							<div class="form-group">
								<label for="pwd">Building Address:</label>  <input type="text"
									class="form-control" id="baddress" placeholder="Enter Building Address"
									name="buildaddr">
							</div>
							<div class="form-group">
								<label for="pwd">Building Location:</label>  <input type="text"
									class="form-control" id="blocation" placeholder="Enter Building Location"
									name="buildloc">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="myApp" ng-controller="customersCtrl">

					<table class="table table-hover">
						<thead>
							<td>Building Id</td>
							<td>Building Name</td>
							<td>Building Address</td>
							<td>Building Location</td>
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="x in plan">
								<td>1</td>
								<td>Some Name</td>
								<td>Some Name</td>
								<td align="center">Email@email.com</td>
								<td align="center"><button class="btn btn-warning">Update</button>
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
    
    document.getElementById('bid').value=col1;
    document.getElementById('bname').value=col2;
    document.getElementById('baddress').value=col3;
	document.getElementById('blocation').value=col3;
  })

</script>
</body>
</html>
