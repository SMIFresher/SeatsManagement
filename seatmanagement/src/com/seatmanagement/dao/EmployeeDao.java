package com.seatmanagement.dao;

import java.util.List;

import com.seatmanagement.model.Employee;

public interface EmployeeDao {

	List<Employee> getEmployeesByDesignation(String designation);

}
