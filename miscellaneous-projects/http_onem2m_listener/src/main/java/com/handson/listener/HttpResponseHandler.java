/**
 * 
 */
package com.handson.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpResponseHandler implements HttpHandler {

	private final ResponseConfiguration responseConfiguration;

	public HttpResponseHandler(ResponseConfiguration responseConfiguration) {
		super();
		this.responseConfiguration = responseConfiguration;
	}

	@Override
	public void handle(HttpExchange t) throws IOException {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(t.getRequestBody()));
				OutputStream os = t.getResponseBody()) {
			String requestBody = readRequestBody(reader);
			printRequest(requestBody, t.getRequestHeaders());
			setResponseHeaders(t.getRequestHeaders(), t.getResponseHeaders());
			printResponse(requestBody, t.getResponseHeaders());
			t.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			os.write(requestBody.getBytes());
		}

	}

	private String readRequestBody(BufferedReader reader) throws IOException {
		StringBuilder out = new StringBuilder();
		for (String line; (line = reader.readLine()) != null;)
			out.append(line);
		return out.toString();
	}

	private void printResponse(String requestBody, Headers responseHeaders) {
		System.out.println("Sending below response headers");
		responseHeaders.forEach((key, value) -> System.out.printf("%s : %s \n", key, value));
		printingTwoEmptyLines();
		System.out.println("Sending response body same as request body ");
		System.out.println(requestBody);
		printingTwoEmptyLines();
	}

	private void printRequest(String requestBody, Headers requestHeaders) {
		System.out.println("Printing request headers");
		requestHeaders.forEach((key, value) -> System.out.printf("%s : %s \n", key, value));
		printingTwoEmptyLines();
		System.out.println("Printing request body");
		System.out.println(requestBody);
		printingTwoEmptyLines();
	}

	private void setResponseHeaders(Headers requestHeaders, Headers responseHeaders) {
		if (responseConfiguration.getContentType() != null)
			responseHeaders.set("Content-Type", responseConfiguration.getContentType());
		if (responseConfiguration.getResponseCode() != null)
			responseHeaders.set("X-M2M-RSC", responseConfiguration.getResponseCode());
		if (responseConfiguration.getRequestIdentifier() != null) {
			String requestIndentifier = responseConfiguration.getRequestIdentifier().equals(
					ResponseConfiguration.REQUEST_IDENTIFIER_FROM_REQUEST) ? requestHeaders.get("X-m2m-ri").get(0)
							: responseConfiguration.getRequestIdentifier();
			responseHeaders.set("X-M2M-RI", requestIndentifier);
		}
	}

	private void printingTwoEmptyLines() {
		System.out.println();
		System.out.println();
	}
}