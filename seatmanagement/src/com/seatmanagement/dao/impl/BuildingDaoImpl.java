package com.seatmanagement.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.model.Building;


/**
 * 
 * @author Adithya Prabhu
 * 
 * This class provides implementation for all Database related activities to 'Building'
 * model object
 *
 */
@Transactional
@Repository
public class BuildingDaoImpl implements BuildingDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 
	 * Saves a 'Building' model in DB
	 * 
	 * @param building
	 */
	@Override
	public void saveBuilding(Building building) {
		
	}
}
