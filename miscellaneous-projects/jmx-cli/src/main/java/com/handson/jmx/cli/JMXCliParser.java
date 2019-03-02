/**
 * 
 */
package com.handson.jmx.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.handson.jmx.monitor.JMXConnection;
import com.handson.jmx.monitor.JMXRequest;

/**
 * @author veera
 *
 */
public class JMXCliParser {

	/**
	 * @param inputOptions
	 * @return
	 * @throws ParseException
	 */
	public JMXRequest parse(String[] inputOptions) throws ParseException {
		Options options = constructOptions();
		CommandLineParser parser = new DefaultParser();
		/*
		 * HelpFormatter formatter = new HelpFormatter();
		 * formatter.printHelp(this.getClass().getSimpleName(), options);
		 */
		CommandLine commandLine = parser.parse(options, inputOptions, false);
		return new JMXRequest(
				new JMXConnection(commandLine.getOptionValue("c"), commandLine.getOptionValue("u"),
						commandLine.getOptionValue("p")),
				commandLine.getOptionValue("d"), commandLine.getOptionValue("t"), commandLine.getOptionValue("a"));
	}

	private Options constructOptions() {
		Options options = new Options();
		Option serverURLOption = new Option("c", true, "JMX Server Configuration URL");
		Option userNameOption = new Option("u", true, "JMX Server User Name");
		Option passwordOption = new Option("p", true, "JMX Server Password for user");
		Option domainOption = new Option("d", true, "JMX Server Domain name of Object to be Monitored");
		Option typeOption = new Option("t", true, "JMX Server Type to be Monitored");
		Option attributesOption = new Option("a", true, "JMX Server attributes of Object to be Monitored");
		serverURLOption.setRequired(true);
		userNameOption.setRequired(true);
		passwordOption.setRequired(true);
		domainOption.setRequired(true);
		typeOption.setRequired(true);
		attributesOption.setRequired(true);
		options.addOption(serverURLOption).addOption(userNameOption).addOption(passwordOption).addOption(domainOption)
				.addOption(typeOption).addOption(attributesOption);
		return options;
	}

}
