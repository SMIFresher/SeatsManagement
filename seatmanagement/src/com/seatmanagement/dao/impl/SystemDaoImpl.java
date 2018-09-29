package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Systems;
import com.seatmanagement.model.Team;

@Transactional
public class SystemDaoImpl implements SystemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Autowired
	GenericDao<Systems> genericDao;

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

	@Override
	public Systems getSystemId(String systemName) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("systemName",systemName));

		Systems system = (Systems) hibernateTemplate.findByCriteria(criteria);
		return system;
	}





	
	

	

}
