package com.deutsche.storetrade;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deutsche.controller.TradeController;
import com.deutsche.model.Trade;
import com.deutsche.service.TradeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TradeController.class)
public class TradeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TradeService tradeService;
	
	String exampleJson = "{\"version\":\"2\",\"counterPartyId\":\"CP-3\",\"bookId\":\"B2\"}";
	
	@Test
	public void createTrade() throws Exception {
		Trade trade = new Trade(1, "CP-1", "B1",new GregorianCalendar(2020, 5, 20).getTime(), new Date(), false);

		//studentService.addCourse to respond back with mockCourse
		Mockito.when(
				tradeService.addTrade(Mockito
						.any(Trade.class))).thenReturn(trade);

		//Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/trades")
				.accept(MediaType.APPLICATION_JSON).content(exampleJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/trades/T-1", response
				.getHeader(HttpHeaders.LOCATION));

	}
}
