package com.handson.jmx.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.handson.jmx.monitor.JMXConnection;
import com.handson.jmx.monitor.JMXRequest;

public class JMXCliParserTest {

	private JMXCliParser jmxCliParser;

	@BeforeEach
	public void setUp() {
		jmxCliParser = new JMXCliParser();
	}

	@Test
	public void testParsingInvalidCliOptionsAndExpectExcpetion() throws ParseException {
		assertThrows(Throwable.class, () -> jmxCliParser.parse(new String[] {}));
	}

	@Test
	public void testParsingGivenCliOptionsAndExpectJMXRequestAfterParsing() throws ParseException {
		JMXRequest jmxRequest = jmxCliParser.parse(new String[] { "-c",
				"service:jmx:remote+http://192.168.128.109:9990", "-u", "admin", "-p", "password", "-d", "java.lang",
				"-t", "Memory", "-a",
				"FreeHeapMemory,FreeNonHeapMemory,HeapMemoryUsage,HeapMemoryUsagePercent,NonHeapMemoryUsage,ObjectName,ObjectPendingFinalizationCount" });
		assertEquals(constructExpectedJMXRequest("service:jmx:remote+http://192.168.128.109:9990", "admin", "password",
				"java.lang", "Memory",
				"FreeHeapMemory,FreeNonHeapMemory,HeapMemoryUsage,HeapMemoryUsagePercent,NonHeapMemoryUsage,ObjectName,ObjectPendingFinalizationCount"),
				jmxRequest, "Expected and Actual " + JMXRequest.class.getSimpleName() + " are not equal.");

	}

	private JMXRequest constructExpectedJMXRequest(String connectionURL, String username, String password,
			String domainName, String type, String attributes) {
		return new JMXRequest(new JMXConnection(connectionURL, username, password), domainName, type, attributes);
	}

}
