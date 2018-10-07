package com.seatmanagement.dao;

import java.util.List;

import com.seatmanagement.model.Organisation;

public interface OrganisationDao {
	
	public List<Organisation> getOrganisationByName(String organisationName);

}
