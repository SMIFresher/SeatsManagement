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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.seatmanagement.service.DashboardService;

/**
 * 
 * @author SaiEswari
 * 
 *         This class gets all requests for 'dashboard Service' model object and
 *         delegates to service classes for business processing
 *
 *
 */
@Controller
@RequestMapping("/Dashboards")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	DashboardService dashboardService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> dashboardCount() {
		logger.info("Controller: DashboardController Method : getAllDashboardCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllDashboardCount(), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> companyDetailsCount() {
		logger.info(
				"Controller: DashboardController Method : getAllCompanyDetailsCount request processing started at : "
						+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllCompanyDetailsCount(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param buildingId
	 * @return
	 */
	@RequestMapping(value = "/floor/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> floorDetailsCount(@PathVariable("buildingId") UUID buildingId) {

		logger.info("Controller: DashboardController Method : getAllFloorDetailsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllFloorDetailsCount(buildingId), HttpStatus.OK);
	}

	/**
	 * 
	 * @param floorId
	 * @return
	 */
	@RequestMapping(value = "/block/{floorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> blockDetailsCount(@PathVariable("floorId") UUID floorId) {

		logger.info("Controller: DashboardController Method : getAllFloorDetailsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllBlockDetailsCount(floorId), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/os", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> osCount() {
		logger.info("Controller: DashboardController Method : getAllOsCount request processing started at : "
				+ LocalDateTime.now());
		return new ResponseEntity(dashboardService.getAllOsCount(), HttpStatus.OK);
	}

}
