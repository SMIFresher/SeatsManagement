package com.seatmanagement.autowire;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.seatmanagement.model.AutowireTestModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/dispatcher-servlet.xml" })
@WebAppConfiguration
public class AutowireTest {
	private static final Logger logger = LoggerFactory.getLogger(AutowireTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	/*@Autowired
	private AutowireTestModel byType;*/
	
	@Autowired
	private AutowireTestModel byName;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void autowireTest() {
		// By default spring autowires by type
		//assertThat(byType.getName(), containsString("autowiredByType"));

		// The second default autowiring is by name
		assertThat(byName.getName(), containsString("autowiredByName"));
	}

}
