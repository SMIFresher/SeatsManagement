package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Employee;

public interface GenericDao<T> {

	public T saveOrUpdate(T t);
	public T getById(T t,UUID id);
	public List getAll(T t) ;
	public boolean delete(T t);
	public void saveAll(List<T> objectsList) ;
	
}
 