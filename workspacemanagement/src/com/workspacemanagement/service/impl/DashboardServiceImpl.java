package com.workspacemanagement.service.impl;

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

import com.workspacemanagement.dao.BlockDao;
import com.workspacemanagement.dao.FloorDao;
import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.dao.SeatingDao;
import com.workspacemanagement.model.Block;
import com.workspacemanagement.model.Building;
import com.workspacemanagement.model.Employee;
import com.workspacemanagement.model.Floor;
import com.workspacemanagement.model.Seating;
import com.workspacemanagement.model.SeatingDetails;
import com.workspacemanagement.model.Systems;
import com.workspacemanagement.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Autowired
	GenericDao<Systems> systemDao;

	/*
	 * @Autowired GenericDao<SeatingDetails> seatingDetailsDao;
	 */

	@Autowired
	GenericDao<Block> blockGenericDao;

	@Autowired
	GenericDao<Employee> employeeDao;

	@Autowired
	GenericDao<Building> buildingDao;

	@Autowired
	FloorDao floorDao;

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
		SeatingDetails seatigDetails = new SeatingDetails();
		Block block = new Block();
		Employee employee = new Employee();

		Integer systemCount = systemDao.getAll(systems).size();

		List<Block> blockList = blockDao.getAll();
		Integer seatcapacity = blockList.stream().filter(Objects::nonNull).map(Block::getBlockCapacity)
				.map(Integer::parseInt).mapToInt(Integer::intValue).sum();

		Integer blockCount = blockGenericDao.getAll(block).size();

		Integer employeeCount = employeeDao.getAll(employee).size();
		Properties properties = new Properties();
		properties.put("Employee Count", employeeCount.toString());
		properties.put("System Count", systemCount.toString());
		properties.put("Seating Detials Count", seatcapacity.toString());
		properties.put("Block Count", blockCount.toString());
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
			properties.put("BuildingName", p.getBuildingName());
			List<Block> flatList = listOfBockCapacitybasedonFloor.stream().flatMap(List::stream)
					.collect(Collectors.toList());
			Integer blockCapacity = flatList.stream().filter(Objects::nonNull).map(Block::getBlockCapacity)
					.map(Integer::parseInt).mapToInt(Integer::intValue).sum();
			List<Integer> seats = getSeatOccupiedByBlock(flatList);
			Integer seatOccupied = seats.stream().mapToInt(Integer::intValue).sum();
			Integer seatsAvailable = null;
			seatsAvailable = blockCapacity - seatOccupied;
			properties.put("Total Seating Capacity", blockCapacity.toString());
			properties.put("Total Seating Occupied", seatOccupied.toString());
			properties.put("Total Seating Avaliable", seatsAvailable.toString());
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
		List<List<Block>> blockIddsss = new ArrayList<>();
		List<Floor> floorList = floorDao.getFloorsByBuildingId(building.getBuildingId());
		floorList.stream().filter(Objects::nonNull).forEach(q -> {
			List<Block> blockIds = getAllBlockByFloor(q);
			blockIddsss.add(blockIds);
		});
		return blockIddsss;
	}

	private List<Block> getAllBlockByFloor(Floor floor) {
		List<Block> blockList = blockDao.getBlocksByFloorId(floor.getFloorId());
		return blockList;
	}

}
