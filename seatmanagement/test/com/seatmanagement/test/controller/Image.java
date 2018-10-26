package com.seatmanagement.test.controller;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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


public class Image {
	
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
	
	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	@Test
	public void saveOrUpdateFloorNormalFlowTest() throws Exception {

		
		UUID fuuid = UUID.randomUUID();

		String buildingIDString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
		UUID buildingId = UUID.fromString(buildingIDString);
		Building building = new Building();
		building.setBuildingId(buildingId);
           Floor floor=new Floor();
		floor.setFloorId(fuuid);
		floor.setBuilding(building);
when(genericDaoMock.getById(any(Building.class), eq(buildingId))).thenReturn(buildingId);

when(genericDaoMock.saveOrUpdate(any(Floor.class))).thenReturn(new Floor());
		MockMultipartFile file = new MockMultipartFile("image", "FoB".getBytes());
			mockMvc.perform(fileUpload("/" + CONTROLLER +"/save/{buildingId}/image", Long.valueOf(1))
					.file(file)
			)
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());;
		}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	