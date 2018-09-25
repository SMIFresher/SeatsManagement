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



import com.seatmanagement.model.Floor;
import com.seatmanagement.service.FloorService;



@Controller
@RequestMapping("/floor")
public class FloorController {

	@Autowired
	private FloorService floorService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/floorsave",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Floor> saveOrUpdate(Floor floor, @RequestParam(value="buildingId") UUID buildingId ) {
		ResponseEntity<Floor> response =  new ResponseEntity(floorService.saveOrUpdate(floor,buildingId),HttpStatus.OK);
		return response;
	}
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="/getAllFloor")
		public ResponseEntity<List<Floor>> getAll(){
			return new ResponseEntity(floorService.getAll(),HttpStatus.OK);
		}
	
	 @SuppressWarnings("rawtypes")
	@RequestMapping(value="/getFloorById",method=RequestMethod.GET)
		public ResponseEntity getFloorById(@RequestParam(value="FloorId") UUID FloorId){
		    Floor floor=new Floor();
		    floor=floorService.getById(floor,FloorId);
			ResponseEntity responseEntity=null;
			if(!(floor.getFloorId()==null)) {
				responseEntity=new ResponseEntity<Floor>(floor,HttpStatus.OK);
			}
			else{
						throw new RuntimeException("Invalid Floor ID");
				}
			
			return responseEntity;
		}
	
	}
