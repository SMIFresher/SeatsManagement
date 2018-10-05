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

import com.seatmanagement.model.Utilities;
import com.seatmanagement.service.UtilitiesService;

/**
 * 
 * @author Harshanaa Ramdas This class gets all requests for 'Utilities' model
 *         object and delegates to service classes for business processing
 */
@Controller
@RequestMapping("/utilities")
public class UtilitiesController {

	private static final Logger logger = LoggerFactory.getLogger(UtilitiesController.class);

	@Autowired
	private UtilitiesService utilitiesService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("Utilities");
	}

	/**
	 * 
	 * @param utilities
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveUtilities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utilities> saveOrUpdate(Utilities utilities) {
		logger.info("Controller: UtilitiesController Method : saveUtilities request processing started at : "
				+ LocalDateTime.now());
		logger.info("Controller: UtilitiesController Method : saveUtilities response sent at : " + LocalDateTime.now());
		return new ResponseEntity(utilitiesService.saveOrUpdate(utilities), HttpStatus.OK);

	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllUtilities", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Utilities>> getAll() {
		logger.info("Controller: UtilitiesController Method : getAllUtilities request processing started at : "
				+ LocalDateTime.now());
		logger.info(
				"Controller: UtilitiesController Method : getAllUtilities response sent at : " + LocalDateTime.now());
		return new ResponseEntity(utilitiesService.getAll(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param utilityId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUtilitiesById")
	public ResponseEntity getBlockById(@RequestParam(value = "utilityId") UUID utilityId) {
		logger.info("Controller: UtilitiesController Method : getUtilitiesById request processing started at : "
				+ LocalDateTime.now());
		Utilities utilities = new Utilities();
		utilities = utilitiesService.getById(utilities, utilityId);
		ResponseEntity responseEntity = null;
		if (!(utilities.getUtilityId() == null)) {
			responseEntity = new ResponseEntity<Utilities>(utilities, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid Utility ID");
		}
		logger.info(
				"Controller: UtilitiesController Method : getUtilitiesById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param utilityId
	 * @return
	 */
	@RequestMapping(value = "/deleteUtilityById", method = RequestMethod.POST)

	public ResponseEntity deleteUtilityById(@RequestParam(value = "utilityId") UUID utilityId) {
		logger.info("Controller: UtilitiesController Method : deleteUtilityById request processing started at : "
				+ LocalDateTime.now());
		Utilities utilities = new Utilities();
		utilities.setUtilityId(utilityId);

		ResponseEntity responseEntity = null;
		if (utilities.getUtilityId() != null) {
			responseEntity = new ResponseEntity(utilitiesService.delete(utilities), HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid ID");
		}
		logger.info(
				"Controller: UtilitiesController Method : deleteUtilityById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addUtilities")
	public ModelAndView getAddsystem() {
		logger.info("Controller: UtilitiesController Method : addUtilities request processing started at : "
				+ LocalDateTime.now());
		logger.info("Controller: UtilitiesController Method : addUtilities response sent at : " + LocalDateTime.now());
		return new ModelAndView("/HR/Utilities");
	}

}
