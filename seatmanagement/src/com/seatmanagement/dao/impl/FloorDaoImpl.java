package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.impl.OrganisationServiceImpl;

/**
 * 
 * @author M.Karthika
 * 
 *         This class provides implementation of FloorDao(interface) for all
 *         Database connection related processing to 'Floor' model object
 * 
 */

@Transactional
@Repository
public class FloorDaoImpl implements FloorDao {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationServiceImpl.class);
	@Autowired
	HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Floor> getFloorsByBuildingId(UUID buildingId){

		logger.info("DAO: FloorDaoImpl Method : getFloorsByBuildingId started at : " + LocalDateTime.now());

		List<Floor> floors = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Floor.class);
		criteria.add(Restrictions.eq("building.buildingId", buildingId));
		criteria.setFetchMode("blocks", FetchMode.SELECT); 
		try {

			floors = (List<Floor>) hibernateTemplate.findByCriteria(criteria);

		} catch (Exception e) {

			throw new ApplicationException("Error while retreiving Floors");
		}
		logger.info("DAO: FloorDaoImpl Method : getFloorsByBuildingId ended at : " + LocalDateTime.now());

		return floors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Floor> getFloorType(UUID buildingId){

		logger.info("DAO: FloorDaoImpl Method : getFloorType started at : " + LocalDateTime.now());

		List<Floor> floors = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Floor.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("floorType"));

		criteria.setProjection(projList);

		criteria.add(Restrictions.eq("building.buildingId", buildingId));

		try {
			floors = (List<Floor>) hibernateTemplate.findByCriteria(criteria);

		} catch (Exception e) {

			throw new ApplicationException("Error while retreiving floors");
		}

		logger.info("DAO: FloorDaoImpl Method : getFloorType ended at : " + LocalDateTime.now());

		return floors;
	}

}