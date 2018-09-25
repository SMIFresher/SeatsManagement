package com.seatmanagement.dao.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.model.Floor;


@Transactional
@Repository
public class FloorDaoImpl implements FloorDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Floor> getAll() {
		
		List<Floor> floorList = new ArrayList<>();
		floorList=	(List<Floor>) hibernateTemplate.find("From Floor");
		return floorList;
	}
	
	

}