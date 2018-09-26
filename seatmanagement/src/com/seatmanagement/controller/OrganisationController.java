package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.OrganisationService;

@Controller
@RequestMapping("/organisation")
public class OrganisationController {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);

	@Autowired
	private OrganisationService organisationService;

	@RequestMapping("/saveOrganisation")
	public ResponseEntity saveOrganisation(Organisation organisation) {

		logger.info("Controller: OrganisationController Method : saveOrganisation request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		try {
			if (Objects.isNull(organisation)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			organisationService.saveOrganisation(organisation);

			responseEntity = new ResponseEntity(HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception at Controller: OrganisationController Method : saveOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}

		logger.info("Controller: OrganisationController Method : saveOrganisation response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

	@RequestMapping("/getAllOrganisations")
	public ResponseEntity getAllOrganisations() {

		logger.info("Controller: OrganisationController Method : getAllOrganisations request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		try {

			List<Organisation> organisations = organisationService.getAllOrganisations();

			responseEntity = new ResponseEntity(organisations, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(
					"Exception at Controller: OrganisationController Method : getAllOrganisations " + e.getMessage());
			logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}

		logger.info("Controller: OrganisationController Method : getAllOrganisations response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

	@RequestMapping("/getOrganisationById")
	public ResponseEntity getOrganisationById(@ModelAttribute UUID organisationId) {

		logger.info("Controller: OrganisationController Method : getOrganisationById request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		try {

			if (Objects.isNull(organisationId)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			Organisation organisation = organisationService.getOrganisationById(organisationId);

			responseEntity = new ResponseEntity(organisation, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(
					"Exception at Controller: OrganisationController Method : getOrganisationById " + e.getMessage());
			logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}

		logger.info("Controller: OrganisationController Method : getOrganisationById response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

	@RequestMapping("/updateOrganisation")
	public ModelAndView updateOrganisation(@ModelAttribute Organisation organisation) {

		logger.info("Controller: OrganisationController Method : updateOrganisation request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;

		try {

			model = new ModelAndView();

			if (Objects.isNull(organisation)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			organisationService.updateOrganisation(organisation);

		} catch (Exception e) {
			logger.error(
					"Exception at Controller: OrganisationController Method : updateOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}

		logger.info("Controller: OrganisationController Method : updateOrganisation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/deleteOrganisationById")
	public ResponseEntity deleteOrganisationById(@RequestParam(value="organisationId") UUID organisationId) {

		logger.info("Controller: OrganisationController Method : deleteOrganisation request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		try {

			if (Objects.isNull(organisationId)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			organisationService.deleteOrganisationById(organisationId);

			responseEntity = new ResponseEntity(HttpStatus.OK);

		} catch (Exception e) {
			logger.error(
					"Exception at Controller: OrganisationController Method : deleteOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}

		logger.info("Controller: OrganisationController Method : deleteOrganisation response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

}
