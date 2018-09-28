package com.workspacemanagement.service.impl;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.dao.UtilitiesDao;
import com.workspacemanagement.model.AdditionalDevice;
import com.workspacemanagement.model.Utilities;
import com.workspacemanagement.service.UtilitiesService;


@Service
public class UtilitiesServiceImpl implements UtilitiesService {

	@Autowired
	GenericDao<Utilities> genericDao;
	@Autowired
	UtilitiesDao utilitiesDao;

	/*public boolean saveOrUpdate(Utilities utility) {
		
		Utilities newUtilities = new Utilities();
		newUtilities.setUtilityName(utility.getUtilityName());
		return genericDao.saveOrUpdate( newUtilities);

	}
*/
	@Override
	public boolean saveOrUpdate(Utilities utilities) {
		
		return genericDao.saveOrUpdate(utilities);
	}
	
	public List<Utilities> getAll() {
		return utilitiesDao.getAll();
	}

	public Utilities getById(Utilities utilities, UUID utilityId) {
		return genericDao.getById(utilities, utilityId);
	}

	public boolean delete(Utilities utilities) {
		return genericDao.delete(utilities);

	}

}
