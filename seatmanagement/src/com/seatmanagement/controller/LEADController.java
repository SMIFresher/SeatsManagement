package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;

@Controller
@RequestMapping("/Lead")
public class LEADController {
	
	private static final Logger logger = LoggerFactory.getLogger(HRController.class);
	
	@RequestMapping("/index")
	public ModelAndView getHRDashboard() throws BusinessException {

		logger.info("Controller: LEADController Method : getHRDashboard request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/index");

		logger.info("Controller: LEADController Method : getHRDashboard response sent at : "
				+ LocalDateTime.now());

		return model;
	}
	
	@RequestMapping("/building")
	public ModelAndView getBuilding() throws BusinessException {

		logger.info("Controller: LEADController Method : building request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/view");

		logger.info("Controller: LEADController Method : Building response sent at : "
				+ LocalDateTime.now());

		return model;
	}

	@RequestMapping("/ReallocationRequestView")
	public ModelAndView reallocationRequestView() throws BusinessException {

		logger.info("Controller: LEADController Method : getReallocationRequestView request processing started at : "
				+ LocalDateTime.now());
		
		// Must Implement: Get logged in user's teamID
		String teamID = "fbb027f1-7a66-4bd3-9c61-d8316182ca54";

		ModelAndView model = new ModelAndView("/Lead/reallocationRequest");
		model.addObject("teamId", teamID);

		logger.info("Controller: LEADController Method : getReallocationRequestView response sent at : "
				+ LocalDateTime.now());

		return model;
	}
	
	@RequestMapping("/SeatingView")
	public ModelAndView seatingView(@RequestParam("floorId") String floorId) throws BusinessException {

		logger.info("Controller: SeatingController Method : getSeatingView request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/Lead/seatingView");

		logger.info("Controller: SeatingController Method : getSeatingView response sent at : "
				+ LocalDateTime.now());
		model.addObject("id", floorId);
		return model;
	}
	
}
