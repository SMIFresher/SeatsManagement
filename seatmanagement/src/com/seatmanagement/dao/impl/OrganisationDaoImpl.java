package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.seatmanagement.dao.OrganisationDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Organisation;

public class OrganisationDaoImpl implements OrganisationDao{
	
	private static final Logger logger = LoggerFactory.getLogger(OrganisationDaoImpl.class);

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Organisation> getOrganisationByName(String organisationName) {
		logger.info("DAO: OrganisationDaoImpl Method : getOrganisationByName started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Organisation.class);
		criteria.add(Restrictions.eq("organisationName", organisationName));

		List<Organisation> organisations = null;
		try {
			organisations = (List<Organisation>) hibernateTemplate.findByCriteria(criteria);
		}catch(Exception e) {
			throw new ApplicationException("Error while retrieving organisation", e);
		}

		logger.info("DAO: OrganisationDaoImpl Method : getOrganisationByName ended at : " + LocalDateTime.now());

		return organisations;
	}

}
