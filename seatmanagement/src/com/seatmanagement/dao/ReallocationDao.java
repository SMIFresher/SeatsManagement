package com.seatmanagement.dao;

import com.seatmanagement.model.Reallocation;

public interface ReallocationDao {

	public Reallocation getReallocationByEmployeeId(String employeeId);

}
