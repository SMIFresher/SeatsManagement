<!DOCTYPE html>
<html>
 <%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>menus</title>
	<!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Our Custom CSS -->
     <link href="<c:url value="css/hrMenus.css" />" rel="stylesheet"  type="text/css" />
	<!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>

</head>

<body>


	<nav class="navbar navbar-expand-md bg-white shadow p-3 mb-5 navbar-dark fixed-top">

	<button type="button" id="sidebarCollapse" class="navbar-btn">
	  <span></span>
	  <span></span>
	  <span></span>
	  </button>
	  <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fas fa-align-justify"></i>
                    </button>


	  <a class="navbar-brand" href="#">
	    <img src="nav-logo.jpg" alt="logo" style="width:100px;">
	  </a>
	  <div class="collapse navbar-collapse" id="collapsibleNavbar">
	    
	  </div> 
	  <form class="form-inline" action="/action_page.php">
	    <input class="form-control mr-sm-2 search" type="text" placeholder="Search">
	  </form> 
	</nav>
	<br><br><br>


    <div class="wrapper">
        <!-- Sidebar Holder -->
        <nav id="sidebar">
            <div class="sidebar-header">
            <div class="sidebar-header">
            
            </div>
                <h3>Menus</h3>
            </div>
           

            <ul class="list-unstyled components">
                <li class="active">
                    <a href="#managebuildingSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage Building</a>
                    <ul class="collapse list-unstyled" id="managebuildingSubmenu">
                        <li>
                            <a href="#floorsubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Floor</a>
                            <ul class="collapse list-unstyled" id="floorsubmenu">
                                <li>
                                    <a href="#floorsubmenu">Add</a>
                                </li>
                                <li>
                                    <a href="#">Delete</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#blocksubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Block</a>
                            <ul class="collapse list-unstyled" id="blocksubmenu">
                                <li>
                                    <a href="#floorsubmenu">Add</a>
                                </li>
                                <li>
                                    <a href="#">Delete</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="active">
                    <a href="#manageallotmentSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage Allotment</a>
                    <ul class="collapse list-unstyled" id="manageallotmentSubmenu">
                        <li>
                            <a href="#">Available Seats</a>
                        </li>
                        <li>
                            <a href="#">Reserved Seats</a>
                        </li>
                        <li>
                            <a href="#">Reallocated Seats</a>
                        </li>
                    </ul>
                </li>
                <li class="active">
                    <a href="#facilitiesSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Facilities</a>
                    <ul class="collapse list-unstyled" id="facilitiesSubmenu">
                        <li>
                            <a href="#systemSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">System</a>
                            <ul class="collapse list-unstyled" id="systemSubmenu">
                                <li>
                                    <a href="#">Add System Details</a>
                                </li>
                                <li>
                                    <a href="#">Add Device Details</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Seating</a>
                        </li>
                        <li>
                            <a href="#">Utilities</a>
                        </li>
                    </ul>
                </li>
                <br>
            </ul>
            
            <br><br><br><br><br>
        </nav>

        
    </div>

    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
                $(this).toggleClass('active');
            });
        });
    </script>
</body>

</html>