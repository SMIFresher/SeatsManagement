package com.seatmanagement.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.seatmanagement.config.TestErrorHandler;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.OrganisationDao;
import com.seatmanagement.model.Organisation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/dispatcher-servlet.xml" })
@WebAppConfiguration
public class OrganisationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(OrganisationControllerTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private OrganisationDao organisationDao;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void controllerInitializedTest() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(wac.getBean("organisationController"));
	}

	@Test
	public void saveOrganisationTest() {
		try {

			// Overall flow test
			mockMvc.perform(
					post("/organisation/saveOrganisation").param("organisationName", "Test Organisation Insert"))
					.andDo(print()).andExpect(status().isOk());

			// Test whether record inserted in database
			Organisation organisation = getOrganisationByName("Test Organisation Insert");
			Assert.assertNotNull(organisation);

			// delete test record
			deleteOrganisationById(organisation.getOrganisationId());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void saveOrganisationNotEmptyValidationTest() {
		try {

			// MockMVC not used. Rest Template to test complete flow
			// Must try to implement Rest template in a mock server
			RestTemplate restTemplate = new RestTemplate();

			restTemplate.setErrorHandler(new TestErrorHandler());

			String saveOrganisationUrl = "http://localhost:8080/seatmanagement/organisation/saveOrganisation";
			HttpHeaders headers = new HttpHeaders();
			headers.set("RequestType", "AJAX");

			Organisation organisation = new Organisation();
			organisation.setOrganisationName("");
			HttpEntity<Organisation> entityReq = new HttpEntity<Organisation>(organisation, headers);

			ResponseEntity<String> result = restTemplate.postForEntity(saveOrganisationUrl, entityReq, String.class);
			/*assertThat(result.getBody(), containsString("Organisation name can not be empty"));*/
			/*System.out.println("debug");*/
		} catch (Exception e) {
			assertThat(e.getMessage(), containsString("Organisation name can not be empty"));
			assertThat(e.getMessage(), containsString("9000"));
			assertThat(e.getMessage(), containsString("500"));
		}
	}

	@Test
	public void updateOrganisationTest() {
		try {
			// insert test record first
			String organisationName = "Test Organisation Update";
			Organisation organisation = saveOrganisation(organisationName);

			// Overall flow test
			mockMvc.perform(post("/organisation/updateOrganisation")
					.param("organisationId", organisation.getOrganisationId().toString())
					.param("organisationName", "Test Organisation after update")).andDo(print())
					.andExpect(status().isOk());

			// Test whether record updated in database
			organisation = (Organisation) genericDao.getById(organisation, organisation.getOrganisationId());
			assertEquals("Test Organisation after update", organisation.getOrganisationName());

			// delete test record
			deleteOrganisationById(organisation.getOrganisationId());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void getOrganisationByIdTest() {
		try {
			// insert test record
			String organisationName = "getOrganisationByIdTest";
			Organisation organisation = saveOrganisation(organisationName);

			// Overall flow test
			MvcResult result = mockMvc
					.perform(post("/organisation/getOrganisationById").param("organisationId",
							organisation.getOrganisationId().toString()))
					.andDo(print()).andExpect(status().isOk()).andReturn();

			// assert record in response
			String content = result.getResponse().getContentAsString();
			Gson gson = new Gson();
			Organisation organisationFromDB = gson.fromJson(content, Organisation.class);
			Assert.assertNotNull(organisationFromDB);

			// delete test record
			deleteOrganisationById(organisation.getOrganisationId());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void getAllOrganisationsTest() {
		try {
			// insert test record
			String organisationName = "getAllOrganisationsTest";
			Organisation organisation = saveOrganisation(organisationName);

			// Overall flow test
			MvcResult result = mockMvc.perform(post("/organisation/getAllOrganisations")).andDo(print())
					.andExpect(status().isOk()).andReturn();

			// assert list is not empty in response
			String content = result.getResponse().getContentAsString();
			Gson gson = new Gson();
			List<Organisation> organisations = gson.fromJson(content, List.class);
			/*assertThat(organisations, not(IsEmptyCollection.empty()));*/

			// delete test record
			deleteOrganisationById(organisation.getOrganisationId());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void deleteOrganisationByIdTest() {
		try {
			// insert test record
			Organisation organisation = saveOrganisation("Test Organisation Delete");

			// Overall flow test
			mockMvc.perform(post("/organisation/deleteOrganisationById").param("organisationId",
					organisation.getOrganisationId().toString())).andDo(print()).andExpect(status().isOk());

			// assert record deleted in db
			Object deleteScenarioOrganisation = genericDao.getById(organisation, organisation.getOrganisationId());

			Assert.assertNull(deleteScenarioOrganisation);
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	private Organisation saveOrganisation(String organisationName) throws Exception {
		Organisation organisation = new Organisation();
		organisation.setOrganisationName(organisationName);

		organisation = (Organisation) genericDao.saveOrUpdate(organisation);

		return organisation;
	}

	private Organisation getOrganisationByName(String organisationName) throws Exception {
		Organisation organisation = null;

		List<Organisation> testOrganisations = organisationDao.getOrganisationByName(organisationName);
		if (Objects.nonNull(testOrganisations) && !testOrganisations.isEmpty()) {
			organisation = testOrganisations.get(0);
		}

		return organisation;
	}

	private void deleteOrganisationById(UUID organisationId) throws Exception {
		Organisation organisation = new Organisation();
		organisation.setOrganisationId(organisationId);
		genericDao.delete(organisation);
	}

}
