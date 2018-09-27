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

import com.seatmanagement.model.Seating;
import com.seatmanagement.service.SeatingService;

@Controller
@RequestMapping("/seating")
public class SeatingController {

	@Autowired
	private SeatingService seatingService;
	

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="/getSeatingByBlockId",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Seating>> getSeatingByBlockId(@RequestParam(value="id") UUID block_id,Seating seating){
			return new ResponseEntity(seatingService.getSeatingByBlockId(seating,block_id),HttpStatus.OK);
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value="/saveSeating")
		public ResponseEntity<Seating> saveOrUpdateSystems(Seating seating, @RequestParam(value="blockID") UUID blockID) {
			
			ResponseEntity responseEntity=null;
			if(seating !=null){
			responseEntity = new ResponseEntity(seatingService.addOrUpdateSeating(seating,blockID),HttpStatus.OK);
			}
			else{
				throw new RuntimeException("Can't save/update");
			}
			return responseEntity;
		}
	
		/*@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping("/getAllSeating")
		public ResponseEntity getAllSeating() {
			
			List<Seating> seatingList = seatingService.getAllSeating();
			ResponseEntity responseEntity = null;
			if(!seatingList.isEmpty()) {
			responseEntity=new ResponseEntity(seatingList,HttpStatus.OK);
			}
			else{
					throw new RuntimeException("Seating List is empty");
				}
			return responseEntity;
		}*/
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="/getAllSeating",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Seating>> getAllSeating(){
			return new ResponseEntity(seatingService.getAllSeating(),HttpStatus.OK);
		}
		
}
