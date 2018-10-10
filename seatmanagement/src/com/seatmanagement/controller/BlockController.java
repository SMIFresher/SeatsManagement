package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Floor;
import com.seatmanagement.service.BlockService;

/**
 * 
 * @author Harshanaa Ramdas
 * 
 *         This class gets all requests for 'Block' model object and delegates
 *         to service classes for business processing
 *
 */
@Controller
@RequestMapping("/block")
public class BlockController {

	private static final Logger logger = LoggerFactory.getLogger(BlockController.class);

	@Autowired
	private BlockService blockService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {

		return new ModelAndView("Block");

	}

	/**
	 * 
	 * @param block
	 * @param floorId
	 * @param utilitiesUUIDs
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveblock", method = RequestMethod.POST)
	public ResponseEntity saveOrUpdate(Block block, @RequestParam(value = "floorId") UUID floorId,
			@RequestParam(value = "utilityList", required = false) List<UUID> utilitiesUUIDs) throws BusinessException {
		logger.info("Controller: BlockController Method : saveBlock request processing started at : "
				+ LocalDateTime.now());
		ResponseEntity responseEntity = null;
		if (Objects.isNull(block) || Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		} 
		blockService.saveOrUpdate(block, floorId, utilitiesUUIDs);
		responseEntity = new ResponseEntity(HttpStatus.OK);
		logger.info("Controller: BlockController Method : saveBlock response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllBlocks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Block>> getAll() {
		logger.info("Controller:  BlockController Method : getAllBlock request processing started at : "
				+ LocalDateTime.now());
		logger.info("Controller:  BlockController Method : getAll Block response sent at : " + LocalDateTime.now());
		return new ResponseEntity(blockService.getAll(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param blockId
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/getBlockById", method = RequestMethod.GET)
	public ResponseEntity getBlockById(@RequestParam(value = "blockId") UUID blockId) throws BusinessException {
		logger.info("Controller: BlockController Method : getBlockById request processing started at : "
				+ LocalDateTime.now());
		ResponseEntity responseEntity = null;
		if(Objects.isNull(blockId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		Block block = new Block();
		block = blockService.getById(block, blockId);
		responseEntity = new ResponseEntity<Block>(block, HttpStatus.OK);
		logger.info("Controller: BlockController Method : getBlockById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param floorId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getBlockByFloorId", method = RequestMethod.GET)
	public ResponseEntity getBlocksByFloorId(@RequestParam(value = "floor_id") UUID floorId) throws BusinessException {
		logger.info("Controller: BlockController Method : getBlockByFloorId request processing started at : "
				+ LocalDateTime.now());
		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		ResponseEntity responseEntity = null;
		List<Block> blocks = null;
		blocks = blockService.getBlocksByFloorId(floorId);
		responseEntity = new ResponseEntity(blocks, HttpStatus.OK);
		logger.info("Controller: BlockController Method : getBlockByFloorId response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param blockType
	 * @param floorId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getBlockByBlockType", method = RequestMethod.GET)
	public ResponseEntity getBlocksByBlockType(@RequestParam(value = "block_type") String blockType,
			@RequestParam(value = "floor_id") UUID floorId) throws BusinessException {
		logger.info("Controller: BlockController Method : getBlockByBlockType request processing started at : "
				+ LocalDateTime.now());
		if (Objects.isNull(floorId) || Objects.isNull(blockType)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		ResponseEntity responseEntity = null;
		List<Block> blocks = null;
		blocks = blockService.getBlocksByBlockType(blockType, floorId);
		responseEntity = new ResponseEntity(blocks, HttpStatus.OK);
		logger.info(
				"Controller: BlockController Method : getBlockByBlockType response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @param blockId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deleteBlockById")
	public ResponseEntity deleteBlockById(@RequestParam(value = "blockId") UUID blockId) throws BusinessException {
		logger.info("Controller: BlockController Method : deleteBlockById request processing started at : "
				+ LocalDateTime.now());
		ResponseEntity responseEntity = null;
		
		if(Objects.isNull(blockId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		Block block = new Block();
		block.setBlockId(blockId);
		blockService.delete(block);
		responseEntity = new ResponseEntity(HttpStatus.OK);
		logger.info("Controller: BlockController Method : deleteBlockById response sent at : " + LocalDateTime.now());
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBlockView", method = RequestMethod.GET)
	public ModelAndView getBlockView() {
		logger.info("Controller: BlockController Method : getBlockView request processing started at : "
				+ LocalDateTime.now());
		logger.info("Controller: BlockController Method : getBlockView response sent at : " + LocalDateTime.now());
		return new ModelAndView("HR/block");
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getModifyBlock")
	public ModelAndView getModifyBuilding() {
		logger.info("Controller: BlockController Method : getModifyBlock request processing started at : "
				+ LocalDateTime.now());
		logger.info("Controller: BlockController Method : getModifyBlock response sent at : " + LocalDateTime.now());
		return new ModelAndView("/HR/ModifyBlock");
	}
	
	@RequestMapping(value="/blockView")
	public ModelAndView blockView(@RequestParam("floorId") String floorId) throws BusinessException {
		logger.info("Controller: SeatingController Method : getSeatingView request processing started at : "
				+ LocalDateTime.now());
		ModelAndView model = new ModelAndView("/HR/blockviews");
		logger.info("Controller: SeatingController Method : getSeatingView response sent at : " + LocalDateTime.now());
		model.addObject("id", floorId);
		return model;
	}

}
