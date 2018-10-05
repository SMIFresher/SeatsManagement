package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Utilities;

/**
 * 
 * @author Harshanaa Ramdas This class provides implementation for all Database
 *         related activities to 'Utilities' model object
 *
 */
public interface UtilitiesService {

	public Utilities saveOrUpdate(Utilities utility);

	public List<Utilities> getAll();

	public Utilities getById(Utilities utility, UUID utilityId) throws BusinessException;

	public boolean delete(Utilities utility);

}
