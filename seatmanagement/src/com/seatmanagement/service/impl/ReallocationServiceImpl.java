package com.seatmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.model.Reallocation;
import com.seatmanagement.service.ReallocationService;

public class ReallocationServiceImpl implements ReallocationService{

	@Autowired
	private ReallocationDao reallocationDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	public Reallocation getReallocationByEmployeeId(String employeeId) {
		// Must implement logic through employee dao
		Reallocation reallocation = reallocationDao.getReallocationByEmployeeId(employeeId);
		return reallocation;
	}

	@Override
	public void saveReallocation(Reallocation reallocation) {
		genericDao.saveOrUpdate(reallocation);
	}

	@Override
	public void updateReallocation(Reallocation reallocation) {
		genericDao.saveOrUpdate(reallocation);
	}

	@Override
	public void deleteReallocation(Reallocation reallocation) {
		genericDao.delete(reallocation);
	}

}
