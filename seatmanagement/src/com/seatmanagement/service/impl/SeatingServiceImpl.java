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
		logger.info("Service: SeatingServiceImpl Method : getSeatingByBlockId request processing started at : " + LocalDateTime.now());
		return seatingDao.getAll(seating,block_id);
	}
	
	@Override
	public boolean addOrUpdateSeating(Seating seating,UUID blockID) {
		logger.info("Service: SeatingServiceImpl Method : addOrUpdateSeating request processing started at : " + LocalDateTime.now());
		Seating newSeating = seating;
		Block newBlock = new Block();
		newBlock=genericDaoBlock.getById(newBlock, blockID);
		newSeating.setBlock(newBlock);
		return genericDao.saveOrUpdate(newSeating);
	}
	
	@Override
	public List<Seating> getAllSeating() {
		logger.info("Service: SeatingServiceImpl Method : getAllSeating request processing started at : " + LocalDateTime.now());
		Seating seating = new Seating();
		@SuppressWarnings("unchecked")
		List<Seating> list = seatingDao.getAllSeating();
		return list;
	}

	@Override
	public void deleteSeatingByBlockId(UUID blockId) {
		// unreference children(seatingdetails) and delete
		logger.info("Service: SeatingServiceImpl Method : deleteSeatingByBlockId request processing started at : " + LocalDateTime.now());
		List<Seating> seatings = seatingDao.getSeatingByBlockId(blockId);
		
		// Scenario: No seating mapped yet
		if(Objects.isNull(seatings)|| seatings.isEmpty()) {
			
		}
		// Scenario: Seating mapped
		else {
			// Scenario a: More than 1 seating mapped to a block
			if(seatings.size()>1) {
				throw new BusinessException("More than one Seating mapped to a Block");
			}
			// Scenario b: Only 1 seating mapped to a block
			else {
				genericDao.delete(seatings.get(0));
			}
		}
		
		
	}

	@Override
	public List<Object> getAllSeatingWithAxisByFloor(UUID floorId) {
		
		logger.info("Service: SeatingServiceImpl Method : getAllSeatingWithAxis request processing started at : " + LocalDateTime.now());
		List<Object> object = new ArrayList<>();                 
		List<Seating> list = seatingDao.getAllSeating();
	    List<Seating> seatingListByFloor = list.stream().filter(Objects::nonNull).filter(p->p.getBlock().getFloor().getFloorId().equals(floorId)).collect(Collectors.toList());
	
	    if(CollectionUtils.isEmpty(seatingListByFloor)) {
			throw new BusinessException(Constant.NO_RECORD_FOUND_FOR_SEATINGS);
		}
		 
		 seatingListByFloor.stream().filter(Objects::nonNull).forEach(k->{
			Properties properties = new Properties();
			properties.put("x",k.getX_axis());
			properties.put("y",k.getY_axis());
			properties.put("note","<center> <a href> BlockId = " + k.getBlock().getBlockId() + " </a> </center> <br> <p> Seat Oocupied: " + k.getSeat_occupied()+" </p> <br> <p> Block Capacity : "+ k.getBlock().getBlockCapacity()+" </p>");
		 object.add(properties);	
		});
		      
		
		return object;
	}
	

	
	
	
}
