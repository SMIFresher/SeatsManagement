package com.seatmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.service.BuildingService;

/**
 * 
 * @author Adithya Prabhu
 * 
 * This class gets all requests for 'Building' model object and delegates to service classes 
 * for business processing
 *
 */
@Controller
public class BuildingController {
	
	@Autowired
	BuildingService buildingService;
	
	@RequestMapping("saveBuilding.do")
	public ModelAndView saveBuilding() {
		
		System.out.println("Service Hit");
		
		return null;
	}
}
