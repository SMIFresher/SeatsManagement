package com.seatmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.AdditionalDeviceDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.service.AdditionalDeviceService;

public class AdditionalDeviceServiceImpl implements AdditionalDeviceService{

	@Autowired
	 GenericDao<AdditionalDevice> genericDao;
	
	@Autowired
	 AdditionalDeviceDao additionalDeviceDao;
	
	@Override
	public boolean saveOrUpdate(AdditionalDevice additionalDevice) {
		
		return genericDao.saveOrUpdate(additionalDevice);
	}
	
	
	
	@Override
	public boolean deleteDevice(AdditionalDevice device) {
		
		return genericDao.delete(device);
	}
	
	

	@Override
	public List<AdditionalDevice> getAll() {
		AdditionalDevice additionalDevice = null;
		List<AdditionalDevice> list=additionalDeviceDao.getAll(); 
		return list;
	}

	

}
