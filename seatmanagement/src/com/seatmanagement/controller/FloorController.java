package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	 * @param image
	 * @return ResponseEntity
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/floorsave",method = RequestMethod.POST)
	
	public ResponseEntity saveFloor( Floor floor,@RequestParam("buildingId") UUID buildingId,@RequestParam("file") MultipartFile image) throws BusinessException {

		logger.info("Controller: FloorController Method : saveFloor request processing started at : "
				+ LocalDateTime.now());
		//ResponseEntity response = null;

		floorService.saveOrUpdateFloors(floor, buildingId,image);
		//response = new ResponseEntity(HttpStatus.OK,);

		logger.info("Controller: FloorController Method : saveFloor response sent at : " + LocalDateTime.now());

	//	return "redirect:/floor/floorSave/";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/seatmanagement/floor/FloorView");    
		return new ResponseEntity<String>(headers,HttpStatus.FOUND);
	}

	/**
	 * 
	 * @return responseEntity
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllFloors",method = RequestMethod.GET)
	public ResponseEntity<List<Floor>> getAllFloors() {

		logger.info("Controller: FloorController Method : getAllFloors request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity response = null;
		@SuppressWarnings("unused")
		List<Floor> floors = floorService.getAllFloors();
		response = new ResponseEntity(floors,HttpStatus.OK);

		logger.info("Controller: FloorController Method : getAllFloors response sent at : " + LocalDateTime.now());

		return response;
	}

	/**
	 * 
	 * @param FloorId
	 * @return responseEntity
	 * @throws BusinessException 
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/floorId",method = RequestMethod.GET)
	public ResponseEntity getFloorById(@RequestParam("floorId") UUID floorId) throws BusinessException {

		logger.info("Controller: FloorController Method : getFloorById request processing started at : "
				+ LocalDateTime.now());

		
		ResponseEntity response = null;
		
		if(Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		Floor floor = new Floor();
		floor = floorService.getByFloorId(floor, floorId);

		response = new ResponseEntity<Floor>(floor, HttpStatus.OK);
		

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
	@RequestMapping(value="/floorbuildingId",method = RequestMethod.GET)
	public ResponseEntity getFloorsByBuildingId(@RequestParam ("buildingId") UUID buildingId)
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
	@RequestMapping(value="/viewfloorType/buildingId",method = RequestMethod.GET)
	public ResponseEntity getFloorTypeByBuildingId(@RequestParam ("buildingId") UUID buildingId)
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
	@RequestMapping(value="/delete/floorId",method = RequestMethod.DELETE)
	public ResponseEntity deleteFloorById(@RequestParam("floorId") UUID floorId) throws BusinessException {

		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		ResponseEntity response = null;

		floorService.deleteByFloorId(floorId);

		response = new ResponseEntity(HttpStatus.OK);

		return response;
	}

}
