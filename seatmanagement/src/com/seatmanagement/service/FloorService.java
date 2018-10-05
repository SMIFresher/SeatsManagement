package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Floor;

/**
 * 
 * @author M.karthika
 * 
 *         Provides interface for all business logic related processing to
 *         'Floor' model object
 *
 *
 */
public interface FloorService {

	/**
	 * 
	 * @param floor
	 * @param buildingId
	 * @return
	 * @throws BusinessException
	 */

	public Floor saveOrUpdateFloors(Floor floor, UUID buildingId) throws BusinessException;

	/**
	 * 
	 * @return Object
	 */

	public List<Floor> getAllFloors();

	/**
	 * 
	 * @param floor
	 * @param floor_id
	 * @return List<Floor>
	 */

	public Floor getByFloorId(Floor floor, UUID floor_id);

	/**
	 * 
	 * @param floorId
	 * @throws BusinessException
	 */

	public void deleteByFloorId(UUID floorId) throws BusinessException;

	/**
	 * 
	 * @param buildingId
	 * @return List<Floor>
	 */

	public List<Floor> getFloorsByBuildingId(UUID buildingId);

	/**
	 * 
	 * @param buildingId
	 * @return List<Floor>
	 */

	public List<Floor> getFloorTypeByBuildingId(UUID buildingId);

}
