package com.seatmanagement.dao.impl;

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
import com.seatmanagement.model.Reallocation;

@Transactional
public class ReallocationDaoImpl implements ReallocationDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ReallocationDaoImpl.class);

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Reallocation getReallocationByEmployeeId(String employeeId) {
		
		logger.info(
				"DAO: ReallocationDaoImpl Method : getReallocationByEmployeeId started at : "
						+ LocalDateTime.now());
		
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("employeeId", employeeId));

		Reallocation reallocation = (Team) hibernateTemplate.findByCriteria(criteria);

		return team;*/
		
		logger.info(
				"DAO: ReallocationDaoImpl Method : getReallocationByEmployeeId ended at : "
						+ LocalDateTime.now());
		return null;
		
		
	}

	@Override
	public void deleteReallocationByBlockId(UUID blockId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("block.blockId", blockId));

		Reallocation reallocation = (Reallocation) hibernateTemplate.findByCriteria(criteria);
		
		// Scenario 1: No reallocation in DB
		if(Objects.isNull(reallocation)) {
			
		}
		// Scenario 2: Reallocation present in DB
		else {
			hibernateTemplate.delete(reallocation);
		}
	}

	@Override
	public List<Reallocation> getReallocationsByBlockId(UUID blockId) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("block.blockId", blockId));

		List<Reallocation> reallocations = (List<Reallocation>) hibernateTemplate.findByCriteria(criteria);
		
		return reallocations;
	}

}
