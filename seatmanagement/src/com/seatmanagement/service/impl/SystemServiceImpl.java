package com.seatmanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

public class SystemServiceImpl implements SystemService{

	@Autowired
	GenericDao<Systems> genericDao;
	
	@Autowired
	SystemDao systemDao;
	
	@Override
	public List<Systems> getAllSystems() {
		Systems system=new Systems();
		List<Systems> list = genericDao.getAll(system);
		return list;
	}

	@Override
	public Systems getById(Systems system, UUID System_id) {
		return genericDao.getById(system, System_id);
	}

	@Override
	public boolean delete(Systems system) {
		return genericDao.delete(system);
	}



	@Override
	public boolean addOrUpdateSystem(Systems system) {
		return genericDao.saveOrUpdate(system);
	}

	@Override
	public Systems getSystemByEmployeeId(UUID EmployeeId) {
		return systemDao.getSystemByEmployeeId(EmployeeId);
	}

	
}
