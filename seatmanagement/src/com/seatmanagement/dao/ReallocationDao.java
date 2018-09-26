package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Reallocation;

public interface ReallocationDao {

	public Reallocation getReallocationByEmployeeId(String employeeId);

	public void deleteReallocationByBlockId(UUID blockId);

	public List<Reallocation> getReallocationsByBlockId(UUID blockId);

}
