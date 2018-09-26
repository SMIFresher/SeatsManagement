package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.AdditionalDevice;

public interface AdditionalDeviceService {
	
	
	public boolean saveOrUpdate(AdditionalDevice additionalDevice);
	
	public boolean deleteDevice(AdditionalDevice additionalDevice);
	
	public List<AdditionalDevice> getAll();
	
}
