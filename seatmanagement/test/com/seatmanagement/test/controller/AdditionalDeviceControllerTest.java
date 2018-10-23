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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.exception.SpringMVCStandardException;
import com.seatmanagement.model.AdditionalDevice;
import com.seatmanagement.model.Organisation;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class AdditionalDeviceControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(AdditionalDeviceControllerTest.class);

	private static final String MODULE = "Additionaldevices";

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
	public void saveAdditionalDeviceTest() {
		try {
			Mockito.when(genericDaoMock.saveOrUpdate(any(AdditionalDevice.class))).thenReturn(new Organisation());
			mockMvc.perform(post("/"+MODULE).param("device_name", "Mouse"))
				.andDo(print())
				.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void saveAdditionalDeviceDatabaseExceptionTest() {
		try {

			// DAO Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while inserting records in  AdditionalDevice");
			Mockito.when(genericDaoMock.saveOrUpdate(any(AdditionalDevice.class))).thenThrow(applicationException);

			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE).param("device_name", "Mouse"));
			});

			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

			assertThat(rootException, instanceOf(ApplicationException.class));
			assertEquals("Error while inserting records in  AdditionalDevice", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	
	@Test
	public void saveAdditionalNotEmptyValidationTest() {
		try {
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE).param("device_name", ""));
			});
			BusinessException rootException = (BusinessException) ExceptionUtils.getRootCause(thrown);

			assertThat(rootException, instanceOf(BusinessException.class));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void saveAdditionalnWithoutRequestParamTest() {
		try {

			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(post("/"+MODULE));
			});
			BusinessException rootException = (BusinessException) ExceptionUtils.getRootCause(thrown);

			assertThat(rootException, instanceOf(BusinessException.class));
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllAdditionalDeviceNormalFlowTest() {
		try {
			// DAO Configurations
			List<AdditionalDevice> additionaldevices = new ArrayList<AdditionalDevice>();
			AdditionalDevice additionaldevice = new AdditionalDevice();
			additionaldevice.setDevice_name("Mouse");
			additionaldevices.add(additionaldevice);
			Mockito.when(genericDaoMock.getAll(any(AdditionalDevice.class))).thenReturn(additionaldevices);

			mockMvc.perform(get("/"+MODULE).param("device_name", "Mouse")).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.[*].device_name", containsInAnyOrder("Mouse")));

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllAdditionalDeviceDatabaseExceptionTest() {
		try {
			// DAO Configurations
			ApplicationException applicationException = new ApplicationException(
					"Error while retreiving records from AdditionalDevice");
			Mockito.when(genericDaoMock.getAll(any(AdditionalDevice.class))).thenThrow(applicationException);

			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(get("/"+MODULE)).andDo(print());
			});
			
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertEquals("Error while retreiving records from AdditionalDevice", rootException.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	@Test
	public void deleteAdditionalDeviceByIdNormalFlowTest() {
		try {
			// DAO Configuration
			Mockito.when(genericDaoMock.delete(any(AdditionalDevice.class))).thenReturn(true);

			mockMvc.perform(delete("/"+MODULE+"/47ba4710-20a5-4546-9fbd-b1ead0f3cfb8"))
					.andDo(print()).andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}

	@Test
	public void deleteAdditionalDeviceByIdDatabaseExceptionTest() {
		try {
			// DAO Configuration
			ApplicationException applicationException = new ApplicationException(
					"Error while deleting records in AdditionalDevice");
			Mockito.when(genericDaoMock.delete(any(Organisation.class))).thenThrow(applicationException);
			
			NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
				mockMvc.perform(delete("/"+MODULE+"/47ba4710-20a5-4546-9fbd-b1ead0f3cfb8"));
			});
			
			ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);
			assertEquals("Error while deleting records in AdditionalDevice", rootException.getMessage());
			
		} catch (Exception e) {
			fail(e.getMessage());

			e.printStackTrace();
		}
	}
	

	


}