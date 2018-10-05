package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.OrganisationService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Organisation' model object
 *
 */
public class OrganisationServiceImpl implements OrganisationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationServiceImpl.class);

	@Autowired
	private GenericDao genericDao;

	@Override
	public void saveOrganisation(Organisation organisation) {

		logger.info("Service: OrganisationServiceImpl Method : saveOrganisation started at : " + LocalDateTime.now());

		genericDao.saveOrUpdate(organisation);

		logger.info("Service: OrganisationServiceImpl Method : saveOrganisation ended at : " + LocalDateTime.now());
	}

	@Override
	public List<Organisation> getAllOrganisations() {

		logger.info(
				"Service: OrganisationServiceImpl Method : getAllOrganisations started at : " + LocalDateTime.now());

		Organisation organisation = new Organisation();

		List<Organisation> organisations = genericDao.getAll(organisation);

		logger.info("Service: OrganisationServiceImpl Method : getAllOrganisations ended at : " + LocalDateTime.now());

		return organisations;
	}

	@Override
	public Organisation getOrganisationById(UUID organisationId) throws BusinessException {
		logger.info(
				"Service: OrganisationServiceImpl Method : getOrganisationById started at : " + LocalDateTime.now());

		Organisation organisation = null;

		organisation = (Organisation) genericDao.getById(organisation, organisationId);

		if (Objects.isNull(organisation)) {
			throw new BusinessException("Organisation record not found");
		}

		logger.info("Service: OrganisationServiceImpl Method : getOrganisationById ended at : " + LocalDateTime.now());

		return organisation;
	}

	@Override
	public void updateOrganisation(Organisation organisation) {
		logger.info("Service: OrganisationServiceImpl Method : updateOrganisation started at : " + LocalDateTime.now());

		genericDao.saveOrUpdate(organisation);

		logger.info("Service: OrganisationServiceImpl Method : updateOrganisation ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteOrganisationById(UUID organisationId) throws BusinessException {
		logger.info(
				"Service: OrganisationServiceImpl Method : deleteOrganisationById started at : " + LocalDateTime.now());

		Organisation organisation = (Organisation) genericDao.getById(new Organisation(), organisationId);

		// Scenario 1 : Organisation does not exist
		if (Objects.isNull(organisation)) {
			throw new BusinessException("Organisation record not found");
		}
		// Scenario 2 : Organisation exists
		else {

			genericDao.delete(organisation);
		}

		logger.info(
				"Service: OrganisationServiceImpl Method : deleteOrganisationById ended at : " + LocalDateTime.now());
	}

}
