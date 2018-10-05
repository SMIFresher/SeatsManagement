package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.ApplicationException;
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
 * @author Harshanaa Ramdas
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Block' model object
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
	SeatingService seatingService;

	@Autowired
	ReallocationService reallocationService;
	private static final Logger logger = LoggerFactory.getLogger(BlockServiceImpl.class);

	public Block saveOrUpdate(Block block, UUID floor_id, List<UUID> utilitiesUUIDs) throws BusinessException {

		logger.info(
				"Service: BlockServiceImpl Method : saveBlock request processing started at : " + LocalDateTime.now());
		Floor floor = new Floor();

		// Floor Mapping
		// Scenario 1: floor id is null
		if (Objects.isNull(floor_id)) {
			throw new BusinessException("Please select a floor");
		}
		// Scenario 2: floor id is present
		else {
			floor = genericDaoFloor.getById(floor, floor_id);
			if(Objects.isNull(floor)) {
				throw new ApplicationException("Floor record not found");
			}
			block.setFloor(floor);
		}

		// utilities mapping
		// Scenario 1: No utilities IDs in request
		if (Objects.isNull(utilitiesUUIDs) || utilitiesUUIDs.isEmpty()) {

		}
		// Scenario 2: utilities IDs present in request
		else {
			Set<Utilities> utilities = new HashSet<Utilities>();
			for (UUID utilityId : utilitiesUUIDs) {
				Utilities utility = new Utilities();
				utility = (Utilities) genericDaoUtility.getById(utility, utilityId);

				if(Objects.isNull(utility)) {
					throw new ApplicationException("Utility record not found");
				}
				utilities.add(utility);
			}

			block.setUtilities(utilities);
		}

		Block result = genericDao.saveOrUpdate(block);
		logger.info("Service: BlockServiceImpl Method : saveBlock response sent at : " + LocalDateTime.now());
		return result;

	}

	public List<Block> getAll() {
		logger.info("Service: BlockServiceImpl Method : listAllBlock request processing started at : "
				+ LocalDateTime.now());
		Block block = new Block();
		List result = genericDao.getAll(block);
		logger.info("Service: BlockServiceImpl Method : listAllBlock response sent at : " + LocalDateTime.now());
		return result;
	}

	public Block getById(Block block, UUID blockId) {
		logger.info("Service: BlockServiceImpl Method : listAllBlockByBlockId request processing started at : "
				+ LocalDateTime.now());
		Block result = genericDao.getById(block, blockId);
		if(Objects.isNull(result)) {
			throw new ApplicationException("Block record not found");
		}
		logger.info(
				"Service: BlockServiceImpl Method : listAllBlockByBlockId response sent at : " + LocalDateTime.now());
		return result;
	}

	@Override
	public void deleteBlocksByFloorId(UUID floorId) throws BusinessException {

		List<Block> blocks = blockDao.getBlocksByFloorId(floorId);

		// No blocks in floor scenario
		if (Objects.isNull(blocks) || blocks.isEmpty()) {

		}
		// Blocks present in floor scenario
		else {
			for (Block block : blocks) {
				// delete seating
				seatingService.deleteSeatingByBlockId(block.getBlockId());

				// delete reallocation
				reallocationService.deleteReallocationsByBlockId(block.getBlockId());
			}
		}

	}

	@Override
	public List<Block> getBlocksByFloorId(UUID floorId) throws BusinessException {
		logger.info("Service: BlockServiceImpl Method : listAllBlockByFloorId request processing started at : "
				+ LocalDateTime.now());
		List<Block> blocks = blockDao.getBlocksByFloorId(floorId);
		logger.info(
				"Service: BlockServiceImpl Method : listAllBlockByFloorId response sent at : " + LocalDateTime.now());
		return blocks;
	}

	@Override
	public List<Block> getBlocksByBlockType(String blockType, UUID floorId) throws BusinessException {
		logger.info("Service: BlockServiceImpl Method : listAllBlockByBlockType request processing started at : "
				+ LocalDateTime.now());
		List<Block> blocks = blockDao.getBlocksByBlockType(blockType, floorId);
		logger.info(
				"Service: BlockServiceImpl Method : listAllBlockByBlockType response sent at : " + LocalDateTime.now());
		return blocks;
	}

	public void delete(Block block) throws BusinessException {

		block = genericDao.getById(block, block.getBlockId());

		// Scenario 1: Block Not present
		if (Objects.isNull(block)) {
			throw new ApplicationException("Block record not found");
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
