package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.model.Reallocation;

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

}
