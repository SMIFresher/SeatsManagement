package com.workspacemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workspacemanagement.dao.BuildingDao;
import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.model.Building;
import com.workspacemanagement.model.Organisation;
import com.workspacemanagement.service.BuildingService;

/**
 * 
 * @author Adithya Prabhu
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Building' model object
 *
 */
@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	GenericDao<Building> genericDao;
	@Autowired
	BuildingDao buildingDao;

	public boolean saveOrUpdate(Building building,UUID organisationId) {
		
		
		Organisation organisation = new Organisation();
		organisation.setOrganisationId(organisationId);
		building.setOrganisation(organisation);
		return genericDao.saveOrUpdate(building);

	}

	
	public List<Building> getAll() {
		return buildingDao.getAll();
	}

	public Building getById(Building building, UUID buildingId) {
		return genericDao.getById(building, buildingId);
	}

	public boolean delete(Building building) {
		return genericDao.delete(building);

	}

}
