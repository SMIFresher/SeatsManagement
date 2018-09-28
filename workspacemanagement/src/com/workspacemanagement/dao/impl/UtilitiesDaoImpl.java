package com.workspacemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workspacemanagement.dao.UtilitiesDao;
import com.workspacemanagement.model.Utilities;

@Transactional
@Repository
public class UtilitiesDaoImpl implements UtilitiesDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Utilities> getAll() {
		List<Utilities> utilityList = new ArrayList<>();
		utilityList = (List<Utilities>) hibernateTemplate.find("From Utilities");
		return utilityList;
	}
}
