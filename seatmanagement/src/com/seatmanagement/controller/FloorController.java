package com.seatmanagement.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.FloorService;

@Controller
@RequestMapping("/floor")
public class FloorController {

	@Autowired
	private FloorService floorService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/floorsave", method = RequestMethod.POST)
	public ResponseEntity saveOrUpdate(Floor floor, @RequestParam(value = "buildingId") UUID buildingId) throws BusinessException {
		ResponseEntity response = null;
		floorService.saveOrUpdate(floor, buildingId);
		response = new ResponseEntity(HttpStatus.OK);
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllFloor")
	public ResponseEntity<List<Floor>> getAll() {
		List<Floor> floors = floorService.getAll();
		return new ResponseEntity(floors, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFloorById", method = RequestMethod.GET)
	public ResponseEntity getFloorById(@RequestParam(value = "FloorId") UUID FloorId) {
		Floor floor = new Floor();
		floor = floorService.getById(floor, FloorId);
		ResponseEntity responseEntity = null;
		if (!(floor.getFloorId() == null)) {
			responseEntity = new ResponseEntity<Floor>(floor, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid Floor ID");
		}

		return responseEntity;
	}
	
	
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFloorByBuildingId", method = RequestMethod.GET)
	public ResponseEntity getFloorsByBuildingId(@RequestParam(value = "buildingId") UUID buildingId) throws BusinessException {
		
		if (Objects.isNull(buildingId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		
		ResponseEntity responseEntity = null;
		List<Floor> floors = null;
		floors = floorService.getFloorsByBuildingId(buildingId);
		responseEntity= new ResponseEntity(floors, HttpStatus.OK);

		return responseEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFloorTypeByBuildingId", method = RequestMethod.GET)
	public ResponseEntity getFloorTypeByBuildingId(@RequestParam(value = "buildingId") UUID buildingId) throws BusinessException {
		
		if (Objects.isNull(buildingId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		
		ResponseEntity responseEntity = null;
		List<Floor> floors = null;
		floors = floorService.getFloorTypeByBuildingId(buildingId);
		responseEntity= new ResponseEntity(floors, HttpStatus.OK);

		return responseEntity;
	}
	
	@RequestMapping(value = "/deleteFloorById")
	public ResponseEntity deleteFloorById(@RequestParam(value = "floorId") UUID floorId) throws BusinessException {
		
		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		
		ResponseEntity responseEntity = null;
		
		floorService.deleteFloorById(floorId);
		
		responseEntity = new ResponseEntity(HttpStatus.OK);
		
		return responseEntity;
	}

}
