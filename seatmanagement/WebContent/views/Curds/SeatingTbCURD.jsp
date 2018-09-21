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

						<h2>Seating Arrangement</h2>
						<form action="#">
							<div class="form-group">
								<label for="seatId">Seat Id:</label> <input type="hidden"
									value="" name="id" id="id"> <input type="text"
									class="form-control" id="seat_id" placeholder="Enter seat id"
									name="seat_id">
							</div>
							<div class="form-group">
								<label for="seatId">Seat Occupied:</label> <input type="text"
									class="form-control" id="seat_occupied" placeholder="Enter seat occupied"
									name="seat_occupied">
							</div>
							<div class="form-group">
								<label for="blockId">Block Id:</label> <input type="text"
									class="form-control" id="block_id" placeholder="Enter block id"
									name="block_id">
							</div>
							<div class="form-group">
								<label for="floorId">Floor Id:</label> <input type="text"
									class="form-control" id="floor_id" placeholder="Enter floor id"
									name="floor_id">
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
						<thead align="center">
							<td>Seat Id</td>
							<td>Seat Occupied</td>
							<td>Block Id</td>
							<td>Floor Id</td>
							<td align="center">Process</td>
						</thead>

						<%
						try{
						connection = DriverManager.getConnection(connectionUrl+database, userid, password);
						statement=connection.createStatement();
						String sql ="select * from seatingdetails";
						resultSet = statement.executeQuery(sql);
						while(resultSet.next()){
						%>
						<tbody align="center">
						<tr ng-repeat="x in plan">
						<td><%=resultSet.getString("seatid") %></td>
						<td><%=resultSet.getString("seatoccupied") %></td>
						<td><%=resultSet.getString("blockid") %></td>
						<td><%=resultSet.getString("floorid") %></td>
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
  $('.table tbody').on('click','tr',function() {
    var currow=$(this).closest('tr');
    var  col1=currow.find('td:eq(0)').text();
    var  col2=currow.find('td:eq(1)').text();
    var  col3=currow.find('td:eq(2)').text();
	var  col4=currow.find('td:eq(3)').text();
    
    document.getElementById('seat_id').value=col1;
    document.getElementById('seat_occupied').value=col2;
    document.getElementById('block_id').value=col3;
	document.getElementById('floor_id').value=col4;
  })

</script>
</body>
</html>
