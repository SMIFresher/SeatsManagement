package com.seatmanagement.service.impl;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.BlockService;
import com.seatmanagement.service.ReallocationService;
import com.seatmanagement.service.SeatingService;


/**
 * 
 * @author Adithya Prabhu
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Building' model object
 *
 */
@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	GenericDao<Block> genericDao;
	@Autowired
	GenericDao<Floor> genericDaoFloor;
	@Autowired
	BlockDao blockDao;
	
	@Autowired
	SeatingService	seatingService;
	
	@Autowired
	ReallocationService reallocationService;

	public boolean saveOrUpdate(Block block,UUID floor_id) {
		
		Block newBlock = new Block();
		Floor floor =  new Floor();
		 newBlock.setBlockName(block.getBlockName());
		 newBlock.setBlockType(block.getBlockType());
		 newBlock.setBlockCapacity(block.getBlockCapacity());
		 newBlock.setBlockDescription(block.getBlockDescription());
		 newBlock.setBlockMeasurement(block.getBlockMeasurement());
		 newBlock.setSquarefeet(block.getSquarefeet());
		 if(Objects.nonNull(floor_id)) {
			
			 floor = genericDaoFloor.getById(floor, floor_id);
		 }
		 if(Objects.isNull(floor)) {
			 throw new BusinessException("Floor is not avaliable");
		 }
		
		newBlock.setFloor(floor);
		return genericDao.saveOrUpdate( newBlock);

	}

	
	public List<Block> getAll() {
		return blockDao.getAll();
	}

	public Block getById(Block block, UUID blockId) {
		return genericDao.getById(block, blockId);
	}


	@Override
	public void deleteBlocksByFloorId(UUID floorId) {
		
		List<Block> blocks = blockDao.getBlocksByFloorId(floorId);
		
		// No blocks in floor scenario
		if(Objects.isNull(blocks) || blocks.isEmpty()) {
			
		}
		// Blocks present in floor scenario
		else {
			for(Block block:blocks) {
				// delete seating
				seatingService.deleteSeatingByBlockId(block.getBlockId());
				
				// delete reallocation
				reallocationService.deleteReallocationsByBlockId(block.getBlockId());
			}
		}
		
	}
	
	@Override
	public List<Block> getBlocksByFloorId(UUID floorId) {
		List<Block> blocks = blockDao.getBlocksByFloorId(floorId);
		return blocks;
	}
	
	@Override
	public List<Block> getBlocksByBlockType(String blockType,UUID floorId) {
		List<Block> blocks = blockDao.getBlocksByBlockType(blockType,floorId);
		return blocks;
	}

	public boolean delete(Block block) {
		return genericDao.delete(block);

	}

}
