package com.seatmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;



import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.SeatingDetailsService;

@Service
@Transactional
public class SeatingDetailsServiceImpl implements SeatingDetailsService {

	@Autowired
	SeatingDetailsDao seatingDetailsDao;
	
	@Autowired
	GenericDao<SeatingDetails> genericDao;
	
	@Override
	public List<SeatingDetails> getAllSeatingDetails(){
		@SuppressWarnings("unchecked")
		SeatingDetails seatingDetails=new SeatingDetails();
		List<SeatingDetails> list = genericDao.getAll(seatingDetails);
		return list;
	}
	
	@Override
	public void saveSeatingDetails(SeatingDetails seatingDetails) {
		//seatingDetailsDao.getAllSeatingDetails();
		genericDao.saveOrUpdate(seatingDetails);
		//seatingDetailsDao.saveSeatingDetails(seatingDetails);
	}
/*
	@Override
	public List<SeatingDetails> getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		return seatingDetailsDao.getEmployeeBySeatId(seatingdetails, seating_id);
	}
*/
	@Override
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		return seatingDetailsDao.getEmployeeBySeatId(seatingdetails, seating_id);
	}
	
	@Override
	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id) {
		return seatingDetailsDao.getSeatByEmployeeId(seatingdetails, employee_id);
	}

	public void saveSeatingDetailsInbatch(SeatingDetails[] seatingDetails,UUID seatingId) {
		
		/*deleteByIdInBatch(seatingId);
			
		Seating seating=new Seating();
		seating = genericdaoSeating.getById(seating, seatingId);
		
		for(SeatingDetails sd:seatingDetails) {
			sd.setSeating(seating);
			String systemName=sd.getSeatingSystemNo();
			sd.setSystem(system.getSystemId(systemName.trim()));
			genericDaoSeatingDetails.saveOrUpdate(sd);
		}*/
		
		seatingDetailsDao.saveSeatingDetailsInbatch(seatingDetails, seatingId);
		
		
		
	}

	@Override
	public void deleteByIdInBatch(UUID seatingId) {
	
		/*List<SeatingDetails> sd=getAllSeatingDetails();
		//hibernateTemplate.deleteAll(sd);
		for(SeatingDetails sd1:sd) {
			hibernateTemplate.delete(sd1);
		}*/
		
		seatingDetailsDao.deleteByIdInBatch(seatingId);
	}

	@Override
	public List<SeatingDetails> getSeatingDetailsBySeatingId(UUID seatingId) {
		return seatingDetailsDao.getSeatingDetailsBySeatingId(seatingId);
	}

	
}
