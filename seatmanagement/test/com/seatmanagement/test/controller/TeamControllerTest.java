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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Team;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class TeamControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(TeamControllerTest.class);
	
	private static final String MODULE = "Teams";
	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private GenericDao genericDaoMock;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
		Mockito.reset(genericDaoMock);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void saveTeamNormalFlowTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
			//DAO Configuration
			
			// Organisation Configuration
			Organisation organisation = new Organisation();
			UUID organisationId = UUID.fromString(organisationIdString);
			
			organisation.setOrganisationId(organisationId);
			
			// Team Configuration
			Team team = new Team();
			team.setOrganisation(organisation);
			UUID teamHeadId = UUID.fromString(teamHeadIdString);
			
			team.setTeamHeadEmployeeId(teamHeadId);
			team.setTeamName(teamName);
			
			// TeamHead Configuration
			Employee teamHead = new Employee();
			
			teamHead.setEmployeeId(teamHeadId);
			teamHead.setFirstName("Team_Head_First_Name");
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Employee.class), any(UUID.class))).thenReturn(teamHead);
			when(genericDaoMock.saveOrUpdate(any(Object.class)))
			   .thenReturn(team)
			   .thenReturn(teamHead);
			
			// Start Test
			mockMvc.perform(post("/"+MODULE)
					.param("organisationId", organisationIdString)
					.param("teamName", teamName)
					.param("teamHeadEmployeeId", teamHeadIdString))
				.andDo(print())
				.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveTeamTeamNameValidationTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("organisationId", organisationIdString)
						.param("teamHeadEmployeeId", teamHeadIdString));
			});
			
			// asserts
			BusinessException rootException = (BusinessException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(BusinessException.class));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveTeamTeamHeadRecordAbsentInDatabaseTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
			//DAO Configuration
			
			// Organisation Configuration
			Organisation organisation = new Organisation();
			UUID organisationId = UUID.fromString(organisationIdString);
			
			organisation.setOrganisationId(organisationId);
			
			// Team Configuration
			Team team = new Team();
			team.setOrganisation(organisation);
			UUID teamHeadId = UUID.fromString(teamHeadIdString);
			
			team.setTeamHeadEmployeeId(teamHeadId);
			team.setTeamName(teamName);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Employee.class), any(UUID.class))).thenReturn(null);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("organisationId", organisationIdString)
						.param("teamName", teamName)
						.param("teamHeadEmployeeId", teamHeadIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Employee (Teamhead) Record not found", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveTeamDatabaseExceptionWhileFetchingEmployeeTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException("Error while retriving records from Employee");
			
			//DAO Configuration
			
			// Organisation Configuration
			Organisation organisation = new Organisation();
			UUID organisationId = UUID.fromString(organisationIdString);
			
			organisation.setOrganisationId(organisationId);
			
			// Team Configuration
			Team team = new Team();
			team.setOrganisation(organisation);
			UUID teamHeadId = UUID.fromString(teamHeadIdString);
			
			team.setTeamHeadEmployeeId(teamHeadId);
			team.setTeamName(teamName);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Employee.class), any(UUID.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("organisationId", organisationIdString)
						.param("teamName", teamName)
						.param("teamHeadEmployeeId", teamHeadIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while retriving records from Employee", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveTeamDatabaseExceptionWhileSavingTeamTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException("Error while saving team");
			
			//DAO Configuration
			
			// Organisation Configuration
			Organisation organisation = new Organisation();
			UUID organisationId = UUID.fromString(organisationIdString);
			
			organisation.setOrganisationId(organisationId);
			
			// Team Configuration
			Team team = new Team();
			team.setOrganisation(organisation);
			UUID teamHeadId = UUID.fromString(teamHeadIdString);
			
			team.setTeamHeadEmployeeId(teamHeadId);
			team.setTeamName(teamName);
			
			// TeamHead Configuration
			Employee teamHead = new Employee();
			
			teamHead.setEmployeeId(teamHeadId);
			teamHead.setFirstName("Team_Head_First_Name");
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Employee.class), any(UUID.class))).thenReturn(teamHead);
			Mockito.when(genericDaoMock.saveOrUpdate(any(Team.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("organisationId", organisationIdString)
						.param("teamName", teamName)
						.param("teamHeadEmployeeId", teamHeadIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while saving team", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveTeamDatabaseExceptionWhileSavingTeamHeadTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException("Error while saving Employee");
			
			//DAO Configuration
			
			// Organisation Configuration
			Organisation organisation = new Organisation();
			UUID organisationId = UUID.fromString(organisationIdString);
			
			organisation.setOrganisationId(organisationId);
			
			// Team Configuration
			Team team = new Team();
			team.setOrganisation(organisation);
			UUID teamHeadId = UUID.fromString(teamHeadIdString);
			
			team.setTeamHeadEmployeeId(teamHeadId);
			team.setTeamName(teamName);
			
			// TeamHead Configuration
			Employee teamHead = new Employee();
			
			teamHead.setEmployeeId(teamHeadId);
			teamHead.setFirstName("Team_Head_First_Name");
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getById(any(Employee.class), any(UUID.class))).thenReturn(teamHead);
			
			when(genericDaoMock.saveOrUpdate(any(Object.class)))
			   .thenReturn(team)
			   .thenThrow(applicationException);
			
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("organisationId", organisationIdString)
						.param("teamName", teamName)
						.param("teamHeadEmployeeId", teamHeadIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while saving Employee", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllNormalFlowTest() {
		try {
			
			//DAO Configuration
			
			// TeamHead Configuration
			Employee employee = new Employee();
			employee.setFirstName("First_Name_Test");
			
			// Team Configuration
			Team team = new Team();
			Set<Employee> employees = new HashSet<Employee>();
			employees.add(employee);
			team.setEmployees(employees);
			team.setTeamName("Team_Name_Test");
			
			List<Team> teams = new ArrayList<Team>();
			teams.add(team);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getAll(any(Team.class))).thenReturn(teams);
			
			
			// Start Test
			MvcResult result = mockMvc.perform(get("/"+MODULE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
			// asserts
			String teamsResponseString = result.getResponse().getContentAsString();
			List<Team> teamsResponse = new Gson().fromJson(teamsResponseString, List.class);
			assertThat(teamsResponse, not(IsEmptyCollection.empty()));
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllNoTeamInDatabaseTest() {
		try {
			
			//DAO Configuration
			
			List<Team> teams = new ArrayList<Team>();
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getAll(any(Team.class))).thenReturn(teams);
			
			
			// Start Test
			MvcResult result = mockMvc.perform(get("/"+MODULE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
			// asserts
			String teamsResponseString = result.getResponse().getContentAsString();
			List<Team> teamsResponse = new Gson().fromJson(teamsResponseString, List.class);
			assertThat(teamsResponse, IsEmptyCollection.empty());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllTeamWithNoEmployeeTest() {
		try {
			
			//DAO Configuration
			
			// Team Configuration
			Team team = new Team();
			team.setTeamName("Team_Name_Test");
			
			List<Team> teams = new ArrayList<Team>();
			teams.add(team);
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getAll(any(Team.class))).thenReturn(teams);
			
			
			// Start Test
			MvcResult result = mockMvc.perform(get("/"+MODULE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
			// asserts
			String teamsResponseString = result.getResponse().getContentAsString();
			List<Team> teamsResponse = new Gson().fromJson(teamsResponseString, List.class);
			assertThat(teamsResponse, not(IsEmptyCollection.empty()));
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllDatabaseExceptionTest() {
		try {
			
			//DAO Configuration
			
			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException("Error while retreiving records from Team");
			
			// Mockito Configuration
			Mockito.when(genericDaoMock.getAll(any(Team.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/"+MODULE));
			});
		
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while retreiving records from Team", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTeamByIdNormalFlowTest() {
		try {
			
			//Request Params
			String teamIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Team.class))).thenReturn(true);
			
			// Start Test
			mockMvc.perform(delete("/"+MODULE+"/deleteTeam/"+teamIdString)).andDo(print())
				.andExpect(status().isOk());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTeamByIdDatabaseExceptionTest() {
		try {
			//Request Params
			String teamIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";

			// DAO Configuration

			// Application Exception Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while deleting record from Team");
						
			// Mockito Configuration
			Mockito.when(genericDaoMock.delete(any(Team.class))).thenThrow(applicationException);
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(delete("/"+MODULE+"/deleteTeam/"+teamIdString));
			});
			
			// asserts
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while deleting record from Team", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getTeamViewNormalFlowTest() {
		try {
			mockMvc.perform(
					get("/"+MODULE+"/TeamView"))
					.andExpect(status().isOk()).andExpect(view().name("HR/team"));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getTeamTBNormalFlowTest() {
		try {
			mockMvc.perform(
					get("/"+MODULE+"/TeamTB"))
					.andExpect(status().isOk()).andExpect(view().name("Curds/TeamTbCURD"));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
}
