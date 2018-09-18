package com.seatmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	BuildingDao buildingDao;

	public void setBuildingDao(BuildingDao buildingDao) {
		this.buildingDao = buildingDao;
	}
	
}
