package com.seatmanagement.controller;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Reallocation;
import com.seatmanagement.service.ReallocationService;

@Controller
@RequestMapping("/reallocation")
public class ReallocationController {

	private static final Logger logger = LoggerFactory.getLogger(ReallocationController.class);

	@Autowired
	private ReallocationService reallocationService;

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

		logger.info(
				"Controller: ReallocationController Method : getReallocationByEmployeeId response sent at : "
						+ LocalDateTime.now());
		return model;
	}

	@RequestMapping("/saveReallocation")
	public ModelAndView saveReallocation(@ModelAttribute Reallocation reallocation) {
		
		logger.info(
				"Controller: ReallocationController Method : saveReallocation request processing started at : "
						+ LocalDateTime.now());

		ModelAndView model = null;

		model = new ModelAndView();

		reallocationService.saveReallocation(reallocation);

		logger.info(
				"Controller: ReallocationController Method : saveReallocation response sent at : "
						+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/updateReallocation")
	public ModelAndView updateReallocation(@ModelAttribute Reallocation reallocation) {
		
		logger.info(
				"Controller: ReallocationController Method : updateReallocation request processing started at : "
						+ LocalDateTime.now());
		
		ModelAndView model = null;

		if (null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ModelAndView();

		reallocationService.updateReallocation(reallocation);

		logger.info(
				"Controller: ReallocationController Method : updateReallocation response sent at : "
						+ LocalDateTime.now());
		
		return model;
	}

	@RequestMapping("/deleteReallocation")
	public ModelAndView deleteReallocation(@ModelAttribute Reallocation reallocation) {
		
		logger.info(
				"Controller: ReallocationController Method : deleteReallocation request processing started at : "
						+ LocalDateTime.now());
		
		ModelAndView model = null;

		if (null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}

		model = new ModelAndView();

		reallocationService.deleteReallocation(reallocation);

		logger.info(
				"Controller: ReallocationController Method : deleteReallocation response sent at : "
						+ LocalDateTime.now());

		return model;
	}
}
