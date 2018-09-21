<!DOCTYPE html>
<html>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>menus</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Our Custom CSS -->

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

</head>

<body>

	<div class="wrapper">
		<!-- Sidebar Holder -->
		<jsp:include page="nav.jsp"></jsp:include>
		<style type="text/css">
		.border {
			padding: 30px;
			box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.7);
		}
		</style>

		<div class="container">
			<br>
			<br><br><br><br>
	
			<div class="col-md-12 border">
				<h2 class="text-center">Block Form</h2>
				<hr>
				<br>
				<form action="/action_page.php" autocomplete="off">
					<div class="form-group">
						<input type="hidden" class="form-control" id="blockId"
							name="BlocdId">
					</div>
					<div class="form-group">
						<input type="hidden" class="form-control" id="floorId"
							name="FloorId">
					</div>

					<label for="blockType">Block Type</label>
					<div class="custom-control custom-radio mb-3">
						<div class="row">
							<div class="col-md-2">
								<input type="radio" class="custom-control-input"
									id="blockTypeRooms" name="example1" value="customEx"> <label
									class="custom-control-label" for="blockTypeRooms">
									Rooms</label>
							</div>
							<div class="col-md-2">
								<input type="radio" class="custom-control-input"
									id="blockTypeCabins" name="example1" value="customEx">
								<label class="custom-control-label" for="blockTypeCabins">
									Cabins</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="blockCapacity">Block Capacity</label> <input
							type="number" class="form-control" id="blockCapacity"
							name="blockCapacity" required="" placeholder="Block Capacity">
					</div>

					<div class="form-group">
						<label for="comment">Block Description</label>
						<textarea class="form-control" rows="5" id="comment"></textarea>
					</div>

					<div class="form-group">
						<label for="blockMeasurement">Block Measurement</label> <input
							type="number" class="form-control" id="blockMeasurement"
							name="blockMeasurement" required=""
							placeholder="Block Measurement">
					</div>

					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>

		</div>



	</div>

</body>

</html>