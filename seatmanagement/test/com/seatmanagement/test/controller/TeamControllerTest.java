package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Team;
import com.seatmanagement.test.util.TestUtil;

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
			
			Mockito.when(genericDaoMock.getById(any(Employee.class), any(UUID.class))).thenReturn(teamHead);
			Mockito.when(genericDaoMock.saveOrUpdate(any(Team.class))).thenReturn(team);
			Mockito.when(genericDaoMock.saveOrUpdate(any(Employee.class))).thenReturn(teamHead);
			
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
	public void saveTeamTeamHeadValidationTest() {
		try {
			// Request Params
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
			// Start Test
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE)
						.param("organisationId", organisationIdString)
						.param("teamName", teamName));
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
	
	/*@Test
	public void saveTeamOrganisationIdValidationTest() {
		try {
			// Request Params
			String teamHeadIdString = "7a82d67e-d24c-4ef7-8241-f9cb225703de";
			String organisationIdString = "47ba4710-20a5-4546-9fbd-b1ead0f3cfb8";
			String teamName = "Test_Team_Name";
			
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
	}*/
}
