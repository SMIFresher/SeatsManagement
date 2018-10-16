package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Reallocation;
import com.seatmanagement.service.ReallocationService;
import com.seatmanagement.service.SystemService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class gets all requests for 'Reallocation' model object and
 *         delegates to service classes for business processing
 *
 */
@Controller
@RequestMapping("/Reallocations")
public class ReallocationController {

	private static final Logger logger = LoggerFactory.getLogger(ReallocationController.class);

	@Autowired
	private ReallocationService reallocationService;
	
	
	
	

	/**
	 * 
	 * @param employeeId
	 * @return ModelAndView
	 */
	@RequestMapping(value="/{employeeId}",method=RequestMethod.GET)
	public ResponseEntity reallocationByEmployeeId(@PathVariable("employeeId") UUID employeeId) {

		logger.info(
				"Controller: ReallocationController Method : getReallocationByEmployeeId request processing started at : "
						+ LocalDateTime.now());

		ResponseEntity model = null;

		if (Objects.isNull(employeeId)) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ResponseEntity(HttpStatus.OK);

		Reallocation reallocation = reallocationService.getReallocationByEmployeeId(employeeId);

		// model.addObject("reallocation", reallocation);

		logger.info("Controller: ReallocationController Method : getReallocationByEmployeeId response sent at : "
				+ LocalDateTime.now());
		return model;
	}

	/**
	 * 
	 * @param reallocation
	 * @return ModelAndView
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity saveReallocation(@RequestParam UUID employeeId,@RequestParam UUID blockId,@RequestParam UUID previousblockId) throws BusinessException {

		logger.info("Controller: BlockController Method : saveBlock request processing started at : "
				+ LocalDateTime.now());
		
		ResponseEntity responseEntity = null;
		
		/*if (Objects.isNull(reallocation)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		} */
		reallocationService.saveReallocation(previousblockId,employeeId,blockId);
		responseEntity = new ResponseEntity(HttpStatus.OK);
		logger.info("Controller: BlockController Method : saveBlock response sent at : " + LocalDateTime.now());
		return responseEntity;
		
	}

	

	/**
	 * 
	 * @param reallocation
	 * @return ModelAndView
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity deleteReallocation( Reallocation reallocation) {

		logger.info("Controller: ReallocationController Method : deleteReallocation request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity model = null;

		if (null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ResponseEntity(HttpStatus.OK);

		reallocationService.deleteReallocation(reallocation);

		logger.info("Controller: ReallocationController Method : deleteReallocation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllReallocationDetails() {
		ResponseEntity model = null;
		logger.info(
				"Controller: SeatingDetailsController Method : getAllSeatingDetails request processing started at : "
						+ LocalDateTime.now());

		model = new ResponseEntity(reallocationService.getAllReallocationDetails(), HttpStatus.OK);

		logger.info("Controller: SeatingDetailsController Method : getAllSeatingDetails response sent at : "
				+ LocalDateTime.now());
		return model;
	}
	@RequestMapping(value = "/Reallocationview")
	public ModelAndView getReallocation() throws BusinessException {

		logger.info("Controller: FloorController Method : getModifyFloor request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/HR/ReallocationView");

		logger.info("Controller: FloorController Method : getModifyFloor response sent at : " + LocalDateTime.now());

		return model;

	}
	
	
	@RequestMapping(value = "/Reallocationrequest")
	public ModelAndView reallocationRequest() throws BusinessException {

		logger.info("Controller: FloorController Method : getModifyFloor request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/ReallocationRequest");

		logger.info("Controller: FloorController Method : getModifyFloor response sent at : " + LocalDateTime.now());

		return model;

	}
	
	
	@RequestMapping(value = "/Reallocationlead")
	public ModelAndView reallocationStatus() throws BusinessException {

		logger.info("Controller: FloorController Method : getModifyFloor request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/ReallocationStatus");

		logger.info("Controller: FloorController Method : getModifyFloor response sent at : " + LocalDateTime.now());

		return model;

	}

}
