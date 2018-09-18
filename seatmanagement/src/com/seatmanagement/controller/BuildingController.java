package com.seatmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.seatmanagement.service.BuildingService;

@Controller
public class BuildingController {
	
	@Autowired
	BuildingService buildingService;
	
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
}
