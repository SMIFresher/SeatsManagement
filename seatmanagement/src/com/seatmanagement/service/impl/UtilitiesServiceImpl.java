package com.seatmanagement.service.impl;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.UtilitiesDao;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Utilities;
import com.seatmanagement.service.UtilitiesService;


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
	public Utilities saveOrUpdate(Utilities utilities) {
		
		return genericDao.saveOrUpdate(utilities);
	}
	
	public List<Utilities> getAll() {
		
		Utilities util=new Utilities();
		return genericDao.getAll(util);
		
	}

	public Utilities getById(Utilities utilities, UUID utilityId) {
		return genericDao.getById(utilities, utilityId);
	}

	public boolean delete(Utilities utilities) {
		return genericDao.delete(utilities);

	}

}
