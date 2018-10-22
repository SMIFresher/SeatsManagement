package com.seatmanagement.dao.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;

@Transactional
@Repository
public class SeatingDetailsDaoImpl implements SeatingDetailsDao {

	private static final Logger logger = LoggerFactory.getLogger(TeamDaoImpl.class);

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<SeatingDetails> getAllSeatingDetails() {
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : getAllSeatingDetails started at : " + LocalDateTime.now());
		
		List<SeatingDetails> seatingList = null;
		try {
			seatingList = (List<SeatingDetails>) hibernateTemplate.find("From SeatingDetails");
		}catch(Exception e) {
			throw new ApplicationException("Error while retreiving SeatingDetails", e);
		}
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : getAllSeatingDetails ended at : " + LocalDateTime.now());
		return seatingList;
	}

	/*
	 * @Override public void saveSeatingDetails(SeatingDetails seatingDetails) {
	 * hibernateTemplate.save(seatingDetails); }
	 */

	@Override
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : getEmployeeBySeatId started at : " + LocalDateTime.now());
		
		SeatingDetails seatingDetails = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
		criteria.add(Restrictions.eq("seating.seatingId", seating_id));
		
		try {
			seatingDetails = (SeatingDetails) hibernateTemplate.findByCriteria(criteria).get(0);
		}
		catch(Exception e) {
			throw new ApplicationException("Error while retreiving SeatingDetail", e);
		}
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : getEmployeeBySeatId ended at : " + LocalDateTime.now());
		return seatingDetails;
	}

	@Override
	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id) {
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : getSeatByEmployeeId started at : " + LocalDateTime.now());
		
		SeatingDetails seatingDetails = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);
		criteria.createAlias("systems", "systems");
		criteria.add(Restrictions.disjunction());
		criteria.add(Restrictions.eq("systems.employee", employee_id));
		
		try {
			seatingDetails = (SeatingDetails) hibernateTemplate.findByCriteria(criteria).get(0);
		}
		catch(Exception e) {
			throw new ApplicationException("Error whi;e retreiving SeatingDetail", e);
		}
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : getSeatByEmployeeId ended at : " + LocalDateTime.now());
		return seatingDetails;
	}

	@Override
	public SeatingDetails deleteBySeatingId(UUID seatingId) {
		
		logger.info("DAO: SeatingDetailsDaoImpl Method : deleteBySeatingId started at : " + LocalDateTime.now());
		logger.info("DAO: SeatingDetailsDaoImpl Method : deleteBySeatingId ended at : " + LocalDateTime.now());
		return null;
	}

	public void saveSeatingDetailsInbatch(SeatingDetails[] seatingDetails, UUID seatingId) throws BusinessException {
		
		/*logger.info(
				"DAO: SeatingDetailsDaoImpl Method : saveSeatingDetailsInbatch started at : " + LocalDateTime.now());
		
		deleteByIdInBatch(seatingId);

		Seating seating = new Seating();
		seating = genericdaoSeating.getById(seating, seatingId);

		for (SeatingDetails sd : seatingDetails) {
			sd.setSeating(seating);
			String systemName = sd.getSeatingSystemNo();
			
			
			sd.setSystem(systemDao.getSystemId(systemName.trim()));
			genericDaoSeatingDetails.saveOrUpdate(sd);
		}

		logger.info("DAO: SeatingDetailsDaoImpl Method : saveSeatingDetailsInbatch ended at : " + LocalDateTime.now());
*/
	}

	@Override
	public void deleteByIdInBatch(UUID seatingId) {
		logger.info("DAO: SeatingDetailsDaoImpl Method : deleteByIdInBatch started at : " + LocalDateTime.now());
		List<SeatingDetails> sd = getSeatingDetailsBySeatingId(seatingId);
		for (SeatingDetails sd1 : sd) {
			hibernateTemplate.delete(sd1);
		}
		logger.info("DAO: SeatingDetailsDaoImpl Method : deleteByIdInBatch ended at : " + LocalDateTime.now());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeatingDetails> getSeatingDetailsBySeatingId(UUID seatingId) {
		logger.info(
				"DAO: SeatingDetailsDaoImpl Method : getSeatingDetailsBySeatingId started at : " + LocalDateTime.now());

		List<SeatingDetails> seatingDetailsList = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SeatingDetails.class);

		criteria.createAlias("seating", "seating");
		criteria.add(Restrictions.eq("seating.seatingId", seatingId));
		//criteria.add(Restrictions.eq("seating.systemId", seatingId));
		criteria.createAlias("system", "system", CriteriaSpecification.LEFT_JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		seatingDetailsList = (List<SeatingDetails>) hibernateTemplate.findByCriteria(criteria);

		logger.info(
				"DAO: SeatingDetailsDaoImpl Method : getSeatingDetailsBySeatingId ended at : " + LocalDateTime.now());
		return seatingDetailsList;
	}

}
