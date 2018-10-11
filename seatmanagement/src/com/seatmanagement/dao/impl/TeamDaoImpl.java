package com.seatmanagement.dao.impl;

import java.time.LocalDateTime;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.TeamDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Team;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class provides implementation for all Database related
 *         activities to 'Team' model object
 *
 */
@Transactional
public class TeamDaoImpl implements TeamDao {

	private static final Logger logger = LoggerFactory.getLogger(TeamDaoImpl.class);

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Team getTeamByName(String teamName) {

		logger.info("DAO: TeamDaoImpl Method : getTeamByName started at : " + LocalDateTime.now());

		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("teamName", teamName));

		Team team = null;
		try {
			team = (Team) hibernateTemplate.findByCriteria(criteria);
		}catch(Exception e) {
			throw new ApplicationException("Error while retrieving team", e);
		}

		logger.info("DAO: TeamDaoImpl Method : getTeamByName ended at : " + LocalDateTime.now());

		return team;
	}

}
