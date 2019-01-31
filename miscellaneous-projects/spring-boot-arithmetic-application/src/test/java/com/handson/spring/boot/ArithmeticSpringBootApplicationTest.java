/**
 * 
 */
package com.handson.spring.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.spring.boot.arithmetic.rest.messages.InputRequest;

/**
 * @author sveera
 *
 */
/*
 * Spring boot unit test cases allows us to run unit test cases with live server
 * deployment in localhost. To do this, we have to
 * use @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) annotation.
 * This will try to do component-scan of spring boot application class (i.e
 * Class Annotated @SpringBootApplication) from unit test case directory towards
 * child directories(this component-scan includes source code directory also).
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
public class ArithmeticSpringBootApplicationTest {

	private static final String THIS_IS_A_ARITHMETIC_CALCULATOR_APPLICATION = "This is a Arithmetic Calculator Application";
	private static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";
	private static final String PERFORM_ARTH_OPERATION = "/performArthOperation";
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("test get Info and expect response")
	public void testGetInfo() throws RestClientException, URISyntaxException, JsonProcessingException {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(new URI(HTTP_LOCALHOST_8080),
				String.class);
		assertEquals("{\"info\":\"" + THIS_IS_A_ARITHMETIC_CALCULATOR_APPLICATION + "\"}", responseEntity.getBody(),
				"Expected and Actual responses are not equal.");
	}

	@Test
	@DisplayName("test Perform Arithematic Operation With Invalid Operator And Except Exception")
	public void testPerformArithematicOperationWithInvalidOperatorAndExceptException() throws Exception {
		assertEquals(getExpectedErrorMessage(), performArithmeticOperationRequest(0, 2, "$").getBody(),
				"Expected and Actual responses are not equal.");
	}

	@CsvSource({ "2,5,*,10.0", "50,10,/,5.0", "45,5,-,40.0" })
	@DisplayName("test Perform Arithematic Operation And Except Result")
	@ParameterizedTest(name = "with firstOperand= {0} , secondOperand= {1} , operation= {2} and expected-result is {3} ")
	public void testPerformArithematicOperationOperationAndExceptResult(double firstOperand, double secondOperand,
			String operation, String expectedResult) throws Exception {
		RequestEntity<String> requestEntity = constructRequestEntity(
				objectMapper.writeValueAsString(new InputRequest(firstOperand, secondOperand, operation)));
		ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(
				new URI(HTTP_LOCALHOST_8080.concat(PERFORM_ARTH_OPERATION)), requestEntity, String.class);
		assertEquals(constructResponseMessage(firstOperand, secondOperand, operation, expectedResult),
				responseEntity.getBody(), "Expected and Actual responses are not equal.");
	}

	private String getExpectedErrorMessage() {
		return "{\"errorMessage\":\"Supported Operations at present are [*, +, -, /]\"}";
	}

	private ResponseEntity<String> performArithmeticOperationRequest(int firstOperand, int secondOperand,
			String operation) throws URISyntaxException, JsonProcessingException {
		RequestEntity<String> requestEntity = constructRequestEntity(
				objectMapper.writeValueAsString(new InputRequest(firstOperand, secondOperand, operation)));
		return this.testRestTemplate.postForEntity(new URI(HTTP_LOCALHOST_8080.concat(PERFORM_ARTH_OPERATION)),
				requestEntity, String.class);
	}

	private RequestEntity<String> constructRequestEntity(String requestBody)
			throws URISyntaxException, JsonProcessingException {
		RequestEntity<String> requestEntity = RequestEntity
				.post(new URI(HTTP_LOCALHOST_8080.concat(PERFORM_ARTH_OPERATION)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(requestBody);
		return requestEntity;
	}

	private String constructResponseMessage(double firstOperand, double secondOperand, String operation,
			String expectedResult) {
		return String.format("{\"firstOperand\":%s,\"secondOperand\":%s,\"operation\":\"%s\",\"result\":%s}",
				firstOperand, secondOperand, operation, expectedResult);
	}

}
