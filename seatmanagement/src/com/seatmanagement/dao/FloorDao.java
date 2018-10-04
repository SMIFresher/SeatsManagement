package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Floor;

public interface FloorDao {

	/*public List<Floor> getAll();*/

	public List<Floor> getFloorsByBuildingId(UUID buildingId);
	
	public List<Floor> getFloorType(UUID buildingId);

}
