package com.seatmanagement.service.impl;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Systems;
import com.seatmanagement.model.Utilities;
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
	GenericDao<Utilities> genericDaoUtility;
	@Autowired
	BlockDao blockDao;
	
	@Autowired
	SeatingService	seatingService;
	
	@Autowired
	ReallocationService reallocationService;

	public boolean saveOrUpdate(Block block,UUID floor_id,List<UUID> utilitiesUUIDs) throws BusinessException {
		
	
			Floor floor =  new Floor();
			/* if(Objects.nonNull(floor_id)) {
				
				 floor = genericDaoFloor.getById(floor, floor_id);
			 }
			 if(Objects.isNull(floor)) {
				 throw new BusinessException("Floor is not avaliable");
			 }
			
			block.setFloor(floor);
			return genericDao.saveOrUpdate(block);*/
			
		// Floor Mapping
				// Scenario 1: floor id is null
				if(Objects.isNull(floor_id)) {
					
				}
				// Scenario 2: floor id is present
				else {
					floor = genericDaoFloor.getById(floor, floor_id);
					block.setFloor(floor);
				}
				
				// utilities mapping
				// Scenario 1: No utilities IDs in request
				if(Objects.isNull(utilitiesUUIDs) ||utilitiesUUIDs.isEmpty()){
					
				}
				// Scenario 2: utilities IDs present in request
				else{
					Set<Utilities> utilities = new HashSet<Utilities> ();
					for(UUID utilityId : utilitiesUUIDs){
						Utilities utility = new Utilities();
						utility = (Utilities) genericDaoUtility.getById(utility, utilityId);
						
						utilities.add(utility);
					}
					
					block.setUtilities(utilities);
				}
				
				return genericDao.saveOrUpdate(block);
			

		}

	
	public List<Block> getAll() {
		Block block =new Block();
		return genericDao.getAll(block);
	}

	public Block getById(Block block, UUID blockId) {
		return genericDao.getById(block, blockId);
	}


	@Override
	public void deleteBlocksByFloorId(UUID floorId) throws BusinessException {
		
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

	public void delete(Block block) throws BusinessException {
		
		block = genericDao.getById(block, block.getBlockId());
		
		// Scenario 1: Block Not present
		if(Objects.isNull(block)) {
			
		}
		// Scenario 2: Block present
		else {
			// delete seating
			seatingService.deleteSeatingByBlockId(block.getBlockId());

			// delete reallocation
			reallocationService.deleteReallocationsByBlockId(block.getBlockId());
			
			// delete block
			genericDao.delete(block);
		}
	}
	

}
