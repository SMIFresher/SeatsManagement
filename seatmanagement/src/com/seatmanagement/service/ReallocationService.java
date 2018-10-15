package com.seatmanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Reallocation;
import com.seatmanagement.model.SeatingDetails;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Provides interface for all business logic related processing to
 *         'Organisation' model object
 *
 */
public interface ReallocationService {

	/**
	 * 
	 * @param employeeId
	 * @return Reallocation
	 */
	public Reallocation getReallocationByEmployeeId(UUID employeeId);

	/**
	 * 
	 * @param reallocation
	 */
	public void saveReallocation(UUID previousblockId,UUID employeeId,UUID blockId);

	/**
	 * 
	 * @param reallocation
	 */
	public void updateReallocation(Reallocation reallocation);

	/**
	 * 
	 * @param reallocation
	 */
	public void deleteReallocation(Reallocation reallocation);

	/**
	 * 
	 * @param blockId
	 * @throws BusinessException
	 */
	public void deleteReallocationsByBlockId(UUID blockId);

	public List<Reallocation> getAllReallocationDetails();
	
	public Reallocation getByReallocationId(Reallocation reallocation, UUID reallocationId);
	
	public List<Reallocation> getReallocationByBlockId(UUID blockId);
	
	public List<Reallocation> getReallocationByReallocationStatus(String reallocationStatus);
	
	public List<Reallocation> getReallocationByRequestDate(LocalDate reallocationRequestedDate);

}
