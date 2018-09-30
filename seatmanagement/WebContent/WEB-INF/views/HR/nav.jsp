<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/seatmanagement/css/HR/nav.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<nav
	class="navbar navbar-expand-md bg-white shadow p-3 mb-5 navbar-dark fixed-top">

	<button type="button" id="sidebarCollapse" class="navbar-btn active">
		<span></span> <span></span> <span></span>
	</button>



	<a class="navbar-brand" href="index.jsp"> <img
		src="/seatmanagement/Logo/nav-logo.jpg" alt="logo" style="width: 100px;">
	</a>
	<div class="collapse navbar-collapse"></div>
	<form class="form-inline" action="/action_page.php">
		<input class="form-control mr-sm-2 search" type="text"
			placeholder="Search">
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
		<li><a href="/seatmanagement/hr/getHRDashboard" class="fa fa-home"> Home</a></li>
		<li><a href="/seatmanagement/organisation/getOrganisationView" class="fa fa-sitemap"> Organization</a></li>
		<li class="active"><a href="#managebuildingSubmenu"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-building"> Manage
				Building</a>
			<ul class="collapse list-unstyled" id="managebuildingSubmenu">
				<li><a href="#buldsubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Building</a>
					<ul class="collapse list-unstyled" id="buldsubmenu">
						<li><a href="/seatmanagement/building/getBuildingViewAndEdit">View</a></li>
						<li><a href="/seatmanagement/building/getModifyBuilding">Modify</a></li>
					</ul></li>
				<li><a href="#floorsubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Floor</a>
					<ul class="collapse list-unstyled" id="floorsubmenu">
						<li><a href="/seatmanagement/floor/getFloorView">Add</a></li>
						<li><a href="#">Modify</a></li>
					</ul></li>
				<li><a href="#blocksubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Block</a>
					<ul class="collapse list-unstyled" id="blocksubmenu">
						<li><a href="/seatmanagement/block/getBlockView">Add</a></li>
						<li><a href="/seatmanagement/seating/getSeatingView">Modify</a></li>
					</ul></li>
			</ul>
		</li>
		<li class="active"><a href="#Employee"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-users"> Employee </a>
			<ul class="collapse list-unstyled" id="Employee">
				<li><a href="team.jsp">Team</a></li>
				<li><a href="Employee.jsp">Employee</a></li>

			</ul></li>
		<li class="active"><a href="#manageallotmentSubmenu"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-map-o"> Manage
				Allotment</a>
			<ul class="collapse list-unstyled" id="manageallotmentSubmenu">
				<li><a href="#">Available Seats</a></li>
				<li><a href="#">Reserved Seats</a></li>
				<li><a href="#">Reallocated Seats</a></li>
			</ul></li>
		<li class="active"><a href="#facilitiesSubmenu"
			data-toggle="collapse" aria-expanded="false" class="dropdown-toggle fa fa-stack-exchange"> Facilities</a>
			<ul class="collapse list-unstyled" id="facilitiesSubmenu">
				<li><a href="#systemSubmenu" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">System</a>
					<ul class="collapse list-unstyled" id="systemSubmenu">
						<li><a href="#">Add System Details</a></li>
						<li><a href="#">Add Device Details</a></li>
					</ul></li>
				<li><a href="#">Utilities</a></li>
			</ul></li>
		<li><a href="ChangePassword.jsp" class="fa fa-exchange"> Change Password</a></li>
		<li><a href="<c:url value="/pagelogout" />" class="fa fa-sign-out"> Logout</a></li>
		<br>
	</ul>


</nav>

<!-- Popper.JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
                $(this).toggleClass('active');
            });
        });
 </script>