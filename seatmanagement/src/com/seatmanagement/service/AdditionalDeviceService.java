package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.AdditionalDevice;

public interface AdditionalDeviceService {
	
	
	public boolean addOrUpdateDevice(AdditionalDevice device);
	public boolean deleteDevice(AdditionalDevice device);
	public List<AdditionalDevice> listAlldevices();
}
