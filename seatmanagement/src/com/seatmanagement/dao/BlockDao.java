package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Block;

/**
 * 
 * @author Adithya Prabhu
 * 
 *         Provides interface for all Database related activities to 'Building'
 *         model object
 *
 */
public interface BlockDao {

	/**
	 * 
	 * Saves a 'Building' model in DB
	 * 
	 * @param building
	 */
	// public void saveBuilding(Building building);

	public List<Block> getAll();

	public List<Block> getBlocksByFloorId(UUID floorId);
	
	
}
