package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Organisation;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *  Provides interface for all business logic related processing to
 *  'Organisation' model object
 *
 */
public interface OrganisationService {

	void saveOrganisation(Organisation organisation);

	List<Organisation> getAllOrganisations();

	Organisation getOrganisationById(UUID organisationId);

	void updateOrganisation(Organisation organisation);

	void deleteOrganisationById(UUID organisationId);

}
