package com.seatmanagement.test.controller;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.NestedServletException;
import com.seatmanagement.dao.GenericDao;
import com.google.gson.Gson;
import com.seatmanagement.dao.FloorDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Floor;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration


public class FloorControllerTest {
	
/*
	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String WEBAPP = "seatmanagement";
	private static final String MODULE = "floor";
	private static final String BASE_URL = "http://"+HOST+":"+PORT+"/"+WEBAPP+"/"+MODULE+"";
	private static final String REQUEST_TYPE = "requestType";
	private static final String REQUEST_TYPE_AJAX = "AJAX";*/
	
	private static final String CONTROLLER = "Floors";
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private GenericDao genericDaoMock;

	@Autowired
	private FloorDao floorDaoMock;
	


	@BeforeEach
	public void setup() {

		Mockito.reset(genericDaoMock);
		Mockito.reset(floorDaoMock);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/*@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void saveOrUpdateFloorNormalFlowTest() throws Exception {

		
		UUID fuuid = UUID.randomUUID();

		String buildingIDString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
		UUID buildingID = UUID.fromString(buildingIDString);
		Building building = new Building();
		building.setBuildingId(buildingID);

		Floor floor=new Floor();
		floor.setFloorId(fuuid);
		floor.setBuilding(building);
		
	
		MultipartFile file =  (MultipartFile) ImageIO.read(new File("D:/DH2-2ndfloor(new).svg"));
		
		ByteArrayInputStream stream = new   ByteArrayInputStream(file.getBytes());
		String myString = IOUtils.toString(stream, "UTF-8");
		
		when(genericDaoMock.getById(any(Building.class), eq(buildingID))).thenReturn(buildingID);

		when(genericDaoMock.saveOrUpdate(any(Floor.class))).thenReturn(new Floor());

		mockMvc.perform(post("/" + CONTROLLER + "/save").param("buildingId",buildingIDString)
				.param("file",myString ))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

	}*/
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllFloorNormalFlowTest() throws Exception {

		UUID buuid = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();
		
		Building build = new Building();
		build.setBuildingId(buuid);

		Floor floor=new Floor();
		floor.setFloorId(fuuid);
		floor.setFloorName("1st floor");
		floor.setFloorType("home");
		floor.setBuilding(build);
		
		List<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);

		when(genericDaoMock.getAll(any(Floor.class))).thenReturn(floors);

      mockMvc.perform(get("/" + CONTROLLER)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
		.andExpect(jsonPath("$.[*].floorType", containsInAnyOrder("home")));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllFloorExceptionTest() throws Exception {
		
		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Floor");

		Mockito.when(genericDaoMock.getAll(any(Floor.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Floor", rootException.getMessage());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getFloorByFloorIdTest() throws Exception {

		UUID buuid = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();
		
		Building build = new Building();
		build.setBuildingId(buuid);

		Floor floor=new Floor();
		floor.setFloorId(fuuid);
		floor.setFloorName("1st floor");
		floor.setFloorType("home");
		floor.setBuilding(build);
		
		List<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		
		when(genericDaoMock.getById(any(Floor.class), eq(fuuid))).thenReturn(floor);
	//	when(floorDaoMock.getFloorsByBuildingId(any(UUID.class))).thenReturn(floors);
		MvcResult result=mockMvc.perform(get("/" + CONTROLLER + "/getFloorById/" + fuuid)).andDo(print())
		.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
				
		String floorResponseString = result.getResponse().getContentAsString();
		Floor floorResponse = new Gson().fromJson(floorResponseString, Floor.class);
		assertEquals(floorResponse.getFloorId(), fuuid);	
				
	}
		
	@Test
	public void getFloorByFloorIdExceptionTest() throws Exception {

		UUID fuuid = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from floor");

		when(genericDaoMock.getById(any(Floor.class), eq(fuuid))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER + "/getFloorById/" + fuuid)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from floor", rootException.getMessage());
	}
	
	@Test
	public void getFloorByBuildingIdTest() throws Exception {

		UUID buuid = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();
		
		Building build = new Building();
		build.setBuildingId(buuid);

		Floor floor=new Floor();
		floor.setFloorId(fuuid);
		floor.setFloorName("1st floor");
		floor.setFloorType("home");
		floor.setBuilding(build);
		
		List<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		
		when(floorDaoMock.getFloorsByBuildingId(any(UUID.class))).thenReturn(floors);
		mockMvc.perform(get("/" + CONTROLLER + "/floor/" + buuid)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].floorType", containsInAnyOrder("home")));
	}
	
	@Test
	public void getFloorByBuildingIdExceptionTest() throws Exception {

		UUID buuid = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from floor");

		when(floorDaoMock.getFloorsByBuildingId(any(UUID.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER + "/floor/" + buuid)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from floor", rootException.getMessage());
	}
	
	
	@Test
	public void floorTypeByBuildingIdTest() throws Exception {

		UUID buuid = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();
		
		Building build = new Building();
		build.setBuildingId(buuid);

		Floor floor=new Floor();
		floor.setFloorId(fuuid);
		floor.setFloorName("1st floor");
		floor.setFloorType("home");
		floor.setBuilding(build);
		
		List<Floor> floors = new ArrayList<Floor>();
		floors.add(floor);
		
		when(floorDaoMock.getFloorType(any(UUID.class))).thenReturn(floors);
		mockMvc.perform(get("/" + CONTROLLER + "/floorTypeByBuildingId/" + buuid)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].floorType", containsInAnyOrder("home")));
	}
	
	@Test
	public void floorTypeByBuildingIdExceptionTest() throws Exception {

		UUID buuid = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from floor");

		when(floorDaoMock.getFloorType(any(UUID.class))).thenThrow(applicationException);
		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER + "/floorTypeByBuildingId/" + buuid)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from floor", rootException.getMessage());
	}
	
/*	@SuppressWarnings("unchecked")
	@Test
	
	public void deleteFloorByIdTest() {
		try {
			
		
			UUID fuuid = UUID.randomUUID();
			
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Floor.class))).thenReturn(true);
			
			// Start Test
			mockMvc.perform(delete("/"+CONTROLLER+"/"+fuuid)).andDo(print())
				.andExpect(status().isOk());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}*/
	
	

	@Test
	public void getModifyFloorTest() throws Exception {
		UUID uuid = UUID.randomUUID();
		
		mockMvc.perform(get("/" + CONTROLLER + "/ModifyFloors/"+uuid)).andExpect(status().isOk())
				.andExpect(view().name("/HR/FloorModify"));
	}

	@Test
	public void getFloorViewTest() throws Exception {
	
		mockMvc.perform(get("/" + CONTROLLER + "/ViewFloors")).andExpect(status().isOk())
				.andExpect(view().name("/HR/Floor"));
	}

  

}
