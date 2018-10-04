package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Systems;

public interface SystemDao {

	public  Systems getSystem(String request);
	public Systems getSystemId(String systemName);
	public List<Systems> getOs(Systems system );
}
