<!DOCTYPE html>
<html>
<head>
	<title>Seats Managament</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/nav.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>


<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>



<div class="container">
  <div class="row">
    <div class="col-md-4">
    	<div class="content text-center" data-toggle="modal" data-target="#myModal">
    	<br><br><br>
    	  <h3>SMI I</h3>
	      <p>SMI I Buliding architecture</p>
    	</div>
    </div>

    <div class="col-md-4 ">
      <div class="content text-center" data-toggle="modal" data-target="#myModal">
        <br><br><br>
         <h3>DH2</h3>
        <p>DH2 Buliding architecture</p>
      </div>
    </div>

    <div class="col-md-4 ">
      <div class="content text-center" data-toggle="modal" data-target="#myModal">
        <br><br><br>
         <h3>Plami</h3>
        <p>Plami Buliding architecture</p>
      </div>
    </div>

    <div class="col-md-4 ">
    	<div class="content text-center" data-toggle="modal" data-target="#AddBuilding">
    		<br><br>
    	   <h3>+</h3>
	      <h3>Add</h3>
        <p></p>
    	</div>
    </div>
	</div>
</div>
<hr>
<div class="container">
  <div class="row">
    <div class="col-md-4">
      <div class="content text-center" data-toggle="modal" data-target="#myModal">
      <br><br><br>
        <h3>Tech Mango</h3>
        <p>Tech Mango Buliding architecture</p>
      </div>
    </div>


    <div class="col-md-4 ">
      <div class="content text-center" data-toggle="modal" data-target="#AddBuilding">
        <br><br>
         <h3>+</h3>
        <h3>Add</h3>
        <p></p>
      </div>
    </div>
  </div>
</div>



<!-- Models -->

<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="row">
          	<div class="col-sm-4 text-center">
          		<h3>SMI I</h3>
          		<p>SMI I floar architecture.....</p>
          	</div>
          	<div class="col-sm-8 cc">
          		<div class="row text-center">
          			<div class="col-sm-12 flr bg-info">
          				<h4>Flr 1</h4>
          			</div>
          			<div class="col-sm-12 flr bg-info">
          				<h4>Flr 1</h4>
          			</div>
          			<div class="col-sm-12 flr">
          				<h4>Flr 1</h4>
          			</div>
          		</div>
          	</div>
          </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>


  <div class="modal fade" id="AddBuilding">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
         <form action="/addBuilding" autocomplete="off">
        <div class="modal-header">
          <h4 class="modal-title">Add Buliding</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="row">
            <div class="col-md-5">
              <h4>Please give a building</h4>
              <ul>
                <li>Enter A Proper Name</li>
                <li>Please Provide a Full Address</li>
                <li>Location EX: - "Chennai Or Madurai"</li>
              </ul>
            </div>
            <div class="col-md-7">
              
                  <div class="form-group">
                    <label for="buildingName">Building Name :</label> <input
                      type="text" class="form-control" id="buildingName"
                      name="buildingName" required="required"
                      placeholder="Building Name">
                  </div>
                  <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea class="form-control" rows="5" id="address"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="location">Building Name :</label> <input type="text"
                      class="form-control" id="location" name="location"
                      required="required" placeholder="Location">
                  </div>
               
            </div>
          </div>
           
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="submit" class="btn btn-success" >Add Building</button>
        </div>
        </form>
      </div>
    </div>
  </div>

</body>
</html>
