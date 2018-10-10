package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.SeatingDetails;

/**
 * 
 * @author vgs-user
 *
 *         Provides interface for all the business logic related processing to
 *         'SeatingDetails' model object
 */

public interface SeatingDetailsService {

	public List<SeatingDetails> getAllSeatingDetails();

	void saveSeatingDetails(SeatingDetails seatingDetails);

	/*
	 * public List<SeatingDetails> getEmployeeBySeatId(SeatingDetails
	 * seatingdetails,UUID seating_id);
	 */
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id);

	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id);

	public List<SeatingDetails> getSeatingDetailsBySeatingId(UUID seatingId);

	public void saveSeatingDetailsInbatch(SeatingDetails[] seatingDetails, UUID seatingId) throws BusinessException;

	public void deleteByIdInBatch(UUID seatingId);

}
