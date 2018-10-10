package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.SeatingDetailsService;
import com.seatmanagement.service.SystemService;

/**
 * 
 * @author vgs-user
 *
 *         This class provides implementation for all business logic related
 *         processing to 'SeatingDetails' model object
 */

@Service
@Transactional
public class SeatingDetailsServiceImpl implements SeatingDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationServiceImpl.class);
	
	@Autowired
	SeatingDetailsDao seatingDetailsDao;

	@Autowired
	GenericDao<SeatingDetails> genericDaoSeatingDetails;

	@Autowired
	GenericDao<Seating> genericdaoSeating;

	@Autowired
	SystemDao systemDao;
	
	@Autowired
	SystemService systemService;

	@Override
	public List<SeatingDetails> getAllSeatingDetails() {
		logger.info("Service: SeatingDetailsServiceImpl Method : getAllSeatingDetails started at : " + LocalDateTime.now());
		@SuppressWarnings("unchecked")
		SeatingDetails seatingDetails = new SeatingDetails();
		List<SeatingDetails> list = genericDaoSeatingDetails.getAll(seatingDetails);
		logger.info("Service: SeatingDetailsServiceImpl Method : getAllSeatingDetails ended at : " + LocalDateTime.now());
		return list;
	}

	@Override
	public void saveSeatingDetails(SeatingDetails seatingDetails) {
		logger.info("Service: SeatingDetailsServiceImpl Method : saveSeatingDetails started at : " + LocalDateTime.now());
		// seatingDetailsDao.getAllSeatingDetails();
		genericDaoSeatingDetails.saveOrUpdate(seatingDetails);
		// seatingDetailsDao.saveSeatingDetails(seatingDetails);
		logger.info("Service: SeatingDetailsServiceImpl Method : saveSeatingDetails ended at : " + LocalDateTime.now());
	}

	/*
	 * @Override public List<SeatingDetails> getEmployeeBySeatId(SeatingDetails
	 * seatingdetails, UUID seating_id) { return
	 * seatingDetailsDao.getEmployeeBySeatId(seatingdetails, seating_id); }
	 */
	@Override
	public SeatingDetails getEmployeeBySeatId(SeatingDetails seatingdetails, UUID seating_id) {
		logger.info("Service: SeatingDetailsServiceImpl Method : getEmployeeBySeatId started at : " + LocalDateTime.now());
		//return seatingDetailsDao.getEmployeeBySeatId(seatingdetails, seating_id);
		SeatingDetails seatingDetails = seatingDetailsDao.getEmployeeBySeatId(seatingdetails, seating_id);
		logger.info("Service: SeatingDetailsServiceImpl Method : getEmployeeBySeatId ended at : " + LocalDateTime.now());
		return seatingDetails;
	}

	@Override
	public SeatingDetails getSeatByEmployeeId(SeatingDetails seatingdetails, UUID employee_id) {
		logger.info("Service: SeatingDetailsServiceImpl Method : getSeatByEmployeeId started at : " + LocalDateTime.now());
		SeatingDetails seatingDetails = seatingDetailsDao.getSeatByEmployeeId(seatingdetails, employee_id);
		logger.info("Service: SeatingDetailsServiceImpl Method : getSeatByEmployeeId ended at : " + LocalDateTime.now());
		//return seatingDetailsDao.getSeatByEmployeeId(seatingdetails, employee_id);
		return seatingDetails;
	}

	public void saveSeatingDetailsInbatch(SeatingDetails[] seatingDetails, UUID seatingId) throws BusinessException {
		logger.info("Service: SeatingDetailsServiceImpl Method : saveSeatingDetailsInbatch started at : " + LocalDateTime.now());
		
		  deleteByIdInBatch(seatingId);
		 
		  Seating seating=new Seating();
		  seating = genericdaoSeating.getById(seating,seatingId);
		  
		  for(SeatingDetails sd:seatingDetails) { 
		
			sd.setSeating(seating);
			String systemName = sd.getSeatingSystemNo().trim();
			
			if(!systemName.equalsIgnoreCase("Emptydesk") && !systemName.equalsIgnoreCase("Exit")) {
				sd.setSystem(systemService.getSystemBySystemName(systemName));
			}
			
			genericDaoSeatingDetails.saveOrUpdate(sd);
		
		logger.info("Service: SeatingDetailsServiceImpl Method : saveSeatingDetailsInbatch started at : " + LocalDateTime.now());	  	
		
	 }
		 

		/*seatingDetailsDao.saveSeatingDetailsInbatch(seatingDetails, seatingId);*/
		logger.info("Service: SeatingDetailsServiceImpl Method : saveSeatingDetailsInbatch ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteByIdInBatch(UUID seatingId) {
		logger.info("Service: SeatingDetailsServiceImpl Method : deleteByIdInBatch started at : " + LocalDateTime.now());
		/*
		 * List<SeatingDetails> sd=getAllSeatingDetails();
		 * //hibernateTemplate.deleteAll(sd); for(SeatingDetails sd1:sd) {
		 * hibernateTemplate.delete(sd1); }
		 */

		seatingDetailsDao.deleteByIdInBatch(seatingId);
		logger.info("Service: SeatingDetailsServiceImpl Method : deleteByIdInBatch ended at : " + LocalDateTime.now());
	}

	@Override
	public List<SeatingDetails> getSeatingDetailsBySeatingId(UUID seatingId) {
		logger.info("Service: SeatingDetailsServiceImpl Method : getSeatingDetailsBySeatingId started at : " + LocalDateTime.now());
		//return seatingDetailsDao.getSeatingDetailsBySeatingId(seatingId);
		List<SeatingDetails> seatingDetails = seatingDetailsDao.getSeatingDetailsBySeatingId(seatingId);
		logger.info("Service: SeatingDetailsServiceImpl Method : getSeatingDetailsBySeatingId ended at : " + LocalDateTime.now());
		return seatingDetails;
	}

}
