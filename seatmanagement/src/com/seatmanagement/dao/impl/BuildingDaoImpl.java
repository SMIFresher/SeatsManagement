package com.seatmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.util.HibernateUtil;


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

	/**
	 * 
	 * Saves a 'Building' model in DB
	 * 
	 * @param building
	 */
	/*@Override
	public void saveBuilding(Building building) {
		
	}*/
@SuppressWarnings({ "unchecked", "deprecation" })
public List<Building> getAll() {
		List<Building> buildingList = new ArrayList<>();
		buildingList =  (List<Building>) hibernateTemplate.find("From Building");
		return buildingList;
	}
}
