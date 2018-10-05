package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.BlockService;
import com.seatmanagement.service.FloorService;

/**
 * 
 * @author M.Karthika
 * 
 *         This class provides implementation from 'FloorService' Interface for
 *         all business logic related processing to 'Floor' model object
 * 
 */

@Service
public class FloorServiceImpl implements FloorService {

	private static final Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

	@Autowired
	GenericDao<Floor> genericDao;

	@Autowired
	GenericDao<Building> genericDaoBuilding;

	@Autowired
	FloorDao floorDao;

	@Autowired
	BlockService blockService;

	public Floor saveOrUpdateFloors(Floor floor, UUID buildingId) throws BusinessException {

		logger.info("Service: FloorServiceImpl Method : saveOrUpdateFloors started at : " + LocalDateTime.now());

		floor = (Floor)genericDao.saveOrUpdate(floor);

		logger.info("Service: FloorServiceImpl Method : saveOrUpdateFloors ended at : " + LocalDateTime.now());

		return floor;
	}

	@Override
	public Floor getByFloorId(Floor floor, UUID floorId) {

		logger.info("Service: FloorServiceImpl Method : getByFloorId started at : " + LocalDateTime.now());

		floor = (Floor) genericDao.getById(floor, floorId);

		logger.info("Service: FloorServiceImpl Method : getByFloorId ended at : " + LocalDateTime.now());

		return floor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Floor> getAllFloors() {
		logger.info("Service: FloorServiceImpl Method : getAllFloors started at : " + LocalDateTime.now());

		Floor floor = new Floor();

		List<Floor> floors = genericDao.getAll(floor);

		logger.info("Service: FloorServiceImpl Method : getAllFloors ended at : " + LocalDateTime.now());

		return floors;
	}

	@Override
	public void deleteByFloorId(UUID floorId) throws BusinessException {

		logger.info("Service: FloorServiceImpl Method : deleteByFloorId started at : " + LocalDateTime.now());

		Floor floor = genericDao.getById(new Floor(), floorId);

		// Scenario 1: Floor not present
		if (Objects.isNull(floor)) {
			throw new BusinessException("Floor not present");
		}
		// Scenario 2: Floor Present
		else {

			// Delete blocks referenced to this floor first
			blockService.deleteBlocksByFloorId(floorId);

			// Delete floor
			genericDao.delete(floor);
		}

		logger.info("Service: FloorServiceImpl Method : deleteByFloorId ended at : " + LocalDateTime.now());
	}

	@Override
	public List<Floor> getFloorsByBuildingId(UUID buildingId) {
		logger.info("Service: FloorServiceImpl Method : getFloorsByBuildingId started at : " + LocalDateTime.now());

		List<Floor> floors = null;
		try {
			floors = floorDao.getFloorsByBuildingId(buildingId);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Service: FloorServiceImpl Method : getFloorsByBuildingId ended at : " + LocalDateTime.now());

		return floors;
	}

	@Override
	public List<Floor> getFloorTypeByBuildingId(UUID buildingId) {

		logger.info("Service: FloorServiceImpl Method : getFloorTypeByBuildingId started at : " + LocalDateTime.now());

		List<Floor> floors = null;
		try {
			floors = floorDao.getFloorType(buildingId);

		} catch (BusinessException e) {
			
			e.printStackTrace();
		}

		logger.info("Service: FloorServiceImpl Method : getFloorTypeByBuildingId ended at : " + LocalDateTime.now());

		return floors;
	}

}
