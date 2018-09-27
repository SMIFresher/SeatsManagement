package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;
import com.seatmanagement.model.Utilities;

public interface UtilitiesService {
	public boolean saveOrUpdate(Utilities utility);

	public List<Utilities> getAll();

	public Utilities getById(Utilities utility,UUID utilityId);

	public boolean delete(Utilities utility);

}
