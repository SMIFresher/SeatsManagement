package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Seating;

/**
 * 
 * @author Sithaara Sivasankar
 * 
 *         This class provides implementation for all Database related
 *         activities to 'Seating' model object
 *
 */
@Transactional
@Repository
public class SeatingDaoImpl implements SeatingDao {

	private static final Logger logger = LoggerFactory.getLogger(SeatingDaoImpl.class);
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Seating> getAll(Seating seating, UUID block_id){
		logger.info("DAO: SeatingDaoImpl Method : getAll started at : " + LocalDateTime.now());
		// List<Seating> seatingList = new ArrayList<>();
		Block block = new Block();
		block.setBlockId(block_id);
		/*
		 * seatingList = (List<Seating>)
		 * hibernateTemplate.find("From Seating  where block_id = '" +block_id + "'" );
		 * return seatingList;
		 * 
		 */
		DetachedCriteria criteria = DetachedCriteria.forClass(Seating.class);
		criteria.add(Restrictions.eq("block", block));
		List<Seating> seat = null;
		try {
			seat = (List<Seating>) hibernateTemplate.findByCriteria(criteria);
		}catch(Exception e) {
			throw new ApplicationException("Error while retreiving seating records", e);
		}
		logger.info("DAO: SeatingDaoImpl Method : getAll ended at : " + LocalDateTime.now());
		return seat;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Seating> getAllSeating() {
		logger.info("DAO: SeatingDaoImpl Method : getAllSeating started at : " + LocalDateTime.now());
		List<Seating> seatingList = null;
		try {
			seatingList = (List<Seating>) hibernateTemplate.find("From Seating");
		}catch(Exception e) {
			throw new ApplicationException("Error while reteiving Seating records", e);
		}
		logger.info("DAO: SeatingDaoImpl Method : getAllSeating ended at : " + LocalDateTime.now());
		return seatingList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seating> getSeatingByBlockId(UUID blockId) {
		logger.info("DAO: SeatingDaoImpl Method : getSeatingByBlockId started at : " + LocalDateTime.now());
		DetachedCriteria criteria = DetachedCriteria.forClass(Seating.class);
		criteria.add(Restrictions.eq("block.blockId", blockId));
		
		List<Seating> seatings = null;
		try {
			seatings = (List<Seating>) hibernateTemplate.findByCriteria(criteria);
		}catch(Exception e) {
			throw new ApplicationException("Error while reteiving Seating record", e);
		}
		logger.info("DAO: SeatingDaoImpl Method : getSeatingByBlockId ended at : " + LocalDateTime.now());
		return seatings;
	}

}
