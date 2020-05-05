/**
 * 
 */
package com.handson.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author sveera
 *
 */
public class CliCommandLineParserTest {

	private CliCommandLineParser cliCommandLineParser;

	@BeforeEach
	public void beforeEach() {
		cliCommandLineParser = new CliCommandLineParser();
	}

	@Test
	@DisplayName("test parse method with no options provided and expect parsing to be success with default values")
	public void testParseWithDefaultOptions() throws ParseException {
		ListenerConfiguration expectedListenerConfiguration = new ListenerConfigurationBuilder().build();
		assertEquals(expectedListenerConfiguration, cliCommandLineParser.parse(new String[] {}),
				"Expected and actual configuration are not equal");
	}

	@Test
	@DisplayName("test parse method with all options provided and expect parsing to be success")
	public void testParseWithAllOptions() throws ParseException {
		ListenerConfiguration expectedListenerConfiguration = new ListenerConfiguration(100, "/notif",
				"application/json", "a123223", "2000");
		assertEquals(
				expectedListenerConfiguration, cliCommandLineParser.parse(new String[] { "-p", "100", "-n", "/notif",
						"-ct", "application/json", "-ri", "a123223", "-rsc", "2000" }),
				"Expected and actual configuration are not equal");
	}

	@DisplayName("test parse method with all options having some empty values and expect parsing to be success")
	public void testParseWithAllOptionsHavingSomeEmptyValues() throws ParseException {
		ListenerConfiguration expectedListenerConfiguration = new ListenerConfiguration(100, "/notif",
				"application/json", "", "");
		assertEquals(expectedListenerConfiguration,
				cliCommandLineParser.parse(
						new String[] { "-p", "100", "-n", "/notif", "-ct", "application/json", "-ri", "", "-rsc", "" }),
				"Expected and actual configuration are not equal");
	}

	@Test
	@DisplayName("test parse method with mixed options provided and expect parsing to be success")
	public void testParseWithMixedOptions() throws ParseException {

		ListenerConfiguration expectedListenerConfiguration = new ListenerConfigurationBuilder().port(100)
				.notificationEndPointPath("/notif").responseCode("3000").build();
		assertEquals(expectedListenerConfiguration,
				cliCommandLineParser.parse(new String[] { "-p", "100", "-n", "/notif", "-rsc", "3000" }),
				"Expected and actual configuration are not equal");
	}

	@Test
	@DisplayName("test parse method with null supported options and expect parsing to be success")
	public void testParseWithNullSupportedOptions() throws ParseException {

		ListenerConfiguration expectedListenerConfiguration = new ListenerConfigurationBuilder().port(100)
				.notificationEndPointPath("/notif").responseCode("3000").responseCode("null").requestIdentifier("null")
				.contentType("null").build();
		assertEquals(expectedListenerConfiguration,
				cliCommandLineParser.parse(
						new String[] { "-p", "100", "-n", "/notif", "-ct", "null", "-ri", "null", "-rsc", "null" }),
				"Expected and actual configuration are not equal");
	}

	@Test
	@DisplayName("test parse method with invalid options and expect parsing to be failed")
	public void testParseWithInvalidOptions() throws ParseException {
		assertThrows(ParseException.class,
				() -> cliCommandLineParser.parse(new String[] { "-k", "100", "-n", "/notif", "-rsc", "3000" }),
				"Expected Parse Exception");
	}

}
