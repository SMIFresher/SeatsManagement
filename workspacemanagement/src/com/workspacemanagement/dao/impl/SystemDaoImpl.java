package com.workspacemanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.workspacemanagement.dao.SystemDao;
import com.workspacemanagement.model.Systems;

@Transactional
public class SystemDaoImpl implements SystemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	

	@SuppressWarnings("deprecation")
	@Override
	public Systems getSystem(String request) {
		
		Systems system=null;
	
		DetachedCriteria criteria = DetachedCriteria.forClass(Systems.class);
		criteria.createAlias("employee","employee",CriteriaSpecification.LEFT_JOIN);
	
		criteria.add(Restrictions.disjunction());
		criteria.add(Restrictions.or
				(Restrictions.eq("systemName",request),Restrictions.eq("employee.employeeRoll",request),Restrictions.like("employee.firstName",request)));

		system= (Systems) hibernateTemplate.findByCriteria(criteria).get(0);
		
		
		return system;
	}

	
	

	

}
