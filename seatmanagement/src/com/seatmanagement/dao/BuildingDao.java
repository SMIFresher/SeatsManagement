package com.seatmanagement.dao;

import com.seatmanagement.model.Building;

/**
 * 
 * @author Adithya Prabhu
 * 
 * Provides interface for all Database related activities to 'Building' model
 * object
 *
 */
public interface BuildingDao {
	
	/**
	 * 
	 * Saves a 'Building' model in DB
	 * 
	 * @param building
	 */
	public void saveBuilding(Building building);
}
