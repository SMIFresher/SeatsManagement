package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.SeatingDetails;


public interface SeatingDetailsService {

	List<Object> getAllSeatingDetails();

	void saveSeatingDetails(SeatingDetails seatingDetails);

}
