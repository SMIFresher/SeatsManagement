package com.workspacemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.dao.SystemDao;
import com.workspacemanagement.model.Systems;
import com.workspacemanagement.service.SystemService;

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

	

	public  Systems getSystem(String request) {
		return systemDao.getSystem(request);
	}

	
}
