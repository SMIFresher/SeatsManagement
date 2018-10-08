
var app = angular.module('workplaceManagement', []);
//AJAX Request Type Header to prepare error response for AJAX seperately

app.config(function ($httpProvider, $httpParamSerializerJQLikeProvider){
	  $httpProvider.defaults.headers.common['RequestType'] = 'AJAX';
});

app.controller('workplaceManagementController', function($scope, $http) {
	

	/**
	 * Save Building
	 */
	 $scope.saveBuildings = function(){ 
		 $http({
		        url: '/seatmanagement/building/build',
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
	 * Save Blocks
	 */
	 $scope.saveBlocks = function(){ 
		 $http({
		        url: '/seatmanagement/block/saveblock',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.blockDetails();
	    	doModal("Information","Block Added Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};
	
	
	 /**
	 * Save Cabins
	 */
	 $scope.saveCabins = function(){ 
		 $http({
		        url: '/seatmanagement/seatingdetails/saveInBatch?seatingId='+seatId,
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
	 * Save Devices
	 */
	 $scope.saveDevices = function(){ 
		 $http({
		        url: '/seatmanagement/Additionaldevice/savedevice',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.deviceDetails();
	    	doModal("Information","Devices Added Successfully....!");
		    }, 
			function(response) { // optional
		    	doModal("Information",response.data.ERROR_MESSAGE);
			});
	};

	
	/**
	 * Save Floors
	 */
	 $scope.saveFloors = function(){ 
		 $http({
		        url: '/seatmanagement/floor/floorsave',
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
	 * Save Systems
	 */
	 $scope.saveSystems = function(){ 
		 $http({
		        url: '/seatmanagement/systems/saveOrUpdateSystem',
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
		        url: '/seatmanagement/systems/assignEmployee',
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
		 $http({
		        url: '/seatmanagement/seating/saveSeating',
		        method: "POST",
		        data: $("#Form").serialize(),
	        headers : {
	                'Content-Type' : 'application/x-www-form-urlencoded'
	            }
		    })
		    .then(function(response) {
		    	$scope.getAllSeats();
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
		        url: '/seatmanagement/utilities/saveUtilities',
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
	 * Get all Devices
	 */
	
	 $scope.deviceDetails = function(){ 
    	$http.post("/seatmanagement/Additionaldevice/getAllDevice")
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
	$scope.TeamDetails=function(){
		 $http.post("/seatmanagement/team/getAllTeam")
	     .then(function successCallback(response) {
	     	console.log("getAllTeam success");
	         $scope.getteam = response.data;
	         console.log(response.data);
	     }, function errorCallback(response) {
	         alert(response.status);
	     });
	};
	 
	/**
	 * Get All Employee
	 */
	$scope.employeeDetails=function(){
		 $http.post("/seatmanagement/employee/getAllEmployees")
	     .then(function successCallback(response) {
	         $scope.getemp = response.data;
	         console.log(response.data);
				
	     }, function errorCallback(response) {
	         alert(response.status);
	     });
		};
	
	/**
	 * Get All systems
	 */
	$scope.systemDetails=function(){
	 $http.post("/seatmanagement/systems/getAllSystems.do")
     .then(function successCallback(response) {
         $scope.getsystem = response.data;
         console.log(response.data);
			
     }, function errorCallback(response) {
         alert(response.status);
     });
	};
	 
	/**
	 * get All System by system Number
	 */
	$scope.getSysDetails=function(sysno){
    	console.log(sysno);
		$http.get("/seatmanagement/systems/getSystem?request="+sysno)
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
     $http.post("/seatmanagement/building/getAllBuildings")
        .then(function successCallback(response) {
            $scope.getBuilding = response.data;
            console.log(response.data);
            
        }, function errorCallback(response) {
            alert(response.status);
        });
    };
    
    /**
     * get All Utilities
     */
    $scope.UtilitiesDetails=function(){
	    $http.post("/seatmanagement/utilities/getAllUtilities")
	    .then(function successCallback(response) {
	        $scope.getUtilities = response.data;
	        console.log(response.data);
	    }, function errorCallback(response) {
	        alert(response.status);
	    });
    };
    
    /**
     * Get All Organization
     */
    $scope.OrganizationDetails=function(){
        $http.post("/seatmanagement/organisation/getAllOrganisations")
        .then(function successCallback(response) {
            $scope.getOrg = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            alert(response.status);
        });
    };

    /**
     * get Floor by Building ID
     */
    $scope.floorDetails=function(buildingId){
        $http.get("/seatmanagement/floor/viewfloor/buildingId?buildingId="+buildingId)
        .then(function successCallback(response) {
            $scope.getFloor = response.data;
            console.log(response.data);
            
        }, function errorCallback(response) {
            alert(response.status);
        });
    };

    /**
     * getEmployee By Designation 
     */
    $scope.onDesignationChange = function() {
    	console.log("designation : " + $scope.designation);
    	$http.get("/seatmanagement/employee/getEmployeesByDesignation?designation="+ $scope.designation)
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
		$http.get("/seatmanagement/block/getBlockByBlockType?block_type=Cabins&floor_id="+floor)
        .then(function successCallback(response) {
            $scope.getBlock = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            alert(response.status);
        });
	};
	
    /**
     * get Floor Details By Floor ID
     */
    $scope.FloorDetailsById = function() {
	    $http.get("/seatmanagement/floor/viewfloor/floorId?FloorId="+gett())
	    .then(function(response) {
	        $scope.FloorDetails = response.data;
	        console.log(response.data);
	    });
    };
    
    /**
     * Get Floor Details
     */
    $scope.FloorDetails = function() {
	    $http.get("/seatmanagement/floor/view")
	    .then(function successCallback(response) {
	        $scope.getFloor = response.data;
	        console.log(response.data);
				
	    }, function errorCallback(response) {
	        alert(response.status);
	    });
    };
    
    /**
     * get All Blocks
     */
    $scope.blockDetails = function() {
	    $http.get("/seatmanagement/block/getAllBlocks")
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
	    $http.get("../dashboard/getAllDashboardCount")
	    .then(function successCallback(response) {
	        $scope.getCountDatas = response.data[0];
	        console.log(response.data);
	    }, function errorCallback(response) {
	    	doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
    /**
     * Company Details Count
     */
    $scope.companyDetailsCount = function() {
	    $http.get("../dashboard/getAllCompanyDetailsCount")
	    .then(function successCallback(response) {
	        $scope.getCompDetails = response.data;
	        console.log(response.data);
			
	    }, function errorCallback(response) {
	       
	        doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
    /**
     * get All Floor Details Count BuildingId
     */
    $scope.getFloor = function(buildingId){ 
	    $http.get("/seatmanagement/dashboard/getAllFloorDetailsCount?buildingId="+buildingId)
	        .then(function successCallback(response) {
	            $scope.getFloorDetails = response.data;
	            console.log(response.data);
	        }, function errorCallback(response) {
	            alert(response.status);
	        });
	};
	
	/**
	 * get All Block Details Count FloorId
	 */
	$scope.getBlock=function(floorId){
		$http.get("/seatmanagement/dashboard/getAllBlockDetailsCount?floorId="+floorId)
        .then(function successCallback(response) {
            $scope.getBlockDetails = response.data;
            console.log(response.data);
			
        }, function errorCallback(response) {
            alert(response.status);
        });
	};
    
    /**
     * get All OS Count
     */
    $scope.osDetailsCount = function() {
	    $http.get("../dashboard/getAllOsCount")
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
    $scope.getAllSeats = function() {
	    $http.get("/seatmanagement/seating/getAllSeating")
	    .then(function successCallback(response) {
	        $scope.getOsDetails = response.data;
	        console.log(response.data);
			
	    }, function errorCallback(response) {
	       
	        doModal('Some Error',response.data.ERROR_MESSAGE);
	    });
    };
    
});
