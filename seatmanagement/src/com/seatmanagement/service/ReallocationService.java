package com.seatmanagement.service;

import com.seatmanagement.model.Reallocation;

public interface ReallocationService {

	public Reallocation getReallocationByEmployeeId(String employeeId);

	public void saveReallocation(Reallocation reallocation);

	public void updateReallocation(Reallocation reallocation);

	public void deleteReallocation(Reallocation reallocation);

}
