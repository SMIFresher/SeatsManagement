package com.workspacemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workspacemanagement.dao.AdditionalDeviceDao;
import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.model.AdditionalDevice;
import com.workspacemanagement.service.AdditionalDeviceService;

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
