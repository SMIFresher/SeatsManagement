package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SystemService;

/**
 * 
 * @author prithivi raj This class provides implementation for all business
 *         logic related processing to 'System' model object
 *
 */
@Transactional
@Service
public class SystemServiceImpl implements SystemService {

	private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

	@Autowired
	GenericDao genericDao;

	@Autowired
	GenericDao<Employee> genericDaoEmp;

	@Autowired
	SystemDao systemDao;

	@Override
	public List<Systems> getAllSystems() {

		logger.info("Service: SystemServiceImpl Method : Get all systems started at : " + LocalDateTime.now());

		Systems system = new Systems();
		List<Systems> list = genericDao.getAll(system);

		logger.info("Service: SystemServiceImpl Method : Get all systems ended at : " + LocalDateTime.now());
		return list;
	}

	@Override
	public Systems getById(Systems system, UUID System_id) {

		logger.info("Service: SystemServiceImpl Method : get By Id started at : " + LocalDateTime.now());

		return (Systems) genericDao.getById(system, System_id);

	}

	@Override
	public boolean delete(Systems system) {

		logger.info("Service: SystemServiceImpl Method : delete by id started at : " + LocalDateTime.now());

		return genericDao.delete(system);
	}

	@Override
	public void addOrUpdateSystem(Systems system, UUID employeeId, List<UUID> additionalDevicesUUIDs) {

		logger.info("Service: SystemServiceImpl Method : addOrUpdateSystem started at : " + LocalDateTime.now());

		Employee employee = new Employee();

		// Employee Mapping
		// Scenario 1: employee id is null
		if (Objects.isNull(employeeId)) {

		}
		// Scenario 2: employee id is present
		else {
			employee = genericDaoEmp.getById(employee, employeeId);
			system.setEmployee(employee);
		}

		// AdditionalDevice mapping
		// Scenario 1: No AdditionDevice IDs in request
		if (Objects.isNull(additionalDevicesUUIDs) || additionalDevicesUUIDs.isEmpty()) {

		}
		// Scenario 2: AdditionDevice IDs present in request
		else {
			Set<AdditionalDevice> additionalDevices = new HashSet<AdditionalDevice>();
			for (UUID additionalDeviceID : additionalDevicesUUIDs) {
				AdditionalDevice additionalDevice = new AdditionalDevice();
				additionalDevice = (AdditionalDevice) genericDao.getById(additionalDevice, additionalDeviceID);

				additionalDevices.add(additionalDevice);
			}

			system.setAdditionalDevice(additionalDevices);
		}

		// TO sync system from view with already existing system in hibernate
		// persistence context we use merge
		if (Objects.nonNull(system.getSystemId()) && (Objects.nonNull(system.getEmployee())
				|| (Objects.nonNull(system.getAdditionalDevice()) && (system.getAdditionalDevice().size() > 0)))) {
			systemDao.mergeSystem(system);
		} else {
			genericDao.saveOrUpdate(system);
		}

		logger.info("Service: SystemServiceImpl Method : addOrUpdateSystem ended at : " + LocalDateTime.now());
	}

	public Systems getSystem(String request) throws BusinessException {

		logger.info("Service: SystemServiceImpl Method : getSystem started at : " + LocalDateTime.now());

		Systems system;
		List<Systems> list = (List<Systems>) systemDao.getSystem(request);

		if (list.isEmpty()) {
			throw new BusinessException("Enter a valid Id");
		} else {
			system = list.get(0);
		}
		logger.info("Service: SystemServiceImpl Method : getSystem ended at : " + LocalDateTime.now());
		return system;
	}

	@Override
	public Systems getSystemBySystemName(String systemName) {

		logger.info("Service: SystemServiceImpl Method : getSystemBySystemName started at : " + LocalDateTime.now());
		
		Systems system;
		try {
		List<Systems> systemList = systemDao.getSystemId(systemName);
		system = systemList.get(0);
		}
		catch(Exception e) {
			throw new ApplicationException("can't retrive system details");		
		}
		logger.info("Service: SystemServiceImpl Method : getSystemBySystemName ended at : " + LocalDateTime.now());
		return system;
	}

	@Override
	public List<Systems> getOscount() {

		logger.info("Service: SystemServiceImpl Method : getOscount started at : " + LocalDateTime.now());

		Systems system = new Systems();
		List<Systems> list = systemDao.getOs(system);

		logger.info("Service: SystemServiceImpl Method : getOscount ended at : " + LocalDateTime.now());
		return list;

	}

	@Override
	public List<Systems> getAllAvailableSystems() {
		logger.info("Service: SystemServiceImpl Method : getAllAvailableSystems started at : " + LocalDateTime.now());

		Systems system = new Systems();
		List<Systems> list = systemDao.getAllAvailableSystems(system);
		 List<Systems> distinctElements = list.stream()
                 .filter( distinctByKey(p -> p.getSystemId()) )
                 .collect( Collectors.toList() );
		 

		logger.info("Service: SystemServiceImpl Method : getAllAvailableSystems ended at : " + LocalDateTime.now());
		return distinctElements;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public void updateReallocation(UUID systemId) {
		logger.info("Service: SystemServiceImpl Method : updateReallocation started at : " + LocalDateTime.now());
		Systems system=new Systems();
		system=(Systems) genericDao.getById(system, systemId);
		system.getEmployee();
		system.setEmployee(null);
		genericDao.saveOrUpdate(system);
		logger.info("Service: SystemServiceImpl Method : updateReallocation ended at : " + LocalDateTime.now());

	}

}
