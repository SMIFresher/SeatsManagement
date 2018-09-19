package com.seatmanagement.dao;

import java.util.List;

public interface genericDao<T> {

	public void insert(T t);
	public T getById(T t,int id);
	public List getAll(T t);
	public boolean delete(T t);
	public boolean update(T t);
	
}
