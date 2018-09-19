package com.seatmanagement.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.seatmanagement.dao.SystemDao;

public class SystemDaoImpl implements SystemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public List<System> getAllSystems() {
		List<System> systems = (List<System>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(System.class));
		return systems;
	}

}
