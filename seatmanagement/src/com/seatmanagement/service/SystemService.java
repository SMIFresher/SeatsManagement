package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.Systems;

public interface SystemService {

	public boolean addOrUpdateSystem(Systems system);
	public List<Systems> getAllSystems();
	/*public Systems getById(Systems system,int System_id);*/
	public boolean delete(Systems system);
	
}
