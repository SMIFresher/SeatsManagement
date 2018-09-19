package com.seatmanagement.dao;

import java.util.List;

import com.seatmanagement.model.SeatingDetails;

public interface SeatingDetailsDao {

	List<SeatingDetails> getAllSeatingDetails();

	void saveSeatingDetails(SeatingDetails seatingDetails);

	
}
