package com.seatmanagement.service.impl;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Floor;

import com.seatmanagement.service.FloorService;


@Service
@Transactional

public class FloorServiceImpl implements FloorService {

	@Autowired
GenericDao<Floor> genericDao;
    
	@Autowired
 FloorDao floorDao;
	
	
   public boolean saveOrUpdate(Floor floor,UUID buildingId) {
		
		Floor newfloor=new Floor();
		
		newfloor.setFloorType(floor.getFloorType());
		newfloor.setFloorName(floor.getFloorName());
		
		Building newbuilding = new Building();
		newbuilding.setBuildingId(buildingId);
		
		return genericDao.saveOrUpdate(newfloor);

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


}
