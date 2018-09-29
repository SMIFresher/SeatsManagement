package com.seatmanagement.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;

@Transactional
public class GenericDaoImpl<T> implements GenericDao<T>{
	
	@Autowired
	public HibernateTemplate hibernateTemplate;
	
	 public boolean saveOrUpdate(T t) {
			boolean b=false;
			
			try{
				hibernateTemplate.saveOrUpdate(t);
				b=true;
				//System.out.println("saved/updated");
			}catch(Exception e) {
				System.out.println(e);
			}
			return b;
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
		try{
			
			hibernateTemplate.delete(t);
			b=true;
			System.out.println("deleeted");
		}catch(Exception e) {
			System.out.println(e); 
		}
		return b;  
	}

	@Override
	public <T>boolean saveAll(T[] objectList) {
		boolean b=false;
		try {
			for(T obj : objectList) {
				hibernateTemplate.saveOrUpdate(obj);
			}
			b=true;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		return b;
	}

}
