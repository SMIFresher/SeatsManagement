package com.seatmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@Controller
public class SystemController {

	@Autowired
	SystemService systemService;
	
	@RequestMapping("getAllSystems.do")
	public ResponseEntity getAllEmployees() {
		List<Systems> systems = systemService.getAllSystems();
		String jsonString = new Gson().toJson(systems);
		return ResponseEntity.ok(jsonString);
	}
}
