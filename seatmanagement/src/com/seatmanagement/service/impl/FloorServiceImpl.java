package com.seatmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Floor;

import com.seatmanagement.service.FloorService;


@Service
@Transactional

public class FloorServiceImpl implements FloorService {

	@Autowired
GenericDao<Floor> genericDao;

	@Autowired
GenericDao<Building> genericDaoBuilding;
	
	@Autowired
 FloorDao floorDao;
	
	
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


}
