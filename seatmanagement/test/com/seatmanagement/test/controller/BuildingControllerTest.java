package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
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
import org.junit.Before;
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
import org.springframework.web.util.NestedServletException;

import com.google.gson.Gson;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Reallocation;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.Team;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class BuildingControllerTest {

	private static final String CONTROLLER = "Buildings";
	
	
	private static final String MODULE = "Buildings";
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private GenericDao genericDaoMock;

	

	@BeforeEach
	public void setup() {

		Mockito.reset(genericDaoMock);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateBuildingNormalFlowTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();
		
		Organisation organisation = new Organisation();
		organisation.setOrganisationId(uuid1);

		when(genericDaoMock.saveOrUpdate(any(Building.class))).thenReturn(new Building());

		mockMvc.perform(post("/" + CONTROLLER + "/" + uuid1).param("buildingName", "DH2").param("buildingAddress", "Poriyalar Nagar")
				.param("buildingLocation", "Madurai").param("squareFeet", "509")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateBuildingDatabaseBuildingExceptionTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();
	

		Organisation organisation = new Organisation();
		organisation.setOrganisationId(uuid1);

		
	
		ApplicationException applicationException = new ApplicationException(
				"Error while inserting records from Building");

		when(genericDaoMock.saveOrUpdate(any(Building.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(post("/" + CONTROLLER + "/" + uuid1).param("buildingName", "DH2").param("buildingAddress", "Poriyalar")
					.param("buildingLocation", "Madurai").param("squareFeet", "509")).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while inserting records from Building", rootException.getMessage());
	}

	


	@SuppressWarnings("unchecked")
	@Test
	public void getAllNormalFlowTest() throws Exception {

		UUID uuid = UUID.randomUUID();
		

		Building building = new Building();
		building.setBuildingId(uuid);
		building.setBuildingName("Dh2");
		building.setBuildingAddress("Poriyalar Nagar");
		building.setBuildingLocation("Madurai");
		building.setSquareFeet((float) 897);
		
		List<Building>building1 = new ArrayList<Building>();
		building1.add(building);

		when(genericDaoMock.getAll(any(Building.class))).thenReturn(building1);

		mockMvc.perform(get("/" + CONTROLLER)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].buildingLocation", containsInAnyOrder("Madurai")));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllBuildingDatabaseExceptionTest() throws Exception {
		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Building");

		Mockito.when(genericDaoMock.getAll(any(Building.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Building", rootException.getMessage());
	}
	
	
	@Test
	public void getBuildingByIdNormalFlowTest() {
		try {
			
			//DAO Configuration
			
			// Building Configuration
			String buildingIDString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			UUID buildingID = UUID.fromString(buildingIDString);
			Building building = new Building();
			building.setBuildingId(buildingID);
				
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Building.class),any(UUID.class))).thenReturn(building);
			
			// Start Test
			MvcResult result = mockMvc.perform(get("/"+MODULE+"/"+buildingID))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();
			
			// Asserts
			String buildingResponseString = result.getResponse().getContentAsString();
			Building buildingResponse = new Gson().fromJson(buildingResponseString, Building.class);
			assertEquals(buildingResponse.getBuildingId(), buildingID);

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	

	
	
	
	
	
	
	
	@Test
	public void getBuildingByIdDatabaseExceptionTest() {
		try {
			//Request Params
			String buildingIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";

			// DAO Configuration

			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while retreiving record from Building");
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Building.class),  any(UUID.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/"+MODULE+"/"+buildingIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while retreiving record from Building", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	
	
	
	@Test
	public void deleteBuildingByIdNormalFlowTest() {
		try {
			
			//Request Params
			String buildingIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Building.class))).thenReturn(true);
			
			// Start Test
			mockMvc.perform(delete("/"+MODULE+"/"+buildingIdString)).andDo(print())
				.andExpect(status().isOk());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	
	@Test
	public void deleteBuildingByIdDatabaseExceptionTest() {
		try {
			//Request Params
			String buildingIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";

			// DAO Configuration

			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while deleting record from Building");
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Building.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(delete("/"+MODULE+"/"+buildingIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while deleting record from Building", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getBuildingViewTest() throws Exception {
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString();
		mockMvc.perform(get("/" + CONTROLLER + "/ViewBuildings")).andExpect(status().isOk())
				.andExpect(view().name("/HR/viewandAddBuilding"));
	}

	@Test
	public void getBuildingModifyTest() throws Exception {
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString();
		mockMvc.perform(get("/" + CONTROLLER + "/ModifyBuildings")).andExpect(status().isOk())
				.andExpect(view().name("/HR/ModifyBuilding"));
	}
	
}

	
