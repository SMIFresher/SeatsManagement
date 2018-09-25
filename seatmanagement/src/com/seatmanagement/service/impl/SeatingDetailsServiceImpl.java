package com.seatmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.service.SeatingDetailsService;

@Service
public class SeatingDetailsServiceImpl implements SeatingDetailsService {

	@Autowired
	SeatingDetailsDao seatingDetailsDao;
	
	@Override
	public List<Object> getAllSeatingDetails() {
		//seatingDetailsDao.getAllSeatingDetails();
		List<Object> object = new ArrayList<>();
		List<SeatingDetails> seatingDetailsList = seatingDetailsDao.getAllSeatingDetails();
		seatingDetailsList.stream().filter(Objects::nonNull).forEach(y->{
		Properties properties = new Properties();
		properties.put("seatingPosition",y.getSeatingPosition());
		//properties.put("x", y.getxAxis());
		//properties.put("y", y.getyAxis());
		//properties.put("note", "<a href> system_id = " + y.getSystemId() +" </a>");
		object.add(properties);
		});
		
		System.out.println(object);
		return object;
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

}
