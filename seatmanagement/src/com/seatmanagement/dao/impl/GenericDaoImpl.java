package com.seatmanagement.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.util.HibernateUtil;

public class GenericDaoImpl<T> implements GenericDao<T>{
	
	public static HibernateTemplate hibernateTemplate;

	 public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		HibernateUtil.hibernateTemplate = hibernateTemplate;
	}
	
	 public boolean saveOrUpdate(T t) {
			boolean b=false;
			
			try{
				HibernateUtil.hibernateTemplate.saveOrUpdate(t);
				b=true;
				//System.out.println("saved/updated");
			}catch(Exception e) {
				System.out.println(e);
			}
			return b;
		}
	public T getById(T t,UUID id) {
		return (T) HibernateUtil.hibernateTemplate.get(t.getClass(),id);	
		
	}
	
	public List getAll(T t) {
		List<T> list = (List<T>) HibernateUtil.hibernateTemplate.loadAll(t.getClass());
		return list;
	}
	
	public boolean delete(T t) {
		boolean b=false;
		try{
			
			HibernateUtil.hibernateTemplate.delete(t);
			b=true;
			System.out.println("deleeted");
		}catch(Exception e) {
			System.out.println(e); 
		}
		return b;  
	}

	@Override
	public boolean saveAll(List<T> objectList) {
		boolean b=false;
		try {
			for(Object obj : objectList) {
				HibernateUtil.hibernateTemplate.save(obj);
			}
			b=true;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		return b;
	}

	@Override
	public boolean deleteById(T t, UUID uuid) {
		boolean b=false;
		try{
			t = (T) HibernateUtil.hibernateTemplate.get(t.getClass(),uuid);
			
			if(null == t) {
				throw new RuntimeException("required data not in DB. Cannot continue DB process.");
			}
			
			HibernateUtil.hibernateTemplate.delete(t);
			b=true;
		}catch(Exception e) {
			throw new RuntimeException("DB operation Uncesuccessful");
		}
		return b; 
	}

}
