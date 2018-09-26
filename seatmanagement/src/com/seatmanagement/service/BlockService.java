package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;

/**
 * 
 * @author Adithya Prabhu
 * 
 *         Provides interface for all business logic related processing to
 *         'Building' model object
 *
 */
public interface BlockService {

	public boolean saveOrUpdate(Block block,UUID floorId);

	public List<Block> getAll();

	public Block getById(Block block, UUID blockId);

	public void deleteBlocksByFloorId(UUID floorId);

	public boolean delete(Block block);
	
	public List<Block> getBlocksByFloorId(UUID floorId);
	
	public List<Block> getBlocksByBlockType(String blockType,UUID floorId);

}
