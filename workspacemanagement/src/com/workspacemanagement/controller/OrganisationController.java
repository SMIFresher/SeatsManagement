package com.workspacemanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.workspacemanagement.exception.BusinessException;
import com.workspacemanagement.model.Constant;
import com.workspacemanagement.model.Organisation;
import com.workspacemanagement.service.OrganisationService;

@Controller
@RequestMapping("/organisation")
public class OrganisationController {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);

	@Autowired
	private OrganisationService organisationService;
	
	@RequestMapping("/getOrganisationView")
	public ModelAndView getOrganisationView () {

		logger.info("Controller: OrganisationController Method : getOrganisationView request processing started at : "
				+ LocalDateTime.now());
		
		ModelAndView model = new ModelAndView("/HR/Organisation");
		
		logger.info("Controller: OrganisationController Method : getOrganisationView response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/saveOrganisation")
	public @ResponseBody Map saveOrganisation(Organisation organisation) {

		logger.info("Controller: OrganisationController Method : saveOrganisation request processing started at : "
				+ LocalDateTime.now());
		
		Map model = new HashMap();
		String status = null;

		try {
			if (Objects.isNull(organisation)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			organisationService.saveOrganisation(organisation);

			status = Constant.RESPONSE_STATUS_OK;
			
		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			
			logger.error("BusinessException caught at Controller: OrganisationController Method : saveOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			// Set status and error message in map
			status = Constant.RESPONSE_STATUS_ERROR;
			
			model.put(Constant.RESPONSE_MESSAGE, errorMessage);
		} catch(RuntimeException e) {
			
			logger.error("RuntimeException caught at Controller: OrganisationController Method : saveOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			throw e;
		} 
		
		model.put(Constant.RESPONSE_STATUS, status);

		logger.info("Controller: OrganisationController Method : saveOrganisation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/getAllOrganisations")
	public @ResponseBody Map getAllOrganisations() {

		logger.info("Controller: OrganisationController Method : getAllOrganisations request processing started at : "
				+ LocalDateTime.now());

		Map model = new HashMap();
		String status = null;
		
		try {

			List<Organisation> organisations = organisationService.getAllOrganisations();
			
			model.put("organisations", organisations);

			status = Constant.RESPONSE_STATUS_OK;
			
			//throw new RuntimeException();
		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			
			logger.error("BusinessException caught at Controller: OrganisationController Method : getAllOrganisations " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			// Set status and error message in map
			status = Constant.RESPONSE_STATUS_ERROR;
			
			model.put(Constant.RESPONSE_MESSAGE, errorMessage);
		} catch(RuntimeException e) {
			
			logger.error("RuntimeException caught at Controller: OrganisationController Method : getAllOrganisations " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			throw e;
		} 
		
		model.put(Constant.RESPONSE_STATUS, status);

		logger.info("Controller: OrganisationController Method : getAllOrganisations response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/getOrganisationById")
	public @ResponseBody Map getOrganisationById(@ModelAttribute UUID organisationId) {

		logger.info("Controller: OrganisationController Method : getOrganisationById request processing started at : "
				+ LocalDateTime.now());

		Map model = new HashMap();
		String status = null;

		try {

			if (Objects.isNull(organisationId)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			Organisation organisation = organisationService.getOrganisationById(organisationId);

			model.put("organisation", organisation);
			status = Constant.RESPONSE_STATUS_OK;
		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			
			logger.error("BusinessException caught at Controller: OrganisationController Method : getOrganisationById " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			// Set status and error message in map
			status = Constant.RESPONSE_STATUS_ERROR;
			
			model.put(Constant.RESPONSE_MESSAGE, errorMessage);
		} catch(RuntimeException e) {
			
			logger.error("RuntimeException caught at Controller: OrganisationController Method : getOrganisationById " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			throw e;
		} 
		
		model.put(Constant.RESPONSE_STATUS, status);

		logger.info("Controller: OrganisationController Method : getOrganisationById response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/updateOrganisation")
	public @ResponseBody Map updateOrganisation(@ModelAttribute Organisation organisation) {

		logger.info("Controller: OrganisationController Method : updateOrganisation request processing started at : "
				+ LocalDateTime.now());

		Map model = new HashMap();
		String status = null;

		try {

			if (Objects.isNull(organisation)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			organisationService.updateOrganisation(organisation);

			status = Constant.RESPONSE_STATUS_OK;
		}catch (BusinessException e) {
			String errorMessage = e.getMessage();
			
			logger.error("BusinessException caught at Controller: OrganisationController Method : updateOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			// Set status and error message in map
			status = Constant.RESPONSE_STATUS_ERROR;
			
			model.put(Constant.RESPONSE_MESSAGE, errorMessage);
		} catch(RuntimeException e) {
			
			logger.error("RuntimeException caught at Controller: OrganisationController Method : updateOrganisation " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			throw e;
		} 
		
		model.put(Constant.RESPONSE_STATUS, status);

		logger.info("Controller: OrganisationController Method : updateOrganisation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/deleteOrganisationById")
	public @ResponseBody Map deleteOrganisationById(@RequestParam(value="organisationId") UUID organisationId) {

		logger.info("Controller: OrganisationController Method : deleteOrganisationById request processing started at : "
				+ LocalDateTime.now());

		Map model = new HashMap();
		String status = null;

		try {

			if (Objects.isNull(organisationId)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			organisationService.deleteOrganisationById(organisationId);

			status = Constant.RESPONSE_STATUS_OK;

		} catch (BusinessException e) {
			String errorMessage = e.getMessage();
			
			logger.error("BusinessException caught at Controller: OrganisationController Method : deleteOrganisationById " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			// Set status and error message in map
			status = Constant.RESPONSE_STATUS_ERROR;
			
			model.put(Constant.RESPONSE_MESSAGE, errorMessage);
		} catch(RuntimeException e) {
			
			logger.error("RuntimeException caught at Controller: OrganisationController Method : deleteOrganisationById " + e.getMessage());
			logger.error("Exception stack : ", e);
			
			throw e;
		} 
		
		model.put(Constant.RESPONSE_STATUS, status);

		logger.info("Controller: OrganisationController Method : deleteOrganisationById response sent at : "
				+ LocalDateTime.now());

		return model;
	}

}
