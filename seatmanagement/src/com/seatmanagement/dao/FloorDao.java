package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;
import com.seatmanagement.model.Floor;

public interface FloorDao {

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

	public List<Floor> getFloorType(UUID buildingId);

}
