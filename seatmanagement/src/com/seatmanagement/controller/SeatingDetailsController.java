package com.seatmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.service.SeatingDetailsService;

@RestController
@RequestMapping("/seatingdetails")
public class SeatingDetailsController {

	@Autowired
	SeatingDetailsService seatingDetailsService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAll",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllSeatingDetails(){
		//List<Object> objectList = seatingDetailsService.getAllSeatingDetails();
		
		
		//String string=new Gson().toJson(objectList);
		return new ResponseEntity(seatingDetailsService.getAllSeatingDetails(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatingDetails> saveSeatingDetails(@RequestBody SeatingDetails seatingDetails ){
		 seatingDetailsService.saveSeatingDetails(seatingDetails);
		return ResponseEntity.ok().build();
	}
}
