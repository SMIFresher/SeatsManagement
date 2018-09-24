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

						<h2>Reallocation</h2>
						<form action="#">
							<div class="form-group">
								<label for="emp_id">Employee Id:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="emp_id"
									placeholder="Enter Employee Id" name="emp_id">
							</div>
							<div class="form-group">
								<label for="location">Location </label> <select name="cars"
									class="custom-select mb-3" id="location">
									<option selected>Select Location</option>
									<option value="madurai">Madurai</option>
									<option value="chennai">Chennai</option>
								</select>
							</div>
							<div class="form-group">
								<label for="build">Building </label> <select name="cars"
									class="custom-select mb-3" id="building">
									<option selected>Select Building</option>
									<option value="smi1">SMI 1</option>
									<option value="dh2">DH 2</option>
									<option value="palami">Palami</option>
									<option value="l1">L1</option>
									<option value="techmango">Tech Mango</option>
								</select>
							</div>
							<div class="form-group">
								<label for="floor">Floor </label> <select name="cars"
									class="custom-select mb-3" id="floor">
									<option selected>Select Floor</option>
									<option value="Ground Floor">Ground Floor</option>
									<option value="First Floor">First Floor</option>
									<option value="Second Floor">Second Floor</option>
								</select>
							</div>
							<div class="form-group">
								<label for="bl">Block Id </label> <input type="text"
									class="form-control" id="bid" name="block" required=""
									placeholder="Block Id">
							</div>
							<div class="form-group">
								<label for="sid"> Seat Id</label> <input type="text"
									class="form-control" id="sid" name="seatid" required=""
									placeholder="Seat Id">
							</div>
							<div class="form-group">
								<label for="aby">Alloted By </label> <input type="text"
									class="form-control" id="allotedby" name="allotedby" required=""
									placeholder="Alloted By ">
							</div>
							<div class="form-group">
								<label for="comment">Status</label> <input type="text"
									class="form-control" id="status" name="status" required=""
									placeholder="Status">
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
							<td>EmployeeId</td>
							<td>Location</td>
							<td>Building</td>
							<td>Floor</td>
							<td>BlockId</td>
							<td>SeatId</td>
							<td>AllotedBy</td>
							<td>Status</td>
							<td align="center">Process</td>
						</thead>
						<%
						try{
						connection = DriverManager.getConnection(connectionUrl+database, userid, password);
						statement=connection.createStatement();
						String sql ="select * from realloccurd";
						resultSet = statement.executeQuery(sql);
						while(resultSet.next()){
						%>
						<tbody align="center">
						<tr ng-repeat="x in plan">
						<td><%=resultSet.getString("empid") %></td>
						<td><%=resultSet.getString("location") %></td>
						<td><%=resultSet.getString("building") %></td>
						<td><%=resultSet.getString("floor") %></td>
						<td><%=resultSet.getString("blockid") %></td>
						<td><%=resultSet.getString("seatid") %></td>
						<td><%=resultSet.getString("allotedby") %></td>
						<td><%=resultSet.getString("status") %></td>
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
			var col6 = currow.find('td:eq(5)').text();                 
			var col7 = currow.find('td:eq(6)').text();
			var col8 = currow.find('td:eq(7)').text(); 

			document.getElementById('emp_id').value = col1;
			$("#location").val(col2).change();
			$("#building").val(col3).change();
			$("#floor").val(col4).change();
			document.getElementById('bid').value = col5;
			document.getElementById('sid').value = col6;
			document.getElementById('allotedby').value = col7;
			document.getElementById('status').value = col8;
		})
	</script>
</body>
</html>
