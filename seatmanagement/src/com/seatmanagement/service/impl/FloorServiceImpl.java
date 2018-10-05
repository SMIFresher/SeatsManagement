package com.seatmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.BlockService;
import com.seatmanagement.service.FloorService;

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
	
   public Floor saveOrUpdate(Floor floor,UUID buildingId) throws BusinessException {

		Building newbuilding = new Building();
		newbuilding.setBuildingId(buildingId);
		 floor.setBuilding(newbuilding);
		return genericDao.saveOrUpdate(floor);
	}
	@Override
	public Floor getById(Floor floor, UUID floorId) {
		// TODO Auto-generated method stub
		return genericDao.getById(floor, floorId);
	}


	@Override
	public List<Floor> getAll() {
		Floor floor=new Floor();
		return genericDao.getAll(floor);
	}
	@Override
	public void deleteFloorById(UUID floorId) throws BusinessException {
		
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
