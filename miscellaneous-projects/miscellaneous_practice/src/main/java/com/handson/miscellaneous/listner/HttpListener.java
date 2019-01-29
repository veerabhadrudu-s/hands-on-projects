
package com.handson.miscellaneous.listner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * @author veeras
 *
 */
public class HttpListener {

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(args[0])), 0);
		server.createContext("/notification", new MyHandler());

		System.out.println("Listener Started on Port ::" + server.getAddress().getPort());

		server.setExecutor(null); // creates a default executor
		server.start();
	}

	static class MyHandler implements HttpHandler {
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
			responseHeaders.set("Content-Type", "text/html");
			responseHeaders.set("X-M2M-RSC", "2000");
			responseHeaders.set("X-M2M-RI", requestHeaders.get("X-m2m-ri").get(0));
			reader.close();

			t.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			// t.setAttribute("X-M2M-RSC", "2000");
			OutputStream os = t.getResponseBody();
			os.write(out.toString().getBytes());
			os.close();

		}
	}
}
