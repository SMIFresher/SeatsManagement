package com.seatmanagement.dao.impl;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.BuildingDao;


@Repository
@Transactional
public class BuildingDaoImpl implements BuildingDao{

	public static HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		BuildingDaoImpl.hibernateTemplate = hibernateTemplate;
	}
}
