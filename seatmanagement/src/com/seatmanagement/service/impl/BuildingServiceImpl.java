package com.seatmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.Building;
import com.seatmanagement.service.BuildingService;

/**
 * 
 * @author Adithya Prabhu
 * 
 * This class provides implementation for all business logic 
 * related processing to 'Building' model object
 *
 */
@Service
public  class BuildingServiceImpl implements BuildingService{
	

	@Autowired
    GenericDao<Building> genericDao;
	@Autowired
	BuildingDao buildingDao;
	
	public boolean saveOrUpdate (Building building) {
		return genericDao.saveOrUpdate(building);
		

	}
	
	@SuppressWarnings("unchecked")
	public List<Building> getAll() {
		return buildingDao.getAll( );
	}

	
	
		
	
	
	public boolean delete(Building building) {
		return genericDao.delete(building);
		
	
}

	

	
	
}
