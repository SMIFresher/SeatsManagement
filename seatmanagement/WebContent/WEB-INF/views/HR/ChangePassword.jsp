<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="Form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <link href="http://f6cf37ee.ngrok.io/seatmanagement/css/Style.css" rel="stylesheet"  type="text/css" />
</head>
<body>
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>
	<div class="container" align="center">
			<div class="col-md-4 border rounded login-form-1" style="height: 450px;">
				<div class="row">
					<div class="col-md-12">

						<h2>Change Password</h2>
						<br>
						<form id="Form" method="post" onsubmit="formSubmit();" autocomplete="off">
							<div class="form-group" align="left">
								<label for="oldPassword">Old Password</label> 
								<input name="oldPassword" class="form-control" id="oldPassword" type="text" placeholder="Enter Old Password"/>
							</div>
							<div class="form-group" align="left">
								<label for="newPassword">New Password</label> 
								<input name="newPassword" class="form-control" id="newPassword" type="text" placeholder="Enter Old Password"/>
							</div>
							<div class="form-group" align="left">
								<label for="reEnterNewPassword">Old Password</label> 
								<input name="reEnterNewPassword" class="form-control" id="reEnterNewPassword" type="text" placeholder="Enter Old Password"/>
							</div>
							<div align="left">
								<button type="submit" class="btn btn-primary">change Password</button>
							</div>
						</form>

					</div>

				</div>
				<br>
			</div>
	</div>	

	
	<script type="text/javascript">
	function formSubmit(){
	
	 $.ajax({
	     url:'../../password/"savepassword"',
	     method : 'POST',
	     data: $("#Form").serialize(),
	     success: function (data) {
	            $('#result').html("<br><div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> <strong>Success!</strong> successful Inserted</div>");
	            location.replace("index.jsp");
	    }
	 	
	});
	}
		
	
		
	</script>

</body>
</html>
