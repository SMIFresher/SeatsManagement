package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Employee;

public interface EmployeeDao {

	List<Employee> getEmployeesByDesignation(String designation);

	List<Employee> getEmployeesByTeamId(UUID teamId);

}
