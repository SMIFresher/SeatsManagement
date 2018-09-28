package com.workspacemanagement.service;

import java.util.List;
import java.util.UUID;

import com.workspacemanagement.model.Systems;

public interface SystemService {

	public boolean addOrUpdateSystem(Systems system);
	public List<Systems> getAllSystems();
	public Systems getById(Systems system,UUID System_id);
	public boolean delete(Systems system);
	public  Systems getSystem(String request);
	
	
}
