package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;

@Transactional
public class SystemDaoImpl implements SystemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	private static final Logger logger = LoggerFactory.getLogger(SystemDaoImpl.class);

	@SuppressWarnings("deprecation")
	@Override
	public List<Systems> getSystem(String request){

		logger.info("Dao: SystemDaoImpl Method : getSystem started at : " + LocalDateTime.now());

		List<Systems> systemList = null;

		
			DetachedCriteria criteria = DetachedCriteria.forClass(Systems.class);
			criteria.createAlias("employee", "employee", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.disjunction());
			criteria.add(Restrictions.or(Restrictions.like("systemName", request, MatchMode.START),
					Restrictions.like("employee.employeeRoll", request, MatchMode.START),
					Restrictions.like("employee.firstName", request, MatchMode.START)));

			systemList = (List<Systems>) hibernateTemplate.findByCriteria(criteria);

			logger.info("Dao: SystemDaoImpl Method : getSystem ended at : " + LocalDateTime.now());

	

		return systemList;
	}

	@Override
	public List<Systems> getSystemId(String systemName){

		logger.info("Dao: SystemDaoImpl Method : getSystemId started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Systems.class);
		criteria.add(Restrictions.eq("systemName", systemName));
		List<Systems> systemList = (List<Systems>) hibernateTemplate.findByCriteria(criteria);

		logger.info("Dao: SystemDaoImpl Method : getSystemId ended at : " + LocalDateTime.now());
		return systemList;
	}

	@Override
	public List<Systems> getOs(Systems system) {

		logger.info("Dao: SystemDaoImpl Method : getOs started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Systems.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("operatingSystem"));
		criteria.setProjection(projList);
		List<Systems> syss = (List<Systems>) hibernateTemplate.findByCriteria(criteria);

		logger.info("Dao: SystemDaoImpl Method : getOs ended at : " + LocalDateTime.now());
		return syss;

	}

	@Override
	public Systems mergeSystem(Systems system) {
		
		logger.info("Dao: SystemDaoImpl Method : mergeSystem started at : " + LocalDateTime.now());

		system = hibernateTemplate.merge(system);

		logger.info("Dao: SystemDaoImpl Method : mergeSystem ended at : " + LocalDateTime.now());
		return system;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Systems> getAllAvailableSystems(Systems system) {

		logger.info("Dao: SystemDaoImpl Method : getAvailableSystems started at : " + LocalDateTime.now());
		// Mysql query: select system_id from system where (system_id )
		// not in ( select system_id from seating_detail);

		// criteria for subquery
		DetachedCriteria subquery = DetachedCriteria.forClass(SeatingDetails.class)
				.createAlias("system", "system", CriteriaSpecification.LEFT_JOIN)
				.setProjection(Property.forName("system.systemId"));

		// criteria for main query
		DetachedCriteria criteria = DetachedCriteria.forClass(Systems.class)
				.add(Property.forName("systemId").notIn(subquery));

		List<Systems> syss = (List<Systems>) hibernateTemplate.findByCriteria(criteria);

		logger.info("Dao: SystemDaoImpl Method : getAvailableSystems ended at : " + LocalDateTime.now());
		return syss;
	}
}
