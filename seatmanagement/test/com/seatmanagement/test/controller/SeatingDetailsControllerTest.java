package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.collection.IsEmptyCollection;
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

import com.google.gson.Gson;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDetailsDao;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Reallocation;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.SeatingDetails;
import com.seatmanagement.model.Systems;
import com.seatmanagement.service.SeatingDetailsService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration

public class SeatingDetailsControllerTest {
	
	//private static final String CONTROLLER = "Seatingdetails";
	private static final String MODULE = "Seatingdetails";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private GenericDao genericDaoMock;
	
	@Autowired
	private SeatingDetailsDao seatingDetailsDaoMock;

	@BeforeEach
	public void setup() {
		Mockito.reset(genericDaoMock);
		Mockito.reset(seatingDetailsDaoMock);
		//MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getAllSeatingDetailsNormalFlowTest() {
		try {
			UUID uuid = UUID.randomUUID();
			UUID sysuuid = UUID.randomUUID();
			UUID seatuuid = UUID.randomUUID();
			
			Systems systems = new Systems();
			systems.setSystemId(sysuuid);
			
			Seating seating = new Seating();
			seating.setSeatingId(seatuuid);
			// DAO Configurations
			List<SeatingDetails> seatingDetails = new ArrayList<SeatingDetails>();
			SeatingDetails seatingDetails1 = new SeatingDetails();
			seatingDetails1.setSeatingDetailsId(uuid);
			seatingDetails1.setSeatingSystemNo("789");
			
			//seatingDetails1.setSystem(systems);
			//seatingDetails1.setSeating(seating);
			
			seatingDetails.add(seatingDetails1);
			Mockito.when(genericDaoMock.getAll(any(SeatingDetails.class))).thenReturn(seatingDetails);
			
			mockMvc.perform(get("/" + MODULE)).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
			.andExpect(jsonPath("$.[*].seatingSystemNo", containsInAnyOrder("789")));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveSeatingDetails()
	{
		try
		{
			UUID sysuuid = UUID.randomUUID();
			UUID seatuuid = UUID.randomUUID();
			UUID seatDetailuuid = UUID.randomUUID();
			String seatingRow = "10";
		
			Systems systems = new Systems();
			systems.setSystemId(sysuuid);
			
			Seating seating = new Seating();
			seating.setSeatingId(seatuuid);
			
			SeatingDetails seatingDetails = new SeatingDetails();
			
			seatingDetails.setSeatingDetailsId(seatDetailuuid);
			seatingDetails.setSystem(systems);
			seatingDetails.setSeating(seating);
			seatingDetails.setSeatingRow(seatingRow);
			
			Mockito.when(genericDaoMock.getById(any(Systems.class), any(UUID.class))).thenReturn(systems);
			Mockito.when(genericDaoMock.getById(any(Seating.class), any(UUID.class))).thenReturn(seating);
			when(genericDaoMock.saveOrUpdate(any(Object.class)))
			   .thenReturn(seatingDetails);
			
			mockMvc.perform(post("/"+MODULE)
					.param("seatingRow", seatingRow))
				.andDo(print())
				.andExpect(status().isOk());
			   
			
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	
	@Test
	public void getSeatDetailsBySeatingId() throws Exception
	{
		UUID uuid = UUID.randomUUID();
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		
		Seating seating = new Seating();
		seating.setSeatingId(uuid1);
		seating.setSystemOccupied(1);
		
		Systems systems = new Systems();
		systems.setSystemId(uuid2);

		SeatingDetails seating1 = new SeatingDetails();
		seating1.setSeatingDetailsId(uuid);
		seating1.setSeatingAccessories("seatingAccessories");
		seating1.setSeatingColum("200");
		seating1.setSeatingRow("500");
		seating1.setSeatingSystemNo("5");
		seating1.setSeating(seating);
		seating1.setSystem(systems);

		List<SeatingDetails> seatingsDet = new ArrayList<SeatingDetails>();
		seatingsDet.add(seating1);

		/*when(seatingDetailsDaoMock.getSeatingDetailsBySeatingId(eq(uuid1))).thenReturn(seatingsDet);

		mockMvc.perform(get("/" + MODULE + "/getSeatDetailsBySeatId/" + uuid1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].seatingColum", containsInAnyOrder("200")));*/
		
		// Mockito Configuration
		Mockito.when(seatingDetailsDaoMock.getSeatingDetailsBySeatingId(eq(uuid1))).thenReturn(seatingsDet);
					
		// Start Test
		MvcResult result = mockMvc.perform(get("/"+MODULE+"/getSeatDetailsBySeatId/" + uuid1))				
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
					
		// Asserts
		String seatingDetResponseString = result.getResponse().getContentAsString();
		List<SeatingDetails> seatingResponse = new Gson().fromJson(seatingDetResponseString,List.class);
		assertThat(seatingResponse, not(IsEmptyCollection.empty()));
		
	}
	
	@Test
	public void getEmployeeBySeatId() throws Exception
	{
		UUID suuid = UUID.randomUUID();
		UUID euuid1 = UUID.randomUUID();
		UUID seuuid2 = UUID.randomUUID();
		UUID sduuid = UUID.randomUUID();
		
		Employee employee = new Employee();
		employee.setEmployeeId(euuid1);
		
		Systems systems =  new Systems();
		systems.setEmployee(employee);
		
		Seating seating = new Seating();
		seating.setSeatingId(seuuid2);
		seating.setSystemOccupied(1);
		
		SeatingDetails seatingDet = new SeatingDetails();
		seatingDet.setSeatingDetailsId(sduuid);
		seatingDet.setSeatingColum("200");
		seatingDet.setSeating(seating);
		seatingDet.setSystem(systems);
		
		
		/*List<SeatingDetails> seatingsDetail = new ArrayList<SeatingDetails>();
		seatingsDetail.add(seatingDet);*/
		
		/*when(seatingDetailsDaoMock.getEmployeeBySeatId(any(SeatingDetails.class),eq(seuuid2))).thenReturn(seatingDet);

		mockMvc.perform(get("/" + MODULE + "/getEmployeeBySeatId/" + seuuid2)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].seatingColum", containsInAnyOrder("200")));*/
		
		Mockito.when(seatingDetailsDaoMock.getEmployeeBySeatId(any(SeatingDetails.class),eq(seuuid2))).thenReturn(seatingDet);
		
		// Start Test
		MvcResult result = mockMvc.perform(get("/"+MODULE+"/getEmployeeBySeatId/" + seuuid2))				
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
					
		// Asserts
		String seatingDetResponseString = result.getResponse().getContentAsString();
		SeatingDetails seatingResponse = new Gson().fromJson(seatingDetResponseString, SeatingDetails.class);
		assertEquals(seatingResponse.getSeatingColum(),"200");
	}
}
