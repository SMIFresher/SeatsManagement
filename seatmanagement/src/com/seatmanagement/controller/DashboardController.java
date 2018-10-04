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

import com.seatmanagement.service.DashboardService;

@Controller
@RequestMapping("/dashboard")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	DashboardService dashboardService;

	@RequestMapping(value = "/getAllDashboardCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllDashboardCount() {
		logger.info("Controller: DashboardController Method : getAllDashboardCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllDashboardCount(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllCompanyDetailsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllCompanyDetailsCount() {
		logger.info("Controller: DashboardController Method : getAllCompanyDetailsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllCompanyDetailsCount(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getAllFloorDetailsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllFloorDetailsCount(@RequestParam (value="buildingId") UUID buildingId) {
		
		logger.info("Controller: DashboardController Method : getAllFloorDetailsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllFloorDetailsCount(buildingId), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getAllBlockDetailsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllBlockDetailsCount(@RequestParam (value="floorId") UUID floorId) {
		
		logger.info("Controller: DashboardController Method : getAllFloorDetailsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllBlockDetailsCount(floorId), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getAllOsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllOsCount() {
		logger.info("Controller: DashboardController Method : getAllOsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllOsCount(), HttpStatus.OK);
	}
	
}
