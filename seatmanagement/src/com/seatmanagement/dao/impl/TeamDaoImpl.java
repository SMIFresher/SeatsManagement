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
import com.seatmanagement.model.Team;
import com.seatmanagement.service.impl.TeamServiceImpl;

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

		Team team = (Team) hibernateTemplate.findByCriteria(criteria);
		
		logger.info("DAO: TeamDaoImpl Method : getTeamByName ended at : " + LocalDateTime.now());
		
		return team;
	}

	@Override
	public Team getTeamById(String teamId) {
		
		logger.info("DAO: TeamDaoImpl Method : getTeamById started at : " + LocalDateTime.now());
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("teamId", teamId));

		Team team = (Team) hibernateTemplate.findByCriteria(criteria);
		
		logger.info("DAO: TeamDaoImpl Method : getTeamById ended at : " + LocalDateTime.now());
		
		return team;
	}

	@Override
	public void deleteTeamById(String teamId) {
		
		logger.info("DAO: TeamDaoImpl Method : deleteTeamById started at : " + LocalDateTime.now());
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("teamId", teamId));

		Team team = (Team) hibernateTemplate.findByCriteria(criteria);
		hibernateTemplate.delete(team);
		
		logger.info("DAO: TeamDaoImpl Method : deleteTeamById ended at : " + LocalDateTime.now());
	}

}
