package com.seatmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SeatingDetailsService;

@Service
public class SeatingDetailsServiceImpl implements SeatingDetailsService {

	@Autowired
	SeatingDetailsDao seatingDetailsDao;
	
	@Autowired
	GenericDao<SeatingDetails> genericDaoSeatingDetails;
	
	@Autowired
	GenericDao<Seating> genericdaoSeating;
	
	@Autowired
	SystemDao system;
	
	@Override
	public List<SeatingDetails> getAllSeatingDetails(){
		@SuppressWarnings("unchecked")
		List<SeatingDetails> list = seatingDetailsDao.getAllSeatingDetails();
		return list;
	}
	
	@Override
	public void saveSeatingDetails(SeatingDetails seatingDetails) {
		//seatingDetailsDao.getAllSeatingDetails();
		seatingDetailsDao.saveSeatingDetails(seatingDetails);
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
		
		Seating seating=new Seating();
		seating = genericdaoSeating.getById(seating, seatingId);
		
		for(SeatingDetails sd:seatingDetails) {
			sd.setSeating(seating);
			String systemName=sd.getSeatingSystemNo();
			sd.setSystem(system.getSystemId(systemName));
			genericDaoSeatingDetails.saveOrUpdate(sd);
		}
		
		
		
	}

	
}
