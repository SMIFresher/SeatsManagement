package com.workspacemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workspacemanagement.dao.AdditionalDeviceDao;
import com.workspacemanagement.dao.BuildingDao;
import com.workspacemanagement.model.AdditionalDevice;
import com.workspacemanagement.model.Building;
import com.workspacemanagement.service.AdditionalDeviceService;

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
