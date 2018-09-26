<%
	
	if(request.getParameter("password")!=null){
		String usr=request.getParameter("username");
		if(usr.equals("HR")){
			%>
				<script>
						location.replace("views/HR");
				</script>
			<%
		}else if(usr.equals("Lead")){
			%>
					<script>
						location.replace("views/Lead");
					</script>
			<%
		}
	}

%>

<!DOCTYPE html>
<html>
 <%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <link href="<c:url value="/css/Style.css" />" rel="stylesheet"  type="text/css" />
</head>
<body>

<!------ Include the above in your HEAD tag ---------->

<div class="container login-container">
            <div class="row">

                 <div class="col-md-6 login-form-2 rounded container " >
                    <h3 style="text-align: left;">SMI Seats Management</h3>
                    <br><br>
                    <dl>
                      <dt class="fa fa-align-justify"> Seats</dt>
                      <dd>SMI seat Availability</dd>
                      <dt class="fa fa-desktop"> Systems</dt>
                      <dd>SMI Systems Availability</dd>
                      <dt class="fa fa-male"> Employees</dt>
                      <dd>SMI Employees Availability</dd>
                    </dl>     
                </div>

                <div class="col-md-6 login-form-1 rounded border">
                    <h3>Login</h3>
                    <form action="" method="post">
                        <div class="form-group">
                            <input type="text" name="username" class="form-control"  placeholder="Employee ID" value="" required="required"/>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Your Password *" value="" required="required" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Login" />
                        </div>
                    </form>
                </div>
               
            </div>
        </div>
  
    
</body>
</html>
