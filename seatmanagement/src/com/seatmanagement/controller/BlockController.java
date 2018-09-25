package com.seatmanagement.controller;

import java.util.List;
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
import com.seatmanagement.model.Block;
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
	@RequestMapping(value="/save",method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Block> saveOrUpdate(@RequestBody Block block , @RequestParam(value="floor_id") UUID floor_id){
		ResponseEntity<Block> response =  new ResponseEntity(blockService.saveOrUpdate(block,floor_id),HttpStatus.OK);
		return response;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllBlocks",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	
	/*@RequestMapping(value="/deleteBlockById",method=RequestMethod.GET)
	public ResponseEntity deleteBlockById(@RequestParam UUID blockId){
		
		Block block = new Block();
		block.setBlockId(blockId);
		
		ResponseEntity responseEntity=null;
		if(block.getBlockId() !=null){
		responseEntity = new ResponseEntity(blockService.delete(block),HttpStatus.OK);
		}
		else{
			throw new RuntimeException("Invalid ID");
		}
		return responseEntity;
	}*/
		
	
	
}
