package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.FloorService;

/**
 * 
 * @author M.karthika This class gets all requests for 'Floor' model object and
 *         delegates to service classes for business processing
 *
 */

@Controller
@RequestMapping("/floor")
public class FloorController {

	private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

	@Autowired
	private FloorService floorService;

	/**
	 * 
	 * @return ModelAndView
	 * @throws BusinessException
	 */

	@RequestMapping("/FloorView")
	public ModelAndView getFloorView() throws BusinessException {

		logger.info("Controller: FloorController Method : getFloorView request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/HR/Floor");

		logger.info("Controller: FloorController Method : getFloorView response sent at : " + LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @return ModelAndView
	 * @throws BusinessException
	 */

	@RequestMapping(value = "/FloorModify")
	public ModelAndView getModifyFloor() throws BusinessException {

		logger.info("Controller: FloorController Method : getModifyFloor request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = new ModelAndView("/HR/FloorModify");

		logger.info("Controller: FloorController Method : getModifyFloor response sent at : " + LocalDateTime.now());

		return model;

	}

	/**
	 * 
	 * @param floor
	 * @param buildingId
	 * @return ResponseEntity
	 * @throws BusinessException
	 */

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/save/buildingId", method = RequestMethod.POST)
	public ResponseEntity saveOrUpdate(@Valid Floor floor, Errors errors,
			@RequestParam(value = "buildingId") UUID buildingId) throws BusinessException {

		logger.info("Controller: FloorController Method : saveOrUpdate request processing started at : "
				+ LocalDateTime.now());
		ResponseEntity response = null;

		// Validation
		if (errors.hasErrors()) {
			throw new BusinessException(errors);
		}

		floorService.saveOrUpdateFloors(floor, buildingId);
		response = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: FloorController Method : saveOrUpdate response sent at : " + LocalDateTime.now());

		return response;
	}

	/**
	 * 
	 * @return responseEntity
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/view")
	public ResponseEntity<List<Floor>> getAllFloors() {

		logger.info("Controller: FloorController Method : getAllFloors request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity response = null;
		@SuppressWarnings("unused")
		List<Floor> floors = floorService.getAllFloors();
		response = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: FloorController Method : getAllFloors response sent at : " + LocalDateTime.now());

		return response;
	}

	/**
	 * 
	 * @param FloorId
	 * @return responseEntity
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/viewfloor/floorId", method = RequestMethod.GET)
	public ResponseEntity getFloorById(@RequestParam(value = "FloorId") UUID FloorId) {

		logger.info("Controller: FloorController Method : getFloorById request processing started at : "
				+ LocalDateTime.now());

		Floor floor = new Floor();
		floor = floorService.getByFloorId(floor, FloorId);
		ResponseEntity response = null;

		if (!(floor.getFloorId() == null)) {
			response = new ResponseEntity<Floor>(floor, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid Floor ID");
		}

		logger.info("Controller: FloorController Method : getFloorById response sent at : " + LocalDateTime.now());

		return response;
	}

	/**
	 * 
	 * @param buildingId
	 * @return responseEntity
	 * @throws BusinessException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewfloor/buildingId", method = RequestMethod.GET)
	public ResponseEntity getFloorsByBuildingId(@RequestParam(value = "buildingId") UUID buildingId)
			throws BusinessException {

		logger.info("Controller: FloorController Method : getFloorsByBuildingId request processing started at : "
				+ LocalDateTime.now());

		if (Objects.isNull(buildingId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		ResponseEntity response = null;
		List<Floor> floors = null;
		floors = floorService.getFloorsByBuildingId(buildingId);
		response = new ResponseEntity(floors, HttpStatus.OK);

		logger.info(
				"Controller: FloorController Method : getFloorsByBuildingId response sent at : " + LocalDateTime.now());

		return response;
	}

	/**
	 * 
	 * @param buildingId
	 * @return ResponseEntity
	 * @throws BusinessException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewfloorType/buildingId", method = RequestMethod.GET)
	public ResponseEntity getFloorTypeByBuildingId(@RequestParam(value = "buildingId") UUID buildingId)
			throws BusinessException {

		if (Objects.isNull(buildingId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		ResponseEntity response = null;
		List<Floor> floors = null;
		floors = floorService.getFloorTypeByBuildingId(buildingId);
		response = new ResponseEntity(floors, HttpStatus.OK);

		return response;
	}

	/**
	 * 
	 * @param floorId
	 * @return ResponseEntity
	 * @throws BusinessException
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete/floorId")
	public ResponseEntity deleteFloorById(@RequestParam(value = "floorId") UUID floorId) throws BusinessException {

		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		ResponseEntity response = null;

		floorService.deleteByFloorId(floorId);

		response = new ResponseEntity(HttpStatus.OK);

		return response;
	}

}
