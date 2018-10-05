package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.controller.UtilitiesController;
import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Systems;

/**
 * 
 * @author Harshanaa Ramdas
 * 
 *         This class provides implementation for all Database related
 *         activities to 'Block' model object
 *
 */
@Transactional
@Repository
public class BlockDaoImpl implements BlockDao {

	private static final Logger logger = LoggerFactory.getLogger(BlockDaoImpl.class);
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Block> getAll() {
		logger.info("DAO: BlockDaoImpl Method : listAllBlock request processing started at : " + LocalDateTime.now());
		List<Block> blockList = new ArrayList<>();
		blockList = (List<Block>) hibernateTemplate.find("From Block");
		logger.info("DAO: BlockDaoImpl Method : saveBlock response sent at : " + LocalDateTime.now());
		return blockList;
	}

	@Override
	public List<Block> getBlocksByFloorId(UUID floorId) {
		logger.info(
				"DAO: BlockDaoImpl Method : listBlockByFloorId request processing started at : " + LocalDateTime.now());
		DetachedCriteria criteria = DetachedCriteria.forClass(Block.class);
		criteria.add(Restrictions.eq("floor.floorId", floorId));
		List<Block> blocks = (List<Block>) hibernateTemplate.findByCriteria(criteria);
		logger.info("DAO: BlockDaoImpl Method : listBlockByFloorId response sent at : " + LocalDateTime.now());
		return blocks;
	}

	/**
	 * For loading block details with respect to the blockType and floorId
	 */

	@Override
	public List<Block> getBlocksByBlockType(String blockType, UUID floorId) {
		logger.info("DAO: BlockDaoImpl Method : listBlockByBlockType request processing started at : "
				+ LocalDateTime.now());
		DetachedCriteria criteria = DetachedCriteria.forClass(Block.class);
		criteria.createAlias("floor", "floor", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.disjunction());
		criteria.add(
				Restrictions.and(Restrictions.eq("blockType", blockType), Restrictions.eq("floor.floorId", floorId)));
		List<Block> blocks = (List<Block>) hibernateTemplate.findByCriteria(criteria);
		logger.info("DAO: BlockDaoImpl Method : listBlockByBlockType response sent at : " + LocalDateTime.now());
		return blocks;
	}

}