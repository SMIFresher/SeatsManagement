package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.Building;

/**
 * 
 * @author Adithya Prabhu
 * 
 * Provides interface for all business logic related processing 
 * to 'Building' model object
 *
 */
public interface BuildingService {
	
	
	public boolean saveOrUpdate(Building building);
	
	public List<Building> getAll();
	
	
	
	public boolean delete(Building building);
	
	
 
}
