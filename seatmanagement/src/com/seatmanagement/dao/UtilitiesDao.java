package com.seatmanagement.dao;
import java.util.List;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Utilities;
/**
 * 
 * @author Harshanaa Ramdas
 *
 */
public interface UtilitiesDao {
	public List<Utilities> getAll() throws BusinessException;
}
