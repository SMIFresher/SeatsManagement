package com.seatmanagement.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Seating;
import com.seatmanagement.service.SeatingService;

@Service
public class SeatingServiceImpl implements SeatingService {

	@Autowired
	private SeatingDao seatingDao;
	
	@Autowired
	GenericDao<Seating> genericDao;
	
	@Autowired
	GenericDao<Block> genericDaoBlock;
	public List<Seating> getSeatingByBlockId(Seating seating, UUID block_id) {
		return seatingDao.getAll(seating,block_id);
	}
	
	@Override
	public boolean addOrUpdateSeating(Seating seating,UUID blockID) {
		Seating newSeating = seating;
		Block newBlock = new Block();
		newBlock=genericDaoBlock.getById(newBlock, blockID);
		newSeating.setBlock(newBlock);
		return genericDao.saveOrUpdate(newSeating);
	}
	
	@Override
	public List<Seating> getAllSeating() {
		Seating seating = new Seating();
		@SuppressWarnings("unchecked")
		List<Seating> list = seatingDao.getAllSeating();
		return list;
	}

	@Override
	public void deleteSeatingByBlockId(UUID blockId) {
		// unreference children(seatingdetails) and delete
		
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
}
