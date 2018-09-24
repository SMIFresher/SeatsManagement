package com.seatmanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.BuildingService;

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
		
		Building newBuilding = new Building();
		newBuilding.setBuildingLocation(building.getBuildingLocation());
		newBuilding.setBuildingAddress(building.getBuildingAddress());
		newBuilding.setBuildingName(building.getBuildingName());

		newBuilding.setSquareFeet(Float.parseFloat(building.getSquareFeetString()));
		Organisation organisation = new Organisation();
		organisation.setOrganisationId(organisationId);
		newBuilding.setOrganisation(organisation);
		return genericDao.saveOrUpdate(newBuilding);

	}

	
	public List<Building> getAll() {
		return buildingDao.getAll();
	}

	public Building getById(Building building, UUID buildingId) {
		return genericDao.getById(building, buildingId);
	}

	/*public boolean delete(Building building) {
		return genericDao.delete(building);

	}*/

}
