<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#multiselect').multiselect({
			buttonWidth : '100%'
		});
	});
</script>
<style type="text/css">
.multiselect-container {
	width: 100% !important;
}
</style>

</head>
<body>

	<br>
	<br>
	<br>

	<div class="container">
		<div class="col-md-12 border">
			<h2 class="text-center">Reallocation</h2>
			<hr>
			<form action="/action_page.php" autocomplete="off">
				<div class="form-group">
					<div>
						<label for="organisation">Team Name </label> <select
							class="custom-select mb-3 teamId" name="teamId">
							<option ng-repeat="organisation1 in getOrganisations"
								value="{{organisation1.organisationId}}">{{organisation1.organisationName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div>
						<label for="organisation">Employee</label>
						<div>
							<select class="custom-select mb-3 empId" name="empId"
								id="multiselect" multiple="multiple">
								<option value="1">Option 1</option>
								<option value="2">Option 2</option>
								<option value="3">Option 3</option>
								<option value="4">Option 4</option>
								<option value="5">Option 5</option>
								<option value="6">Option 6</option>
							</select>
							<div></div>
						</div>
						<div class="form-group">
							<div>
								<label for="organisation">Organisation </label> <select
									class="custom-select mb-3 organisationId" name="organisationId">
									<option ng-repeat="organisation1 in getOrganisations"
										value="{{organisation1.organisationId}}">{{organisation1.organisationName}}</option>
								</select>
							</div>
						</div>
						<div>
							<label for="organisation">Building</label> <select
								class="custom-select mb-3 buildId" name="buildId">
								<option ng-repeat="organisation1 in getOrganisations"
									value="{{organisation1.organisationId}}">{{organisation1.organisationName}}</option>
							</select>
						</div>

						<div>
							<label for="organisation">Floor</label> <select
								class="custom-select mb-3 floorId" name="floorId">
								<option ng-repeat="organisation1 in getOrganisations"
									value="{{organisation1.organisationId}}">{{organisation1.organisationName}}</option>
							</select>
						</div>

						<div>
							<label for="organisation">Block</label> <select
								class="custom-select mb-3 blockId" name="blockId">
								<option ng-repeat="organisation1 in getOrganisations"
									value="{{organisation1.organisationId}}">{{organisation1.organisationName}}</option>
							</select>
						</div>

						<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>

	</div>
</body>
</html>