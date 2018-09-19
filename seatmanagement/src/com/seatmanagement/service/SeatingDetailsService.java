package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.SeatingDetails;


public interface SeatingDetailsService {

	List<SeatingDetails> getAllSeatingDetails();

	void saveSeatingDetails(SeatingDetails seatingDetails);

}
