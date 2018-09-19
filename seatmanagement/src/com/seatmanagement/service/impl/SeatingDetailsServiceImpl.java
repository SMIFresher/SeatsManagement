package com.seatmanagement.service.impl;

import java.util.List;

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
	public List<SeatingDetails> getAllSeatingDetails() {
		//seatingDetailsDao.getAllSeatingDetails();
		return seatingDetailsDao.getAllSeatingDetails();
	}
	
	@Override
	public void saveSeatingDetails(SeatingDetails seatingDetails) {
		//seatingDetailsDao.getAllSeatingDetails();
		seatingDetailsDao.saveSeatingDetails(seatingDetails);
	}

}
