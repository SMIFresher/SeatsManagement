package com.workspacemanagement.controller;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.workspacemanagement.model.AdditionalDevice;
import com.workspacemanagement.model.Seating;
import com.workspacemanagement.model.Systems;
import com.workspacemanagement.service.AdditionalDeviceService;

@Controller
@RequestMapping("/Additionaldevice")
public class AdditionalDeviceController {
	
	@Autowired
	private AdditionalDeviceService additionalDeviceService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("AdditionalDevice");
	}
	

	@RequestMapping(value="/savedevice",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdditionalDevice> saveSeatingDetails(AdditionalDevice additionalDevice ){
		
		return new ResponseEntity(additionalDeviceService.saveOrUpdate(additionalDevice),HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllDevice",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdditionalDevice>> getAll(){
		return new ResponseEntity(additionalDeviceService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteByDeviceId",method=RequestMethod.POST)
	public ResponseEntity deleteDeviceById(@RequestParam UUID additional_device_id){
		
		AdditionalDevice additionalDevice = new AdditionalDevice();
		additionalDevice.setAdditional_device_id(additional_device_id);
		
		ResponseEntity responseEntity=null;
		if(additionalDevice.getAdditional_device_id() !=null){
		responseEntity = new ResponseEntity(additionalDeviceService.deleteDevice(additionalDevice),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid Device ID");
		}
		return responseEntity;
	}
	
	
}
