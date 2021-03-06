<!DOCTYPE html>
<html>
<head>
	<title>Seats Managament</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
	
  <link rel="stylesheet" type="text/css" href="css/style.css">
	
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>


<div class="container-fluid ">
  <div class="row">
    <div class="col-sm-6 col-md-3">
    	<div class="content text-center"  data-toggle="modal" data-target="#myModal">
    	  <h1><span class="fa fa-users" airal="true"></span> 29 </h1>
	      <p>Total Employee</p>
    	</div>
    </div>

    <div class="col-sm-6 col-md-3">
      <div class="content text-center"  data-toggle="modal" data-target="#myModal">
        <h1><span class="fa fa-desktop" airal="true"></span> 29 </h1>
        <p>Total Systems</p>
      </div>
    </div>

    <div class="col-sm-6 col-md-3">
      <div class="content text-center"  data-toggle="modal" data-target="#myModal">
        <h1><span class="fa fa-bar-chart" airal="true"></span> 29 </h1>
        <p>Total Seats</p>
      </div>
    </div>

    <div class="col-sm-6 col-md-3">
      <div class="content text-center"  data-toggle="modal" data-target="#myModal">
        <h1><span class="fa fa-tasks" airal="true"></span> 29 </h1>
        <p>Total Blocks</p>
      </div>
    </div>
	</div>
</div>
<hr>
<div class="container-fluid ">
  <div class="row">
    <div class="col-lg-4">
     <div class="table-responsive">
         <div class="col-sm-12 bg-primary text-white">
          <br>
          <h4>Device Details</h4>
          <br>
        </div>
         <div class="col-sm-12">
           <table class="table border">
            <br>
            <p>System OS</p>
            <tbody>
              <tr>
                <td>Windows</td>
                <td><span class="badge badge-pill badge-primary">50</span></td>
              </tr>
              <tr>
                <td>Linux</td>
                <td><span class="badge badge-pill badge-primary">20</span></td>
              </tr>
            </tbody>
          </table>
        </div>

      </div>
    </div>
    <div class="col-lg-8">
      <div class="table-responsive">
        <div class="col-sm-12 bg-primary text-white">
          <br>
          <h4>Company Details</h4>
          <br>
        </div>
         <table class="table border">
          <thead>
            <tr>
              <th>Batch</th>
              <th>Company</th>
              <th>Total Employe</th>
              <th>Total Seats</th>
              <th>Available Seats</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><span class="fa fa-bookmark text-primary"></span></td>
              <td>SMI</td>
              <td>100</td>
              <td>120</td>
              <td>20</td>
            </tr>
            <tr>
              <td><span class="fa fa-share-alt text-info"></span></td>
              <td>Tech Mango</td>
              <td>100</td>
              <td>120</td>
              <td>20</td>
            </tr>
            <tr>
              <td><span class="fa fa-share-alt text-info"></span></td>
              <td>VGS</td>
              <td>100</td>
              <td>120</td>
              <td>20</td>
            </tr>
          </tbody>
        </table>
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


 
</body>
</html>
