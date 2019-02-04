/**
 * 
 */
package com.handson.spring.boot.arithmetic.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.spring.boot._configuration.ArithmeticApplicationSpringConfiguration;
import com.handson.spring.boot.arithmetic.rest.messages.InputRequest;

/**
 * @author sveera
 *
 */

/*
 * @SpringJUnitWebConfig annotation is extending 3 annotations.
 * 
 * @ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
 * 
 * @ContextConfiguration
 * 
 * @WebAppConfiguration . Either above 3 annotations or @SpringJUnitWebConfig
 * and @ExtendWith(RestDocumentationExtension.class) can be used to create
 * Spring MVC Test case.
 *
 * Migrated from Spring XML based configuration to Spring bean based
 * configuration. XML based configuration is still kept for reference purpose.
 */

@SpringJUnitWebConfig(classes={ ArithmeticApplicationSpringConfiguration.class })
@ExtendWith(RestDocumentationExtension.class)
@EnableAutoConfiguration
//@ContextConfiguration("classpath:test-rest-spring-config.xml")
public class ArithmeticCalculatorRestControllerTest {

	private static final String PERFORM_ARTH_OPERATION = "/performArthOperation";
	private MockMvc mockMvc;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation)
			throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
				.alwaysDo(
						document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
				.build();
	}

	@Test
	@DisplayName("test get Info and expect response")
	public void testGetInfo() throws Exception {
		this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo((response) -> {
					assertEquals("{\"info\":\"This is a Arithmetic Calculator Application\"}",
							response.getResponse().getContentAsString());
				});

	}

	@Test
	@DisplayName("test Perform Arithematic Operation With Invalid Operator And Except Exception")
	public void testPerformArithematicOperationWithInvalidOperatorAndExceptException() throws Exception {
		this.mockMvc.perform(constructPostRequest(1, 2, "&")).andExpect(status().isBadRequest())
				.andDo(createResultHandlerErrorValidator());
	}

	@Test
	@DisplayName("test Perform Arithematic Operation With Add Operator And Except Result")
	public void testPerformArithematicOperationWithAddOperatorAndExceptResult() throws Exception {
		this.mockMvc.perform(constructPostRequest(1, 2, "+")).andExpect(status().isOk())
				.andDo(createResultHandlerValidator(1, 2, "+", "3.0"));
	}

	@CsvSource({ "2,5,*,10.0", "50,10,/,5.0", "45,5,-,40.0" })
	@DisplayName("test Perform Arithematic Operation And Except Result")
	@ParameterizedTest(name = "with firstOperand= {0} , secondOperand= {1} , operation= {2} and expected-result is {3} ")
	public void testPerformArithematicOperationOperationAndExceptResult(double firstOperand, double secondOperand,
			String operation, String expectedResult) throws Exception {
		this.mockMvc.perform(constructPostRequest(firstOperand, secondOperand, operation)).andExpect(status().isOk())
				.andDo(createResultHandlerValidator(firstOperand, secondOperand, operation, expectedResult));
	}

	private MockHttpServletRequestBuilder constructPostRequest(double firstOperand, double secondOperand,
			String operation) throws JsonProcessingException {
		return post(PERFORM_ARTH_OPERATION).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(constructInputRequestJson(firstOperand, secondOperand, operation));
	}

	private ResultHandler createResultHandlerErrorValidator() {
		return (result) -> {
			assertEquals(getExpectedErrorMessage(), result.getResponse().getContentAsString(),
					"Expected and Actual Response Messages are not equal");
		};
	}

	private ResultHandler createResultHandlerValidator(double firstOperand, double secondOperand, String operation,
			String expectedResult) {
		return (result) -> {
			assertEquals(
					String.format("{\"firstOperand\":%s,\"secondOperand\":%s,\"operation\":\"%s\",\"result\":%s}",
							firstOperand, secondOperand, operation, expectedResult),
					result.getResponse().getContentAsString(), "Expected and Actual Response Messages are not equal");
		};
	}

	private String constructInputRequestJson(double firstOparand, double secondOparand, String operation)
			throws JsonProcessingException {
		return objectMapper.writeValueAsString(
				new InputRequest(firstOparand, secondOparand, operation));
	}

	private String getExpectedErrorMessage() {
		return "{\"errorMessage\":\"Supported Operations at present are [*, +, -, /]\"}";
	}

}
