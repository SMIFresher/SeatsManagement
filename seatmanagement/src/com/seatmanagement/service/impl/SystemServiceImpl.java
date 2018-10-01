package com.seatmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

@Transactional
public class SystemServiceImpl implements SystemService{

	@Autowired
	GenericDao<Systems> genericDao;
	
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
		return genericDao.getById(system, System_id);
	}

	@Override
	public boolean delete(Systems system) {
		return genericDao.delete(system);
	}



	@Override
	public boolean addOrUpdateSystem(Systems system,UUID employeeId,Object [] SystemAdditionalDevice) throws JsonProcessingException {
		Employee employee= new Employee();
		boolean f = false;
		if(Objects.isNull(employeeId)) {
			f = genericDao.saveOrUpdate(system);
		}else {
		employee = genericDaoEmp.getById(employee, employeeId);
		/*updaatedSystem.setAllotmentStatus(system.getAllotmentStatus());
		updaatedSystem.setNetworkType(system.getNetworkType());
		updaatedSystem.setOperatingSystem(system.getOperatingSystem());
		updaatedSystem.setSystemName(system.getSystemName());
		updaatedSystem.setSystemType(system.getSystemType());
		updaatedSystem.setEmployee(employee);*/
		system.setEmployee(employee);
		f = genericDao.saveOrUpdate(system);
		for(int i=0; i<= SystemAdditionalDevice.length; i++) {
			/*ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(SystemAdditionalDevice[i]);
			System.out.println(json);*/
			System.out.println(SystemAdditionalDevice[i]);
			
		}
		}
		return f;
	}

	

	public  Systems getSystem(String request) {
		return systemDao.getSystem(request);
	}

	@Override
	public Systems getSystemBySystemName(String systemName) {
		
		return systemDao.getSystemId(systemName);
	}



	
}
