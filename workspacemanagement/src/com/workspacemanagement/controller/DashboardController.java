package com.workspacemanagement.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workspacemanagement.service.DashboardService;

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
}
