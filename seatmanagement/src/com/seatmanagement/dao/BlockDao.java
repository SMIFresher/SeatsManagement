package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
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
	 * @throws BusinessException 
	 */
	

	public List<Block> getBlocksByFloorId(UUID floorId) throws BusinessException;
	
	public List<Block> getBlocksByBlockType(String blockType,UUID floorId) throws BusinessException;
	
	
	
	
}
