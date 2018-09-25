package com.seatmanagement.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Building;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.BuildingService;

/**
 * 
 * @author Adithya Prabhu
 * 
 * This class gets all requests for 'Building' model object and delegates to service classes 
 * for business processing
 *
 */
@Controller
@RequestMapping("/building")
public class BuildingController {
	
	//private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);
	
	@Autowired
	private BuildingService buildingService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("Build");
	}
	
	
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/build",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Building> saveOrUpdate(Building building , @RequestParam(value="organisationId") UUID organisationId){
		 //buildingService.saveOrUpdate(building);
		//return ResponseEntity.ok().build();
		ResponseEntity<Building> response =  new ResponseEntity(buildingService.saveOrUpdate(building,organisationId),HttpStatus.OK);
		return response;
	}
	
	
	
	@RequestMapping(value="/getAllBuildings")
	public ResponseEntity<List<Building>> getAll(){
		//List<Object> objectList = seatingDetailsService.getAllSeatingDetails();
		
		
		//String string=new Gson().toJson(objectList);
		return new ResponseEntity(buildingService.getAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getBuildingById",method=RequestMethod.GET)
	public ResponseEntity getBuildingById(@RequestParam(value="buildingId") UUID buildingId){
		Building building = new Building();
		building=buildingService.getById(building,buildingId);
		ResponseEntity responseEntity=null;
		if(!(building.getBuildingId()==null)) {
			responseEntity=new ResponseEntity<Building>(building,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid Building ID");
			}
		
		return responseEntity;
	}
	
	
	/*@RequestMapping(value="/deleteBuildingById",method=RequestMethod.GET)
	public ResponseEntity deleteBuildingById(@RequestParam UUID buildingId){
		
		Building building = new Building();
		building.setBuildingId(buildingId);
		
		ResponseEntity responseEntity=null;
		if(building.getBuildingId() !=null){
		responseEntity = new ResponseEntity(buildingService.delete(building),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid ID");
		}
		return responseEntity;
	}*/
		
	
	
}
