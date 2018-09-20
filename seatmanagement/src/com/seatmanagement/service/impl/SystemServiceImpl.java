package com.seatmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

public class SystemServiceImpl implements SystemService{

	@Autowired
	GenericDao<Systems> genericDao;
	
	@Override
	public List<Systems> getAllSystems() {
		Systems system = null;
		List<Systems> systems = genericDao.getAll(system);
		return systems;
	}

	@Override
	public Systems getById(Systems system, int System_id) {
		return  genericDao.getById(system,System_id);
	}

	@Override
	public boolean delete(Systems system) {
		return genericDao.delete(system);
	}



	@Override
	public boolean addOrUpdateSystem(Systems system) {
		// TODO Auto-generated method stub
		return genericDao.saveOrUpdate(system);
	}

	
}
