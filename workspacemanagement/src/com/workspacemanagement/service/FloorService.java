package com.workspacemanagement.service;

import java.util.List;
import java.util.UUID;

import com.workspacemanagement.model.Floor;

public interface FloorService {

   

	public boolean saveOrUpdate(Floor floor,UUID buildingId);
	public List<Floor> getAll();
	public Floor getById(Floor floor,UUID floor_id);
	/*public boolean delete(Floor floor);*/
	public void deleteFloorById(UUID floorId);
	public List<Floor> getFloorsByBuildingId(UUID buildingId);
	public List<Floor> getFloorTypeByBuildingId(UUID buildingId);


	
    }
