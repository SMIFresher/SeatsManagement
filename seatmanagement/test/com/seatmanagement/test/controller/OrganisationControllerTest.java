package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.service.OrganisationService;

public class OrganisationControllerTest {
	
	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String MODULE = "organisation";
	private static final String WEBAPP = "seatmanagement";
	private static final String BASE_URL = "http://"+HOST+":"+PORT+"/"+WEBAPP+"/"+MODULE+"";
	private static final String REQUEST_TYPE = "requestType";
	private static final String REQUEST_TYPE_AJAX = "AJAX";

	private static final Logger logger = LoggerFactory.getLogger(OrganisationControllerTest.class);
	
	@Autowired
	private GenericDao genericDaoMock;
	
	@Autowired
	private OrganisationService organisationService;

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
	
	@Ignore
	@Test
	public void saveOrganisationWithoutRequestParamTest() {
		try {

			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/saveOrganisation");
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(500));
		    assertThat(responseBody, containsString("Organisation name can not be empty"));
		    assertThat(responseBody, containsString("Organisation name can not be null"));
		    assertThat(responseBody, containsString("\"ERROR_CODE\":9000"));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void getOrganisationViewTest() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/getOrganisationView");
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		    
		    client.close();

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	/*@Test
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
	}*/

	@Test
	public void getAllOrganisationsNormalFlowTest() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/getAllOrganisations");
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    List organisations = new ArrayList();
		    organisations.add(new Organisation());
		    organisations.add(new Organisation());
		    
		    when(genericDaoMock.getAll(any((Organisation.class)))).thenReturn(organisations);
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	/*@Test
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
