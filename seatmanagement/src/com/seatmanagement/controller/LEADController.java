package com.seatmanagement.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;

@Controller
@RequestMapping("/lead")
public class LEADController {
	
	private static final Logger logger = LoggerFactory.getLogger(HRController.class);
	
	@RequestMapping("/index")
	public ModelAndView getHRDashboard() throws BusinessException {

		logger.info("Controller: HRController Method : getHRDashboard request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/index");

		logger.info("Controller: HRController Method : getHRDashboard response sent at : "
				+ LocalDateTime.now());

		return model;
	}
	@RequestMapping("/building")
	public ModelAndView getBuilding() throws BusinessException {

		logger.info("Controller: HRController Method : building request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/view");

		logger.info("Controller: HRController Method : Building response sent at : "
				+ LocalDateTime.now());

		return model;
	}

}
