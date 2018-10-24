package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.seatmanagement.dao.EmployeeDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Team;



@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class EmployeeControllerTest {
	
	private static final String CONTROLLER = "Employees";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@SuppressWarnings("rawtypes")
	@Autowired
	private GenericDao genericDaoMock;

	@Autowired
	private EmployeeDao employeeDaoMock;

	@BeforeEach
	public void setup() {

		Mockito.reset(genericDaoMock);

		Mockito.reset(employeeDaoMock);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void saveEmployeeWithoutTeamTestNormalFlow(){
	
		UUID organizationUUID=UUID.randomUUID();
		
		try {
			
			//Organization Configuration
			Organisation organization = new Organisation();
			organization.setOrganisationId(organizationUUID);
			
			//Mockito Configuration			
			when(genericDaoMock.saveOrUpdate(any(Employee.class))).thenReturn(new Employee());
			
			// Test Start
			mockMvc.perform(post("/" + CONTROLLER+"/" + organizationUUID).param("employeeRoll", "SMI_001").param("firstName", "FIRSTNAME")
					.param("lastName", "LASTNAME").param("designation", "DESIGNATION").param("doj", "23/10/2018")
					.param("organisationId", organizationUUID.toString())).andExpect(status().isOk()).andDo(print());
		}		
		catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveEmployeeWithTeamTestNormalFlow(){
	
		UUID organizationUUID=UUID.randomUUID();
		UUID teamUUID=UUID.randomUUID();
		
		try {
			
			//Organization Configuration
			Organisation organization = new Organisation();
			organization.setOrganisationId(organizationUUID);
			
			Team team = new Team();
			team.setTeamId(teamUUID);
			
			//Mockito Configuration			
			when(genericDaoMock.saveOrUpdate(any(Employee.class))).thenReturn(new Employee());
			
			// Test Start
			mockMvc.perform(post("/" + CONTROLLER+"/" + organizationUUID + "/" + teamUUID).param("employeeRoll", "SMI_001").param("firstName", "FIRSTNAME")
					.param("lastName", "LASTNAME").param("designation", "DESIGNATION").param("doj", "23/10/2018")
					.param("teamId", teamUUID.toString()).param("organisationId", organizationUUID.toString())).andExpect(status().isOk()).andDo(print());
		}		
		catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveEmployeeWithoutTeamDatabaseExceptionWhileSavingEmployeeTest() {
		try {
			// Request Params
			UUID organizationUUID = UUID.randomUUID();
			
			// Exception Configuration
			String exceptionMessage = "Error while saving Employee record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.saveOrUpdate(any(Employee.class))).thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/" + CONTROLLER+"/" + organizationUUID).param("employeeRoll", "SMI_001").param("firstName", "FIRSTNAME")
						.param("lastName", "LASTNAME").param("designation", "DESIGNATION").param("doj", "23/10/2018")
						.param("organisationId", organizationUUID.toString()));
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
	public void saveEmployeeWithTeamDatabaseExceptionWhileSavingEmployeeTest() {
		try {
			// Request Params
			UUID organizationUUID = UUID.randomUUID();
			UUID teamUUID = UUID.randomUUID();
			// Exception Configuration
			String exceptionMessage = "Error while saving Employee record";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.saveOrUpdate(any(Employee.class))).thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/" + CONTROLLER+"/" + organizationUUID + "/" + teamUUID).param("employeeRoll", "SMI_001").param("firstName", "FIRSTNAME")
						.param("lastName", "LASTNAME").param("designation", "DESIGNATION").param("doj", "23/10/2018")
						.param("teamId", teamUUID.toString()).param("organisationId", organizationUUID.toString())).andExpect(status().isOk()).andDo(print());
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
	public void getAllEmployeeDetailsNormalFlowTest() {
		try {			
			
			// Employee Configuration
			Employee firstEmployee = new Employee();
			Employee secondEmployee = new Employee();
			
			List<Employee> employee = new ArrayList<Employee>();
			
			employee.add(firstEmployee);
			employee.add(secondEmployee);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getAll(any(Employee.class))).thenReturn(employee);
				
			// Start Test			
			MvcResult mvcResult = mockMvc.perform(get("/"+CONTROLLER)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
			// Asserts
			String employeeResponseString = mvcResult.getResponse().getContentAsString();
			List<Employee> employeeInResponse = new Gson().fromJson(employeeResponseString, List.class);
			assertThat(employeeInResponse, not(IsEmptyCollection.empty()));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllEmployeeDetailsDatabaseExceptionTest() {
		try {			
			// DAO Configuration
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Employee records";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getAll(any(Employee.class))).thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/"+CONTROLLER));
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
	public void getEmployeesByDesignationNormalFlowTest() {
		try {			
			
			String employeeDesignation="Designation of Employee";
			
			// Employee Configuration
			Organisation organization = new Organisation();
			Team team = new Team();
			Employee employee = new Employee();
			
			employee.setEmployeeId(UUID.randomUUID());
			employee.setEmployeeRoll("ROLL NUMBER");
			employee.setFirstName("FirstName");
			employee.setLastName("LastName");
			employee.setDoj("24/10/2018");
			employee.setDesignation("Designation of Employee");
			employee.setOrganisation(organization);
			employee.setTeam(team);
			
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			
			// Mockito Configuration
			Mockito.when(employeeDaoMock.getEmployeesByDesignation(employeeDesignation)).thenReturn(employeeList);
				
			// Start Test			
			MvcResult mvcResult = mockMvc.perform(get("/"+CONTROLLER+"/"+"Designation").param("designation", employeeDesignation)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
			// Asserts
			String employeeResponseString = mvcResult.getResponse().getContentAsString();
			List<Employee> employeeInResponse = new Gson().fromJson(employeeResponseString, List.class);
			assertThat(employeeInResponse, not(IsEmptyCollection.empty()));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getEmployeesByDesignationDatabaseExceptionTest() {
		try {			
String employeeDesignation="Designation of Employee";
			
			// Employee Configuration
			Organisation organization = new Organisation();
			Team team = new Team();
			Employee employee = new Employee();
			
			employee.setEmployeeId(UUID.randomUUID());
			employee.setEmployeeRoll("ROLL NUMBER");
			employee.setFirstName("FirstName");
			employee.setLastName("LastName");
			employee.setDoj("24/10/2018");
			employee.setDesignation("Designation of Employee");
			employee.setOrganisation(organization);
			employee.setTeam(team);
			
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Employee by designation records";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(employeeDaoMock.getEmployeesByDesignation(employeeDesignation)).thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				MvcResult mvcResult = mockMvc.perform(get("/"+CONTROLLER+"/"+"Designation").
						param("designation", employeeDesignation)).andDo(print()).andExpect(status().isOk()).andReturn();
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
	public void getEmployeesByTeamIdNormalFlowTest() {
		try {			
			
			UUID teamUUID=UUID.randomUUID();
			
			// Employee Configuration
			Organisation organization = new Organisation();
			Team team = new Team();
			team.setTeamId(teamUUID);
			
			Employee employee = new Employee();
			
			employee.setEmployeeId(UUID.randomUUID());
			employee.setEmployeeRoll("ROLL NUMBER");
			employee.setFirstName("FirstName");
			employee.setLastName("LastName");
			employee.setDoj("24/10/2018");
			employee.setDesignation("Designation of Employee");
			employee.setOrganisation(organization);
			employee.setTeam(team);
			
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			
			// Mockito Configuration
			Mockito.when(employeeDaoMock.getEmployeesByTeamId(teamUUID)).thenReturn(employeeList);
				
			// Start Test			
			MvcResult mvcResult = mockMvc.perform(get("/"+CONTROLLER+"/"+teamUUID).param("teamId", teamUUID.toString())).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
			// Asserts
			String employeeResponseString = mvcResult.getResponse().getContentAsString();
			List<Employee> employeeInResponse = new Gson().fromJson(employeeResponseString, List.class);
			assertThat(employeeInResponse, not(IsEmptyCollection.empty()));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getEmployeesByTeamIdDatabaseExceptionTest() {
		try {			
			UUID teamUUID=UUID.randomUUID();
			
			// Employee Configuration
			Organisation organization = new Organisation();
			Team team = new Team();
			team.setTeamId(teamUUID);
			
			Employee employee = new Employee();
			
			employee.setEmployeeId(UUID.randomUUID());
			employee.setEmployeeRoll("ROLL NUMBER");
			employee.setFirstName("FirstName");
			employee.setLastName("LastName");
			employee.setDoj("24/10/2018");
			employee.setDesignation("Designation of Employee");
			employee.setOrganisation(organization);
			employee.setTeam(team);
			
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			
			// Exception Configuration
			String exceptionMessage = "Error while retreiving Employee by TeamId records";
			ApplicationException applicationException = new ApplicationException(exceptionMessage);
			
			// Mockito Configuration
			Mockito.when(employeeDaoMock.getEmployeesByTeamId(teamUUID)).thenThrow(applicationException);
				
			// Start Test			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				MvcResult mvcResult = mockMvc.perform(get("/"+CONTROLLER+"/"+teamUUID).param("teamId", teamUUID.toString())).andDo(print())
						.andExpect(status().isOk()).andReturn();
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
	public void deleteEmployeeByIdNormalFlowTest() {
		try {
			
			//Request Params
			UUID employeeUUID = UUID.randomUUID();
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Employee.class))).thenReturn(true);
			
			// Start Test
			mockMvc.perform(delete("/"+CONTROLLER+ "/" +employeeUUID)).andDo(print())
				.andExpect(status().isOk());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteEmployeeByIdDatabaseExceptionTest() {
		try {
			//Request Params
			UUID employeeUUID = UUID.randomUUID();

			// DAO Configuration

			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException("Error while deleting record from Employee");
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Employee.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(delete("/"+CONTROLLER+"/"+employeeUUID));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while deleting record from Employee", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
}
