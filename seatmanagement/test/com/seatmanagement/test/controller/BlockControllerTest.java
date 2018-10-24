package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.google.gson.Gson;
import com.seatmanagement.dao.BlockDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration



public class BlockControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(BlockControllerTest.class);


	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private GenericDao genericDaoMock;
	
	@Autowired
	private BlockDao blockDaoMock;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		Mockito.reset(genericDaoMock);
		Mockito.reset(blockDaoMock);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	
	@Test
	public void saveOrUpdateTest() {
		try {
			List<Block> params=new ArrayList();
			Mockito.when(genericDaoMock.saveOrUpdate(any(Block.class))).thenReturn(new Block());
			mockMvc.perform(post("/block").param("blockName", "Block Testing")
					.param("blockType", "Cabin")
					.param("blockCapacity", "5")
					.param("blockDescription", "Training Hall")
					.param("blockMeasurement", "10")
					);

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	
	@Test
	public void saveOrUpdateBlockDatabaseBlockExceptionTest() {
		try {
			// Request Params
			String floorIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			UUID fuuid=UUID.randomUUID();
			UUID buuid=UUID.randomUUID();
			// DAO Configuration
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Block record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
		
			Floor floor=new Floor();
			floor.setFloorId(fuuid);
			floor.setFloorName("1st floor");
			floor.setFloorType("home");
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Floor.class), any(UUID.class))).thenThrow(applicationException);
			/*mockMvc.perform(get("/Floors"+ "/getFloorById/" + fuuid)).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());
			
			
			Mockito.when(genericDaoMock.saveOrUpdate(any(Block.class)))
				.thenThrow(applicationException);*/
			
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/block")
						.param("floorId", floorIDString)
						.param("blockName", "Block Testing")
						.param("blockType", "Cabin")
						.param("blockCapacity", "5")
						.param("blockDescription", "Training Hall")
						.param("blockMeasurement", "10"));
			});
			
			// Asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals(exceptionMessage, rootException.getMessage());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllTest() {
		try {
			// Dao Configurations
			List<Block> blocks = new ArrayList<Block>();

			Block block=new Block();
			block.setBlockType("Cabin");
			block.setBlockName("L1");
			block.setBlockMeasurement("100");
			block.setBlockDescription("Admin Room");
			block.setBlockCapacity("10");

			blocks.add(block);

			Mockito.when(genericDaoMock.getAll(any(Block.class))).thenReturn(blocks);

			mockMvc.perform(get("/Blocks")).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.[*].blockName", containsInAnyOrder("L1")));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getAllBlockDatabaseExceptionTest() throws Exception {
		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Block");

		Mockito.when(genericDaoMock.getAll(any(Block.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/Blocks")).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Block", rootException.getMessage());
	}
	
	
	
	@Test
	public void getBlockByIdTest(){
		try {
			UUID uuid=UUID.randomUUID();
		//	List<Block> blocks = new ArrayList<Block>();
			Block block=new Block();
			block.setBlockId(uuid);
			block.setBlockType("Cabin");
			block.setBlockName("L1");
			block.setBlockMeasurement("100");
			block.setBlockDescription("Admin Room");
			block.setBlockCapacity("10");
		//	blocks.add(block);

			Mockito.when(genericDaoMock.getById(any(Block.class),eq(uuid))).thenReturn(block);

			MvcResult result = mockMvc.perform(get("/Blocks/BlockById/"+uuid))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
				
			// Asserts
			String blockResponseString = result.getResponse().getContentAsString();
			Block reallocationInResponse = new Gson().fromJson(blockResponseString, Block.class);
			assertEquals(reallocationInResponse.getBlockDescription(), "Admin Room");
		}catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	

	@Test
	public void getBlocksByFloorIdTest() {
		try {
			UUID uuid=UUID.randomUUID();
			UUID uuids=UUID.randomUUID();
			Floor floor=new Floor();
			floor.setFloorId(uuid);
		
			Block block=new Block();
			block.setBlockId(uuids);
			block.setBlockType("Cabin");
			block.setBlockName("L1");
			block.setBlockMeasurement("100");
			block.setBlockDescription("Admin Room");
			block.setBlockCapacity("10");
			block.setFloor(floor);
			
			List<Block> blocks = new ArrayList<Block>();
			blocks.add(block);
			Mockito.when(blockDaoMock.getBlocksByFloorId(eq(uuid))).thenReturn(blocks);

			mockMvc.perform(get("/Blocks/BlocksByFloorId/"+uuid)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		}catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void getBlockByFloorIdDatabaseExceptionTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Blocks");

		when(blockDaoMock.getBlocksByFloorId(any(UUID.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/Blocks/BlocksByFloorId/" + uuid1)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Blocks", rootException.getMessage());
	}
	
	
	@Test
	public void getBlockByBlockTypeTest() {
		try {
			UUID uuid=UUID.randomUUID();
			UUID uuids=UUID.randomUUID();
			Floor floor=new Floor();
			floor.setFloorId(uuid);
			List<Block> blocks = new ArrayList<Block>();
			Block block=new Block();
			block.setBlockId(uuids);
			block.setBlockType("Cabin");
			block.setBlockName("L1");
			block.setBlockMeasurement("100");
			block.setBlockDescription("Admin Room");
			block.setBlockCapacity("10");
			block.setFloor(floor);
			blocks.add(block);
			Mockito.when(blockDaoMock.getBlocksByBlockType(eq("Cabin"),eq(uuid))).thenReturn(blocks);

			mockMvc.perform(get("/Blocks/BlockType").param("block_type","Cabin").param("floor_id","fccefb43-42bd-4335-a11e-b0069237c123")).andDo(print())
					.andExpect(status().isOk());
		}catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deleteBlockTest() {
		try {
			// DAO Configuration
			UUID uuid=UUID.randomUUID();
			//	List<Block> blocks = new ArrayList<Block>();
				Block block=new Block();
				block.setBlockId(uuid);
				block.setBlockType("Cabin");
				block.setBlockName("L1");
				block.setBlockMeasurement("100");
				block.setBlockDescription("Admin Room");
				block.setBlockCapacity("10");
			Mockito.when(genericDaoMock.getById(any(Block.class),eq(uuid))).thenReturn(block);
			Mockito.when(genericDaoMock.delete(any(Block.class))).thenReturn(true);
			mockMvc.perform(delete("/Blocks/"+uuid))
					.andDo(print()).andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
}