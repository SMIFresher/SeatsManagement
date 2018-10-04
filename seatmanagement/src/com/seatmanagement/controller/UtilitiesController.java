package com.seatmanagement.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;


import com.seatmanagement.model.Utilities;
import com.seatmanagement.service.UtilitiesService;


@Controller
@RequestMapping("/utilities")
public class UtilitiesController {
	
	//private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);
	
	@Autowired
	private UtilitiesService utilitiesService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("Utilities");
	}
	      
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/saveUtilities",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utilities> saveOrUpdate( Utilities utilities ){
		
		return new ResponseEntity(utilitiesService.saveOrUpdate(utilities),HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllUtilities",produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<Utilities>> getAll(){
		return new ResponseEntity(utilitiesService.getAll(),HttpStatus.OK);
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getUtilitiesById")
	public ResponseEntity getBlockById(@RequestParam(value="utilityId") UUID utilityId){
		Utilities utilities = new Utilities();
		utilities=utilitiesService.getById(utilities,utilityId);
		ResponseEntity responseEntity=null;
		if(!(utilities.getUtilityId()==null)) {
			responseEntity=new ResponseEntity<Utilities>(utilities,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid Utility ID");
			}
		
		return responseEntity;
	}
	
	
	@RequestMapping(value="/deleteUtilityById",method=RequestMethod.POST)

	public ResponseEntity deleteUtilityById(@RequestParam(value="utilityId") UUID utilityId){
		
		Utilities utilities = new Utilities();
		utilities.setUtilityId(utilityId);
		
		ResponseEntity responseEntity=null;
		if(utilities.getUtilityId() !=null){
		responseEntity = new ResponseEntity(utilitiesService.delete(utilities),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid ID");
		}
		return responseEntity;
	}
	@RequestMapping(value = "/addUtilities")
	public ModelAndView getAddsystem() {
		return new ModelAndView("/HR/Utilities");
	}
	
}
