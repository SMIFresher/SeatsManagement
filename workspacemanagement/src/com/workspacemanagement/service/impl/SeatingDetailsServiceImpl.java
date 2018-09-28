package com.workspacemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workspacemanagement.dao.SeatingDetailsDao;
import com.workspacemanagement.model.Seating;
import com.workspacemanagement.model.SeatingDetails;
import com.workspacemanagement.service.SeatingDetailsService;

@Service
public class SeatingDetailsServiceImpl implements SeatingDetailsService {

	@Autowired
	SeatingDetailsDao seatingDetailsDao;
	
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

}
