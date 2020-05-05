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

		BufferedReader reader = new BufferedReader(new InputStreamReader(t.getRequestBody()));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			out.append(line);
		}

		Headers requestHeaders = t.getRequestHeaders();
		System.out.println("Content-type :" + requestHeaders.get("Content-type"));
		System.out.println("Content-length :" + requestHeaders.get("Content-length"));
		System.out.println("X-m2m-origin :" + requestHeaders.get("X-m2m-origin"));
		System.out.println("X-m2m-nm :" + requestHeaders.get("X-m2m-nm"));
		System.out.println("X-m2m-ri :" + requestHeaders.get("X-m2m-ri"));
		System.out.println("Authorization :" + requestHeaders.get("Authorization"));
		System.out.println("Accept : " + requestHeaders.get("Accept"));
		System.out.println("Accept Charset :" + requestHeaders.get("Accept-Charset"));
		System.out.println();
		System.out.println(out.toString());
		System.out.println();
		System.out.println();

		Headers responseHeaders = t.getResponseHeaders();
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

		reader.close();

		t.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		OutputStream os = t.getResponseBody();
		os.write(out.toString().getBytes());
		os.close();

	}
}