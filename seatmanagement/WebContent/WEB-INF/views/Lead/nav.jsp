<link rel="stylesheet" type="text/css" href="css/nav.css">
<!-- Nav Bar -->

<nav class="navbar navbar-expand-md bg-white shadow p-3 mb-5 navbar-dark fixed-top">


  <a class="navbar-brand" href="index.jsp">
    <img src="../Logo/nav-logo.jpg" alt="logo" style="width:100px;">
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="fa fa-bars " style="font-size: 30px;"  aria-hidden="true" ></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link text-dark" href="/seatmanagement/lead/index">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-dark" href="/seatmanagement/lead/building">View</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-dark" href="#">Logout</a>
      </li>     
    </ul>
  </div> 
  <form class="form-inline" action="#" data-toggle="modal" data-target="#myModal">
    <input class="form-control mr-sm-2 search" type="text" placeholder="Search">
  </form> 
</nav>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>



<div class="modal fade" id="search">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Information</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-4 border">
              <h1></h1><span class="fa fa-desktop" style="font-size: 20px;" aria-hidden="true"> System Information</span></h1>
              <hr>

              <p><b>System ID : </b> VGS_01 <br><b>Network Type : </b> LAN Connection <br>
              <b>Operating System : </b> Windows</p>
              <b>Additional Device </b>
                <ul>
                  <li>Keyboard</li>
                  <li>Head Phone</li>
                </ul>
              
            </div>
            <div class="col-sm-8 border border-0 border-right-1">
            
                <div class="col-sm-12 text-dark">
                  <h1></h1><span class="fa fa-user-o" style="font-size: 20px;" aria-hidden="true"> User Information</span></h1>
                </div>
                <hr>
                <div class="col-sm-12 text-dark">
                  <ul>
                  <li><b>Name: </b>Venkat Narayanan <br></li>
                  <li><b>Date of Join: </b>12-10-2018 <br></li>
                  <li><b>Employe ID: </b>SMI_761</li>
                  <li><b>Team: </b>Corex <br></li>
                  <li><b>Team Head: </b>Some Person <br></li>

                  </ul>
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