package com.seatmanagement.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SeatingDetailsService;

import net.bytebuddy.description.type.TypeVariableToken;

/**
 * 
 * @author vgs-user
 *
 *         This class gets all requests for 'SeatingDetails' model object and
 *         delegates to service classes for business processing
 * 
 */

@RestController
@RequestMapping("/Seatingdetails")
public class SeatingDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(SeatingDetailsController.class);

	@Autowired
	SeatingDetailsService seatingDetailsService;

	/**
	 * 
	 * @return
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllSeatingDetails() {
		ResponseEntity model = null;
		logger.info(
				"Controller: SeatingDetailsController Method : getAllSeatingDetails request processing started at : "
						+ LocalDateTime.now());
		model = new ResponseEntity(seatingDetailsService.getAllSeatingDetails(),HttpStatus.OK);
		logger.info("Controller: SeatingDetailsController Method : getAllSeatingDetails response sent at : "
				+ LocalDateTime.now());
		return model;
	}

	/**
	 * 
	 * @param seatingDetails
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatingDetails> saveSeatingDetails( SeatingDetails seatingDetails) {
		ResponseEntity model = null;
		logger.info("Controller: SeatingDetailsController Method : saveSeatingDetails request processing started at : "
				+ LocalDateTime.now());
		seatingDetailsService.saveSeatingDetails(seatingDetails);
		model = new ResponseEntity(HttpStatus.OK);
		logger.info("Controller: SeatingDetailsController Method : saveSeatingDetails response sent at : "
				+ LocalDateTime.now());
		// return ResponseEntity.ok().build();
		return model;
	}

	/**
	 * 
	 * @param seating_details
	 * @param seatingId
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws BusinessException 
	 */

	@PostMapping(value = "/saveInBatch")
	public ResponseEntity<SeatingDetails> saveSeatingDetailsInBatch(@RequestBody String seating_details,
			@RequestParam UUID seatingId) throws UnsupportedEncodingException, BusinessException {
		ResponseEntity model = null;
		logger.info(
				"Controller: SeatingDetailsController Method : saveSeatingDetailsInBatch request processing started at : "
						+ LocalDateTime.now());
		// System.out.println(seatingDeatilsJsonString);
		String decodedJsonString = java.net.URLDecoder.decode(seating_details, "UTF-8");
		String formattedJsonString = decodedJsonString.split("=")[1].toString();

		SeatingDetails[] seatingDetailsObjectArray = new Gson().fromJson(formattedJsonString, SeatingDetails[].class);
		seatingDetailsService.saveSeatingDetailsInbatch(seatingDetailsObjectArray, seatingId);
		model = new ResponseEntity(HttpStatus.OK);
		// return ResponseEntity.ok().build();
		logger.info("Controller: SeatingDetailsController Method : saveSeatingDetailsInBatch response sent at : "
				+ LocalDateTime.now());
		return model;
	}

	

	/**
	 * 
	 * @param seating_id
	 * @return
	 */

	@RequestMapping(value = "/getEmployeeBySeatId/{seating_id}", method = RequestMethod.GET)
	public ResponseEntity<SeatingDetails> getEmployeeBySeatId(@PathVariable("seating_id") UUID seating_id) {
		logger.info("Controller: SeatingDetailsController Method : getEmployeeBySeatId request processing started at : "
				+ LocalDateTime.now());
		SeatingDetails seatingDetails = new SeatingDetails();
		seatingDetails = seatingDetailsService.getEmployeeBySeatId(seatingDetails, seating_id);
		ResponseEntity<SeatingDetails> responseEntity = null;
		if (!(seatingDetails.getSeatingDetailsId() == null)) {
			responseEntity = new ResponseEntity<SeatingDetails>(seatingDetails, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid Seat ID");
		}
		
		logger.info("Controller: SeatingDetailsController Method : getEmployeeBySeatId response sent at : "
				+ LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param seatingId
	 * @return
	 */

	@RequestMapping(value = "/getSeatDetailsBySeatId/{seatingId}", method = RequestMethod.GET)
	public ResponseEntity getSeatingDetailBySeatId(@PathVariable( "seatingId") UUID seatingId) {
		logger.info(
				"Controller: SeatingDetailsController Method : getSeatingDetailBySeatId request processing started at : "
						+ LocalDateTime.now());
		ResponseEntity responseEntity = null;

		List<SeatingDetails> seatingDetailsList = seatingDetailsService.getSeatingDetailsBySeatingId(seatingId);

		if (!(seatingDetailsList == null)) {
			responseEntity = new ResponseEntity<List<SeatingDetails>>(seatingDetailsList, HttpStatus.OK);
		} else {
			throw new RuntimeException("No Record Found");
		}
		
		logger.info("Controller: SeatingDetailsController Method : getSeatingDetailBySeatId response sent at : "
				+ LocalDateTime.now());
		return responseEntity;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteSeatingDetailById", method = RequestMethod.GET)
	public ResponseEntity deleteSeatingDetailById(@RequestParam(value = "seatingDetailsId") UUID seatingDetailsId)
			throws BusinessException {

		logger.info(
				"Controller: SeatingDetailsController Method : deleteSeatingDetailById request processing started at : "
						+ LocalDateTime.now());

		ResponseEntity model = null;

		if (Objects.isNull(seatingDetailsId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		seatingDetailsService.deleteSeatingDetailById(seatingDetailsId);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: SeatingDetailsController Method : deleteSeatingDetailById response sent at : "
				+ LocalDateTime.now());

		return model;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/undoSystemIdReference", method = RequestMethod.POST)
	public ResponseEntity undoSystemIdReference(@RequestParam(value="seatingDetailsId") UUID seatingDetailsId) throws BusinessException
	{
		logger.info(
				"Controller: SeatingDetailsController Method : undoSystemIdReference request processing started at : "
						+ LocalDateTime.now());

		ResponseEntity model = null;

		if (Objects.isNull(seatingDetailsId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		seatingDetailsService.undoSystemIdReference(seatingDetailsId);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: SeatingDetailsController Method : undoSystemIdReference response sent at : "
				+ LocalDateTime.now());

		return model;
	}

}
