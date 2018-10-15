package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.BuildingService;

/**
 * 
 * @author SaiEswari
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Building' model object
 *
 */
@Service
public class BuildingServiceImpl implements BuildingService {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationServiceImpl.class);

	@Autowired
	GenericDao<Building> genericDao;

	public Building saveOrUpdate(Building building, UUID organisationId) {

		logger.info("Service: BuildingServiceImpl Method : saveOrUpdate started at : " + LocalDateTime.now());
		Organisation organisation = new Organisation();
		organisation.setOrganisationId(organisationId);
		building.setOrganisation(organisation);
		genericDao.saveOrUpdate(building);

		logger.info("Service: BuildingServiceImpl Method : saveOrUpdate ended at : " + LocalDateTime.now());
		return building;

	}

	public List<Building> getAll() {
		logger.info("Service: BuildingServiceImpl Method : getAll started at : " + LocalDateTime.now());
		Building building = new Building();
		logger.info("Service: BuildingServiceImpl Method : getAll ended at : " + LocalDateTime.now());
		return genericDao.getAll(building);
	}

	@Override
	public Building getById(Building building, UUID buildingId) {
		logger.info("Service: BuildingServiceImpl Method : getById started at : " + LocalDateTime.now());

		building = (Building) genericDao.getById(building, buildingId);

		logger.info("Service: BuildingServiceImpl Method : getById ended at : " + LocalDateTime.now());

		return building;
	}

	public boolean delete(Building building) {
		logger.info("Service: BuildingServiceImpl Method : delete started at : " + LocalDateTime.now());

		return genericDao.delete(building);
	}

	

}
