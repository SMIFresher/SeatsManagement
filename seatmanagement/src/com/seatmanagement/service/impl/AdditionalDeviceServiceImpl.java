package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.service.AdditionalDeviceService;

/**
 * 
 * @author SaiEswari
 * 
 * This class provides implementation for all business logic related
 *         processing to 'AdditionalDevice' model object
 *
 */
public class AdditionalDeviceServiceImpl implements AdditionalDeviceService {
	private static final Logger logger = LoggerFactory.getLogger(AdditionalDeviceServiceImpl.class);

	@Autowired
	GenericDao<AdditionalDevice> genericDao;

	
	@Override
	public AdditionalDevice saveOrUpdate(AdditionalDevice additionalDevice) {
		logger.info("Service: AdditionalDeviceServiceImpl Method : saveOrUpdate started at : " + LocalDateTime.now());
		genericDao.saveOrUpdate(additionalDevice);
		logger.info("Service: AdditionalDeviceServiceImpl Method : saveOrUpdate ended at : " + LocalDateTime.now());
		return additionalDevice;

	}

	@Override
	public boolean deleteDevice(AdditionalDevice additionalDevice) {
		logger.info("Service: AdditionalDeviceServiceImpl Method : deleteDevice started at : " + LocalDateTime.now());

		return genericDao.delete(additionalDevice);

	}

	@Override
	public List<AdditionalDevice> getAll() {
		logger.info("Service: AdditionalDeviceServiceImpl Method : getAll started at : " + LocalDateTime.now());

		AdditionalDevice additionalDevice = new AdditionalDevice();
		List<AdditionalDevice> list = genericDao.getAll(additionalDevice);
		logger.info("Service: AdditionalDeviceServiceImpl Method : getAll ended at : " + LocalDateTime.now());

		return list;
	}

}
