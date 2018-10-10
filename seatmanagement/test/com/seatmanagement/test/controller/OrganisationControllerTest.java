package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrganisationControllerTest {
	
	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String MODULE = "organisation";
	private static final String WEBAPP = "seatmanagement";
	private static final String BASE_URL = "http://"+HOST+":"+PORT+"/"+WEBAPP+"/"+MODULE+"";
	private static final String REQUEST_TYPE = "requestType";
	private static final String REQUEST_TYPE_AJAX = "AJAX";

	private static final Logger logger = LoggerFactory.getLogger(OrganisationControllerTest.class);
	
	@Value("${hibernate.format_sql}") String propertyValue;
		
	@Test
	public void propertyFileTest() {
		try {
			
			assertThat(propertyValue, containsString("test"));
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

@Ignore
	@Test
	public void saveOrganisationTest() {
		try {

			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/saveOrganisation");
		    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		    params.add(new BasicNameValuePair("organisationName", "Save Organisation Test"));
		    httpPost.setEntity(new UrlEncodedFormEntity(params));
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void saveOrganisationNotEmptyValidationTest() {
		try {

			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/saveOrganisation");
		    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		    params.add(new BasicNameValuePair("organisationName", ""));
		    httpPost.setEntity(new UrlEncodedFormEntity(params));
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(500));
		    assertThat(responseBody, containsString("\"ERROR_MESSAGE\":\"Organisation name can not be empty\""));
		    assertThat(responseBody, containsString("\"ERROR_CODE\":9000"));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	/*@Test
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
			assertThat(organisations, not(IsEmptyCollection.empty()));

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
	}*/

}
