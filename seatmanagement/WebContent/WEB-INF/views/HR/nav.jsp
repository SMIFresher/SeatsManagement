<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/seatmanagement/css/HR/nav.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="shortcut icon" href="/seatmanagement/images/favicon.ico" type="image/x-icon">

<nav
	class="navbar navbar-expand-md bg-white shadow p-3 mb-5 navbar-dark fixed-top">

	<button type="button" id="sidebarCollapse" class="navbar-btn active">
		<span></span> <span></span> <span></span>
	</button>



	<a class="navbar-brand" href="/seatmanagement/hr/index"> <img
		src="/seatmanagement/Logo/nav-logo.jpg" alt="logo" style="width: 100px;">
	</a>
	<div class="collapse navbar-collapse"></div>
	<form class="form-inline" id="src" action="#" ng-submit="getSysDetails(search)"  >
		<input class="form-control mr-sm-2 search" ng-model="search" type="text" placeholder="Search" >
	</form>
</nav>

<nav id="sidebar" class="active">
	<br>
	<br>
	<br>
	<br> 
	<div class="sidebar-header">


		<h3>Menus</h3>

	</div>

	<ul class="list-unstyled components">
		<li><a href="/seatmanagement/hr/index" class="fa fa-home"> Home</a></li>
		<li><a href="/seatmanagement/organisation/getOrganisationView" class="fa fa-sitemap"> Organization</a></li>
		<li><a href="/seatmanagement/building/getBuildingViewAndEdit" class="fa fa-building"> Manage Buildings</a></li>
		<li class="active"><a href="#managebuildingSubmenu"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-building"> Manage
				Floors</a>
			<ul class="collapse list-unstyled" id="managebuildingSubmenu">
				<li><a href="#floorsubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Floor</a>
					<ul class="collapse list-unstyled" id="floorsubmenu">
						<li><a href="/seatmanagement/floor/FloorView">Add</a></li>
						<li><a href="/seatmanagement/floor/FloorModify">Modify</a></li>
					</ul></li>
				<li><a href="#blocksubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Block</a>
					<ul class="collapse list-unstyled" id="blocksubmenu">
						<li><a href="/seatmanagement/block/getBlockView">Add</a></li>
						<li><a href="/seatmanagement/seating/getSeating">Assign Block</a></li>
						<li><a href="/seatmanagement/block/getModifyBlock">Modify</a></li>
					</ul></li>
			</ul>
		</li>
		<li class="active"><a href="#Employee"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-users"> Employee </a>
			<ul class="collapse list-unstyled" id="Employee">
				<li><a href="/seatmanagement/employee/getEmployeeView">Employee</a></li>
				<li><a href="/seatmanagement/team/getTeamView">Team</a></li>
				

			</ul></li>
		<li class="active"><a href="#manageallotmentSubmenu"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-map-o"> Manage
				Allotment</a>
			<ul class="collapse list-unstyled" id="manageallotmentSubmenu">
				<li><a href="/seatmanagement/hr/available">Available Seats</a></li>
				<li><a href="#">Reserved Seats</a></li>
				<li><a href="#">Reallocated Seats</a></li>
			</ul></li>
		<li class="active"><a href="#facilitiesSubmenu"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-stack-exchange"> Facilities</a>
			<ul class="collapse list-unstyled" id="facilitiesSubmenu">
				<li><a href="#systemSubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">System</a>
					<ul class="collapse list-unstyled" id="systemSubmenu">
						<li><a href="/seatmanagement/systems/addSystem">Add System Details</a></li>
							<li><a href="/seatmanagement/systems/getModifySystem">Modify System Details</a></li>
						<li><a href="/seatmanagement/Additionaldevice/addAdditionalDevice">Add Device Details</a></li>
					</ul></li>
				<li><a href="/seatmanagement/utilities/addUtilities">Utilities</a></li>
			</ul></li>
		<li><a href="ChangePassword.jsp" class="fa fa-exchange"> Change Password</a></li>
		<li><a href="/seatmanagement/pagelogout" class="fa fa-sign-out"> Logout</a></li>
		<br>
	</ul>


</nav>




<!-- Popper.JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
                $(this).toggleClass('active');
            });
        });
        $('#src').on('submit', function(e){
        	$('#click').trigger('click');
        });
 </script>

<script src="/seatmanagement/js/error.js"></script>

