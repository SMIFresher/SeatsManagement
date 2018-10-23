package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.seatmanagement.dao.ReallocationDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Reallocation;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class ReallocationControllerTest {
private static final Logger logger = LoggerFactory.getLogger(TeamControllerTest.class);
	
	private static final String MODULE = "Reallocations";
	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ReallocationDao reallocationDaoMock;
	
	@Autowired
	private GenericDao genericDao;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
		Mockito.reset(reallocationDaoMock);
		Mockito.reset(genericDao);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void reallocationByEmployeeIdNormalFlowTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			
			//DAO Configuration
			
			// Reallocation Configuration
			String reallocationIDString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			UUID reallocationID = UUID.fromString(reallocationIDString);
			Reallocation reallocation = new Reallocation();	
			reallocation.setReallocationId(reallocationID);
				
			// Mockito Configuration
			Mockito.when(reallocationDaoMock.getReallocationByEmployeeId(any(UUID.class))).thenReturn(reallocation);
			
			// Start Test
			MvcResult result = mockMvc.perform(get("/"+MODULE+"/"+employeeIDString))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();
			
			// Asserts
			String reallocationResponseString = result.getResponse().getContentAsString();
			Reallocation reallocationResponse = new Gson().fromJson(reallocationResponseString, Reallocation.class);
			assertEquals(reallocationResponse.getReallocationId(), reallocationID);

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void reallocationByEmployeeIdDatabaseExceptionTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			
			//DAO Configuration
			
			// ApplicationException Configuration
			String ExceptionMessage = "Error while retreiving Reallocation record";
			ApplicationException applicationException = new ApplicationException(ExceptionMessage);
				
			// Mockito Configuration
			Mockito.when(reallocationDaoMock.getReallocationByEmployeeId(any(UUID.class))).thenThrow(applicationException);
			
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/"+MODULE+"/"+employeeIDString));
			});
			
			// Asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals(ExceptionMessage, rootException.getMessage());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveReallocationNormalFlowTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String blockIdString = "0ba75bf7-500e-42d1-bc3e-8217141a39ee";
			String previousblockIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			
			//DAO Configuration
			
			// Employee Configuration
			Employee employee = new Employee();	
			
			// Block Configuration
			Block block = new Block();	
			
			Block previousBlock = new Block();	
				
			// Mockito Configuration
			Mockito.when(genericDao.getById(any(Object.class), any(UUID.class)))
				.thenReturn(employee)
				.thenReturn(block)
				.thenReturn(previousBlock);
			
			Mockito.when(genericDao.saveOrUpdate(any(Reallocation.class))).thenReturn(null);
			
			// Start Test
			mockMvc.perform(post("/"+MODULE)
					.param("employeeId", employeeIDString)
					.param("blockId", blockIdString)
					.param("previousblockId", previousblockIdString))
				.andDo(print())
				.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveReallocationDatabaseExceptionWhileFetchingEmployeeTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String blockIdString = "0ba75bf7-500e-42d1-bc3e-8217141a39ee";
			String previousblockIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			
			// DAO Configuration
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Employee record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDao.getById(any(Employee.class), any(UUID.class)))
				.thenThrow(applicationException);
			
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("employeeId", employeeIDString)
						.param("blockId", blockIdString)
						.param("previousblockId", previousblockIdString));
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
	
	@Test
	public void saveReallocationDatabaseExceptionWhileFetchingBlockTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String blockIdString = "0ba75bf7-500e-42d1-bc3e-8217141a39ee";
			String previousblockIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			
			// DAO Configuration
			
			// Employee Configuration
			Employee employee = new Employee();	
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Block record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDao.getById(any(Object.class), any(UUID.class)))
				.thenReturn(employee)
				.thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("employeeId", employeeIDString)
						.param("blockId", blockIdString)
						.param("previousblockId", previousblockIdString));
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
	
	@Test
	public void saveReallocationDatabaseExceptionWhileFetchingPreviousBlockTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String blockIdString = "0ba75bf7-500e-42d1-bc3e-8217141a39ee";
			String previousblockIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			
			// DAO Configuration
			
			// Employee Configuration
			Employee employee = new Employee();	
			
			// Block Configuration
			Block block = new Block();	
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Block record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDao.getById(any(Object.class), any(UUID.class)))
				.thenReturn(employee)
				.thenReturn(block)
				.thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("employeeId", employeeIDString)
						.param("blockId", blockIdString)
						.param("previousblockId", previousblockIdString));
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
	
	@Test
	public void saveReallocationDatabaseExceptionWhileSavingReallocationTest() {
		try {
			// Request Params
			String employeeIDString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String blockIdString = "0ba75bf7-500e-42d1-bc3e-8217141a39ee";
			String previousblockIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			
			// DAO Configuration
			
			// Employee Configuration
			Employee employee = new Employee();	
			
			// Block Configuration
			Block block = new Block();	
			
			Block previousBlock = new Block();	
			
			// Exception Configuration
			String exceptionMessage = "Error while saving Reallocation record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDao.getById(any(Object.class), any(UUID.class)))
				.thenReturn(employee)
				.thenReturn(block)
				.thenReturn(previousBlock);
			
			Mockito.when(genericDao.saveOrUpdate(any(Reallocation.class))).thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("employeeId", employeeIDString)
						.param("blockId", blockIdString)
						.param("previousblockId", previousblockIdString));
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
	
	@Test
	public void getAllReallocationDetailsNormalFlowTest() {
		try {			
			// DAO Configuration
			
			// Reallocation Configuration
			Reallocation reallocationRecordOne = new Reallocation();
			Reallocation reallocationRecordTwo = new Reallocation();
			
			List<Reallocation> reallocations = new ArrayList<Reallocation>();
			
			reallocations.add(reallocationRecordOne);
			reallocations.add(reallocationRecordTwo);
			
			// Mockito Configuration
			Mockito.when(genericDao.getAll(any(Reallocation.class)))
				.thenReturn(reallocations);
				
			// Start Test			
			MvcResult mvcResult = mockMvc.perform(get("/"+MODULE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
			// Asserts
			String reallocationResponseString = mvcResult.getResponse().getContentAsString();
			List<Reallocation> reallocationsInResponse = new Gson().fromJson(reallocationResponseString, List.class);
			assertThat(reallocationsInResponse, not(IsEmptyCollection.empty()));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllReallocationDetailsDatabaseExceptionTest() {
		try {			
			// DAO Configuration
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Reallocation records";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDao.getAll(any(Reallocation.class)))
				.thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/"+MODULE));
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
	
	@Test
	public void getReallocationNormalFlowTest() {
		try {			
			mockMvc.perform(
					get("/"+MODULE+"/Reallocationview"))
					.andExpect(status().isOk()).andExpect(view().name("/HR/ReallocationView"));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void reallocationRequestNormalFlowTest() {
		try {			
			mockMvc.perform(
					get("/"+MODULE+"/Reallocationrequest"))
					.andExpect(status().isOk()).andExpect(view().name("/Lead/ReallocationRequest"));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void reallocationStatusNormalFlowTest() {
		try {			
			mockMvc.perform(
					get("/"+MODULE+"/Reallocationlead"))
					.andExpect(status().isOk()).andExpect(view().name("/Lead/ReallocationStatus"));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
}
