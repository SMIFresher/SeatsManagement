package com.seatmanagement.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@Transactional
public class SystemServiceImpl implements SystemService{

	@Autowired
	GenericDao genericDao;
	
	@Autowired
	GenericDao<Employee> genericDaoEmp;
	
	@Autowired
	SystemDao systemDao;
	
	@Override
	public List<Systems> getAllSystems() {
		Systems system=new Systems();
		List<Systems> list = genericDao.getAll(system);
		return list;
	}

	@Override
	public Systems getById(Systems system, UUID System_id) {
		return (Systems) genericDao.getById(system, System_id);
	}

	@Override
	public boolean delete(Systems system) {
		return genericDao.delete(system);
	}



	@Override
	public void addOrUpdateSystem(Systems system,UUID employeeId,List<UUID> additionalDevicesUUIDs ){
		Employee employee= new Employee();
		
		// Employee Mapping
		// Scenario 1: employee id is null
		if(Objects.isNull(employeeId)) {
			
		}
		// Scenario 2: employee id is present
		else {
			employee = genericDaoEmp.getById(employee, employeeId);
			system.setEmployee(employee);
		}
		
		// AdditionalDevice mapping
		// Scenario 1: No AdditionDevice IDs in request
		if(Objects.isNull(additionalDevicesUUIDs) || additionalDevicesUUIDs.isEmpty()){
			
		}
		// Scenario 2: AdditionDevice IDs present in request
		else{
			Set<AdditionalDevice> additionalDevices = new HashSet<AdditionalDevice> ();
			for(UUID additionalDeviceID : additionalDevicesUUIDs){
				AdditionalDevice additionalDevice = new AdditionalDevice();
				additionalDevice = (AdditionalDevice) genericDao.getById(additionalDevice, additionalDeviceID);
				
				additionalDevices.add(additionalDevice);
			}
			
			system.setAdditionalDevice(additionalDevices);
		}
		
		genericDao.saveOrUpdate(system);
	}

	

	public  Systems getSystem(String request) {
		return systemDao.getSystem(request);
	}

	@Override
	public Systems getSystemBySystemName(String systemName) {
		
		return systemDao.getSystemId(systemName);
	}

	
}
