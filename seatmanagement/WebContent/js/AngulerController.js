
var app = angular.module('workplaceManagement', []);
//AJAX Request Type Header to prepare error response for AJAX seperately

app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
});
app.filter('unique', function() {
   return function(collection, keyname) {
  var output = [], 
      keys = [];
  angular.forEach(collection, function(item) {
  var key = item[keyname];
  if(keys.indexOf(key) === -1) {
      keys.push(key); 
          output.push(item);
      }
  });
      return output;
   };
});
   
app.controller('workplaceManagementController', function($scope, $http) {
	
/**
	 * Save Organisation
	 */
	 $scope.saveOrganisations = function(){
		
		 $http({
		        url: '/seatmanagement/Organisations',
		        method: "POST",
		        data: $("#Form").serialize(),
		        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.OrganisationDetails();
		    	doModal("Information","Organisation Added Successfully....!");
		    }, 
		    function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
		    });
	 };
	/**
	 * Save Building
	 */
	 $scope.saveBuildings = function(){
		var build = $("#oid").val();
		console.log(build);
		$http({
		        url: '/seatmanagement/Buildings/'+build,
		        method: "POST",
		        data: $("#Form").serialize(),
		        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.buildingDetails();
		    	doModal("Information","Building Added Successfully....!");
		    }, 
		    function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
		    });
	 };
	 /**
		 * Save Floors
		 */
		 $scope.saveFloors = function(){ 
			 var floor = $("#bid").val();
				console.log(floor);

			 $http({
			        url: '/seatmanagement/Floors/'+floor,
			        method: "POST",
			        data: $("#Form").serialize(),
		        headers : {
		                'Content-Type' : 'application/x-www-form-urlencoded'
		            }
			    })
			    .then(function(response) {
			    	$scope.FloorDetails();
		    	doModal("Information","Floors Added Successfully....!");
			    }, 
				function(response) { // optional
			    	doModal("Information",response.data.ERROR_MESSAGE);
				});
		};
	 
	 
	 /**
	 * Save Blocks
	 */
	 $scope.saveBlocks = function(){ 
		 var fId = $("#flrId").val();
		 console.log("fId :" +fId);
		 $http({
		        url: '/seatmanagement/Blocks/block',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    		location.replace("/seatmanagement/Seatings/Seating?floorId=" +fId);
		    	//location.replace("/seatmanagement/Seatings/seatingview?floorId=" +fId);
		    },
		    	 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	/**
	 * Save SeatingDetails
	 */
	 $scope.saveCabins = function(){ 
		 $http({
		        url: '/seatmanagement/Seatingdetails/saveInBatch?seatingId='+seatId,
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/json'
	            }
		    })
		    .then(function(response) {
		    location.replace("");  
	    	//doModal("Information","Cabin Added Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	}; 

	 /**
	 * Save AdditionalDevices
	 */
	 $scope.saveAdditionalDevices = function(){ 
		 $http({
		        url: '/seatmanagement/Additionaldevices',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.AdditionaldeviceDetails();
	    	doModal("Information","Devices Added Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	/**
	 * Save Systems
	 */
	 $scope.saveSystems = function(){ 
		 $http({
		        url: '/seatmanagement/Systems/system',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.systemDetails();
	    	doModal("Information","Systems Updated Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	 /**
	 * Assign System to Employee
	 */
	 $scope.assignSystems = function(){ 
		 $http({
		        url: '/seatmanagement/Systems/Employee',
		        method: "POST",
		        data: $("#Form1").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.systemDetails();
	    	doModal("Information","System Assigned Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	/**
	 * save Seats
	 */
	 $scope.saveSeats = function(){ 
		 var seat = $("#boid").val();
			console.log(seat);
		 $http({
		        url: '/seatmanagement/Seatings/'+seat,
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.seatDetails();
	    	doModal("Information","Seats Added Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	/**
	 * save Utilities
	 */
	 $scope.saveUtilities = function(){ 
		 $http({
		        url: '/seatmanagement/Utilities',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.UtilitiesDetails();
	    	doModal("Information","Utilities Added Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	/**
	 * Save Team
	 */
	$scope.saveTeam = function(){ 
		 var team = $("#ogid").val();
			console.log(team);
		 $http({
		        url: '/seatmanagement/Teams/'+team,
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.teamDetails();
		    	doModal("Information","Team Updated Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	/**
	 * Save Reallocation
	 */
	$scope.saveReallocation= function(){ 
		 $http({
		        url: '/seatmanagement/Reallocations',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.deviceDetails();
		    	doModal("Information","Reallocation Updated Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	
	
	/**
	 * Save Employee
	 */
	$scope.saveEmployee = function(){ 
		var teamId = $("#teamid").val();
		var org = $("#orgid").val();		
		console.log(org);
		if(teamId == null){
			
			$http({
		        url: '/seatmanagement/Employees/'+org,
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.employeeDetails();
		    	doModal("Information","Employee Updated Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
		}else{
			 $http({
			        url: '/seatmanagement/Employees/'+org+"/"+teamId,
			        method: "POST",
			        data: $("#Form").serialize(),
		        headers : {
		                'Content-Type' : 'application/x-www-form-urlencoded'
		            }
			    })
			    .then(function(response) {
			    	$scope.employeeDetails();
			    	doModal("Information","Team Updated Successfully....!");
			    }, 
				function(response) { // optional
			    	doModal("Information",response.data.ERROR_MESSAGE);
			});
		}
	}
	
	/**
	 * Save LeadRequests
	 */
	$scope.leadRequests = function(){ 
		console.log($("#Form").serialize());
		 $http({
		        url: '/seatmanagement/Reallocations',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	location.replace("/seatmanagement/Reallocations/Reallocationlead");
		    	doModal("Information","Request Sent Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};

	
	 
	/**
	 * Get all AdditionalDevices
	 */
	
	 $scope.AdditionaldeviceDetails = function(){ 
    	$http.get("/seatmanagement/Additionaldevices")
	        .then(function successCallback(response) {
	            $scope.getAddDev = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	        	doModal("Information",response.data.ERROR_MESSAGE);
        });
	  };
	    
	/**
	 * get All Team
	 */
	$scope.teamDetails=function(){
		 $http.get("/seatmanagement/Teams")
	     .then(function successCallback(response) {
	     	console.log("getAllTeam success");
	         $scope.getteam = response.data;
	         console.log(response.data);
	     }, function errorCallback(response) {
	          
	     });
	};
	 
	/**
	 * Get All Employee
	 */
	$scope.employeeDetails=function(){
		 $http.get("/seatmanagement/Employees")
		 
	     .then(function successCallback(response) {
	    	 console.log("start");
	         $scope.getemp = response.data;
	         console.log(response.data);
	         console.log("end");
	     }, function errorCallback(response) {
	          
	     });
		};
	
	/**
	 * Get All systems
	 */
	$scope.systemDetails=function(){
	 $http.get("/seatmanagement/Systems")
     .then(function successCallback(response) {
         $scope.getsystem = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
          
     });
	};
	 
	/**
	 * get All System by system Number
	 */
	$scope.getSysDetails=function(sysno){
    	console.log(sysno);
		$http.get("/seatmanagement/Systems/System?request="+sysno)
			.then(function(response) {
			$scope.DetailsSystems = response.data;
			console.log(response.data);
		}, function errorCallback(response) {
			console.log(response.data);
			if (response.data.ERROR_CODE == "9002")
				doModal('Information', "Internal server Error");
			else
				doModal('Some Error', response.data.ERROR_MESSAGE);
		});
    };
	 
	/**
	 * get All Building
	 */
    $scope.buildingDetails=function(){
     $http.get("/seatmanagement/Buildings")
        .then(function successCallback(response) {
            $scope.getBuilding = response.data;
            console.log(response.data);
            
        }, function errorCallback(response) {
            
        });
    };
    
     /**
     * get All Utilities
     */
    $scope.UtilitiesDetails=function(){
	    $http.get("/seatmanagement/Utilities")
	    .then(function successCallback(response) {
	        $scope.getUtilities = response.data;
	        console.log(response.data);
	    }, function errorCallback(response) {
	        
	    });
    };
     /**
     * Get All Organization
     */
    $scope.OrganisationDetails=function(){
        $http.get("/seatmanagement/Organisations")
        .then(function successCallback(response) {
            $scope.getOrg = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            
        });
    };
    
    /**
     * get Floor by Building ID
     */
    $scope.floorDetailsByBuilding=function(buildingId){
        $http.get("/seatmanagement/Floors/floor/"+buildingId)
        .then(function successCallback(response) {
            $scope.getFloor= response.data;
            console.log(response.data);
           
        }, function errorCallback(response) {
             
        });
    };

    
    /**
     * get Reallocation by EmployeeId
     */
    $scope.reallocationDetailsByemployeeId=function(employeeId){
        $http.get("/seatmanagement/Reallocations/"+employeeId)
        .then(function successCallback(response) {
            $scope.getFloor= response.data;
            console.log(response.data);
           
        }, function errorCallback(response) {
             
        });
    };
    
    /**
     * getEmployee By Designation 
     */
    $scope.onDesignationChange = function() {
    	console.log("designation : " + $scope.designation);
    	$http.get("/seatmanagement/Employees/Designation?designation="+ $scope.designation)
        .then(function successCallback(response) {
            $scope.getTeamHeads = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
      });	
    };
    
    /**
     * Get Block Details by Floor Id and Block Type Cabin
     */
    $scope.blockDetailsByFloor=function(floor){
    
		$http.get("/seatmanagement/Blocks/BlockType?block_type=Cabins&floor_id="+floor)
        .then(function successCallback(response) {
            $scope.getBlock = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            
        });
	};
	
      /**
     * get Floor Details By Floor ID
     */
    $scope.FloorDetailsById = function() {
	    $http.get("/seatmanagement/Floors/"+gett())
	    .then(function(response) {
	        $scope.FloorDetails = response.data;
	        console.log(response.data);
	    });
    };
    
    /**
     * get Team Details By Team ID
     */
    $scope.TeamDetailsById = function(teamId) {
	    $http.get("/seatmanagement/Teams/teamId/"+teamId)
	    .then(function(response) {
	        $scope.teamsDetails = response.data;
	        console.log(response.data);
	    });
    };
    
  /**
   * Get FloorDetails
   */
    $scope.FloorDetails = function() {
	    $http.get("/seatmanagement/Floors")
	    .then(function successCallback(response) {
	        $scope.getFloor = response.data;
	        console.log(response.data);
				
	    }, function errorCallback(response) {
	        
	    });
    };
    
    
    /**
     * get All Blocks
     */
    $scope.blockDetails = function() {
	    $http.get("/seatmanagement/Blocks")
	    .then(function successCallback(response) {
	        $scope.getBlock= response.data;
	        console.log(response.data);
				
	    }, function errorCallback(response) {
	   	 doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    /**
     * get All Dashboard Count
     */
    $scope.dashboardCount = function() {
	    $http.get("/seatmanagement/Dashboards/dashboard")
	    .then(function successCallback(response) {
	        $scope.getCountDatas = response.data[0];
	        console.log(response.data);
	    }, function errorCallback(response) {
	    	doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
    /**
     * Company Details Count in Dashboard
     */
    $scope.companyDetailsCount = function() {
	    $http.get("/seatmanagement/Dashboards/company")
	    .then(function successCallback(response) {
	        $scope.getCompDetails = response.data;
	        console.log(response.data);
			
	    }, function errorCallback(response) {
	       
	        doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
     /**
     * get All Floor Details Count BuildingId in Dashboard
     */
    $scope.getFloor = function(buildingId){ 
	    $http.get("/seatmanagement/Dashboards/floor/"+buildingId)
	        .then(function successCallback(response) {
	            $scope.getFloorDetails = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            
	        });
	};
	/**
	 * get All Block Details Count FloorId in Dashboard
	 */
	$scope.getBlock=function(floorId){
		$http.get("/seatmanagement/Dashboards/block/"+floorId)
        .then(function successCallback(response) {
            $scope.getBlockDetails = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            
        });
	};
    
    /**
     * get All OS Count in Dashboard
     */
    $scope.osDetailsCount = function() {
	    $http.get("/seatmanagement/Dashboards/os ")
	    .then(function successCallback(response) {
	        $scope.getOsDetails = response.data;
	        console.log(response.data);
			
	    }, function errorCallback(response) {
	       
	        doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
    /**
     * get All Seats
     */
    $scope.seatDetails = function() {
	    $http.get("/seatmanagement/Seatings")
	    .then(function successCallback(response) {
	        $scope.getOsDetails = response.data;
	        console.log(response.data);
			
	    }, function errorCallback(response) {
	       
	        doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
    /**
     * get All Reallocations
     */
    $scope.getAllReallocations = function() {
     $http.get("/seatmanagement/Reallocations")
     .then(function successCallback(response) {
         $scope.getAllReallocation = response.data;
         console.log(response.data);
   
     }, function errorCallback(response) {
        
         doModal('Some Error',response.data.ERROR_MESSAGE);
     });
    };
    
    /**
     * get Image
     */
    $scope.getImages = function(floorId) {
        $http.get("/seatmanagement/Floors/Image?floorId="+floorId)
        .then(function successCallback(response) {
            $scope.getImage = response.data;
            console.log(response.data);
      
        }, function errorCallback(response) {
           
            doModal('Some Error',response.data.ERROR_MESSAGE);
        });
     };


    /**
     * *************************************************************************
     */
    
    /**
     * Delete Methods
     */
    
    
    /**
     * **************************************************************************
     */
    
    
        /**
     *  Delete Organization
     */
    
    $scope.deleteOrganisation = function(orgId){ 
		 $http({
		        url: '/seatmanagement/Organisations/'+orgId,
		        method: "DELETE",
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.OrganisationDetails();
	    	doModal("Information","Organization Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	/**
	 * Delete Building
	 */
	$scope.deleteBuilding = function(buildingId){ 
		 $http({
		        url: '/seatmanagement/Buildings/'+buildingId,
		        method: "DELETE",
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.buildingDetails();
		    	doModal("Information","Building Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	/**
	 * Delete FLoor
	 */
	$scope.deleteFloor = function(floorId){ 
		 $http({
		        url: '/seatmanagement/Floors/'+floorId,
		        method: "DELETE",
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.FloorDetails();
		    	doModal("Information","Floor Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	/**
	 * Delete Block
	 */
	$scope.deleteBlock= function(blockId){ 
		 $http({
		        url: '/seatmanagement/Blocks/'+blockId,
		        method: "DELETE",
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.blockDetails();
		    	doModal("Information","Block Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	/**
	 * Delete Utilities
	 */
	$scope.deleteUtilities= function(utilityId){ 
		 $http({
		        url: '/seatmanagement/Utilities/'+utilityId,
		        method: "DELETE",
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.UtilitiesDetails();
		    	doModal("Information","Utilities Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	/**
	 * Delete System
	 */
	$scope.deleteSystem= function(systemId){ 
		 $http({
		        url: '/seatmanagement/Systems/'+systemId,
		        method: "DELETE",
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.systemDetails();
		    	doModal("Information","System Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	/**
	 * Delete Team
	 */
	$scope.deleteTeam = function(teamId){ 
		 $http({
		        url: '/seatmanagement/Teams//deleteTeam'+teamId,
		        method: "DELETE",
		        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.teamDetails();
		    	doModal("Information","Team Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	/**
	 * Delete Employee
	 */
	
	$scope.deleteEmployee = function(employeeId){ 
		 $http({
		        url: '/seatmanagement/Employees/'+employeeId,
		        method: "DELETE",
		        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.employeeDetails();
		    	doModal("Information","Employee Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	/**
	 * Delete AdditionalDevice
	 */
	
	$scope.deleteAdditionalDevice = function(additional_device_id){ 
		 $http({
		        url: '/seatmanagement/Additionaldevices/'+additional_device_id,
		        method: "DELETE",
		        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.AdditionaldeviceDetails();
		    	doModal("Information","AdditionalDevice Deleted Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
		 
		 /**
			 * Delete Reallocations
			 */
			
			$scope.deleteReallocations = function(){ 
				 $http({
				        url: '/seatmanagement/Reallocations',
				        method: "DELETE",
				        headers : {
			                'Content-Type' : 'application/x-www-form-urlencoded'
			            }
				    })
				    .then(function(response) {
				    	$scope.AdditionaldeviceDetails();
				    	doModal("Information","Reallocations Deleted Successfully....!");
				    }, 
					function(response) { // optional
				    	doModal("Information",response.data.ERROR_MESSAGE);
					});
	};
	

});
