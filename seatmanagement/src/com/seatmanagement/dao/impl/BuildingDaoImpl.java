package com.seatmanagement.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.model.Building;

@Transactional
@Repository
public class BuildingDaoImpl implements BuildingDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void saveBuilding(Building building) {
		
	}
}
