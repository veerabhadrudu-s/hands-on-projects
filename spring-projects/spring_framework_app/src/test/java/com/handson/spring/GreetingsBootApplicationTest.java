/**
 * 
 */
package com.handson.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClientException;

/**
 * @author sveera
 * 
 *         <pre>
 * This is a spring boot unit test case
 *         </pre>
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = { "sever.port=9080" })
public class GreetingsBootApplicationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("test get Root URI and expect response")
	public void testGreetingsBootApplication() throws RestClientException, URISyntaxException {
		/*
		 * String response = testRestTemplate.execute(new URI("http://localhost:8080/"),
		 * HttpMethod.GET, (ClientHttpRequest request) -> { }, (ClientHttpResponse
		 * clientHttpResponse) -> { BufferedReader bufferedReader = new BufferedReader(
		 * new InputStreamReader(clientHttpResponse.getBody())); return
		 * bufferedReader.readLine(); });
		 */
		ResponseEntity<String> response = testRestTemplate.getForEntity(new URI("http://localhost:8080/"),
				String.class);

		assertEquals(getControllerInfoExpectedResponse(), response.getBody(),
				"Expected and actual responses are not equal");
	}

	private String getControllerInfoExpectedResponse() {
		return "{\"msg\" :\"This is a greetings Controller !!!\"}";
	}

}
