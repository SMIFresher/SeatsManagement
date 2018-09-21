package com.seatmanagement.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Systems;
import com.seatmanagement.model.Team;

@Transactional
public class SystemDaoImpl implements SystemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Systems getSystemByEmployeeId(UUID EmployeeId) {
	
		return (Systems) hibernateTemplate.findByCriteria(
				DetachedCriteria.forClass(Systems.class)
		        .add(Restrictions.eq("employee_id",EmployeeId)));
		
		
	}

	

}
