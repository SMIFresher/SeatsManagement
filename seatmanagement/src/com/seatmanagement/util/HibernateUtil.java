package com.seatmanagement.util;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HibernateUtil {

	public static HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		HibernateUtil.hibernateTemplate = hibernateTemplate;
	}
}
