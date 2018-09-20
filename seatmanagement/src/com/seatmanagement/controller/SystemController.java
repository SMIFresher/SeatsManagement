package com.seatmanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@Controller
@RequestMapping("/systems")
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/saveOrUpdateSystem",method=RequestMethod.POST )
	public ResponseEntity<Systems> saveOrUpdateSystems(@RequestBody Systems system ) {
		return new ResponseEntity(systemService.addOrUpdateSystem(system),HttpStatus.OK);
	}
	
	@RequestMapping(value="/getSystemById",method=RequestMethod.GET)
	public ResponseEntity getSystemById(@RequestParam UUID id){
		Systems system = new Systems();
		system=systemService.getById(system, id);
		String string=new Gson().toJson(system);
		return ResponseEntity.ok(string);
	}
	
	
	
}
