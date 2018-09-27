package com.seatmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Systems;


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

	@Override
	public List<Block> getBlocksByFloorId(UUID floorId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Block.class);
		criteria.add(Restrictions.eq("floor.floorId", floorId));

		List<Block> blocks = (List<Block>) hibernateTemplate.findByCriteria(criteria);
		return blocks;
	}
	
	@Override
	public List<Block> getBlocksByBlockType(String blockType,UUID floorId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Block.class);
		criteria.createAlias("floor","floor",CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.disjunction());
		criteria.add(Restrictions.and
				(Restrictions.eq("blockType",blockType),Restrictions.eq("floor.floorId",floorId)));
		List<Block> blocks = (List<Block>) hibernateTemplate.findByCriteria(criteria);
		return blocks;
	}
	

}