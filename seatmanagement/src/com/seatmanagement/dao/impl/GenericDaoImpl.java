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
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;

@Transactional
public class GenericDaoImpl<T> implements GenericDao<T>{
	
	@Autowired
	public HibernateTemplate hibernateTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);
	
	 public T saveOrUpdate(T t){
		 
		 	logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl) Method : saveOrUpdate started at : " + LocalDateTime.now());
		 
		 	try {
			hibernateTemplate.saveOrUpdate(t);
		 	}
		 	catch(Exception e) {
				throw new ApplicationException("Error while inserting records", e);
			}
			
		 	logger.info("Dao: GenericDaoImpl Method : saveOrUpdate ended at : " + LocalDateTime.now());
			return t;
		}
	 
	public T getById(T t,UUID id) {
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : getById started at : " + LocalDateTime.now());
		
		try{
			t=(T) hibernateTemplate.get(t.getClass(),id);	
		}
		catch(Exception e) {
			throw new ApplicationException("Error while retriving records", e);
		}
		
		logger.info("Dao: GenericDaoImpl Method : getById ended at : " + LocalDateTime.now());
		return t;		
		
	}
	
	public List getAll(T t) {
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : getAll started at : " + LocalDateTime.now());
		
		List<T> list;
		try {
			list = (List<T>) hibernateTemplate.loadAll(t.getClass());
		}
		catch(Exception e) {
			throw new ApplicationException("Error while retriving records", e);
		}
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : saveOrUpdate ended at : " + LocalDateTime.now());
		return list;
	}
	
	public boolean delete(T t) {
		boolean b=false;
		
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : delete started at : " + LocalDateTime.now());
		
			try{
				hibernateTemplate.delete(t);
				b=true;
			}
			catch (Exception e) {
				throw new ApplicationException("Error while deleting records", e);
			}
			
		logger.info("Dao: "+t.getClass().getSimpleName()+"(GenericDaoImpl)  Method : delete ended at : " + LocalDateTime.now());
		return b;  
	}

	
	@Override
	public void saveAll(List<T> objectsList) {
		for(T obj : objectsList) {
			
			logger.info("Dao: "+obj.getClass().getSimpleName()+"(GenericDaoImpl)  Method : saveAll started at : " + LocalDateTime.now());
			
			try{
				hibernateTemplate.saveOrUpdate(obj);
			}
			catch(Exception e) {
				throw new ApplicationException("Error while inserting bulk records", e);
			}
			
			logger.info("Dao: "+obj.getClass().getSimpleName()+"(GenericDaoImpl)  Method : saveAll ended at : " + LocalDateTime.now());
		}
	}

}
