/**
 * 
 */
package com.handson.spring.boot.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sveera
 *
 */

/*
 * This unit test case is intentionally been created using xml based
 * configuration.
 * 
 * @SpringJUnitWebConfig annotation is extending below annotations. Either below
 * 2 annotations or @SpringJUnitWebConfig can be used to create Spring MVC
 * Testcase.
 * 
 * @ExtendWith(SpringExtension.class)
 * 
 * @WebAppConfiguration
 */
@SpringJUnitWebConfig
@ContextConfiguration("classpath:test-rest-spring-config.xml")
@EnableAutoConfiguration
public class ArithmeticCalculatorRestControllerTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private MockMvc mockMvc;
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@DisplayName("test Perform Arithematic Operation With Invalid Operator And Except Exception")
	public void testPerformArithematicOperationWithInvalidOperatorAndExceptException() throws Exception {
		this.mockMvc.perform(post("/performArthOperation").content(constructInputRequestJson(1, 2, "&")))
				.andExpect(status().isBadRequest()).andDo((result) -> {
					logger.info((result.getResponse().getContentAsString()));
					assertEquals(getExpectedErrorMessage(), result.getResponse().getContentAsString(),
							"Expected and Actual Response Messages are not equal");
				});
	}

	@Test
	@DisplayName("test Perform Arithematic Operation With Add Operator And Except Result")
	public void testPerformArithematicOperationWithAddOperatorAndExceptResult() throws Exception {
		this.mockMvc.perform(post("/performArthOperation").content(constructInputRequestJson(1, 2, "+")))
				.andExpect(status().isOk()).andDo((result) -> {
					logger.info((result.getResponse().getContentAsString()));
					assertEquals("{\"firstOperand\":1.0,\"secondOperand\":2.0,\"operation\":\"+\",\"result\":3.0}",
							result.getResponse().getContentAsString(),
							"Expected and Actual Response Messages are not equal");
				});
	}

	@CsvSource({ "2,5,*,10.0", "50,10,/,5.0", "45,5,-,40.0" })
	@DisplayName("test Perform Arithematic Operation And Except Result")
	@ParameterizedTest(name = "with firstOperand= {0} , secondOperand= {1} , operation= {2} and expected-result is {3} ")
	public void testPerformArithematicOperationOperationAndExceptResult(double firstOperand, double secondOperand,
			String operation, String expectedResult) throws Exception {
		this.mockMvc
				.perform(post("/performArthOperation")
						.content(constructInputRequestJson(firstOperand, secondOperand, operation)))
				.andExpect(status().isOk()).andDo((result) -> {
					logger.info((result.getResponse().getContentAsString()));
					assertEquals(
							String.format(
									"{\"firstOperand\":%s,\"secondOperand\":%s,\"operation\":\"%s\",\"result\":%s}",
									firstOperand, secondOperand, operation, expectedResult),
							result.getResponse().getContentAsString(),
							"Expected and Actual Response Messages are not equal");
				});
	}

	private String constructInputRequestJson(double firstOparand, double secondOparand, String operation)
			throws JsonProcessingException {
		return objectMapper.writeValueAsString(
				new ArithmeticCalculatorRestController.InputRequest(firstOparand, secondOparand, operation));
	}

	private String getExpectedErrorMessage() {
		return "{\"errorMessage\":\"Supported Operations at present are [*, +, -, /]\"}";
	}

}
