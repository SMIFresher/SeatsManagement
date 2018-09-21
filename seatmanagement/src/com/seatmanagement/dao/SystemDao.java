package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Systems;

public interface SystemDao {

	public Systems getSystemByEmployeeId(UUID id);

}
