<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>ADD Block Seating Details</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
  <!-- <link rel="stylesheet" href="style.css" type="text/css" media="screen" /> -->
  <script type="text/javascript" src="/seatmanagement/js/redips-drag-min.js"></script>
  <script type="text/javascript" src="/seatmanagement/js/script.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="/seatmanagement/js/ajaxConfig.js"></script>

</head>

<style type="text/css">
  td{
    width: 100px;
    height: 100px;
  }
</style>


<body>


<jsp:include page="nav.jsp"></jsp:include>
<br><br><br><br>




<div id="viewSystems" class="content" ng-app="Systems" ng-controller="getSystemDetails">

<div class="container-fluid" id="redips-drag">
  <div class="row">
   <style type="text/css">
    .table-borderless > tbody > tr > td,
    .table-borderless > tbody > tr > th,
    .table-borderless > tfoot > tr > td,
    .table-borderless > tfoot > tr > th,
    .table-borderless > thead > tr > td,
    .table-borderless > thead > tr > th {
        border: 2px solid rgba(0,0,0,0);
    }
  </style>
  <div class="col-md-12 ">
    <div class="row">
      <div class="col-md-6">
        <br>
      <div class="row">
        <div class="col-md-12">
            <h3> <span class="fa fa-th" aria-hidden="true"></span> Block Type</h3>
            <p><b>Bulding name :</b> SMI I</p>
        </div>
      </div>
     </div>
      <div class="col-md-6">
        <div class="container pull-right" style="float: right;">
          <br><br>
          <input type="button" value="Save Block Diagram" class="btn btn-success float-md-right" onclick="save('json')" title="Send content to the server (it will only show accepted parameters)" data-toggle="modal" data-target="#saveModel"/>
        </div>
      </div>

    </div>
      <hr>
    </div>
  <div class="col-md-9 text-center" >
    
        <table id="block" class="table table-borderless" style="border:5px solid; padding: 30px;" >
          <tbody>
           <c:forEach var="i" begin="0" end="5" step="1" varStatus ="status">
   				 <tr>
   				 <c:forEach var="j" begin="0" end="5" step="1" varStatus ="status">
   				 <td>
	   				 <c:forEach items="${datasRow}" var="sys"> 
	   				 <c:if test="${(i==sys.seatingRow) && (j==sys.seatingColum) && (fn:contains(sys.seatingAccessories,'laptop')) }">
	   				  	<div id="laptop" class="redips-drag" style="width: 100%;" ng-click="getSysDetails('${sys.seatingSystemNo}')" class="content text-center" data-toggle="modal" data-target="#myModal">
		             		 <span class="fa fa-laptop" style="font-size: 60px;"></span><br>${sys.seatingSystemNo}
		            	</div>
	   				 </c:if>
	   				 </c:forEach>
	   				 <c:forEach items="${datasRow}" var="sys"> 
	   				 <c:if test="${(i==sys.seatingRow) && (j==sys.seatingColum) && (fn:contains(sys.seatingAccessories,'desktop')) }">
	   				  	<div id="desktop" class="redips-drag" style="width: 100%;" ng-click="getSysDetails('${sys.seatingSystemNo}')" class="content text-center" data-toggle="modal" data-target="#myModal">
		             		 <span class="fa fa-desktop" style="font-size: 60px;"></span><br>${sys.seatingSystemNo}
		            	</div>
	   				 </c:if>
	   				 </c:forEach>
	   				 <c:forEach items="${datasRow}" var="sys"> 
	   				 <c:if test="${(i==sys.seatingRow) && (j==sys.seatingColum) && (fn:contains(sys.seatingAccessories,'exit')) }">
	   				  	<div id="exit" class="redips-drag" style="width: 100%;" ng-click="getSysDetails('${sys.seatingSystemNo}')" class="content text-center" data-toggle="modal" data-target="#myModal">
		             		 <span class="fa fa-bars" style="font-size: 60px;"></span><br>${sys.seatingSystemNo}
		            	</div>
	   				 </c:if>
	   				 </c:forEach>
	   				 <c:forEach items="${datasRow}" var="sys"> 
	   				 <c:if test="${(i==sys.seatingRow) && (j==sys.seatingColum) && (fn:contains(sys.seatingAccessories,'Emptydesk')) }">
	   				  	<div id="Emptydesk" class="redips-drag" style="width: 100%;" ng-click="getSysDetails('${sys.seatingSystemNo}')" class="content text-center" data-toggle="modal" data-target="#myModal">
		             		 <span class="fa fa-stop" style="font-size: 60px;"></span><br>${sys.seatingSystemNo}
		            	</div>
	   				 </c:if>
	   				 </c:forEach>
	   				
   				 </td>
		   		 </c:forEach>
		   		 </tr>
		   </c:forEach>
          </tbody>
      </table>

  </div>
  <div class="col-md-3 text-center" style="overflow:auto;">
    <div id="sys" ng-app="Systems" ng-controller="getSystemDetails">
      <table class="table table-borderless">
        <tbody >
           <tr>
            <td>
              <div id="Emptydesk" class="redips-drag redips-clone" style="width: 100%;">
                <span class="fa fa-stop" style="font-size: 60px;"></span><br>Emptydesk
              </div>
            </td>
            <td>
              <div id="exit" class="redips-drag redips-clone" style="width: 100%;">
                <span class="fa fa-bars" style="font-size: 60px;"></span><br>Exit 
              </div>
            </td>
            <td class="redips-trash bg-danger text-white">
              <span class="fa fa-trash" style="font-size: 50px;"></span><br>
              Delete
            </td>
          </tr>
		 <tr >
	          <c:forEach items="${list}" var="element" varStatus="i"> 
	            <td>
		            <div id="${element.systemType}" class="redips-drag  ng-cloak" style="width: 100%;" >
		              <span class="fa fa-${element.systemType}" style="font-size: 60px;"></span><br>${element.systemName}
		            </div>
	            </td>
		            <c:if test="${(i.count)%3==0}">
		            	</tr> <tr>
		            </c:if>
	          </c:forEach>
          </tr>
        </tbody>
      </table>

    </div>
  </div>


   
<div class="modal fade " id="myModal" >
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
              <p>SMI I floor architecture.....</p>
            </div>
            <div class="col-sm-8 cc">
              <div class="row text-center">
                
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


<div class="modal fade " id="saveModel" >
    <div class="modal-dialog modal-md">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header  text-center">
          <h4 class="modal-title">Conformation Message</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-12 text-center">
              <h3>Are You Sure</h3>
              
              <p>Do you want to save this details</p>
              <form id="Form" onsubmit="formSubmit();">
              	<input type="hidden" id="saveDetails" name="seating_details">
             
				<input type="submit" value="Submit" class="btn btn-primary"/>
              </form>
            </div>
          </div>
        </div>
        
        <!-- Modal footer -->
 
      </div>
    </div>
  </div>
  
  
  
  </div>
</div>
</div>


<script type="text/javascript">


var app = angular.module('Systems', []);
//AJAX Request Type Header to prepare error response for AJAX seperately
app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
	});
app.controller('getSystemDetails', function($scope, $http) {
    $http.get("getAllSystems.do")
    .then(function(response) {
        $scope.systems = response.data;
        console.log(response.data);
    },function errorCallback(response) {
    	console.log(response.data);
    	doModal('Some Error',response.data.ERROR_MESSAGE);
    });

    $scope.getSysDetails=function(sysno){
    	console.log(sysno);
    };    
});


</script>

	<script type="text/javascript">
	var seatId='<%=request.getParameter("seatingId") %>';
	function formSubmit() {

			$.ajax({
				url : '/seatmanagement/seatingdetails/saveInBatch?seatingId='+seatId,
				method : 'POST',
				contentType:'application/json',
				data : $("#Form").serialize(),

				dataType:'json',
				success : function(data) {
					var status = data.RESPONSE_STATUS;
					if (status == "OK") {

					}
					if (status == "ERROR") {
						var message = response.data.RESPONSE_MESSAGE;
						// Business Error scenario
						// provision to display business error message
					}
					console.log("dfgdgdfg");
					location.replace('/seatmanagement/systems/EditView?seatingId='+seatId);  
				},
				error : function(response) {
					var status = response.RESPONSE_STATUS;
					var message = response.RESPONSE_MESSAGE;
					var errorCode = response.ERROR_CODE;
					console.log("Response Status : " + status);
					console.log("Response Message : " + message);
					console.log("ErrorCode : " + errorCode);
					location.replace('/seatmanagement/systems/EditView?seatingId='+seatId); 
				}
				

			});
		}
	
	</script>


</body>
</html>
