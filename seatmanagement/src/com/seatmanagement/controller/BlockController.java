package com.seatmanagement.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
 * @author Adithya Prabhu
 * 
 * This class gets all requests for 'Building' model object and delegates to service classes 
 * for business processing
 *
 */
@Controller
@RequestMapping("/block")
public class BlockController {
	
	//private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);
	
	@Autowired
	private BlockService blockService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("Block");
	}
	
	
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/saveblock",method=RequestMethod.POST)
	public ResponseEntity saveOrUpdate(Block block , @RequestParam(value="floorId") UUID floorId) throws BusinessException{
		blockService.saveOrUpdate(block,floorId);
		ResponseEntity response =  new ResponseEntity(HttpStatus.OK);
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllBlocks",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Block>> getAll(){
		return new ResponseEntity(blockService.getAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getBlockById",method=RequestMethod.GET)
	public ResponseEntity getBlockById(@RequestParam(value="blockId") UUID blockId){
		Block block = new Block();
		block=blockService.getById(block,blockId);
		ResponseEntity responseEntity=null;
		if(!(block.getBlockId()==null)) {
			responseEntity=new ResponseEntity<Block>(block,HttpStatus.OK);
		}
		else{
					throw new RuntimeException("Invalid Block ID");
			}
		
		return responseEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getBlockByFloorId", method = RequestMethod.GET)
	public ResponseEntity getBlocksByFloorId(@RequestParam(value = "floor_id") UUID floorId) throws BusinessException {
		
		if (Objects.isNull(floorId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		
		ResponseEntity responseEntity = null;
		List<Block> blocks = null;
		blocks = blockService.getBlocksByFloorId(floorId);
		responseEntity= new ResponseEntity(blocks, HttpStatus.OK);

		return responseEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getBlockByBlockType", method = RequestMethod.GET)
	public ResponseEntity getBlocksByBlockType(@RequestParam(value = "block_type") String blockType,@RequestParam(value = "floor_id") UUID floorId ) throws BusinessException {
		if (Objects.isNull(floorId)) {
		if (Objects.isNull(blockType)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		}
		ResponseEntity responseEntity = null;
		List<Block> blocks = null;
		blocks = blockService.getBlocksByBlockType(blockType,floorId);
		responseEntity= new ResponseEntity(blocks, HttpStatus.OK);

		return responseEntity;
	}
	
	@RequestMapping(value="/deleteBlockById")
	public ResponseEntity deleteBlockById(@RequestParam(value="blockId") UUID blockId) throws BusinessException{
		
		Block block = new Block();
		block.setBlockId(blockId);
		
		ResponseEntity responseEntity=null;
		if(block.getBlockId() !=null){
			blockService.delete(block);
			responseEntity = new ResponseEntity(HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid ID");
		}
		return responseEntity;
	}
		
	@RequestMapping(value = "/getBlockView", method = RequestMethod.GET)
	public ModelAndView getBlockView() {
		return new ModelAndView("HR/block");
	}
	
}
