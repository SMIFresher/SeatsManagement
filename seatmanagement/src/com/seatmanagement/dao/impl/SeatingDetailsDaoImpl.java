package com.seatmanagement.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.model.SeatingDetails;

@Transactional
@Repository
public class SeatingDetailsDaoImpl implements SeatingDetailsDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	GenericDao<SeatingDetails> genericDao;
	
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

	/*@Override
	public List<SeatingDetails> getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		Seating seating = new Seating();
		seating.setSeatingId(seating_id);
		seatingList =  (List<Seating>) hibernateTemplate.find("From Seating  where block_id = '" +block_id + "'" );
		return seatingList;
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
		criteria.add(Restrictions.eq("seating", seating));
		@SuppressWarnings("unchecked")
		List<SeatingDetails> seat =  (List<SeatingDetails>) hibernateTemplate.findByCriteria(criteria);
		return seat;
	}*/
	
	@Override
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		SeatingDetails seatingDetails = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
		//criteria.createAlias("system","systems");
		criteria.createAlias("seating","seating",CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.disjunction());
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




}
