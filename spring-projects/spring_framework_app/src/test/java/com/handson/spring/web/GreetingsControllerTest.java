package com.handson.spring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author sveera
 * 
 *         <pre>
 * This is a spring MVC unit test case.
 * 
 * \@ExtendWith(SpringExtension.class)
 * \@ContextConfiguration(classes =GreetingsConfiguration.class)
 * \@WebAppConfiguration
 * Below annotation is equivalent to above three annotations
 *         </pre>
 */
@SpringJUnitWebConfig(classes = GreetingsConfiguration.class)
public class GreetingsControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeEach
	public void beforeEach() {
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(webApplicationContext);
		mockMvc = mockMvcBuilder.build();
	}

	@Test
	@DisplayName("test GreetingsController and expect it to be not null")
	public void testGreetingsController() {
		assertNotNull(webApplicationContext.getBean(GreetingsController.class));
	}

	@Test
	@DisplayName("test get Controller Info and expect result")
	public void testGetControllerInfo() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertEquals(getControllerInfoExpectedResponse(), mvcResult.getResponse().getContentAsString());
	}

	@Test
	@DisplayName("test get greetings operation by passsing name and expect greetings message in JSON Format")
	public void testSendGreetings() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/greetings/Veera").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertEquals(getExpectedGreetingsMessage(), mvcResult.getResponse().getContentAsString());
	}

	private String getExpectedGreetingsMessage() {
		return "{\"msg\":\"Hello Veera . Have a Good day !!!\"}";
	}

	private String getControllerInfoExpectedResponse() {
		return "{\"msg\" :\"This is a greetings Controller !!!\"}";
	}

	@AfterEach
	public void afterEach() {

	}

}
