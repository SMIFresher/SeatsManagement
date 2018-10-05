package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.controller.SystemController;
import com.seatmanagement.dao.GenericDao;

@Transactional
public class GenericDaoImpl<T> implements GenericDao<T>{
	
	@Autowired
	public HibernateTemplate hibernateTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);
	
	 public T saveOrUpdate(T t) {
			hibernateTemplate.saveOrUpdate(t);
			return t;
		}
	 
	public T getById(T t,UUID id) {
		return (T) hibernateTemplate.get(t.getClass(),id);	
		
	}
	
	public List getAll(T t) {
		List<T> list = (List<T>) hibernateTemplate.loadAll(t.getClass());
		return list;
	}
	
	public boolean delete(T t) {
		boolean b=false;
		
			hibernateTemplate.delete(t);
			b=true;
		
		return b;  
	}

	
	@Override
	public void saveAll(List<T> objectsList) {
		for(T obj : objectsList) {
			hibernateTemplate.saveOrUpdate(obj);
		}
	}

}
