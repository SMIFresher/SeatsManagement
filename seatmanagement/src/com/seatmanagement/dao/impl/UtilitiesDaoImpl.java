package com.seatmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.seatmanagement.dao.UtilitiesDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Utilities;
/**
 * 
 * @author Harshanaa Ramdas
 * This class provides implementation for all Database related
 * activities to 'Utilities' model object
 *
 */
@Transactional
@Repository
public class UtilitiesDaoImpl implements UtilitiesDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	/**
	 * 
	 * @return
	 * @throws BusinessException 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Utilities> getAll() throws BusinessException {
		List<Utilities> utilityList = null;
		try {
			utilityList = (List<Utilities>) hibernateTemplate.find("From Utilities");
		}catch(Exception e) {
			throw new BusinessException("Error while retreiving Utility records");
		}
		return utilityList;
	}
}
