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

						<h2>Team</h2>
						<form action="#">
							<div class="form-group">
								<label for="teadId">Team Id:</label> <input type="hidden" value=""
									name="id" id="id"> <input type="text"
									class="form-control" id="team_id" placeholder="Enter Team Id"
									name="team_id">
							</div>
							<div class="form-group">
								<label for="systemType">Team Name:</label> <select
									name="cars" class="custom-select mb-3" id="teamName">
									<option selected>Team Name</option>
									<option value="Corex">Corex</option>
									<option value="Vision">Vision</option>
									<option value="Credential Management">Credential Management</option>
								</select>
							</div>
							<div class="form-group">
								<label for="teadhead">Team Head:</label>  <input type="text"
									class="form-control" id="team" placeholder="Enter Team Head"
									name="team_head">
							</div>
							<div class="form-group">
								<label for="teamMember">Team Member Count:</label> <input type="text"
									class="form-control" id="team_member" placeholder="Enter team member count"
									name="team_member">
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
							<td>Team Id</td>
							<td>Team Name</td>
							<td>Team Head</td>
							<td>Team Member Count</td>
							<td align="center">Process</td>
						</thead>
						<%
						try{
						connection = DriverManager.getConnection(connectionUrl+database, userid, password);
						statement=connection.createStatement();
						String sql ="select * from teamtbcurd";
						resultSet = statement.executeQuery(sql);
						while(resultSet.next()){
						%>
						<tbody align="center">
						<tr ng-repeat="x in plan">
						<td><%=resultSet.getString("teamid") %></td>
						<td><%=resultSet.getString("teamname") %></td>
						<td><%=resultSet.getString("teamhead") %></td>
						<td><%=resultSet.getString("Teammembercount") %></td>
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

			document.getElementById('team_id').value = col1;
			$("#teamName").val(col2).change();
			document.getElementById('team').value = col3;
			document.getElementById('team_member').value = col4;
		})
	</script>
</body>
</html>
