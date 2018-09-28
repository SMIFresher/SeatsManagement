package com.workspacemanagement.dao.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workspacemanagement.dao.FloorDao;
import com.workspacemanagement.model.Floor;
import com.workspacemanagement.model.Reallocation;
import com.workspacemanagement.model.Team;


@Transactional
@Repository
public class FloorDaoImpl implements FloorDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Floor> getAll() {
		
		List<Floor> floorList = new ArrayList<>();
		floorList=	(List<Floor>) hibernateTemplate.find("From Floor");
		return floorList;
	}

	@Override
	public List<Floor> getFloorsByBuildingId(UUID buildingId) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Floor.class);
		criteria.add(Restrictions.eq("building.buildingId", buildingId));

		List<Floor> floors = (List<Floor>) hibernateTemplate.findByCriteria(criteria);

		return floors;
	}
	
	
	
	
	@Override
	public List<Floor> getFloorType(UUID buildingId) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Floor.class);
		ProjectionList projList = Projections.projectionList();
	    projList.add(Projections.property("floorType"));
	    
	    criteria.setProjection(projList);
		
		criteria.add(Restrictions.eq("building.buildingId", buildingId));
		
				List<Floor> floors = (List<Floor>) hibernateTemplate.findByCriteria(criteria);

		return floors;
	}
	
	

}