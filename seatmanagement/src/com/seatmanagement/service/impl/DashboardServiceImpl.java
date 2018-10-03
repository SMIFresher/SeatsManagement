package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.dao.FloorDao;
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
	GenericDao<Floor> floorDao;
	
	@Autowired
	GenericDao<Block> blockDao1;


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
			List<List<Block>> listOfBlockCapacitybasedonFloor = getAllFloorByBuilding(p);
			properties.put(Constant.BUILDING_NAME, p.getBuildingName());
			List<Block> flatList = listOfBlockCapacitybasedonFloor.stream().flatMap(List::stream)
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
			List<Seating> seatingList = getSeatOccupiedByBlockId(z.getBlockId());
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
	
	/*private List<Block> getAllBlockByBlock(Block block) {
		// Convert the Set to List AND GET All Block From Floor
		List<Block> blockList = block.getBlocks().stream().filter(Objects::nonNull).collect(Collectors.toList());
		return blockList;
	}
	*/
	
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Object> getAllFloorDetailsCount(UUID buildingId) {
		List<Object> totalFloorDetailsCount = new ArrayList<>();
		logger.info(
				"ServiceImpl: DashboardServiceImpl Method : getAllFloorDetailsCount request count for total Seat, Seat Occupied and Available Seat  processing started at : "
						+ LocalDateTime.now());
		Building b=new Building(); 
		Floor floor = new Floor();
		Block block = new Block();
		Building building=buildingDao.getById(b, buildingId);
	
		building.getFloors().stream().filter(Objects::nonNull).forEach(p -> {
			Properties properties = new Properties();
			List<Block> listOfBlockCapacitybasedonFloor = getAllBlockByFloor(p);
			properties.put(Constant.BUILDING_NAME, building.getBuildingName());
			properties.put(Constant.FLOOR_NAME, p.getFloorName());
			
			
			Integer blockCapacity = listOfBlockCapacitybasedonFloor.stream().filter(Objects::nonNull).map(Block::getBlockCapacity)
					.map(Integer::parseInt).mapToInt(Integer::intValue).sum();
			List<Integer> seats = getSeatOccupiedByBlock(listOfBlockCapacitybasedonFloor);
			Integer seatOccupied = seats.stream().mapToInt(Integer::intValue).sum();
			Integer seatsAvailable = null;
			seatsAvailable = blockCapacity - seatOccupied;
			System.out.println(seatsAvailable);
		
			properties.put(Constant.TOTAL_SEATING_CAPACITY, blockCapacity.toString());
			properties.put(Constant.TOTAL_SEATING_OCCUPIED, seatOccupied.toString());
			properties.put(Constant.TOTAL_SEATING_AVAILABLE, seatsAvailable.toString());
			totalFloorDetailsCount.add(properties);
		
		});

		return totalFloorDetailsCount;
	
	
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Object> getAllBlockDetailsCount(UUID floorId) {
		List<Object> totalBlockDetailsCount = new ArrayList<>();
		logger.info(
				"ServiceImpl: DashboardServiceImpl Method : getAllBlockDetailsCount request count for total Seat, Seat Occupied and Available Seat  processing started at : "
						+ LocalDateTime.now());
		//Building b= new Building();
		Floor f = new Floor();
		Block block = new Block();
		
		Floor floor=floorDao.getById(f, floorId);
		
		floor.getBlocks().stream().filter(Objects::nonNull).forEach(p -> {
			Properties properties = new Properties();
			
			//properties.put(Constant.BUILDING_NAME, b.getBuildingName());
			properties.put(Constant.FLOOR_NAME, floor.getFloorName());
			Integer blockCapacityPerBlock = Integer.valueOf(p.getBlockCapacity());
			
			List<Seating> seatOccupiedList = getSeatOccupiedByBlockId(p.getBlockId());
			Integer seatOccupied = seatOccupiedList.stream().filter(Objects::nonNull).map(Seating::getSeat_occupied).mapToInt(Integer::intValue).sum();
			
			Integer seatsAvailable = null;
			seatsAvailable = blockCapacityPerBlock - seatOccupied;
			
			
			properties.put(Constant.BLOCK_NAME, p.getBlockName());
			properties.put(Constant.TOTAL_SEATING_CAPACITY, blockCapacityPerBlock.toString());
			properties.put(Constant.TOTAL_SEATING_OCCUPIED, seatOccupied.toString());
			properties.put(Constant.TOTAL_SEATING_AVAILABLE, seatsAvailable.toString());
			totalBlockDetailsCount.add(properties);
		
		});

		return totalBlockDetailsCount;
	
	
}

	private List<Seating> getSeatOccupiedByBlockId(UUID blockId) {
		
		
		return seatingDao.getSeatingByBlockId(blockId);
	}
}


	
	
	
	

