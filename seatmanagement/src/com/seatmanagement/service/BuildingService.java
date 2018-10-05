package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Building;

/**
 * 
 * @author SaiEswari
 * 
 *         Provides interface for all business logic related processing to
 *         'Building' model object
 *
 */

public interface BuildingService {

	/**
	 * 
	 * @param building
	 * @param organisationId
	 * @return
	 */
	public Building saveOrUpdate(Building building, UUID organisationId);

	/**
	 * 
	 * @return
	 */
	public List<Building> getAll();

	/**
	 * 
	 * @param building
	 * @param buildingId
	 * @return
	 */
	public Building getById(Building building, UUID buildingId);

	/**
	 * 
	 * @param building
	 * @return
	 */
	public boolean delete(Building building);

}
