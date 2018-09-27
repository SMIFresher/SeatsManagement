package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Seating;

public interface SeatingService {

	public List<Seating> getSeatingByBlockId(Seating seating, UUID seating_id);

	public boolean addOrUpdateSeating(Seating seating, UUID blockID);

	public List<Seating> getAllSeating();

	public void deleteSeatingByBlockId(UUID uuid);

	public List<Object> getAllSeatingWithAxis();
}
