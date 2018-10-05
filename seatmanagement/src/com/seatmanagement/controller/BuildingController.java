package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.BuildingService;

/**
 * 
 * @author SaiEswari
 * 
 *         This class gets all requests for 'Building' model object and
 *         delegates to service classes for business processing
 *
 */
@Controller
@RequestMapping("/building")
public class BuildingController {

	private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

	@Autowired
	private BuildingService buildingService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("Build");
	}

	/**
	 * 
	 * @param building
	 * @param organisationId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/build", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Building> saveOrUpdate(Building building,
			@RequestParam(value = "organisationId") UUID organisationId) {

		logger.info("Controller: BuildingController Method : saveOrUpdate request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity<Building> response = new ResponseEntity(buildingService.saveOrUpdate(building, organisationId),
				HttpStatus.OK);

		logger.info("Controller: BuildingController Method : saveOrUpdate response sent at : " + LocalDateTime.now());

		return response;

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/getAllBuildings")
	public ResponseEntity getAll() {

		logger.info("Controller: BuildingController Method : getAll request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		List<Building> building = buildingService.getAll();

		model = new ResponseEntity(building, HttpStatus.OK);

		logger.info("Controller: BuildingController Method : getAll response sent at : " + LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @param buildingId
	 * @return
	 */
	@RequestMapping(value = "/getBuildingById", method = RequestMethod.GET)
	public ResponseEntity getBuildingById(@RequestParam(value = "buildingId") UUID buildingId) {
		logger.info("Controller: BuildingController Method : getBuildingById request processing started at : "
				+ LocalDateTime.now());

		Building building = new Building();
		building = buildingService.getById(building, buildingId);
		ResponseEntity responseEntity = null;
		if (!(building.getBuildingId() == null)) {
			responseEntity = new ResponseEntity<Building>(building, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid Building ID");
		}

		logger.info(
				"Controller: BuildingController Method : getBuildingById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param buildingId
	 * @return
	 */
	@RequestMapping(value = "/deleteBuildingById", method = RequestMethod.POST)
	public ResponseEntity deleteBuildingById(@RequestParam UUID buildingId) {
		logger.info("Controller: BuildingController Method : deleteBuildingById request processing started at : "
				+ LocalDateTime.now());
		if (Objects.isNull(buildingId)) {
			throw new ApplicationException("Required param not present");
		}

		ResponseEntity responseEntity = null;

		Building building = new Building();
		building.setBuildingId(buildingId);

		buildingService.delete(building);

		responseEntity = new ResponseEntity(HttpStatus.OK);
		logger.info(
				"Controller: BuildingController Method : deleteBuildingById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBuildingViewAndEdit")
	public ModelAndView getBuildingViewAndEdit() {
		logger.info("Controller: BuildingController Method : getBuildingViewAndEdit request processing started at : "
				+ LocalDateTime.now());
		ModelAndView model = new ModelAndView("/HR/viewandAddBuilding");
		logger.info("Controller: BuildingController Method : getBuildingViewAndEdit response sent at : "
				+ LocalDateTime.now());
		return model;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getModifyBuilding")
	public ModelAndView getModifyBuilding() {
		logger.info("Controller: BuildingController Method : getModifyBuilding request processing started at : "
				+ LocalDateTime.now());
		ModelAndView model = new ModelAndView("/HR/ModifyBuilding");
		logger.info(
				"Controller: BuildingController Method : getModifyBuilding response sent at : " + LocalDateTime.now());
		return model;

	}
}
