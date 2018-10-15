package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Employee;
import com.seatmanagement.service.EmployeeService;


@Controller
@RequestMapping("/Employees")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value="/{organisationId}",method=RequestMethod.POST)
	public ResponseEntity saveEmployeeWithoutTeam(Employee employee, @PathVariable("organisationId") UUID organisationId) throws BusinessException {

		logger.info("Controller: EmployeeController Method : saveEmployeeWithoutTeam request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;
		if (Objects.isNull(employee)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		employeeService.saveEmployeeWithoutTeam(employee, organisationId);

		responseEntity = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: EmployeeController Method : saveEmployeeWithoutTeam response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

	@RequestMapping(value="/{teamId}/{organisationId}",method=RequestMethod.POST)
	public ResponseEntity saveEmployeeWithTeam(Employee employee, @PathVariable( "teamId") UUID teamId,
			@PathVariable( "organisationId") UUID organisationId) throws BusinessException {
	

		logger.info("Controller: EmployeeController Method : saveEmployeeWithTeam request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;
		if (Objects.isNull(employee)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		employeeService.saveEmployee(employee, teamId, organisationId);

		responseEntity = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: EmployeeController Method : saveEmployeeWithTeam response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity getAllEmployees() {

		logger.info("Controller: EmployeeController Method : getAllEmployees request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		List<Employee> employees = employeeService.getAllEmployees();

		responseEntity = new ResponseEntity(employees, HttpStatus.OK);

		logger.info("Controller: EmployeeController Method : getAllEmployees response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

/*	@RequestMapping("/getEmployeeById")
	public ResponseEntity getEmployeeById(@ModelAttribute UUID employeeId) throws BusinessException {
		logger.info("Controller: EmployeeController Method : getEmployeeById request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		if (Objects.isNull(employeeId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		Employee employee = employeeService.getEmployeeById(employeeId);

		responseEntity = new ResponseEntity(employee, HttpStatus.OK);

		logger.info(
				"Controller: EmployeeController Method : getEmployeeById response sent at : " + LocalDateTime.now());

		return responseEntity;
	}*/

	

	@RequestMapping(value="/{employeeId}",method=RequestMethod.DELETE)
	public ResponseEntity deleteEmployeeById(@PathVariable("employeeId") UUID employeeId) throws BusinessException {

		logger.info("Controller: EmployeeController Method : deleteEmployee request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		if (Objects.isNull(employeeId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		employeeService.deleteEmployeeById(employeeId);

		responseEntity = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: EmployeeController Method : deleteEmployee response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}
	
	
	@RequestMapping("/Designation")
	public ResponseEntity getEmployeesByDesignation(@RequestParam(value="designation") String designation) throws BusinessException {
		logger.info("Controller: EmployeeController Method : getEmployeesByDesignation request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		if (Objects.isNull(designation)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		List<Employee> employees = employeeService.getEmployeesByDesignation(designation);

		responseEntity = new ResponseEntity(employees, HttpStatus.OK);

		logger.info(
				"Controller: EmployeeController Method : getEmployeesByDesignation response sent at : " + LocalDateTime.now());

		return responseEntity;
	}
	
	@RequestMapping(value="/{teamId}" ,method=RequestMethod.GET)
	public ResponseEntity getEmployeesByTeamId(@PathVariable("teamId") UUID teamId) throws BusinessException {
		logger.info("Controller: EmployeeController Method : getEmployeesByTeamId request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		if (Objects.isNull(teamId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		List<Employee> employees = employeeService.getEmployeesByTeamId(teamId);

		responseEntity = new ResponseEntity(employees, HttpStatus.OK);

		logger.info(
				"Controller: EmployeeController Method : getEmployeesByTeamId response sent at : " + LocalDateTime.now());

		return responseEntity;
	}
	
	@RequestMapping("/EmployeeView")
	public ModelAndView getEmployeeView(){

		logger.info("Controller: EmployeeController Method : getEmployeeView request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;

		model = new ModelAndView("HR/Employee");

		logger.info("Controller: EmployeeController Method : getEmployeeView response sent at : "
				+ LocalDateTime.now());

		return model;
	}
	
	
}
