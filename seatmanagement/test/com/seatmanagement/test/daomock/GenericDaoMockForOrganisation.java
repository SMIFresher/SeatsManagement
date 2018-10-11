package com.seatmanagement.test.daomock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.Organisation;

public class GenericDaoMockForOrganisation<T> implements GenericDao<T> {
	 public T saveOrUpdate(T t){
		 
			return t;
		}
	 
	public T getById(T t,UUID id) {
		return t;		
		
	}
	
	public List getAll(T t) {
		
		List<T> list = new ArrayList<>();
		list.add((T) new Organisation());
		list.add((T) new Organisation());
		
		return list;
	}
	
	public boolean delete(T t) {
		boolean b=true;
		return b;  
	}

	
	@Override
	public void saveAll(List<T> objectsList) {
	}
}
