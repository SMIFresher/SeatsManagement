package com.seatmanagement.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Reallocation;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class provides implementation for all Database related
 *         activities to 'Reallocation' model object
 *
 */
@Transactional
public class ReallocationDaoImpl implements ReallocationDao {

	private static final Logger logger = LoggerFactory.getLogger(ReallocationDaoImpl.class);

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Reallocation getReallocationByEmployeeId(String employeeId) {

		logger.info(
				"DAO: ReallocationDaoImpl Method : getReallocationByEmployeeId started at : " + LocalDateTime.now());

		/*
		 * DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		 * criteria.add(Restrictions.eq("employeeId", employeeId));
		 * 
		 * Reallocation reallocation = (Team)
		 * hibernateTemplate.findByCriteria(criteria);
		 * 
		 * return team;
		 */

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationByEmployeeId ended at : " + LocalDateTime.now());
		return null;

	}

	@Override
	public void deleteReallocationByBlockId(UUID blockId) {

		logger.info(
				"DAO: ReallocationDaoImpl Method : deleteReallocationByBlockId started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("block.blockId", blockId));

		Reallocation reallocation = null;

		try {
			reallocation = (Reallocation) hibernateTemplate.findByCriteria(criteria);
		} catch (Exception e) {
			throw new ApplicationException("Error while retreiving reallocation record", e);
		}

		// Scenario 1: No reallocation in DB
		if (Objects.isNull(reallocation)) {
			throw new ApplicationException("Reallocation record not found");
		}
		// Scenario 2: Reallocation present in DB
		else {
			try {
				hibernateTemplate.delete(reallocation);
			} catch (Exception e) {
				throw new ApplicationException("Error while deleting reallocation record", e);
			}
		}

		logger.info("DAO: ReallocationDaoImpl Method : deleteReallocationByBlockId ended at : " + LocalDateTime.now());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reallocation> getReallocationsByBlockId(UUID blockId) {

		List<Reallocation> reallocations = null;

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationsByBlockId started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("block.blockId", blockId));

		try {
			reallocations = (List<Reallocation>) hibernateTemplate.findByCriteria(criteria);
		} catch (Exception e) {
			throw new ApplicationException("Error while retreiving reallocation records", e);
		}

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationsByBlockId ended at : " + LocalDateTime.now());

		return reallocations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reallocation> getReallocationsByStatus(String reallocationStatus) {

		List<Reallocation> reallocations = null;

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationsByBlockId started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("reallocationStatus", reallocationStatus));

		try {
			reallocations = (List<Reallocation>) hibernateTemplate.findByCriteria(criteria);
		} catch (Exception e) {
			throw new ApplicationException("Error while retreiving reallocation records", e);
		}

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationsByBlockId ended at : " + LocalDateTime.now());

		return reallocations;
	}

	@Override
	public List<Reallocation> getReallocationByRequestDate(LocalDate reallocationRequestedDate) {

		List<Reallocation> reallocations = null;

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationByRequestDate started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("reallocationRequestedDate", reallocationRequestedDate));

		try {
			reallocations = (List<Reallocation>) hibernateTemplate.findByCriteria(criteria);
		} catch (Exception e) {
			throw new ApplicationException("Error while retreiving reallocation records", e);
		}

		logger.info("DAO: ReallocationDaoImpl Method : getReallocationByRequestDate ended at : " + LocalDateTime.now());

		return reallocations;
	}

}
