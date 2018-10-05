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
		 
		 	logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl) Method : saveOrUpdate started at : " + LocalDateTime.now());
		 
			hibernateTemplate.saveOrUpdate(t);
			
			logger.info("Dao: GenericDaoImpl Method : saveOrUpdate ended at : " + LocalDateTime.now());
			
			return t;
		}
	 
	public T getById(T t,UUID id) {
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : getById started at : " + LocalDateTime.now());
		
		return (T) hibernateTemplate.get(t.getClass(),id);	
		
	}
	
	public List getAll(T t) {
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : getAll started at : " + LocalDateTime.now());
		
		List<T> list = (List<T>) hibernateTemplate.loadAll(t.getClass());
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : saveOrUpdate ended at : " + LocalDateTime.now());
		return list;
	}
	
	public boolean delete(T t) {
		boolean b=false;
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : delete started at : " + LocalDateTime.now());
		
			hibernateTemplate.delete(t);
			b=true;
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : delete ended at : " + LocalDateTime.now());
		return b;  
	}

	
	@Override
	public void saveAll(List<T> objectsList) {
		for(T obj : objectsList) {
			
			logger.info("Dao: "+obj.getClass().getSimpleName()+"(GenericDaoImpl)  Method : saveAll started at : " + LocalDateTime.now());
			
			hibernateTemplate.saveOrUpdate(obj);
			
			logger.info("Dao: "+obj.getClass().getSimpleName()+"(GenericDaoImpl)  Method : saveAll ended at : " + LocalDateTime.now());
		}
	}

}
