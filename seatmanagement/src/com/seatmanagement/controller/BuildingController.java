package com.seatmanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Building;
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
	
	@Autowired
	private BuildingService buildingService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("Build");
	}
	
	
	
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/build",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Building> saveOrUpdate(@RequestBody Building building ){
		 //buildingService.saveOrUpdate(building);
		//return ResponseEntity.ok().build();
		ResponseEntity<Building> response =  new ResponseEntity(buildingService.saveOrUpdate(building),HttpStatus.OK);
		return response;
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllBuildings",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Building>> getAll(){
		//List<Object> objectList = seatingDetailsService.getAllSeatingDetails();
		
		
		//String string=new Gson().toJson(objectList);
		return new ResponseEntity(buildingService.getAll(),HttpStatus.OK);
	}
	
	
}
