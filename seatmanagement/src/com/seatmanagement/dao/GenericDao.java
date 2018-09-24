package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Organisation;

public interface GenericDao<T> {

	public boolean saveOrUpdate(T t);
	public T getById(T t,UUID id);
	public List getAll(T t);
	public boolean delete(T t);
	public boolean saveAll(List<T> objectList);
	public boolean deleteById(T t, UUID organisationId);
	
}
 