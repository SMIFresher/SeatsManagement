package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.OrganisationService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 * This class gets all requests for 'Organisation' model object and delegates to service classes 
 * for business processing
 *
 */
@Controller
@RequestMapping("/organisation")
public class OrganisationController {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);

	@Autowired
	private OrganisationService organisationService;

	@RequestMapping("/getOrganisationView")
	public ModelAndView getOrganisationView(){

		logger.info("Controller: OrganisationController Method : getOrganisationView request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/HR/Organisation");
		
		logger.info("Controller: OrganisationController Method : getOrganisationView response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/saveOrganisation")
	public ResponseEntity saveOrganisation(@Valid Organisation organisation, Errors errors) throws BusinessException {

		logger.info("Controller: OrganisationController Method : saveOrganisation request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		// Validation
		if (errors.hasErrors()) {
			throw new BusinessException(errors);
		}

		organisationService.saveOrganisation(organisation);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: OrganisationController Method : saveOrganisation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/getAllOrganisations")
	public ResponseEntity getAllOrganisations() {

		logger.info("Controller: OrganisationController Method : getAllOrganisations request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		List<Organisation> organisations = organisationService.getAllOrganisations();

		model = new ResponseEntity(organisations, HttpStatus.OK);

		logger.info("Controller: OrganisationController Method : getAllOrganisations response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/getOrganisationById")
	public ResponseEntity getOrganisationById(@ModelAttribute UUID organisationId) throws BusinessException {

		logger.info("Controller: OrganisationController Method : getOrganisationById request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		if (Objects.isNull(organisationId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		Organisation organisation = organisationService.getOrganisationById(organisationId);

		
		model = new ResponseEntity (organisation, HttpStatus.OK);

		logger.info("Controller: OrganisationController Method : getOrganisationById response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/updateOrganisation")
	public ResponseEntity updateOrganisation(@ModelAttribute Organisation organisation) throws BusinessException {

		logger.info("Controller: OrganisationController Method : updateOrganisation request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		if (Objects.isNull(organisation)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		organisationService.updateOrganisation(organisation);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: OrganisationController Method : updateOrganisation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/deleteOrganisationById")
	public ResponseEntity deleteOrganisationById(@RequestParam(value = "organisationId") UUID organisationId)
			throws BusinessException {

		logger.info(
				"Controller: OrganisationController Method : deleteOrganisationById request processing started at : "
						+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		if (Objects.isNull(organisationId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		organisationService.deleteOrganisationById(organisationId);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: OrganisationController Method : deleteOrganisationById response sent at : "
				+ LocalDateTime.now());

		return model;
	}

}
