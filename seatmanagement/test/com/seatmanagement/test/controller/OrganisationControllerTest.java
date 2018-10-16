package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Organisation;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class OrganisationControllerTest  {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganisationControllerTest.class);
	
	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String MODULE = "organisation";
	private static final String WEBAPP = "seatmanagement";
	private static final String BASE_URL = "http://"+HOST+":"+PORT+"/"+WEBAPP+"/"+MODULE+"";
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

	@Disabled
	@Test
	public void saveOrganisationNotEmptyValidationTest() {
		try {
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {mockMvc.perform(post("/Organisations").param("organisationName", ""));});
			BusinessException rootException = (BusinessException) ExceptionUtils.getRootCause(thrown);
			
			assertThat(rootException, instanceOf(BusinessException.class));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	/*@Test
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

	@Test
	public void getAllOrganisationsNormalFlowTest() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/getAllOrganisations");
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    List organisationList = new Gson().fromJson(responseBody, List.class);
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		    assertThat(organisationList.size(), is(2));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void deleteOrganisationByIdNormalFlowTest() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/deleteOrganisationById");
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		    params.add(new BasicNameValuePair("organisationId", "3150e520-cd6b-11e8-b568-0800200c9a66"));
		    httpPost.setEntity(new UrlEncodedFormEntity(params));
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteOrganisationByIdRequestParamNullTest() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/deleteOrganisationById");
		    httpPost.setHeader(REQUEST_TYPE, REQUEST_TYPE_AJAX);
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    
		    String responseBody = EntityUtils.toString(response.getEntity());
		    
		    assertThat(response.getStatusLine().getStatusCode(), equalTo(400));
		    assertThat(responseBody, containsString("\"ERROR_MESSAGE\":\"Internal Server Error\""));
		    assertThat(responseBody, containsString("\"ERROR_CODE\":9002"));
		    
		    client.close();
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
*/
}
