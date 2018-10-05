package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Reallocation;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Provides interface for all Database related activities to
 *         'Reallocation' model object
 *
 */
public interface ReallocationDao {

	/**
	 * 
	 * @param employeeId
	 * @return Reallocation
	 */
	public Reallocation getReallocationByEmployeeId(String employeeId);

	/**
	 * 
	 * @param blockId
	 * @throws BusinessException 
	 */
	public void deleteReallocationByBlockId(UUID blockId) throws BusinessException;

	/**
	 * 
	 * @param blockId
	 * @return List<Reallocation>
	 * @throws BusinessException 
	 */
	public List<Reallocation> getReallocationsByBlockId(UUID blockId) throws BusinessException;

}
