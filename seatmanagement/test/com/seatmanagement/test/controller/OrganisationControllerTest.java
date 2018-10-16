package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.exception.SpringMVCStandardException;
import com.seatmanagement.model.Organisation;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class OrganisationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationControllerTest.class);

	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String MODULE = "organisation";
	private static final String WEBAPP = "seatmanagement";
	private static final String BASE_URL = "http://" + HOST + ":" + PORT + "/" + WEBAPP + "/" + MODULE + "";
	private static final String REQUEST_TYPE = "requestType";
	private static final String REQUEST_TYPE_AJAX = "AJAX";

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
	public void saveOrganisationTest() {
		try {
			Mockito.when(genericDaoMock.saveOrUpdate(any(Organisation.class))).thenReturn(new Organisation());
			mockMvc.perform(post("/Organisations").param("organisationName", "Test Organisation"));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void saveOrganisationDatabaseExceptionTest() {
		try {

			// DAO Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while inserting records in  Organisation");
			Mockito.when(genericDaoMock.saveOrUpdate(any(Organisation.class))).thenThrow(applicationException);

			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/Organisations").param("organisationName", "Test Organisation"));
			});

			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while inserting records in  Organisation", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void saveOrganisationNotEmptyValidationTest() {
		try {
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/Organisations").param("organisationName", ""));
			});
			BusinessException rootException = (BusinessException) ExceptionUtils.getRootCause(thrown);

			assertThat(rootException, instanceOf(BusinessException.class));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void saveOrganisationWithoutRequestParamTest() {
		try {

			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/Organisations"));
			});
			BusinessException rootException = (BusinessException) ExceptionUtils.getRootCause(thrown);

			assertThat(rootException, instanceOf(BusinessException.class));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void getOrganisationViewTest() {
		try {
			mockMvc.perform(
					get("/Organisations/ViewAndEditOrganisations").param("organisationName", "Test Organisation"))
					.andExpect(status().isOk()).andExpect(view().name("/HR/Organisation"));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void getAllOrganisationsNormalFlowTest() {
		try {
			// DAO Configurations
			List<Organisation> organisations = new ArrayList<Organisation>();
			Organisation organisation = new Organisation();
			organisation.setOrganisationName("Test Organisation");
			organisations.add(organisation);
			Mockito.when(genericDaoMock.getAll(any(Organisation.class))).thenReturn(organisations);

			mockMvc.perform(get("/Organisations").param("organisationName", "Test Organisation")).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.[*].organisationName", containsInAnyOrder("Test Organisation")));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllOrganisationsDatabaseExceptionTest() {
		try {
			// DAO Configurations
			ApplicationException applicationException = new ApplicationException(
					"Error while retreiving records from Organisation");
			Mockito.when(genericDaoMock.getAll(any(Organisation.class))).thenThrow(applicationException);

			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/Organisations")).andDo(print());
			});
			
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertEquals("Error while retreiving records from Organisation", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void deleteOrganisationByIdNormalFlowTest() {
		try {
			// DAO Configuration
			Mockito.when(genericDaoMock.delete(any(Organisation.class))).thenReturn(true);

			mockMvc.perform(delete("/Organisations/47ba4710-20a5-4546-9fbd-b1ead0f3cfb8"))
					.andDo(print()).andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void deleteOrganisationByIdDatabaseExceptionTest() {
		try {
			// DAO Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while deleting records in Organisation");
			Mockito.when(genericDaoMock.delete(any(Organisation.class))).thenThrow(applicationException);
			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(delete("/Organisations/47ba4710-20a5-4546-9fbd-b1ead0f3cfb8"));
			});
			
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertEquals("Error while deleting records in Organisation", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
}
