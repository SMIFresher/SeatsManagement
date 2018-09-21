<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

	<%
	String id = request.getParameter("userid");
	String driver = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/";
	String database = "seatmanagementexample";
	String userid = "root";
	String password = "root";
	try {
	Class.forName(driver);
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	%>

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

						<h2>Blocks</h2>
						<form action="#">
							<div class="form-group">
								<label for="pwd">Floor Id:</label> <input type="hidden" value=""
									name="id" id="id"> <input type="text"
									class="form-control" id="floor_id" placeholder="Enter Floor Id"
									name="floor_id">
							</div>
							<div class="form-group">
								<label for="systemType">Block Type:</label>
								<div class="custom-control custom-radio mb-3">
									<div class="row">
										<div class="col-md-4">
											<input type="radio" class="custom-control-input" id="Rooms"
												name="example1" value="Rooms"> <label
												class="custom-control-label" for="Rooms">Rooms</label>
										</div>
										<div class="col-md-4">
											<input type="radio" class="custom-control-input"
												id="Cabins" name="example1" value="Cabins"> <label
												class="custom-control-label" for="Cabins">Cabins</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="pwd">Block Measurement:</label> <input type="hidden" value=""
									name="id" id="id"> <input type="text"
									class="form-control" id="block_measure" placeholder="Enter Block Measurement"
									name="block_measure">
							</div>
							<div class="form-group">
								<label for="pwd">Block Capacity:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="block_capacity"
									placeholder="Enter Block Capacity" name="block_capacity">
							</div>
							<div class="form-group">
								<label for="pwd">Block Description:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="block_desc"
									placeholder="Enter Block Description" name="block_desc">
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
							<td>Floor Id</td>
							<td>Block Type</td>
							<td>Measurement</td>
							<td>Capacity</td>
							<td>Description</td>
							<td align="center">Process</td>
						</thead>
						<%
						try{
						connection = DriverManager.getConnection(connectionUrl+database, userid, password);
						statement=connection.createStatement();
						String sql ="select * from blocktbcurd";
						resultSet = statement.executeQuery(sql);
						while(resultSet.next()){
						%>
						<tbody align="center">
						<tr ng-repeat="x in plan">
						<td><%=resultSet.getString("floorid") %></td>
						<td><%=resultSet.getString("blocktype") %></td>
						<td><%=resultSet.getString("blockmeasurement") %></td>
						<td><%=resultSet.getString("blockcapacity") %></td>
						<td><%=resultSet.getString("blockdescription") %></td>
						<td align="center"><button class="btn btn-danger">Delete</button></td>
						</tr>
						</tbody>
						<%
						}
						connection.close();
						} catch (Exception e) {
						e.printStackTrace();
						}
						%>
					</table>
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
		$('.table tbody').on('click', 'tr', function() {
			var currow = $(this).closest('tr');
			var col1 = currow.find('td:eq(0)').text();
			var col2 = currow.find('td:eq(1)').text();
			var col3 = currow.find('td:eq(2)').text();
			var col4 = currow.find('td:eq(3)').text();
			var col5 = currow.find('td:eq(4)').text();

			document.getElementById('floor_id').value = col1;
			document.getElementById(col2).checked = true;
			document.getElementById('block_measure').value = col3;
			document.getElementById('block_capacity').value = col4;
			document.getElementById('block_desc').value = col5;
			
		})
	</script>
</body>
</html>
