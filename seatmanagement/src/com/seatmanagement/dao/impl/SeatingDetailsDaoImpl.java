package com.seatmanagement.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.model.SeatingDetails;

@Transactional
@Repository
public class SeatingDetailsDaoImpl implements SeatingDetailsDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<SeatingDetails> getAllSeatingDetails() {
		
		return (List<SeatingDetails>) hibernateTemplate.find("From SeatingDetails");
	}

	@Override
	public  void saveSeatingDetails(SeatingDetails seatingDetails) {
	  hibernateTemplate.save(seatingDetails);
	}

}
