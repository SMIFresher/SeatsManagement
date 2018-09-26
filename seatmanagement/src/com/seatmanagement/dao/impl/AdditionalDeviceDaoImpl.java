package com.seatmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.AdditionalDeviceDao;
import com.seatmanagement.dao.BuildingDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Building;
import com.seatmanagement.service.AdditionalDeviceService;

@Transactional
@Repository
public class AdditionalDeviceDaoImpl implements AdditionalDeviceDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<AdditionalDevice> getAll() {
		List<AdditionalDevice> DeviceList = new ArrayList<>();
		DeviceList= (List<AdditionalDevice>) hibernateTemplate.find("From AdditionalDevice ");
		return DeviceList;
		
	}
}
