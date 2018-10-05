package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Systems;

/**
 * 
 * @author Harshanaa Ramdas
 * 
 *         Provides interface for all business logic related processing to
 *         'Block' model object
 *
 */
public interface BlockService {

	public Block saveOrUpdate(Block block, UUID floorId, List<UUID> utilitiesUUIDs) throws BusinessException;

	public List<Block> getAll();

	public Block getById(Block block, UUID blockId);

	public void deleteBlocksByFloorId(UUID floorId) throws BusinessException;

	public void delete(Block block) throws BusinessException;

	public List<Block> getBlocksByFloorId(UUID floorId) throws BusinessException;

	public List<Block> getBlocksByBlockType(String blockType, UUID floorId) throws BusinessException;

}
