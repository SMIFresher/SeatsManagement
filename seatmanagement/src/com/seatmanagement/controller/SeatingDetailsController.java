package com.seatmanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SeatingDetailsService;

@RestController
@RequestMapping("/seatingdetails")
public class SeatingDetailsController {

	@Autowired
	SeatingDetailsService seatingDetailsService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllSeatingDetails",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllSeatingDetails(){
		return new ResponseEntity(seatingDetailsService.getAllSeatingDetails(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatingDetails> saveSeatingDetails(@RequestBody SeatingDetails seatingDetails ){
		 seatingDetailsService.saveSeatingDetails(seatingDetails);
		return ResponseEntity.ok().build();
	}
	

	
	
	@RequestMapping(value="/saveInBatch",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatingDetails> saveSeatingDetailsInBatch(@RequestBody SeatingDetails[] seatingDetails ,@RequestParam UUID seatingId){
		
		
		seatingDetailsService.saveSeatingDetailsInbatch(seatingDetails,seatingId);
		return ResponseEntity.ok().build();
	}
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getEmployeeBySeatId",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatingDetails> getEmployeeBySeatId(@RequestParam(value="id") UUID seating_id,SeatingDetails seatingdetails){
		return new ResponseEntity(seatingDetailsService.getEmployeeBySeatId(seatingdetails,seating_id),HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/getEmployeeBySeatId",method=RequestMethod.GET)
	public ResponseEntity<SeatingDetails> getEmployeeBySeatId(@RequestParam(value="id") UUID seating_id){
		SeatingDetails seatingDetails = new SeatingDetails();
		seatingDetails=seatingDetailsService.getEmployeeBySeatId(seatingDetails, seating_id);
		ResponseEntity<SeatingDetails> responseEntity=null;
		if(!(seatingDetails.getSeatingDetailsId()==null)) {
			responseEntity=new ResponseEntity<SeatingDetails>(seatingDetails,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid Seat ID");
			}
		
		return responseEntity;
	}
	
	/*@RequestMapping(value="/getSeatByEmployeeId",method=RequestMethod.GET)
	public ResponseEntity<SeatingDetails> getSystemByEmployeeId(@RequestParam(value="empid") UUID employee_id){
		SeatingDetails seatingDetails = new SeatingDetails();
		seatingDetails=seatingDetailsService.getSeatByEmployeeId(seatingDetails, employee_id);
		ResponseEntity<SeatingDetails> responseEntity=null;
		if(!(seatingDetails.getSeatingDetailsId()==null)) {
			responseEntity=new ResponseEntity<SeatingDetails>(seatingDetails,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid Employee ID");
			}
		
		return responseEntity;
	}*/
}
