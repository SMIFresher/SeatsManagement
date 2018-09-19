package com.seatmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.service.SystemService;

public class SystemServiceImpl implements SystemService{

	@Autowired
	SystemDao systemDao;
	
	@Override
	public List<System> getAllSystems() {
		List<System> systems = systemDao.getAllSystems();
		return systems;
	}

}
