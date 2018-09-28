package com.workspacemanagement.service;

import java.util.List;
import java.util.UUID;

import com.workspacemanagement.model.SeatingDetails;


public interface SeatingDetailsService {

	public List<SeatingDetails> getAllSeatingDetails();

	void saveSeatingDetails(SeatingDetails seatingDetails);
	
	/*public List<SeatingDetails> getEmployeeBySeatId(SeatingDetails seatingdetails,UUID seating_id);
	*/
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id);

	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id);

}
