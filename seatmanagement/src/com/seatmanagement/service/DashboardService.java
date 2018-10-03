package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

public interface DashboardService {

	List<Object> getAllDashboardCount();

	List<Object> getAllCompanyDetailsCount();
	
	List<Object> getAllFloorDetailsCount(UUID buildingId);
	
	List<Object> getAllBlockDetailsCount(UUID floorId);

}
