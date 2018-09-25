package com.seatmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.model.Block;


/**
 * 
 * @author Adithya Prabhu
 * 
 *         This class provides implementation for all Database related
 *         activities to 'Building' model object
 *
 */
@Transactional
@Repository
public class BlockDaoImpl implements BlockDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 
	 * Saves a 'Building' model in DB
	 * 
	 * @param building
	 */
	/*
	 * @Override public void saveBuilding(Building building) {
	 * 
	 * }
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Block> getAll() {
		List<Block> blockList = new ArrayList<>();
		blockList = (List<Block>) hibernateTemplate.find("From Block");
		return blockList;
	}
	

}