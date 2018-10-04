package com.seatmanagement.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;

@Transactional
@Repository
public class SeatingDetailsDaoImpl implements SeatingDetailsDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	GenericDao<SeatingDetails> genericDaoSeatingDetails;
	
	@Autowired
	GenericDao<Seating> genericdaoSeating;
	
	@Autowired
	SystemDao system;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<SeatingDetails> getAllSeatingDetails() {
		List<SeatingDetails> seatingList = new ArrayList<>();
		seatingList = (List<SeatingDetails>) hibernateTemplate.find("From SeatingDetails");
		return seatingList;
	}

	@Override
	public  void saveSeatingDetails(SeatingDetails seatingDetails) {
	  hibernateTemplate.save(seatingDetails);
	}

	
	
	@Override
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		SeatingDetails seatingDetails = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
		//criteria.createAlias("system","systems");
		criteria.createAlias("seating","seating",CriteriaSpecification.INNER_JOIN);
		//criteria.add(Restrictions.disjunction());
		criteria.add(Restrictions.eq("seating.seatingId",seating_id));
		seatingDetails= (SeatingDetails) hibernateTemplate.findByCriteria(criteria).get(0);
		return seatingDetails;
	}
	
	@Override
	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id) {
		SeatingDetails seatingDetails = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
		criteria.createAlias("systems","systems");
		//criteria.createAlias("employee","employee");
		criteria.add(Restrictions.disjunction());
		criteria.add(Restrictions.eq("systems.employee",employee_id));
		seatingDetails= (SeatingDetails) hibernateTemplate.findByCriteria(criteria).get(0);
		return seatingDetails;
	}

	@Override
	public SeatingDetails deleteBySeatingId(UUID seatingId) {

		
		return null;
	}
	
public void saveSeatingDetailsInbatch(SeatingDetails[] seatingDetails,UUID seatingId) {
		
		deleteByIdInBatch(seatingId);
			
		Seating seating=new Seating();
		seating = genericdaoSeating.getById(seating, seatingId);
		
		for(SeatingDetails sd:seatingDetails) {
			sd.setSeating(seating);
			String systemName=sd.getSeatingSystemNo();
			sd.setSystem(system.getSystemId(systemName.trim()));
			genericDaoSeatingDetails.saveOrUpdate(sd);
		}
		
		
		
	}

	@Override
	public void deleteByIdInBatch(UUID seatingId) {
	
		List<SeatingDetails> sd=getAllSeatingDetails();
		//hibernateTemplate.deleteAll(sd);
		for(SeatingDetails sd1:sd) {
			hibernateTemplate.delete(sd1);
		}
	}

	@Override
	public List<SeatingDetails> getSeatingDetailsBySeatingId(UUID seatingId) {
		
			List<SeatingDetails> seatingDetailsList = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
			
			criteria.createAlias("seating","seating").setFetchMode("seating",FetchMode.SELECT);
			criteria.add(Restrictions.eq("seating.seatingId",seatingId));
			seatingDetailsList= (List<SeatingDetails>) hibernateTemplate.findByCriteria(criteria);
			
		
		return seatingDetailsList;
	}





}
