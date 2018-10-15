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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Seating;
import com.seatmanagement.service.SeatingService;

/**
 * 
 * @author Sithaara Sivasankar
 * 
 *         This class gets all requests for 'Seating' model object and delegates
 *         to service classes for business processing
 *
 */
@Controller
@RequestMapping("/Seatings")
public class SeatingController {

	private static final Logger logger = LoggerFactory.getLogger(SeatingController.class);

	@Autowired
	private SeatingService seatingService;

	/**
	 * 
	 * @param block_id
	 * @param seating
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{block_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Seating>> getSeatingByBlockId(@PathVariable("block_id") UUID block_id, Seating seating)
			throws BusinessException {
		ResponseEntity responseEntity = null;
		logger.info("Controller: SeatingController Method : getSeatingByBlockId request processing started at : "
				+ LocalDateTime.now());
		if (Objects.isNull(block_id)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		responseEntity = new ResponseEntity(seatingService.getSeatingByBlockId(seating, block_id), HttpStatus.OK);
		logger.info(
				"Controller: SeatingController Method : getSeatingByBlockId response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param seating
	 * @param blockID
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{blockID}", method = RequestMethod.POST)
	public ResponseEntity<Seating> saveOrUpdateSeating( Seating seating, @PathVariable("blockID") UUID blockID)
			throws BusinessException {
		ResponseEntity responseEntity = null;
		logger.info("Controller: SeatingController Method : saveOrUpdateSeating request processing started at : "
				+ LocalDateTime.now());
		if (Objects.isNull(blockID) || Objects.isNull(seating)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		responseEntity = new ResponseEntity(seatingService.addOrUpdateSeating(seating, blockID), HttpStatus.OK);
		logger.info(
				"Controller: SeatingController Method : saveOrUpdateSeating response sent at : " + LocalDateTime.now());
		return responseEntity;
	}
	
	
	/**
	 * 
	 * @param floorId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/AllSeatingWithAxisByFloor/{floorId}",method = RequestMethod.GET)
	public ResponseEntity<List<Object>> allSeatingWithAxisByFloor(@PathVariable("floorId") UUID floorId)
			throws BusinessException {
		ResponseEntity responseEntity = null;
		logger.info(
				"Controller: SeatingController Method : getAllSeatingWithAxisByFloor request processing started at : "
						+ LocalDateTime.now());
		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		responseEntity = new ResponseEntity(seatingService.getAllSeatingWithAxisByFloor(floorId), HttpStatus.OK);
		logger.info("Controller: SeatingController Method : getAllSeatingWithAxisByFloor response sent at : "
				+ LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param floorId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/seatingWithAxisByFloorLead/{floorId}",method = RequestMethod.GET)
	public ResponseEntity<List<Object>> seatingWithAxisByFloorLead(@PathVariable("floorId") UUID floorId)
			throws BusinessException {
		ResponseEntity responseEntity = null;
		logger.info(
				"Controller: SeatingController Method : getAllSeatingWithAxisByFloorLead request processing started at : "
						+ LocalDateTime.now());
		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		responseEntity = new ResponseEntity(seatingService.getAllSeatingWithAxisByFloorLead(floorId), HttpStatus.OK);
		logger.info("Controller: SeatingController Method : getAllSeatingWithAxisByFloorLead response sent at : "
				+ LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Seating>> getAllSeating() {
		ResponseEntity responseEntity = null;
		logger.info("Controller: SeatingController Method : getAllSeating request processing started at : "
				+ LocalDateTime.now());
		responseEntity = new ResponseEntity(seatingService.getAllSeating(), HttpStatus.OK);
		logger.info("Controller: SeatingController Method : getAllSeating response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/Seating")
	public ModelAndView getSeating() throws BusinessException {

		logger.info("Controller: SeatingController Method : getSeatingView request processing started at : "
				+ LocalDateTime.now());
		ModelAndView model = new ModelAndView("/HR/seating");
		logger.info("Controller: SeatingController Method : getSeatingView response sent at : " + LocalDateTime.now());
		return model;
	}

	/**
	 * 
	 * @param floorId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/seatingview",method = RequestMethod.GET)
	public ModelAndView getSeatingView(@RequestParam(value="floorId") String floorId) throws BusinessException {
		logger.info("Controller: SeatingController Method : getSeatingView request processing started at : "
				+ LocalDateTime.now());
		ModelAndView model = new ModelAndView("/HR/seatingView");
		logger.info("Controller: SeatingController Method : getSeatingView response sent at : " + LocalDateTime.now());
		model.addObject("id", floorId);
		return model;
	}

}
