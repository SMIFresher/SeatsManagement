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
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Employee;
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
		logger.info(
				"DAO: SeatingDetailsDaoImpl Method : saveSeatingDetailsInbatch started at : " + LocalDateTime.now());
		deleteByIdInBatch(seatingId);
		Block block = new Block();
		Seating seating = new Seating();
		Systems system=new Systems();
		Employee employee=new Employee();
		
		seating = genericdaoSeating.getById(seating, seatingId);
		int old_count = seating.getSystemOccupied();
		block=seating.getBlock();
		int block_capacity = Integer.parseInt(block.getBlockCapacity());
		int new_count = 0,seat_occupied=0;
		
		for (SeatingDetails sd : seatingDetails) {
			if (!(sd.getSeatingSystemNo().trim().equals("Emptydesk") || sd.getSeatingSystemNo().equals("Exit"))) {
				new_count++;
			}
		}
		int total_count = old_count + new_count;
		if (total_count <= block_capacity) {
			for (SeatingDetails sd : seatingDetails) {
				String systemName = sd.getSeatingSystemNo().trim();
				if (!(sd.getSeatingSystemNo().trim().equalsIgnoreCase("Emptydesk") || sd.getSeatingSystemNo().equalsIgnoreCase("Exit"))) {	
				system = systemService.getSystemBySystemName(systemName);
				employee=system.getEmployee();
				if(employee!=null) {
					seat_occupied++;
				}
				sd.setSystem(system);
				}
				sd.setSeating(seating);
				genericDaoSeatingDetails.saveOrUpdate(sd);
			}
				seating.setSeat_occupied(seat_occupied);
				seating.setSystemOccupied(total_count);
				genericdaoSeating.saveOrUpdate(seating);
		}
		/*
		 * DetachedCriteria blockcriteria = DetachedCriteria.forClass(Employee.class)
		 * .add(Restrictions.eq("employee.employeeId", employee_id));
		 * .setProjection(Projections.rowCount()); Integer count = (Integer)
		 * blockcriteria.uniqueResult(); int total=new_count + count;
		 * 
		 * 
		 * sd.setSeating(seating); String systemName = sd.getSeatingSystemNo();
		 * sd.setSystem(system.getSystemId(systemName.trim()));
		 * genericDaoSeatingDetails.saveOrUpdate(sd);
		 */
		else {
			// throw exception
			throw new ApplicationException("Error while saving");
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
	
	@Override
	public void deleteSeatingDetailById(UUID seatingDetailsId) {
		logger.info(
				"Service: SeatingDetailsServiceImpl Method : deleteSeatingDetailById started at : " + LocalDateTime.now());

		SeatingDetails seatingDetails= (SeatingDetails) genericDaoSeatingDetails.getById(new SeatingDetails(), seatingDetailsId);

		// Scenario 1 : seatingDetails does not exist
		if (Objects.isNull(seatingDetails)) {
			throw new ApplicationException("SeatingDetail record not found");
		}
		// Scenario 2 : seatingDetails exists
		else {

			genericDaoSeatingDetails.delete(seatingDetails);
		}

		logger.info(
				"Service: SeatingDetailsServiceImpl Method : deleteSeatingDetailById ended at : " + LocalDateTime.now());
		
	}

}
