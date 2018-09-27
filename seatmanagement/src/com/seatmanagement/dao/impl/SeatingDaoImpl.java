package com.seatmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Seating;

@Transactional
@Repository
public class SeatingDaoImpl implements SeatingDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Seating> getAll(Seating seating, UUID block_id) {
		//List<Seating> seatingList = new ArrayList<>();
		Block block = new Block();
		block.setBlockId(block_id);
		/*seatingList =  (List<Seating>) hibernateTemplate.find("From Seating  where block_id = '" +block_id + "'" );
		return seatingList;*/
		DetachedCriteria criteria = DetachedCriteria.forClass(Seating.class);
		criteria.add(Restrictions.eq("block", block));
		List<Seating> seat =  (List<Seating>) hibernateTemplate.findByCriteria(criteria);
		return seat;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Seating> getAllSeating() {
		List<Seating> seatingList = new ArrayList<>();
		seatingList = (List<Seating>) hibernateTemplate.find("From Seating");
		return seatingList;
	}

	@Override
	public List<Seating> getSeatingByBlockId(UUID blockId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Seating.class);
		criteria.add(Restrictions.eq("block.blockId", blockId));

		List<Seating> seatings = (List<Seating>) hibernateTemplate.findByCriteria(criteria);
		return seatings;
	}

}
