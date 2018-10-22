package com.seatmanagement.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

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

import com.google.gson.Gson;
import com.seatmanagement.dao.ReallocationDao;
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
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
		Mockito.reset(reallocationDaoMock);
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
}
