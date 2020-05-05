/**
 * 
 */
package com.handson.listener;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author sveera
 *
 */
public class CliCommandLineParser {
	private Options options = new Options();
	private CommandLineParser commandLineParser = new DefaultParser();

	public CliCommandLineParser() {
		options.addOption(new Option("p", true, "On Port to be listened"));
		options.addOption(new Option("n", true,
				"Notification end point path to be listened.This must start with / Ex:- /notification"));
		options.addOption(new Option("ct", true,
				"Content Type to be sent in response.To ignore this header to be sent in response, use special option value 'null'"));
		options.addOption(new Option("ri", true,
				"Request Identifier to be sent.To ignore this header to be sent in response, use special option value 'null'"));
		options.addOption(new Option("rsc", true,
				"Response code to be sent.To ignore this header to be sent in response, use special option value 'null'"));
		options.addOption(new Option("h", "help", false, "Print this help Message"));
	}

	public ListenerConfiguration parse(String[] cliOptions) throws ParseException {

		try {
			return parseOptions(cliOptions, options, commandLineParser);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			System.out.println();
			System.out.println();
			printHelpOption();
			throw e;
		}

	}

	public void printHelpOption() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("java -jar http_onem2m_listener.jar <options>\n\n "
				+ "Default option values are -p 8080 -n /notification -ri <Read from request primitive> -rsc 2000\n\n",
				options);
	}

	private ListenerConfiguration parseOptions(String[] cliOptions, Options options,
			CommandLineParser commandLineParser) throws ParseException {
		CommandLine commandLine = commandLineParser.parse(options, cliOptions, false);
		ListenerConfigurationBuilder listenerConfigurationBuilder = new ListenerConfigurationBuilder();
		listenerConfigurationBuilder = commandLine.hasOption("p")
				? listenerConfigurationBuilder.port(Integer.parseInt(commandLine.getOptionValue("p")))
				: listenerConfigurationBuilder;
		listenerConfigurationBuilder = commandLine.hasOption("n")
				? listenerConfigurationBuilder.notificationEndPointPath(commandLine.getOptionValue("n"))
				: listenerConfigurationBuilder;
		listenerConfigurationBuilder = commandLine.hasOption("ct")
				? listenerConfigurationBuilder.contentType(commandLine.getOptionValue("ct"))
				: listenerConfigurationBuilder;
		listenerConfigurationBuilder = commandLine.hasOption("ri")
				? listenerConfigurationBuilder.requestIdentifier(commandLine.getOptionValue("ri"))
				: listenerConfigurationBuilder;
		listenerConfigurationBuilder = commandLine.hasOption("rsc")
				? listenerConfigurationBuilder.responseCode(commandLine.getOptionValue("rsc"))
				: listenerConfigurationBuilder;
		return listenerConfigurationBuilder.build();
	}

}
