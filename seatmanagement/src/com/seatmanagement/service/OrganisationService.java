package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Organisation;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Provides interface for all business logic related processing to
 *         'Organisation' model object
 *
 */
public interface OrganisationService {

	/**
	 * 
	 * @param organisation
	 */
	void saveOrganisation(Organisation organisation);

	/**
	 * 
	 * @return List<Organisation>
	 */
	List<Organisation> getAllOrganisations();

	/**
	 * 
	 * @param organisationId
	 * @return Organisation
	 */
	Organisation getOrganisationById(UUID organisationId);

	/**
	 * 
	 * @param organisation
	 */
	void updateOrganisation(Organisation organisation);

	/**
	 * 
	 * @param organisationId
	 */
	void deleteOrganisationById(UUID organisationId);

}
