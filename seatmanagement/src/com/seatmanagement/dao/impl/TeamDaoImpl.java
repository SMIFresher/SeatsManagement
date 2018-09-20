package com.seatmanagement.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.TeamDao;
import com.seatmanagement.model.Team;

@Transactional
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Team getTeamByName(String teamName) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("teamName", teamName));

		Team team = (Team) hibernateTemplate.findByCriteria(criteria);
		
		return team;
	}

	@Override
	public Team getTeamById(String teamId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("teamId", teamId));

		Team team = (Team) hibernateTemplate.findByCriteria(criteria);
		return team;
	}

	@Override
	public void deleteTeamById(String teamId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
		criteria.add(Restrictions.eq("teamId", teamId));

		Team team = (Team) hibernateTemplate.findByCriteria(criteria);
		hibernateTemplate.delete(team);
	}

}
