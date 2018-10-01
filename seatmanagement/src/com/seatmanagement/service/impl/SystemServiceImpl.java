package com.seatmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@Transactional
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
	public boolean addOrUpdateSystem(Systems system,UUID systemId) {
		/*Employee emp = new Employee();
		system.setEmployee(emp);*/
		boolean f = false;
		if(Objects.isNull(systemId)) {
			Systems updaatedSystem = genericDao.getById(system, systemId);
			updaatedSystem.setAllotmentStatus(system.getAllotmentStatus());
			updaatedSystem.setNetworkType(system.getNetworkType());
			updaatedSystem.setOperatingSystem(system.getOperatingSystem());
			updaatedSystem.setSystemName(system.getSystemName());
			updaatedSystem.setSystemType(system.getSystemType());
			updaatedSystem.setEmployee(system.getEmployee());
			f = genericDao.saveOrUpdate(updaatedSystem);
		}else {
		f = genericDao.saveOrUpdate(system);
		}
		return f;
	}

	

	public  Systems getSystem(String request) {
		return systemDao.getSystem(request);
	}

	@Override
	public Systems getSystemBySystemName(String systemName) {
		
		return systemDao.getSystemId(systemName);
	}



	
}
