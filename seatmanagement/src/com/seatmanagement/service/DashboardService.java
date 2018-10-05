package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Systems;

/**
 * 
 * @author SaiEswari
 *
 *         Provides interface for all business logic related processing to
 *         'DashboardService' model object
 */
public interface DashboardService {

	/**
	 * 
	 * @return
	 */
	List<Object> getAllDashboardCount();

	/**
	 * 
	 * @return
	 */
	List<Object> getAllCompanyDetailsCount();

	/**
	 * 
	 * @param buildingId
	 * @return
	 */
	List<Object> getAllFloorDetailsCount(UUID buildingId);

	/**
	 * 
	 * @param floorId
	 * @return
	 */
	List<Object> getAllBlockDetailsCount(UUID floorId);

	/**
	 * 
	 * @return
	 */
	List<Object> getAllOsCount();

}
