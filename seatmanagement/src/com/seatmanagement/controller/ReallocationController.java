package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Reallocation;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.service.ReallocationService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class gets all requests for 'Reallocation' model object and
 *         delegates to service classes for business processing
 *
 */
@Controller
@RequestMapping("/reallocation")
public class ReallocationController {

	private static final Logger logger = LoggerFactory.getLogger(ReallocationController.class);

	@Autowired
	private ReallocationService reallocationService;

	/**
	 * 
	 * @param employeeId
	 * @return ModelAndView
	 */
	@RequestMapping("/getReallocationByEmployeeId")
	public ModelAndView getReallocationByEmployeeId(@ModelAttribute String employeeId) {

		logger.info(
				"Controller: ReallocationController Method : getReallocationByEmployeeId request processing started at : "
						+ LocalDateTime.now());

		ModelAndView model = null;

		if (StringUtils.isBlank(employeeId)) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ModelAndView();

		Reallocation reallocation = reallocationService.getReallocationByEmployeeId(employeeId);

		model.addObject("reallocation", reallocation);

		logger.info("Controller: ReallocationController Method : getReallocationByEmployeeId response sent at : "
				+ LocalDateTime.now());
		return model;
	}

	/**
	 * 
	 * @param reallocation
	 * @return ModelAndView
	 */
	@RequestMapping("/saveReallocation")
	public ModelAndView saveReallocation(@ModelAttribute Reallocation reallocation) {

		logger.info("Controller: ReallocationController Method : saveReallocation request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;

		model = new ModelAndView();

		reallocationService.saveReallocation(reallocation);

		logger.info("Controller: ReallocationController Method : saveReallocation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @param reallocation
	 * @return ModelAndView
	 */
	@RequestMapping("/updateReallocation")
	public ModelAndView updateReallocation(@ModelAttribute Reallocation reallocation) {

		logger.info("Controller: ReallocationController Method : updateReallocation request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;

		if (null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ModelAndView();

		reallocationService.updateReallocation(reallocation);

		logger.info("Controller: ReallocationController Method : updateReallocation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @param reallocation
	 * @return ModelAndView
	 */
	@RequestMapping("/deleteReallocation")
	public ModelAndView deleteReallocation(@ModelAttribute Reallocation reallocation) {

		logger.info("Controller: ReallocationController Method : deleteReallocation request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;

		if (null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ModelAndView();

		reallocationService.deleteReallocation(reallocation);

		logger.info("Controller: ReallocationController Method : deleteReallocation response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllReallocationDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllReallocationDetails() {
		ResponseEntity model = null;
		logger.info(
				"Controller: ReallocationController Method : getAllReallocationDetails request processing started at : "
						+ LocalDateTime.now());

		model = new ResponseEntity(reallocationService.getAllReallocationDetails(), HttpStatus.OK);

		logger.info("Controller: ReallocationController Method : getAllReallocationDetails response sent at : "
				+ LocalDateTime.now());
		return model;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getByReallocationId",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getByReallocationId(@RequestParam(value = "reallocationId") UUID reallocationId)
	{
		Reallocation reallocation = new Reallocation();
		ResponseEntity model = null;
		logger.info(
				"Controller: ReallocationController Method : getByReallocationId request processing started at : "
						+ LocalDateTime.now());

		model = new ResponseEntity(reallocationService.getByReallocationId(reallocation,reallocationId), HttpStatus.OK);

		logger.info("Controller: ReallocationController Method : getByReallocationId response sent at : "
				+ LocalDateTime.now());
		return model;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getReallocationByBlockId",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getReallocationByBlockId(@RequestParam(value = "blockId") UUID blockId)
	{
		
		ResponseEntity model = null;
		logger.info(
				"Controller: ReallocationController Method : getReallocationByBlockId request processing started at : "
						+ LocalDateTime.now());

		List<Reallocation> reallocationList = reallocationService.getReallocationByBlockId(blockId);

		if (!(reallocationList == null)) {
			model = new ResponseEntity<List<Reallocation>>(reallocationList, HttpStatus.OK);
		} else {
			throw new RuntimeException("No Record Found");
		}
		
		logger.info("Controller: ReallocationController Method : getReallocationByBlockId response sent at : "
				+ LocalDateTime.now());
		return model;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getReallocationByReallocationStatus",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getReallocationByReallocationStatus(@RequestParam(value = "reallocationStatus") String reallocationStatus)
	{
		
		ResponseEntity model = null;
		logger.info(
				"Controller: ReallocationController Method : getReallocationByReallocationStatus request processing started at : "
						+ LocalDateTime.now());

		List<Reallocation> reallocationList = reallocationService.getReallocationByReallocationStatus(reallocationStatus);

		if (!(reallocationList == null)) {
			model = new ResponseEntity<List<Reallocation>>(reallocationList, HttpStatus.OK);
		} else {
			throw new RuntimeException("No Record Found");
		}
		
		logger.info("Controller: ReallocationController Method : getReallocationByReallocationStatus response sent at : "
				+ LocalDateTime.now());
		return model;
		
	}
}
