package com.workspacemanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.workspacemanagement.dao.EmployeeDao;
import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.dao.OrganisationDao;
import com.workspacemanagement.model.Employee;
import com.workspacemanagement.model.Team;
import com.workspacemanagement.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private GenericDao genericDao;

	@Override
	public void saveEmployee(Employee employee,UUID team_id) {

		logger.info("Service: EmployeeServiceImpl Method : saveEmployee started at : " + LocalDateTime.now());
		
		Team team=new Team();
		team.setTeamId(team_id);
		employee.setTeam(team);
		genericDao.saveOrUpdate(employee);

		logger.info("Service: EmployeeServiceImpl Method : saveEmployee ended at : " + LocalDateTime.now());
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		logger.info("Service: EmployeeServiceImpl Method : getAllEmployees started at : " + LocalDateTime.now());

		Employee employee = new Employee();
		
		List<Employee> employees = genericDao.getAll(employee);

		logger.info("Service: EmployeeServiceImpl Method : getAllEmployees ended at : " + LocalDateTime.now());
		
		return employees;
	}

	@Override
	public Employee getEmployeeById(UUID employeeId) {
		logger.info("Service: EmployeeServiceImpl Method : getEmployeeById started at : " + LocalDateTime.now());
	
		Employee employee = null;
		
		employee = (Employee) genericDao.getById(employee, employeeId);

		logger.info("Service: EmployeeServiceImpl Method : getEmployeeById ended at : " + LocalDateTime.now());
		
		return employee;
	}

	@Override
	public void updateEmployee(Employee employee) {
		logger.info("Service: EmployeeServiceImpl Method : updateEmployee started at : " + LocalDateTime.now());
		
		genericDao.saveOrUpdate(employee);

		logger.info("Service: EmployeeServiceImpl Method : updateEmployee ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteEmployeeById(UUID employeeId) {
		logger.info("Service: EmployeeServiceImpl Method : deleteEmployeeById started at : " + LocalDateTime.now());
		
		Employee employee = new Employee();
		
		employee.setEmployeeId(employeeId);
		
		genericDao.delete(employee);

		logger.info("Service: EmployeeServiceImpl Method : deleteEmployeeById ended at : " + LocalDateTime.now());		
	}



	




}
