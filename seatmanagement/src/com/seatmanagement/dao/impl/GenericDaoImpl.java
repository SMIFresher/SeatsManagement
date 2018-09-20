package com.seatmanagement.dao.impl;

import java.util.List;

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
	public T getById(T t,int id) {
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
	
	
	

}
