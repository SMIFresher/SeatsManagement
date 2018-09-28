package com.workspacemanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workspacemanagement.dao.FloorDao;
import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.exception.BusinessException;
import com.workspacemanagement.model.Block;
import com.workspacemanagement.model.Building;
import com.workspacemanagement.model.Floor;
import com.workspacemanagement.service.BlockService;
import com.workspacemanagement.service.FloorService;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
GenericDao<Floor> genericDao;

	@Autowired
GenericDao<Building> genericDaoBuilding;
	
	@Autowired
 FloorDao floorDao;
	
	@Autowired
	BlockService blockService;
	
   public boolean saveOrUpdate(Floor floor,UUID buildingId) {
		
		Floor newfloor=new Floor();
		Building newbuilding = new Building();
		
		newfloor.setFloorType(floor.getFloorType());
		newfloor.setFloorName(floor.getFloorName());
		
		if(Objects.nonNull(newbuilding)) {
			newbuilding=genericDaoBuilding.getById(newbuilding,buildingId);
		}
		 if(Objects.isNull(newbuilding)) {
			 throw new BusinessException("Building is not avaliable");
		 }
		
		 newfloor.setBuilding(newbuilding);
		return genericDao.saveOrUpdate( newfloor);
	}
	@Override
	public Floor getById(Floor floor, UUID floorId) {
		// TODO Auto-generated method stub
		return genericDao.getById(floor, floorId);
	}


	@Override
	public List<Floor> getAll() {
		// TODO Auto-generated method stub
	return floorDao.getAll();
	}
	@Override
	public void deleteFloorById(UUID floorId) {
		
		Floor floor = genericDao.getById(new Floor(), floorId);
		
		//Scenario 1: Floor not present
		if(Objects.isNull(floor)) {
			throw new BusinessException("Floor not present");
		}
		// Scenario 2: Floor Present
		else {
			
			// Delete blocks referenced to this floor first
			blockService.deleteBlocksByFloorId(floorId);
			
			// Delete floor
			genericDao.delete(floor);
		}
	}
	
	@Override
	public List<Floor> getFloorsByBuildingId(UUID buildingId) {
		List<Floor> floors = floorDao.getFloorsByBuildingId(buildingId);
		return floors;
	}
	
	
	
	@Override
	public List<Floor> getFloorTypeByBuildingId(UUID buildingId) {
		List<Floor> floors = floorDao.getFloorType(buildingId);
		return floors;
	}


}
