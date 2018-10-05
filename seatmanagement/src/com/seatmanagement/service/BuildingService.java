package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Building;

/**
 * 
 * @author Adithya Prabhu
 * 
 *         Provides interface for all business logic related processing to
 *         'Building' model object
 *
 */
public interface BuildingService {

	public Building saveOrUpdate(Building building,UUID organisationId);

	public List<Building> getAll();

	public Building getById(Building building, UUID buildingId);

	public boolean delete(Building building);

}
