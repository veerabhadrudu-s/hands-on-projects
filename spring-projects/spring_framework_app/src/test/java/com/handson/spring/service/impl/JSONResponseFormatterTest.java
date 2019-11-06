/**
 * 
 */
package com.handson.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author sveera
 *
 */

public class JSONResponseFormatterTest {

	private JSONResponseFormatter jsonResponseFormatter;

	@BeforeEach
	public void beforeEach() {
		jsonResponseFormatter = new JSONResponseFormatter();
	}

	@Test
	@DisplayName("test CreateJSONResponse with input message and expect JSON Message")
	public void testCreateJSONResponse() {

		assertEquals("{\"msg\":\"Veera\"}", jsonResponseFormatter.createJSONResponse("Veera"),
				"Expected and Actual Responses are not equal");

	}

}
