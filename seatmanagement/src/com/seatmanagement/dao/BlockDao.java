package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Block;

/**
 * 
 * @author Harshanaa Ramdas
 *
 */
public interface BlockDao {

	/**
	 * 
	 * @param floorId
	 * @return
	 */
	

	public List<Block> getBlocksByFloorId(UUID floorId);
	
	public List<Block> getBlocksByBlockType(String blockType,UUID floorId);
	
	
	
	
}
