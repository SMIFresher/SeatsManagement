package com.seatmanagement.service;

import java.util.List;

import com.seatmanagement.model.AdditionalDevice;

/**
 * 
 * @author SaiEswari
 * 
 *         Provides interface for all business logic related processing to
 *         'AdditionalDevice' model object
 *
 */
public interface AdditionalDeviceService {

	/**
	 * 
	 * @param additionalDevice
	 * @return
	 */
	public AdditionalDevice saveOrUpdate(AdditionalDevice additionalDevice);

	/**
	 * 
	 * @param additionalDevice
	 * @return
	 */
	public boolean deleteDevice(AdditionalDevice additionalDevice);

	/**
	 * 
	 * @return
	 */
	public List<AdditionalDevice> getAll();

}
