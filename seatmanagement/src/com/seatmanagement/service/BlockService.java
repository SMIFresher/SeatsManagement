package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Block;

/**
 * 
 * @author Adithya Prabhu
 * 
 *         Provides interface for all business logic related processing to
 *         'Building' model object
 *
 */
public interface BlockService {

	public boolean saveOrUpdate(Block block,UUID floor_id);

	public List<Block> getAll();

	public Block getById(Block block, UUID blockId);

	/*public boolean delete(Building building);
*/
}
