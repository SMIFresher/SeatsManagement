package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Systems;

public interface SystemDao {

	public Systems getSystem(String request) throws BusinessException;

	public Systems getSystemId(String systemName) throws BusinessException;

	public List<Systems> getOs(Systems system);

	public Systems mergeSystem(Systems system);

	public List<Systems> getAllAvailableSystems(Systems system);
}
