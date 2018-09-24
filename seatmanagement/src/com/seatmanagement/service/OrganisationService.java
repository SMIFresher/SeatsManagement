package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Organisation;

public interface OrganisationService {

	void saveOrganisation(Organisation organisation);

	List<Organisation> getAllOrganisations();

	Organisation getOrganisationById(UUID organisationId);

	void updateOrganisation(Organisation organisation);

	void deleteOrganisationById(UUID organisationId);

}
