package com.seatmanagement.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.service.GenericDaoService;

@Service
public class GenericDaoServiceImpl<T> implements GenericDaoService<T> {

	@Autowired
	private GenericDao<T> genericDao;
	
	@Override
	public void insert(T t) {
		
		genericDao.insert(t);
	}

	@Override
	public T getById(T t, int id) {
		
		return genericDao.getById(t, id);
	}

	@Override
	public List getAll(T t) {
		
		return genericDao.getAll(t);
	}

	@Override
	public boolean delete(T t) {
		
		return genericDao.delete(t);
	}

	@Override
	public boolean update(T t) {
		
		return genericDao.update(t);
	}

}
