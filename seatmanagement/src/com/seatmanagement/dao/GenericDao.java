package com.seatmanagement.dao;

import java.util.List;

public interface GenericDao<T> {

	public boolean saveOrUpdate(T t);
	public T getById(T t,int id);
	public List getAll(T t);
	public boolean delete(T t);
	
	
}
 