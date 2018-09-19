package com.seatmanagement.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.seatmanagement.dao.genericDao;

import com.seatmanagement.util.*;

import net.bytebuddy.description.type.TypeDescription.Generic;

public class GenericDaoImpl<T> implements genericDao<T>{
	
	public static HibernateTemplate hibernateTemplate;

	 public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		HibernateUtil.hibernateTemplate = hibernateTemplate;
	}
	
	public void insert(T t) {
		HibernateUtil.hibernateTemplate.save(t);
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
	
	public boolean update(T t) {
		boolean b=false;
		
		try{
			HibernateUtil.hibernateTemplate.saveOrUpdate(t);
			b=true;
			System.out.println("updated");
		}catch(Exception e) {
			System.out.println(e);
		}
		return b;
	}
	

}
