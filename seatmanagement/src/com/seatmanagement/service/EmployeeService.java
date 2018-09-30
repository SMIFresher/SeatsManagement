package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Employee;


public interface EmployeeService {
	
	void saveEmployee(Employee employee,UUID team_id, UUID organisationId);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(UUID employeeId);

	void updateEmployee(Employee employee);

	void deleteEmployeeById(UUID employee);

	void saveEmployeeWithoutTeam(Employee employee, UUID organisationId);

	List<Employee> getEmployeesByDesignation(String designation);
	
}
