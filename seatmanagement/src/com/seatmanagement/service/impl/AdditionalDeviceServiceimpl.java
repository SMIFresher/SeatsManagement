package com.seatmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.service.AdditionalDeviceService;

public class AdditionalDeviceServiceimpl implements AdditionalDeviceService{

	@Autowired
	private GenericDao<AdditionalDevice> genericDao;
	
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
		List<AdditionalDevice> list=genericDao.getAll(additionalDevice); 
		return list;
	}

	

}
