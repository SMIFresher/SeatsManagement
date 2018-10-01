package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Autowired
	GenericDao<Systems> systemDao;

	@Autowired
	GenericDao<SeatingDetails> seatingDetailsDao;

	@Autowired
	GenericDao<Employee> employeeDao;

	@Autowired
	GenericDao<Building> buildingDao;

	@Autowired
	BlockDao blockDao;
	
	@Autowired
	SeatingDao seatingDao;

	public List<Object> getAllDashboardCount() {
		List<Object> totalDashboardCount = new ArrayList<>();
		logger.info(
				"ServiceImpl: DashboardServiceImpl Method : getAllDashboardCount request count for system, seating details, block , Employee  processing started at : "
						+ LocalDateTime.now());

		Systems systems = new Systems();
		Employee employee = new Employee();
		Integer systemCount = systemDao.getAll(systems).size();
		
		List<Block> blockList = blockDao.getAll();
		Integer seatcapacity = blockList.stream().filter(Objects::nonNull).map(Block::getBlockCapacity)
				.map(Integer::parseInt).mapToInt(Integer::intValue).sum();

		Integer blockCount = blockList.size();

		Integer employeeCount = employeeDao.getAll(employee).size();
		Properties properties = new Properties();
		properties.put(Constant.EMPLOYEE_COUNT, employeeCount.toString());
		properties.put(Constant.SYSTEM_COUNT, systemCount.toString());
		properties.put(Constant.SEATING_DETAILS_COUNT, seatcapacity.toString());
		properties.put(Constant.BLOCK_COUNT, blockCount.toString());
		totalDashboardCount.add(properties);
		return totalDashboardCount;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Object> getAllCompanyDetailsCount() {
		List<Object> totalCompanyDetailsCount = new ArrayList<>();
		logger.info(
				"ServiceImpl: DashboardServiceImpl Method : getAllCompanyDetailsCount request count for toatal Seat, Seat Occupied and Available Seat  processing started at : "
						+ LocalDateTime.now());
		Building building = new Building();
		Floor floor = new Floor();
		Block block = new Block();

		List<Building> buildingList = buildingDao.getAll(building);

		buildingList.stream().filter(Objects::nonNull).forEach(p -> {
			Properties properties = new Properties();
			List<List<Block>> listOfBockCapacitybasedonFloor = getAllFloorByBuilding(p);
			properties.put(Constant.BUILDING_NAME, p.getBuildingName());
			List<Block> flatList = listOfBockCapacitybasedonFloor.stream().flatMap(List::stream)
					.collect(Collectors.toList());
			Integer blockCapacity = flatList.stream().filter(Objects::nonNull).map(Block::getBlockCapacity)
					.map(Integer::parseInt).mapToInt(Integer::intValue).sum();
			List<Integer> seats = getSeatOccupiedByBlock(flatList);
			Integer seatOccupied = seats.stream().mapToInt(Integer::intValue).sum();
			Integer seatsAvailable = null;
			seatsAvailable = blockCapacity - seatOccupied;
			properties.put(Constant.TOTAL_SEATING_CAPACITY, blockCapacity.toString());
			properties.put(Constant.TOTAL_SEATING_OCCUPIED, seatOccupied.toString());
			properties.put(Constant.TOTAL_SEATING_AVAILABLE, seatsAvailable.toString());
			totalCompanyDetailsCount.add(properties);
		});

		return totalCompanyDetailsCount;
	}

	private List<Integer> getSeatOccupiedByBlock(List<Block> flatList) {
		List<Integer> seatOccupiedList = new ArrayList<>();
		flatList.stream().filter(Objects::nonNull).forEach(z -> {
			List<Seating> seatingList = seatingDao.getSeatingByBlockId(z.getBlockId());
			int seatOccupied = 0;
			for (Seating s : seatingList) {
				seatOccupied = s.getSeat_occupied();
			}
			seatOccupiedList.add(seatOccupied);
		});

		return seatOccupiedList;
	}

	private List<List<Block>> getAllFloorByBuilding(Building building) {
		// Convert the Set to List AND GET All Floor From Building
		List<List<Block>> blockIddsss = new ArrayList<>();
	    building.getFloors().stream().filter(Objects::nonNull).forEach(q -> {
			List<Block> blockIds = getAllBlockByFloor(q);
			blockIddsss.add(blockIds);
		});
		return blockIddsss;
	}

	private List<Block> getAllBlockByFloor(Floor floor) {
		// Convert the Set to List AND GET All Block From Floor
		List<Block> blockList = floor.getBlocks().stream().filter(Objects::nonNull).collect(Collectors.toList());
		return blockList;
	}

}
