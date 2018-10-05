package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.seatmanagement.controller.SeatingController;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Seating;
import com.seatmanagement.service.SeatingService;

/**
 * 
 * @author Sithaara Sivasankar
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Seating' model object
 */
@Service
public class SeatingServiceImpl implements SeatingService {

	private static final Logger logger = LoggerFactory.getLogger(SeatingServiceImpl.class);

	@Autowired
	private SeatingDao seatingDao;

	@Autowired
	GenericDao<Seating> genericDao;

	@Autowired
	GenericDao<Block> genericDaoBlock;

	public List<Seating> getSeatingByBlockId(Seating seating, UUID block_id) {
		logger.info("Service: SeatingServiceImpl Method : getSeatingByBlockId request processing started at : "
				+ LocalDateTime.now());
		List<Seating> list = seatingDao.getAll(seating, block_id);
		logger.info("Service: SeatingServiceImpl Method : getSeatingByBlockId response processing ended at : "
				+ LocalDateTime.now());
		return list;
	}

	@Override
	public Seating addOrUpdateSeating(Seating seating, UUID blockID) {

		logger.info("Service: SeatingServiceImpl Method : addOrUpdateSeating request processing started at : "
				+ LocalDateTime.now());
		Seating newSeating = seating;
		Block newBlock = new Block();
		newBlock = genericDaoBlock.getById(newBlock, blockID);
		newSeating.setBlock(newBlock);
		genericDao.saveOrUpdate(newSeating);
		logger.info("Service: SeatingServiceImpl Method : addOrUpdateSeating response processing ended at : "
				+ LocalDateTime.now());
		return newSeating;
	}

	@Override
	public List<Seating> getAllSeating() {
		logger.info("Service: SeatingServiceImpl Method : getAllSeating request processing started at : "
				+ LocalDateTime.now());
		Seating seating = new Seating();
		@SuppressWarnings("unchecked")
		List<Seating> list = genericDao.getAll(seating);
		logger.info("Service: SeatingServiceImpl Method : getAllSeating response processing ended at : "
				+ LocalDateTime.now());
		return list;
	}

	@Override
	public void deleteSeatingByBlockId(UUID blockId) throws BusinessException {
		// unreference children(seatingdetails) and delete
		logger.info("Service: SeatingServiceImpl Method : deleteSeatingByBlockId request processing started at : "
				+ LocalDateTime.now());
		List<Seating> seatings = seatingDao.getSeatingByBlockId(blockId);

		// Scenario: No seating mapped yet
		if (Objects.isNull(seatings) || seatings.isEmpty()) {

		}
		// Scenario: Seating mapped
		else {
			// Scenario a: More than 1 seating mapped to a block
			if (seatings.size() > 1) {
				throw new BusinessException("More than one Seating mapped to a Block");
			}
			// Scenario b: Only 1 seating mapped to a block
			else {
				genericDao.delete(seatings.get(0));
			}
		}
		logger.info("Service: SeatingServiceImpl Method : deleteSeatingByBlockId response processing ended at : "
				+ LocalDateTime.now());

	}

	@Override
	public List<Object> getAllSeatingWithAxisByFloor(UUID floorId) throws BusinessException {

		logger.info("Service: SeatingServiceImpl Method : getAllSeatingWithAxis request processing started at : "
				+ LocalDateTime.now());
		List<Object> object = new ArrayList<>();
		List<Seating> list = seatingDao.getAllSeating();
		List<Seating> seatingListByFloor = list.stream().filter(Objects::nonNull)
				.filter(p -> p.getBlock().getFloor().getFloorId().equals(floorId)).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(seatingListByFloor)) {
			throw new BusinessException(Constant.NO_RECORD_FOUND_FOR_SEATINGS);
		}

		seatingListByFloor.stream().filter(Objects::nonNull).forEach(k -> {
			Properties properties = new Properties();
			properties.put("x", k.getX_axis());
			properties.put("y", k.getY_axis());
			properties.put("note",
					"<center>  BlockType = " + k.getBlock().getBlockName() + "  <p> Seat Oocupied: "
							+ k.getSeat_occupied() + " ||  Block Capacity : " + k.getBlock().getBlockCapacity()
							+ " </p><a href='/seatmanagement/systems/EditView?seatingId=" + k.getSeatingId()
							+ "'><button class='btn btn-primary btn-sm'>view cabins</button></a></center>");
			object.add(properties);
		});
		logger.info("Service: SeatingServiceImpl Method : getAllSeatingWithAxis response processing ended at : "
				+ LocalDateTime.now());

		return object;
	}

	@Override
	public List<Object> getAllSeatingWithAxisByFloorLead(UUID floorId) throws BusinessException {

		logger.info("Service: SeatingServiceImpl Method : getAllSeatingWithAxis request processing started at : "
				+ LocalDateTime.now());
		List<Object> object = new ArrayList<>();
		List<Seating> list = seatingDao.getAllSeating();
		List<Seating> seatingListByFloor = list.stream().filter(Objects::nonNull)
				.filter(p -> p.getBlock().getFloor().getFloorId().equals(floorId)).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(seatingListByFloor)) {
			throw new BusinessException(Constant.NO_RECORD_FOUND_FOR_SEATINGS);
		}

		seatingListByFloor.stream().filter(Objects::nonNull).forEach(k -> {
			Properties properties = new Properties();
			properties.put("x", k.getX_axis());
			properties.put("y", k.getY_axis());
			properties.put("note",
					"<center>  BlockType = " + k.getBlock().getBlockName() + "  <p> Seat Oocupied: "
							+ k.getSeat_occupied() + " ||  Block Capacity : " + k.getBlock().getBlockCapacity()
							+ " </p><a href='/seatmanagement/systems/View?seatingId=" + k.getSeatingId()
							+ "'><button class='btn btn-primary btn-sm'>view cabins</button></a></center>");
			object.add(properties);
		});

		logger.info("Service: SeatingServiceImpl Method : getAllSeatingWithAxis response processing ended at : "
				+ LocalDateTime.now());

		return object;
	}

}
