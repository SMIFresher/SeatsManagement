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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.google.gson.Gson;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SystemDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Building;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Seating;
import com.seatmanagement.model.Systems;
import com.seatmanagement.model.Team;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class SystemControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(SystemControllerTest.class);

	private static final String MODULE = "Systems";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private GenericDao genericDaoMock;
	
	@Autowired
	private SystemDao systemDaoMock;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		Mockito.reset(genericDaoMock);
		Mockito.reset(systemDaoMock);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void saveSystemTest() {
		try {
			
			UUID sys = UUID.randomUUID();
			String systemType="laptop";
			
			Systems system=new Systems();
			system.setSystemId(sys);
			
			Mockito.when(genericDaoMock.saveOrUpdate(any(Systems.class))).thenReturn(system);
			mockMvc.perform(post("/"+MODULE+"/system").param("system_type", systemType))
				.andDo(print())
				.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveSystemExceptionTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while inserting records in Systems");

		Mockito.when(genericDaoMock.saveOrUpdate(any(Systems.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(post("/" + MODULE + "/system").param("system_type", "laptop")).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while inserting records in Systems", rootException.getMessage());
	}
	
	
	
	@Test
	public void saveSystemWithEmployeTest() {
		try {
			
			UUID sys = UUID.randomUUID();
			UUID emp = UUID.randomUUID();
			
			String systemType="laptop";
			Employee employee=new Employee();
			employee.setEmployeeId(emp);
			
			Systems system=new Systems();
			system.setSystemId(sys);
			system.setEmployee(employee);
			
			
			Mockito.when(genericDaoMock.getById(any(Object.class), any(UUID.class))).thenReturn(system).thenReturn(employee);
			Mockito.when(systemDaoMock.mergeSystem(any(Systems.class))).thenReturn(system);
			Mockito.when(genericDaoMock.saveOrUpdate(any(Systems.class))).thenReturn(system);
			
			
			mockMvc.perform(post("/"+MODULE+"/Employee").param("systemId", sys.toString()).param("employeeId", emp.toString()))
				.andDo(print())
				.andExpect(status().isOk());

		} catch (Exception e) {
		 	fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveSystemWithEmployeExceptionTest() throws Exception {

		UUID sys = UUID.randomUUID();
		UUID emp = UUID.randomUUID();
		
		String systemType="laptop";
		Employee employee=new Employee();
		employee.setEmployeeId(emp);
		
		Systems system=new Systems();
		system.setSystemId(sys);
		system.setEmployee(employee);

		Mockito.when(genericDaoMock.getById(any(Object.class), any(UUID.class))).thenReturn(system).thenReturn(employee);
		
		ApplicationException applicationException = new ApplicationException(
				"Error while inserting records in Systems");
		
		Mockito.when(systemDaoMock.mergeSystem(any(Systems.class))).thenThrow(applicationException);
		//Mockito.when(genericDaoMock.saveOrUpdate(any(Systems.class))).thenThrow(applicationException);
		
		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(post("/"+MODULE+"/Employee")
					.param("systemId", sys.toString())
					.param("employeeId", emp.toString())).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while inserting records in Systems", rootException.getMessage());
	}
	
	@Test
	public void getSystemByIdTest() throws Exception {

		try {
			UUID sys_uid = UUID.randomUUID();

			Systems system = new Systems();
			system.setSystemId(sys_uid);

			when(genericDaoMock.getById(any(Systems.class), any(UUID.class))).thenReturn(system);
			MvcResult result = mockMvc.perform(get("/" + MODULE + "/" + sys_uid)).andDo(print())
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andReturn();

			assertEquals(system.getSystemId(), sys_uid);

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}

	}
	
	
	@Test
	public void getAllSystemsNormalFlowTest() throws Exception {
		
		UUID uuid = UUID.randomUUID();
		UUID uuid1 = UUID.randomUUID();
		Systems system=new Systems();
		system.setSystemId(uuid);
		Systems sys1=new Systems();
		sys1.setSystemId(uuid1);
		
		ArrayList<Systems> lst = new ArrayList();;
		lst.add(system);
		lst.add(sys1);

		when(genericDaoMock.getAll(any(Systems.class))).thenReturn(lst);

		mockMvc.perform(get("/" + MODULE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())	;
	}
	
	@Test
	public void getSystemFlowTest() throws Exception{
		
		try {
			String request = "something";
			Systems systems = new Systems();
			systems.setSystemName(request);
			ArrayList<Systems> systemList = new ArrayList<>();
			systemList.add(systems);

			Mockito.when(systemDaoMock.getSystem(eq(request))).thenReturn(systemList);

			mockMvc.perform(get("/" + MODULE + "/System").param("request", request)).andDo(print())
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteSystemByIdTest() throws Exception{
		try {
			//Request Params
			UUID uuid = UUID.randomUUID();
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Systems.class))).thenReturn(true);
			
			// Start Test
			mockMvc.perform(delete("/"+MODULE+"/"+uuid)).andDo(print())
				.andExpect(status().isOk());
		}catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void getOsCountTest() {
		try {
			Systems systems=new Systems();
			ArrayList<Systems> al=new ArrayList<>();
			al.add(systems);
			when(systemDaoMock.getOs(any(Systems.class))).thenReturn(al);
			mockMvc.perform(get("/"+MODULE+"/operatingSystem")).andDo(print())
			.andExpect(status().isOk());
			
		}catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllAvailSystemsTest() {
		try {
			Systems systems=new Systems();
			ArrayList<Systems> al=new ArrayList<>();
			al.add(systems);
			when(systemDaoMock.getAllAvailableSystems(any(Systems.class))).thenReturn(al);
			mockMvc.perform(get("/"+MODULE+"/getAllAvailableSystems")).andDo(print())
			.andExpect(status().isOk());
			
		}catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	

}