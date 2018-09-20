package com.seatmanagement.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.model.Reallocation;

public class ReallocationDaoImpl implements ReallocationDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Reallocation getReallocationByEmployeeId(String employeeId) {
		return null;
		
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Reallocation.class);
		criteria.add(Restrictions.eq("employeeId", employeeId));

		Reallocation reallocation = (Team) hibernateTemplate.findByCriteria(criteria);

		return team;*/
	}

}
