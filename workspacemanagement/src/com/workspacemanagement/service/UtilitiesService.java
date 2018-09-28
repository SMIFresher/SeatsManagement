package com.workspacemanagement.service;

import java.util.List;
import java.util.UUID;

import com.workspacemanagement.model.Utilities;

public interface UtilitiesService {
	public boolean saveOrUpdate(Utilities utility);

	public List<Utilities> getAll();

	public Utilities getById(Utilities utility,UUID utilityId);

	public boolean delete(Utilities utility);

}
