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
import com.seatmanagement.dao.UtilitiesDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Utilities;
import com.seatmanagement.service.UtilitiesService;

@Service
public class UtilitiesServiceImpl implements UtilitiesService {

	private static final Logger logger = LoggerFactory.getLogger(UtilitiesServiceImpl.class);
	@Autowired
	GenericDao<Utilities> genericDao;
	@Autowired
	UtilitiesDao utilitiesDao;

	@Override
	public Utilities saveOrUpdate(Utilities utilities) {
		logger.info("Service: UtilitiesServiceImpl Method : saveUtilities request processing started at : "
				+ LocalDateTime.now());
		Utilities result = genericDao.saveOrUpdate(utilities);
		logger.info("Service: UtilitiesServiceImpl Method : saveUtilities response sent at : " + LocalDateTime.now());
		return result;
	}

	public List<Utilities> getAll() {

		logger.info("Service: UtilitiesServiceImpl Method : listAllUtilities request processing started at : "
				+ LocalDateTime.now());
		Utilities util = new Utilities();
		List result = genericDao.getAll(util);
		logger.info(
				"Service: UtilitiesServiceImpl Method : listAllUtilities response sent at : " + LocalDateTime.now());
		return result;

	}

	public Utilities getById(Utilities utilities, UUID utilityId) throws BusinessException {
		logger.info("Service: UtilitiesServiceImpl Method : listUtilitiesById request processing started at : "
				+ LocalDateTime.now());
		Utilities result = genericDao.getById(utilities, utilityId);
		if(Objects.isNull(result)) {
			throw new BusinessException("Utilities record not found");
		}
		logger.info(
				"Service: UtilitiesServiceImpl Method : listUtilitiesById response sent at : " + LocalDateTime.now());
		return result;
	}

	public boolean delete(Utilities utilities) {
		logger.info("Service: UtilitiesServiceImpl Method : deleteUtilities request processing started at : "
				+ LocalDateTime.now());
		boolean result = genericDao.delete(utilities);
		logger.info("Service: UtilitiesServiceImpl Method : deleteUtilities response sent at : " + LocalDateTime.now());
		return result;

	}

}
