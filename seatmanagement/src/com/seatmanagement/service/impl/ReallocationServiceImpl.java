package com.seatmanagement.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Reallocation;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.service.ReallocationService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Reallocation' model object
 *
 */
@Service
public class ReallocationServiceImpl implements ReallocationService {

	private static final Logger logger = LoggerFactory.getLogger(ReallocationServiceImpl.class);
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
	String localDate = LocalDate.now().toString();

	@Autowired
	private ReallocationDao reallocationDao;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Reallocation getReallocationByEmployeeId(UUID employeeId) {

		logger.info("Service: ReallocationServiceImpl Method : getReallocationByEmployeeId started at : "
				+ LocalDateTime.now());

		// Must implement logic through employee dao
		Reallocation reallocation = reallocationDao.getReallocationByEmployeeId(employeeId);

		logger.info("Service: ReallocationServiceImpl Method : getReallocationByEmployeeId ended at : "
				+ LocalDateTime.now());

		return reallocation;
	}

	@Override
	public void saveReallocation(UUID previousblockId,UUID employeeId,UUID blockId) {

		logger.info("Service: ReallocationServiceImpl Method : saveReallocation started at : " + LocalDateTime.now());
		
		Reallocation reallocation=new Reallocation();
		Employee employee=new Employee();
		employee=(Employee) genericDao.getById(employee, employeeId);
		Block block=new Block();
		block=(Block) genericDao.getById(block, blockId);
		Block previousBlock=new Block();
		previousBlock=(Block) genericDao.getById(block, previousblockId);
		
		reallocation.setEmployee(employee);
		reallocation.setBlock(block);
		reallocation.setPreviousBlock(previousBlock);
		reallocation.setReallocationStatus("Pending");
		reallocation.setReallocationRequestedDate(localDate);
		
		genericDao.saveOrUpdate(reallocation);

		logger.info("Service: ReallocationServiceImpl Method : saveReallocation ended at : " + LocalDateTime.now());
	}

	@Override
	public void updateReallocation(Reallocation reallocation) {

		logger.info("Service: ReallocationServiceImpl Method : updateReallocation started at : " + LocalDateTime.now());

		reallocation.setReallocatedDate(localDate);
		
		genericDao.saveOrUpdate(reallocation);

		logger.info("Service: ReallocationServiceImpl Method : updateReallocation ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteReallocation(Reallocation reallocation) {

		logger.info("Service: ReallocationServiceImpl Method : deleteReallocation started at : " + LocalDateTime.now());

		genericDao.delete(reallocation);

		logger.info("Service: ReallocationServiceImpl Method : deleteReallocation ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteReallocationsByBlockId(UUID blockId) {

		logger.info("Service: ReallocationServiceImpl Method : deleteReallocationsByBlockId started at : "
				+ LocalDateTime.now());
		List<Reallocation> reallocations = reallocationDao.getReallocationsByBlockId(blockId);

		// Scenario 1: No reallocations mapped
		if (Objects.isNull(reallocations) || reallocations.isEmpty()) {

		}
		// Scenario 2 : Reallocations mapped
		else {
			for (Reallocation reallocation : reallocations) {

				reallocationDao.deleteReallocationByBlockId(blockId);
			}
		}

		logger.info("Service: ReallocationServiceImpl Method : deleteReallocationsByBlockId ended at : "
				+ LocalDateTime.now());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reallocation> getAllReallocationDetails() {

		logger.info("Service: ReallocationServiceImpl Method : getAllReallocationDetails started at : "
				+ LocalDateTime.now());

		Reallocation reallocation = new Reallocation();

		List<Reallocation> list = genericDao.getAll(reallocation);

		logger.info("Service: ReallocationServiceImpl Method : getAllReallocationDetails ended at : "
				+ LocalDateTime.now());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Reallocation getByReallocationId(Reallocation reallocation, UUID reallocationId) {
		logger.info(
				"Service: ReallocationServiceImpl Method : getByReallocationId started at : " + LocalDateTime.now());

		reallocation = (Reallocation) genericDao.getById(reallocation, reallocationId);

		logger.info("Service: ReallocationServiceImpl Method : getByReallocationId ended at : " + LocalDateTime.now());

		return reallocation;

	}

	@Override
	public List<Reallocation> getReallocationByBlockId(UUID blockId) {
		logger.info("Service: ReallocationServiceImpl Method : getSeatingDetailsBySeatingId started at : "
				+ LocalDateTime.now());

		List<Reallocation> reallocation = reallocationDao.getReallocationsByBlockId(blockId);

		logger.info("Service: ReallocationServiceImpl Method : getSeatingDetailsBySeatingId ended at : "
				+ LocalDateTime.now());

		return reallocation;

	}

	@Override
	public List<Reallocation> getReallocationByReallocationStatus(String reallocationStatus) {
		logger.info("Service: ReallocationServiceImpl Method : getReallocationByReallocationStatus started at : "
				+ LocalDateTime.now());

		List<Reallocation> reallocation = reallocationDao.getReallocationsByStatus(reallocationStatus);

		logger.info("Service: ReallocationServiceImpl Method : getReallocationByReallocationStatus ended at : "
				+ LocalDateTime.now());

		return reallocation;
	}

	@Override
	public List<Reallocation> getReallocationByRequestDate(LocalDate reallocationRequestedDate) {
		
		logger.info("Service: ReallocationServiceImpl Method : getReallocationByRequestDate started at : "
				+ LocalDateTime.now());

		List<Reallocation> reallocation = reallocationDao.getReallocationByRequestDate(reallocationRequestedDate);

		logger.info("Service: ReallocationServiceImpl Method : getReallocationByRequestDate ended at : "
				+ LocalDateTime.now());

		return reallocation;
	}

}
