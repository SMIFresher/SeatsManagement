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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.DashboardService;
import com.seatmanagement.service.SeatingDetailsService;
import com.seatmanagement.service.SystemService;
import com.seatmanagement.service.impl.DashboardServiceImpl;

@RestController
@RequestMapping("/systems")
public class SystemController {

	@Autowired
	private SystemService systemService;
	
	
	@Autowired
	SeatingDetailsService seatingDetailsService;
	
	
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
	
	@RequestMapping(value="/EditView")
	public ModelAndView getView(){
		ModelAndView mav=new ModelAndView("HR/cabins");
		mav.addObject("list", systemService.getAllSystems());
		mav.addObject("datasRow", seatingDetailsService.getAllSeatingDetails());
		return mav;
	}
	
	@RequestMapping(value="/View")
	public ModelAndView View(){
		ModelAndView mav=new ModelAndView("HR/Viewcabins");

		return mav;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/saveOrUpdateSystem",method=RequestMethod.POST )
	public ResponseEntity<Systems> saveOrUpdateSystems(Systems system) {
		
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
	
	@RequestMapping(value="/getSystem",method=RequestMethod.GET)
	public ResponseEntity getSystem(@RequestParam String request){
		Systems system = new Systems();
		system=systemService.getSystem(request);
		ResponseEntity responseEntity=null;
		if(!(system.getSystemId()==null)) {
			responseEntity=new ResponseEntity<Systems>(system,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid System ID");
			}
		
		return responseEntity;
	}
	
	/*@RequestMapping(value="/getSystemBySystemName",method=RequestMethod.GET)
	public ResponseEntity getSystemBySystemName(@RequestParam String systemName){
		Systems system = new Systems();
		system=systemService.getSystem(systemName);
		ResponseEntity responseEntity=null;
		if(!(system.getSystemId()==null)) {
			responseEntity=new ResponseEntity<Systems>(system,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid System ID");
			}
		
		return responseEntity;
	}
	*/
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

	@RequestMapping(value = "/addSystem")
	public ModelAndView getAddsystem() {
		return new ModelAndView("/HR/System");
	}
	
	@RequestMapping(value = "/getModifySystem")
	public ModelAndView getModifySystem() {
		return new ModelAndView("/HR/ModifySystem");
	}
	
	
	
}
