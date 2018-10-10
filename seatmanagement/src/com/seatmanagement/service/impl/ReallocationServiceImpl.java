package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.exception.BusinessException;
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

	@Autowired
	private ReallocationDao reallocationDao;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Reallocation getReallocationByEmployeeId(String employeeId) {

		logger.info("Service: ReallocationServiceImpl Method : getReallocationByEmployeeId started at : "
				+ LocalDateTime.now());

		// Must implement logic through employee dao
		Reallocation reallocation = reallocationDao.getReallocationByEmployeeId(employeeId);

		logger.info("Service: ReallocationServiceImpl Method : getReallocationByEmployeeId ended at : "
				+ LocalDateTime.now());

		return reallocation;
	}

	@Override
	public void saveReallocation(Reallocation reallocation) {

		logger.info("Service: ReallocationServiceImpl Method : saveReallocation started at : " + LocalDateTime.now());

		genericDao.saveOrUpdate(reallocation);

		logger.info("Service: ReallocationServiceImpl Method : saveReallocation ended at : " + LocalDateTime.now());
	}

	@Override
	public void updateReallocation(Reallocation reallocation) {

		logger.info("Service: ReallocationServiceImpl Method : updateReallocation started at : " + LocalDateTime.now());

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
		logger.info("Service: SeatingDetailsServiceImpl Method : getAllReallocationDetails started at : "
				+ LocalDateTime.now());
		Reallocation reallocation = new Reallocation();
		List<Reallocation> list = genericDao.getAll(reallocation);
		logger.info("Service: SeatingDetailsServiceImpl Method : getAllReallocationDetails ended at : "
				+ LocalDateTime.now());
		return list;
	}

}
