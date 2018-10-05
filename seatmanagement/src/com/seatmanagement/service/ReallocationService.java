package com.seatmanagement.service;

import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Reallocation;

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
	public Reallocation getReallocationByEmployeeId(String employeeId);

	/**
	 * 
	 * @param reallocation
	 */
	public void saveReallocation(Reallocation reallocation);

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
	public void deleteReallocationsByBlockId(UUID blockId) throws BusinessException;

}
