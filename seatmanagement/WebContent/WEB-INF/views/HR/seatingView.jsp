
<!DOCTYPE html>
<html lang="en">
<head>
<title>Seating Assign</title>
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
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.2/dist/leaflet.css" />
<script type="text/javascript" src="https://unpkg.com/leaflet@1.0.2/dist/leaflet.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/seatmanagement/js/imgViewer2.js"></script>
<script type="text/javascript" src="/seatmanagement/js/ajaxConfig.js"></script>
</head>

<body>

<div ng-app="workplaceManagement" ng-controller="workplaceManagementController" ng-init="FloorDetailsById();">
<!-- Nav Bar -->
<jsp:include page="nav.jsp"></jsp:include>

<br><br><br><br><br>

	<div class="container">
		<div class="row">
			<div class="col-md-4 border">
				<br>
				<div class="row">
					<div class="col-md-12">
						<h2>Seating Arrangement</h2>
						<hr>
							
								<h3> <span class="fa fa-th" aria-hidden="true"></span>{{FloorDetails.floorName}}</h3>
							      <p>{{FloorDetails.floorType}}</p>
							      <hr>
							      <p><b>Building Name:</b>{{FloorDetails.building.buildingName}} </p>
							      <p><b>Organisation :</b>{{FloorDetails.building.organisation.organisationName}}</p>
							      <p align="justify"> <b>Address : </b> <span>{{FloorDetails.building.buildingAddress}}</p>
							      <p><b>Location :</b> {{FloorDetails.building.buildingLocation}}</span>
							      </p>
								
								
							
					</div>

				</div>
				<br>
			</div>
			<div class="col-md-8">
				<div class="borderr text-center">

			      <img  id="image1" src="/seatmanagement/images/HR/vgs.svg" style="border: 30px solid #555; padding:20px;" width="80%"  />
			  
			     </div>
			</div>
		</div>
	</div>
	<div ng-include="'/seatmanagement/models/search.html'"></div>
</div>









    


<script type="text/javascript">

function gett(){
    dd="${id}";
    return dd;
 }

        ;(function($) {
/*
 *  Here we extend the imgViewer widget to display markers and notes
 *
 * This can be done with a few lines of code because of the capabilities of Leaflet
*/
  $.widget("wgm.imgNotes2", $.wgm.imgViewer2, {
    options: {
/*
 *  Default action for addNote callback
*/
      addNote: function(data) {
        var map = this.map,
          loc = this.relposToLatLng(data.x, data.y);
        var marker = L.marker(loc).addTo(map).bindPopup(data.note+"");
        marker.on("popupopen", function() {
          var temp = this;
          $(".marker-delete-button:visible").click(function () {
            temp.remove();
          });
        });
      }
    },
    
/*
 *  Add notes from a javascript array
 */
    import: function(notes) {
      if (this.ready) {
        var self = this;
        $.each(notes, function() {
          self.options.addNote.call(self, this);
        }); 
      }
    }
  });
  $(document).ready( function() {
    var $img = $("#image1").imgNotes2({
            onReady: function() {
              var notes
              var response = '';
              $.ajax({ type: "GET",   
                       url: "/seatmanagement/seating/getAllSeatingWithAxis?floorId="+gett(),   
                       async: false,
                       success : function(text)
                       {
                           response = text;
                       }
              }); 
               console.log(response );
              this.import(response);
            }
           
          });
  });
})(jQuery);

</script>

<script src="/seatmanagement/js/AngulerController.js"></script>


</body>
</html>
