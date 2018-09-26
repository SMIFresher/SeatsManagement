package com.seatmanagement.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDao;
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
		Seating newSeating = new Seating();
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
		
		Seating seating = seatingDao.getSeatingByBlockId(blockId);
		
		// Scenario: No seating mapped yet
		if(Objects.isNull(seating)) {
			
		}
		// Scenario: Seating mapped
		else {
			
			// unreference all SeatingDetails
			seating.setSeatingDetails(new HashSet());
			
			genericDao.saveOrUpdate(seating);
		}
		
		genericDao.delete(seating);
	}
}
