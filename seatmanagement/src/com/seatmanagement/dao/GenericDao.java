package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

public interface GenericDao<T> {

	public boolean saveOrUpdate(T t);
	public T getById(T t,UUID id);
	public List getAll(T t);
	public boolean delete(T t);
	public <T> boolean  saveAll(T[] objectList);
	
}
 