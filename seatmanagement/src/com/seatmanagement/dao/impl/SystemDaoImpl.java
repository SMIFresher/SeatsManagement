package com.seatmanagement.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Systems;

@Transactional
public class SystemDaoImpl implements SystemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public List<Systems> getAllSystems() {
		List<Systems> systems = (List<Systems>) hibernateTemplate.loadAll(Systems.class);
		return systems;
	}

}
