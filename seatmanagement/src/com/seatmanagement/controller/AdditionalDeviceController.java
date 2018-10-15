package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.AdditionalDeviceService;

/**
 * 
 * @author SaiEswari
 * 
 *         This class gets all requests for 'Additionaldevice' model object and
 *         delegates to service classes for business processing
 *
 */

@Controller
@RequestMapping("/Additionaldevices")
public class AdditionalDeviceController {

	private static final Logger logger = LoggerFactory.getLogger(AdditionalDeviceController.class);
	@Autowired
	private AdditionalDeviceService additionalDeviceService;

	

	/**
	 * 
	 * @param additionalDevice
	 * @param errors
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdditionalDevice> saveAdditionalDevices(AdditionalDevice additionalDevice, Errors errors)
			throws BusinessException {

		logger.info(
				"Controller: AdditionalDeviceController Method : saveAdditionalDevice request processing started at : "
						+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		// Validation
		if (errors.hasErrors()) {
			throw new BusinessException(errors);
		}

		additionalDeviceService.saveOrUpdate(additionalDevice);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: AdditionalDeviceController Method : saveOrganisation response sent at : "
				+ LocalDateTime.now());

		return model;

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAllDevice() {

		logger.info("Controller: AdditionalDeviceController Method : getAllDevice request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;
		String status = null;

		List<AdditionalDevice> additionalDevice = additionalDeviceService.getAll();

		model = new ResponseEntity(additionalDevice, HttpStatus.OK);

		logger.info("Controller: AdditionalDeviceController Method : getAllOrganisations response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @param additional_device_id
	 * @return
	 */

	@RequestMapping(value = "/{additional_device_id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteDeviceById(@PathVariable("additional_device_id") UUID additional_device_id) {
		logger.info("Controller: AdditionalDeviceController Method : deleteDeviceById request processing started at : "
				+ LocalDateTime.now());

		AdditionalDevice additionalDevice = new AdditionalDevice();
		additionalDevice.setAdditional_device_id(additional_device_id);

		ResponseEntity responseEntity = null;
		if (additionalDevice.getAdditional_device_id() != null) {
			responseEntity = new ResponseEntity(additionalDeviceService.deleteDevice(additionalDevice), HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid Device ID");
		}
		logger.info("Controller: AdditionalDeviceController Method : deleteDeviceById response sent at : "
				+ LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/AdditionalDevice")
	public ModelAndView getAddsystem() {

		logger.info("Controller: AdditionalDeviceController Method : getAddsystem request processing started at : "
				+ LocalDateTime.now());
		ModelAndView model = new ModelAndView("/HR/device");
		logger.info("Controller: AdditionalDeviceController Method : getAddsystem response sent at : "
				+ LocalDateTime.now());
		return model;
	}

}
