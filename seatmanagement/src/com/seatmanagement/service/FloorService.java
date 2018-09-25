package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Floor;

public interface FloorService {

   

	public boolean saveOrUpdate(Floor floor,UUID buildingId);
	public List<Floor> getAll();
	public Floor getById(Floor floor,UUID floor_id);
	/*public boolean delete(Floor floor);*/


	
    }
