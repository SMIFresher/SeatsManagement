package com.seatmanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@RestController
@RequestMapping("/systems")
public class SystemController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping("getAllSystems.do")
	public ResponseEntity getAllEmployees() {
		
		List<Systems> systemList = systemService.getAllSystems();
		ResponseEntity responseEntity = null;
		if(!systemList.isEmpty()) {
		responseEntity=new ResponseEntity(systemList,HttpStatus.OK);
		}
		else{
				throw new RuntimeException("Systems List is empty");
			}
		return responseEntity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/saveOrUpdateSystem",method=RequestMethod.POST )
	public ResponseEntity<Systems> saveOrUpdateSystems(@RequestBody Systems system) {
		
		ResponseEntity responseEntity=null;
		if(system !=null){
		responseEntity = new ResponseEntity(systemService.addOrUpdateSystem(system),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Cant save/update");
		}
		return responseEntity;
	}
	
	@RequestMapping(value="/getSystemById",method=RequestMethod.GET)
	public ResponseEntity getSystemById(@RequestParam UUID systemId){
		Systems system = new Systems();
		system=systemService.getById(system, systemId);
		ResponseEntity responseEntity=null;
		if(!(system.getSystemId()==null)) {
			responseEntity=new ResponseEntity<Systems>(system,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid System ID");
			}
		
		return responseEntity;
	}
	
	@RequestMapping(value="/getSystemByEmployee",method=RequestMethod.GET)
	public ResponseEntity getSystemByEmployeeId(@RequestParam UUID employeeId){
		Systems system = new Systems();
		system=systemService.getSystemByEmployeeId(employeeId);
		ResponseEntity responseEntity=null;
		if(!(system.getSystemId()==null)) {
			responseEntity=new ResponseEntity<Systems>(system,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid System ID");
			}
		
		return responseEntity;
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.GET)
	public ResponseEntity deleteSystemById(@RequestParam UUID systemId){
		
		Systems system = new Systems();
		system.setSystemId(systemId);
		
		ResponseEntity responseEntity=null;
		if(system.getSystemId() !=null){
		responseEntity = new ResponseEntity(systemService.delete(system),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid ID");
		}
		return responseEntity;
	}
	
	
	
}
