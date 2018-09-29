package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;
import com.seatmanagement.model.SeatingDetails;

public interface SeatingDetailsDao {

	List<SeatingDetails> getAllSeatingDetails();

	void saveSeatingDetails(SeatingDetails seatingDetails);

/*	public List<SeatingDetails> getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id);
*/	
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id);
	
	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id);
	
	
}
