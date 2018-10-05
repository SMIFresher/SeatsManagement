package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Seating;

/**
 * 
 *
 * @author Sithaara Sivasankar
 * 
 *         This class provides interface for all Database related activities to
 *         'Seating' model object
 *
 */
public interface SeatingDao {

	public List<Seating> getAll(Seating seating, UUID block_id);

	public List<Seating> getAllSeating();

	public List<Seating> getSeatingByBlockId(UUID blockId);
}
