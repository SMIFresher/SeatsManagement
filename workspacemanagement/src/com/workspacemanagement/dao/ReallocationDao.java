package com.workspacemanagement.dao;

import java.util.List;
import java.util.UUID;

import com.workspacemanagement.model.Reallocation;

public interface ReallocationDao {

	public Reallocation getReallocationByEmployeeId(String employeeId);

	public void deleteReallocationByBlockId(UUID blockId);

	public List<Reallocation> getReallocationsByBlockId(UUID blockId);

}
