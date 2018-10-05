package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Seating;

public interface SeatingService {

	public List<Seating> getSeatingByBlockId(Seating seating, UUID seating_id);

	public Seating addOrUpdateSeating(Seating seating, UUID blockID);

	public List<Seating> getAllSeating();

	public void deleteSeatingByBlockId(UUID uuid) throws BusinessException;

	public List<Object> getAllSeatingWithAxisByFloor(UUID floorId) throws BusinessException;

	List<Object> getAllSeatingWithAxisByFloorLead(UUID floorId) throws BusinessException;
}
