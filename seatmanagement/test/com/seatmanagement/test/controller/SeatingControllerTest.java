package com.seatmanagement.test.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.SeatingDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Block;
import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Seating;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@ContextConfiguration("file:WebContent/WEB-INF/Application-Context.xml")
@WebAppConfiguration
public class SeatingControllerTest {

	private static final String CONTROLLER = "Seatings";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@SuppressWarnings("rawtypes")
	@Autowired
	private GenericDao genericDaoMock;

	@Autowired
	private SeatingDao seatingDaoMock;

	@BeforeEach
	public void setup() {

		Mockito.reset(genericDaoMock);

		Mockito.reset(seatingDaoMock);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllSeatingNormalFlowTest() throws Exception {

		UUID uuid = UUID.randomUUID();
		UUID uuid1 = UUID.randomUUID();

		Block block = new Block();
		block.setBlockId(uuid1);

		Seating seating1 = new Seating();
		seating1.setSeatingId(uuid);
		seating1.setSeat_occupied(2);
		seating1.setX_axis("200");
		seating1.setY_axis("500");
		seating1.setSystemOccupied(5);
		seating1.setBlock(block);

		List<Seating> seatings = new ArrayList<Seating>();
		seatings.add(seating1);

		when(genericDaoMock.getAll(any(Seating.class))).thenReturn(seatings);

		mockMvc.perform(get("/" + CONTROLLER)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].systemOccupied", containsInAnyOrder(5)));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllSeatingDatabaseExceptionTest() throws Exception {
		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Seating");

		Mockito.when(genericDaoMock.getAll(any(Seating.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Seating", rootException.getMessage());
	}

	@Test
	public void getSeatingTest() throws Exception {
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString();
		mockMvc.perform(get("/" + CONTROLLER + "/Seating").param("floorId", suuid)).andExpect(status().isOk())
				.andExpect(view().name("/HR/seating"));
	}

	@Test
	public void getSeatingViewTest() throws Exception {
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString();
		mockMvc.perform(get("/" + CONTROLLER + "/ViewSeating").param("floorId", suuid)).andExpect(status().isOk())
				.andExpect(view().name("/HR/seatingView"));
	}

	@Test
	public void getSeatingByBlockIdNormalFlowTest() throws Exception {

		UUID uuid = UUID.randomUUID();
		UUID uuid1 = UUID.randomUUID();

		Block block = new Block();
		block.setBlockId(uuid1);

		Seating seating1 = new Seating();
		seating1.setSeatingId(uuid);
		seating1.setSeat_occupied(2);
		seating1.setX_axis("200");
		seating1.setY_axis("500");
		seating1.setSystemOccupied(5);
		seating1.setBlock(block);

		List<Seating> seatings = new ArrayList<Seating>();
		seatings.add(seating1);

		when(seatingDaoMock.getAll(any(Seating.class), eq(uuid1))).thenReturn(seatings);

		mockMvc.perform(get("/" + CONTROLLER + "/" + uuid1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(jsonPath("$.[*].systemOccupied", containsInAnyOrder(5)));
	}

	@Test
	public void getSeatingByBlockIdDatabaseExceptionTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Seating");

		when(seatingDaoMock.getAll(any(Seating.class), eq(uuid1))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER + "/" + uuid1)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Seating", rootException.getMessage());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateSeatingNormalFlowTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();

		Floor floor = new Floor();
		floor.setFloorId(fuuid);

		Block block = new Block();
		block.setBlockId(uuid1);
		block.setFloor(floor);

		when(genericDaoMock.getById(any(Block.class), eq(uuid1))).thenReturn(block);

		when(genericDaoMock.saveOrUpdate(any(Seating.class))).thenReturn(new Seating());

		mockMvc.perform(post("/" + CONTROLLER + "/" + uuid1).param("seat_occupied", "3").param("x_axis", "200")
				.param("y_axis", "500").param("systemOccupied", "5")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateSeatingDatabaseBlockExceptionTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while inserting records from Block");

		when(genericDaoMock.getById(any(Block.class), eq(uuid1))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(post("/" + CONTROLLER + "/" + uuid1).param("seat_occupied", "3").param("x_axis", "200")
					.param("y_axis", "500").param("systemOccupied", "5")).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while inserting records from Block", rootException.getMessage());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateSeatingDatabaseSeatingExceptionTest() throws Exception {

		UUID uuid1 = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();

		Floor floor = new Floor();
		floor.setFloorId(fuuid);

		Block block = new Block();
		block.setBlockId(uuid1);
		block.setFloor(floor);

		when(genericDaoMock.getById(any(Block.class), eq(uuid1))).thenReturn(block);

		ApplicationException applicationException = new ApplicationException(
				"Error while inserting records from Seating");

		when(genericDaoMock.saveOrUpdate(any(Seating.class))).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(post("/" + CONTROLLER + "/" + uuid1).param("seat_occupied", "3").param("x_axis", "200")
					.param("y_axis", "500").param("systemOccupied", "5")).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while inserting records from Seating", rootException.getMessage());
	}

	@Test
	public void floorAxisNormalFlow() throws Exception {
		UUID buuid = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();
		UUID suuid = UUID.randomUUID();

		Floor floor = new Floor();
		floor.setFloorId(fuuid);

		Block block = new Block();
		block.setBlockId(buuid);
		block.setFloor(floor);

		Seating seating1 = new Seating();
		seating1.setSeatingId(suuid);
		seating1.setSeat_occupied(2);
		seating1.setX_axis("200");
		seating1.setY_axis("500");
		seating1.setSystemOccupied(5);
		seating1.setBlock(block);

		List<Seating> seatings = new ArrayList<Seating>();
		seatings.add(seating1);

		when(seatingDaoMock.getAllSeating()).thenReturn(seatings);

		mockMvc.perform(get("/" + CONTROLLER + "/floorAxis/" + fuuid)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

	}

	@Test
	public void floorAxisDatabaseExceptionTest() throws Exception {
		UUID fuuid = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Seating");

		when(seatingDaoMock.getAllSeating()).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER + "/floorAxis/" + fuuid)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Seating", rootException.getMessage());
	}

	@Test
	public void floorLeadAxisNormalFlow() throws Exception {
		UUID buuid = UUID.randomUUID();
		UUID fuuid = UUID.randomUUID();
		UUID suuid = UUID.randomUUID();

		Floor floor = new Floor();
		floor.setFloorId(fuuid);

		Block block = new Block();
		block.setBlockId(buuid);
		block.setFloor(floor);

		Seating seating1 = new Seating();
		seating1.setSeatingId(suuid);
		seating1.setSeat_occupied(2);
		seating1.setX_axis("200");
		seating1.setY_axis("500");
		seating1.setSystemOccupied(5);
		seating1.setBlock(block);

		List<Seating> seatings = new ArrayList<Seating>();
		seatings.add(seating1);

		when(seatingDaoMock.getAllSeating()).thenReturn(seatings);

		mockMvc.perform(get("/" + CONTROLLER + "/floorLeadAxis/" + fuuid)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

	}

	@Test
	public void floorLeadAxisDatabaseExceptionTest() throws Exception {
		UUID fuuid = UUID.randomUUID();

		ApplicationException applicationException = new ApplicationException(
				"Error while retreiving records from Seating");

		when(seatingDaoMock.getAllSeating()).thenThrow(applicationException);

		NestedServletException thrown = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/" + CONTROLLER + "/floorLeadAxis/" + fuuid)).andDo(print());
		});

		ApplicationException rootException = (ApplicationException) ExceptionUtils.getRootCause(thrown);

		assertEquals("Error while retreiving records from Seating", rootException.getMessage());
	}
	
}
