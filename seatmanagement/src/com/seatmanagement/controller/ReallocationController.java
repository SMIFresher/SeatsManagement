package com.seatmanagement.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Reallocation;
import com.seatmanagement.service.ReallocationService;

@Controller
public class ReallocationController {
	
	@Autowired
	private ReallocationService reallocationService;
	
	@RequestMapping("getReallocationByEmployeeId")
	public ModelAndView getReallocationByEmployeeId(@ModelAttribute String employeeId) {
		
		if(StringUtils.isBlank(employeeId)) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		Reallocation reallocation = reallocationService.getReallocationByEmployeeId(employeeId);
		
		model.addObject("reallocation", reallocation);
		
		return model;
	}
	
	@RequestMapping("saveReallocation")
	public ModelAndView saveReallocation(@ModelAttribute Reallocation reallocation) {
		
		if(null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		reallocationService.saveReallocation(reallocation);
		
		return model;
	}
	
	@RequestMapping("updateReallocation")
	public ModelAndView updateReallocation(@ModelAttribute Reallocation reallocation) {
		
		if(null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		reallocationService.updateReallocation(reallocation);
		
		return model;
	}
	
	@RequestMapping("deleteReallocation")
	public ModelAndView deleteReallocation(@ModelAttribute Reallocation reallocation) {
		
		if(null == reallocation) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		reallocationService.deleteReallocation(reallocation);
		
		return model;
	}
}
