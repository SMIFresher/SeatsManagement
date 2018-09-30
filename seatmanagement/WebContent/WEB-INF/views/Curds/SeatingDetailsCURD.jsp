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
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
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

						<h2>Seating Details</h2>
						<form action="#">
							<div class="form-group">
								<label for="pwd">Seating Details Id:</label> <input
									type="hidden" value="" name="id" id="id"> <input
									type="text" class="form-control" id="sdid"
									placeholder="Enter Seating Details Id" name="seatDetId">
							</div>
							<div class="form-group">
								<label for="pwd">Seating Id:</label> <input type="text"
									class="form-control" id="sid" placeholder="Enter Seating Id"
									name="seatId">
							</div>
							<div class="form-group">
								<label for="pwd">System Id:</label> <input type="text"
									class="form-control" id="sysid" placeholder="Enter System Id"
									name="systemId">
							</div>

							<div class="form-group">
								<label for="pwd">Seating Position:</label> <input type="text"
									class="form-control" id="seatPos"
									placeholder="Enter Seating Position" name="seatingPos">
							</div>
							<div class="form-group">
								<label class="control-label" for="date">Date</label> <input
									class="form-control" id="date" name="date"
									placeholder="DD/MM/YYY" type="text" />

								<script>
									$(document)
											.ready(
													function() {
														var date_input = $('input[name="date"]'); //our date input has the name "date"
														var container = $('.bootstrap-iso form').length > 0 ? $(
																'.bootstrap-iso form')
																.parent()
																: "body";
														date_input
																.datepicker({
																	format : 'dd/mm/yyyy',
																	container : container,
																	todayHighlight : true,
																	autoclose : true,
																})
													})
								</script>
							</div>
							<button type="submit" class="btn btn-primary">Update</button>
						</form>

					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div ng-app="myApp" ng-controller="customersCtrl">

					<table class="table table-hover">
						<thead>
							<td>DetailsId</td>
							<td>SeatingId</td>
							<td>SystemId</td>
							<td>SeatingPosition</td>
							<td>Date</td>
							<td align="center">Process</td>
						</thead>
						<tbody>
							<tr ng-repeat="x in plan">
								<td>1</td>
								<td>1</td>
								<td>1</td>
								<td>x</td>
								<td>2018-09-07</td>
								<td align="center"><button class="btn btn-warning">Update</button>
									<button class="btn btn-danger">Delete</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</script>
</body>
</html>
