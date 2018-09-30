package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Seating;
import com.seatmanagement.service.SeatingService;

@Controller
@RequestMapping("/seating")
public class SeatingController {

	private static final Logger logger = LoggerFactory.getLogger(SeatingController.class);
	
	@Autowired
	private SeatingService seatingService;
	

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="/getSeatingByBlockId",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Seating>> getSeatingByBlockId(@RequestParam(value="id") UUID block_id,Seating seating){
			logger.info("Controller: SeatingController Method : getSeatingByBlockId request processing started at : " + LocalDateTime.now());
			return new ResponseEntity(seatingService.getSeatingByBlockId(seating,block_id),HttpStatus.OK);
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value="/saveSeating")
		public ResponseEntity<Seating> saveOrUpdateSystems(Seating seating, @RequestParam(value="blockID") UUID blockID) {
			logger.info("Controller: SeatingController Method : saveOrUpdateSystems request processing started at : " + LocalDateTime.now());
			ResponseEntity responseEntity=null;
			if(seating !=null){
			responseEntity = new ResponseEntity(seatingService.addOrUpdateSeating(seating,blockID),HttpStatus.OK);
			}
			else{
				throw new RuntimeException("Can't save/update");
			}
			return responseEntity;
		}
	
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping("/getAllSeatingWithAxis")
		public ResponseEntity<List<Object>> getAllSeatingWithAxisByFloor(@RequestParam("floorId") UUID floorId) throws BusinessException {
			logger.info("Controller: SeatingController Method : getAllSeatingWithAxis request processing started at : " + LocalDateTime.now());
			return new ResponseEntity(seatingService.getAllSeatingWithAxisByFloor(floorId),HttpStatus.OK);
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="/getAllSeating",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Seating>> getAllSeating(){
			logger.info("Controller: SeatingController Method : getAllSeating request processing started at : " + LocalDateTime.now());
			return new ResponseEntity(seatingService.getAllSeating(),HttpStatus.OK);
		}
		
		@RequestMapping("/getSeatingView")
		public ModelAndView getSeatingView() throws BusinessException {

			logger.info("Controller: SeatingController Method : getSeatingView request processing started at : "
					+ LocalDateTime.now());

			ModelAndView model = new ModelAndView("/HR/seating");

			logger.info("Controller: SeatingController Method : getSeatingView response sent at : "
					+ LocalDateTime.now());

			return model;
		}
		
}
