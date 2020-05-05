
package com.handson.listener;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 * @author veeras
 *
 */
public class HttpListener {

	public static void main(String[] args) throws Exception {
		CliCommandLineParser cliCommandLineParser = new CliCommandLineParser();
		if (args.length == 1 && (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help"))) {
			cliCommandLineParser.printHelpOption();
			return;
		}
		ListenerConfiguration listenerConfiguration = cliCommandLineParser.parse(args);
		HttpServer server = HttpServer.create(new InetSocketAddress(listenerConfiguration.getPort()), 0);
		server.createContext(listenerConfiguration.getNotificationEndPointPath(),
				new HttpResponseHandler(listenerConfiguration));

		System.out.println("OneM2M Listener Started on Port ::" + server.getAddress().getPort());

		server.setExecutor(null); // creates a default executor
		server.start();
	}
}
