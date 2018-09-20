package com.seatmanagement.service;

import java.util.List;

public interface GenericDaoService<T> {

	public void insert(T t);
	public T getById(T t,int id);
	public List getAll(T t);
	public boolean delete(T t);
	public boolean update(T t);
}
