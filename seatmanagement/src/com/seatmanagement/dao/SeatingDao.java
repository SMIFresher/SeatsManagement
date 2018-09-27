package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Seating;

public interface SeatingDao {

	public List<Seating> getAll(Seating seating, UUID block_id);
	public List<Seating> getAllSeating();
	public List<Seating> getSeatingByBlockId(UUID blockId);
}
