package com.seatmanagement.test.controller;

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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloorControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FloorControllerTest.class);
	
	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String WEBAPP = "seatmanagement";
	private static final String MODULE = "floor";
	private static final String BASE_URL = "http://"+HOST+":"+PORT+"/"+WEBAPP+"/"+MODULE+"";
	private static final String REQUEST_TYPE = "requestType";
	private static final String REQUEST_TYPE_AJAX = "AJAX";
	
	@Test
	public void saveFloorNormalFlowTest() {
		try {

			CloseableHttpClient client = HttpClients.createDefault();
			
		    HttpPost httpPost = new HttpPost(BASE_URL+"/floorsave");
		    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		    params.add(new BasicNameValuePair("buildingId", "e1bf4b60-b9fd-475e-9bf7-16cd6b6d9003"));
		    params.add(new BasicNameValuePair("floorType", "Commercial"));
		    params.add(new BasicNameValuePair("floorName", "Ground Floor"));
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

}
