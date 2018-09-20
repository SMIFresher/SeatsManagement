package com.seatmanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@Controller
public class SystemController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping("getAllSystems.do")
	public ResponseEntity getAllEmployees() {
		List<Systems> systems = systemService.getAllSystems();
		
		if(null != systems && !systems.isEmpty()) {
			for(Systems system : systems) {
				system.getEmployee().setSystem(null);
			}
		}
		
		String jsonString = new Gson().toJson(systems);
		return ResponseEntity.ok(jsonString);
	}
}
