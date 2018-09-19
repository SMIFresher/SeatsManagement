package com.seatmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.service.SeatingDetailsService;

@Controller
@RequestMapping("/seatingdetails")
public class SeatingDetailsController {

	@Autowired
	SeatingDetailsService seatingDetailsService;
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SeatingDetails>> getAllSeatingDetails(){
		//List<SeatingDetails> seatingDetails = seatingDetailsService.getAllSeatingDetails();
		return new ResponseEntity<>(seatingDetailsService.getAllSeatingDetails(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatingDetails> saveSeatingDetails(@RequestBody SeatingDetails seatingDetails ){
		 seatingDetailsService.saveSeatingDetails(seatingDetails);
		return ResponseEntity.ok().build();
	}
}
