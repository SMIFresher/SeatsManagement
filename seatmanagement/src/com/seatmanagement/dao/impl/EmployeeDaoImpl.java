package com.seatmanagement.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.EmployeeDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Employee;

@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Employee> getEmployeesByDesignation(String designation) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("designation", designation));
		List<Employee> employees = null;
		try {
			employees = (List<Employee>) hibernateTemplate.findByCriteria(criteria);
		}catch(Exception e) {
			throw new ApplicationException("Error while retreiving employees", e);
		}
		
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByTeamId(UUID teamId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("team.teamId", teamId));
		List<Employee> employees = null;
		try {
			employees = (List<Employee>) hibernateTemplate.findByCriteria(criteria);
		}catch(Exception e) {
			throw new ApplicationException("Error while retreiving employees", e);
		}
		
		return employees;
	}

}
