package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SeatingDetailsService;
import com.seatmanagement.service.SystemService;
import com.seatmanagement.service.impl.SystemServiceImpl;


@RestController
@RequestMapping("/systems")
public class SystemController {

	@Autowired
	private SystemService systemService;
	
	
	@Autowired
	SeatingDetailsService seatingDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@RequestMapping("getAllSystems.do")
	public ResponseEntity getAllEmployees() throws BusinessException {
		
		logger.info("controller: SystemController Method : getAllEmployees request processing started at : " + LocalDateTime.now());
		
		List<Systems> systemList = systemService.getAllSystems();
		
	
		
		ResponseEntity responseEntity = null;
		responseEntity=new ResponseEntity(systemList,HttpStatus.OK);
		
		logger.info("controller: SystemController Method : getAllEmployees response sent at : " + LocalDateTime.now());
		return responseEntity;
	}
	
	@RequestMapping(value="/EditView")
	public ModelAndView getView(){
		
		logger.info("controller: SystemController Method : getView  request processing started at : " + LocalDateTime.now());
		
		ModelAndView mav=new ModelAndView("HR/cabins");
		mav.addObject("list", systemService.getAllSystems());
		mav.addObject("datasRow", seatingDetailsService.getAllSeatingDetails());
		
		logger.info("controller: SystemController Method : getView response sent at : " + LocalDateTime.now());
		return mav;
	}
	
	@RequestMapping(value="/View")
	public ModelAndView View(@RequestParam("seatingId") String seatingId)throws BusinessException {
		
		logger.info("controller: SystemController Method : View request processing started at : " + LocalDateTime.now());
		
		ModelAndView mav=new ModelAndView("Lead/Viewcabins");

		logger.info("controller: SystemController Method : View response sent at :  : " + LocalDateTime.now());
		return mav;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/saveOrUpdateSystem",method=RequestMethod.POST )
	public ResponseEntity<Systems> saveOrUpdateSystems(Systems system, 
			@RequestParam(value="employeeId" , required=false) UUID employeeId,
			@RequestParam(value="additionalDeviceList", required=false) List<UUID> additionalDevicesUUIDs) {
		
		logger.info("controller: SystemController Method : saveOrUpdateSystems request processing started at : " + LocalDateTime.now());
		
		ResponseEntity responseEntity=null;
		if(system !=null){
			systemService.addOrUpdateSystem(system, employeeId, additionalDevicesUUIDs);
			responseEntity = new ResponseEntity(HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Cant save/update");
		}
		
		logger.info("controller: SystemController Method : saveOrUpdateSystems response sent at : " + LocalDateTime.now());
		return responseEntity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/assignEmployee",method=RequestMethod.POST )
	public ResponseEntity<Systems> saveOrUpdateSystems1(Systems system, 
			@RequestParam(value="systemId" , required=false) UUID systemId,
			@RequestParam(value="employeeId" , required=false) UUID employeeId,
			@RequestParam(value="additionalDeviceList", required=false) List<UUID> additionalDevicesUUIDs) {
		
		logger.info("controller: SystemController Method : saveOrUpdateSystems1 request processing started at : " + LocalDateTime.now());
		
			system=systemService.getById(system, systemId);
			system.setAllotmentStatus("Active");
		
		ResponseEntity responseEntity=null;
		if(system !=null){
			systemService.addOrUpdateSystem(system, employeeId, additionalDevicesUUIDs);
			responseEntity = new ResponseEntity(HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Cant save/update");
		}
		
		logger.info("controller: SystemController Method : saveOrUpdateSystems1 response sent at : " + LocalDateTime.now());
		return responseEntity;
	}
	
	@RequestMapping(value="/getSystemById",method=RequestMethod.GET)
	public ResponseEntity getSystemById(@RequestParam UUID systemId){
		
		logger.info("controller: SystemController Method : getSystemById request processing started at : " + LocalDateTime.now());
		
		Systems system = new Systems();
		system=systemService.getById(system, systemId);
		ResponseEntity responseEntity=null;
		if(!(system.getSystemId()==null)) {
			responseEntity=new ResponseEntity<Systems>(system,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid System ID");
			}
		
		logger.info("controller: SystemController Method : getSystemById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}
	
	@RequestMapping(value="/getSystem",method=RequestMethod.GET)
	public ResponseEntity getSystem(@RequestParam String request){
		
		logger.info("controller: SystemController Method : getSystem request processing started at : " + LocalDateTime.now());
		
		Systems system = new Systems();
		system=systemService.getSystem(request);
		ResponseEntity responseEntity=null;
		if(!(system.getSystemId()==null)) {
			responseEntity=new ResponseEntity<Systems>(system,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid System ID");
			}
		
		logger.info("controller: SystemController Method : getSystem response sent at : " + LocalDateTime.now());
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
		
		logger.info("controller: SystemController Method : deleteSystemById request processing started at : " + LocalDateTime.now());
		
		Systems system = new Systems();
		system.setSystemId(systemId);
		
		ResponseEntity responseEntity=null;
		if(system.getSystemId() !=null){
		responseEntity = new ResponseEntity(systemService.delete(system),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid ID");
		}
		
		logger.info("controller: SystemController Method : deleteSystemById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

 @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getOs", method = RequestMethod.GET)
	public ResponseEntity getOscount() throws BusinessException {
	 
	 	logger.info("controller: SystemController Method : getOscount request processing started at : " + LocalDateTime.now());
		
		/*if (Objects.isNull(systemId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		*/
		List<Systems> sys = systemService.getOscount();
		ResponseEntity responseEntity = null;
		
		responseEntity= new ResponseEntity(sys, HttpStatus.OK);
		
		logger.info("controller: SystemController Method : getOscount response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	@RequestMapping(value = "/addSystem")
	public ModelAndView getAddsystem() {
		
		logger.info("controller: SystemController Method : getAddsystem request processed at : " + LocalDateTime.now());
		
		return new ModelAndView("/HR/System");
	}
	
	@RequestMapping(value = "/getModifySystem")
	public ModelAndView getModifySystem() {
		
		logger.info("controller: SystemController Method : getModifySystem request processed at : " + LocalDateTime.now());
		
		return new ModelAndView("/HR/ModifySystem");
	}
	
	
	
}
