package com.seatmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

public class SystemServiceImpl implements SystemService{

	@Autowired
	SystemDao systemDao;
	
	@Override
	public List<Systems> getAllSystems() {
		List<Systems> systems = systemDao.getAllSystems();
		return systems;
	}

}
