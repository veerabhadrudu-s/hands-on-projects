/**
 * 
 */
package com.handson.jmx.monitor;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import com.handson.jmx.monitor.JMXConnection;
import com.handson.jmx.monitor.JMXRemoteMonitor;

/**
 * @author sveera
 *
 */
public class JMXRemoteMonitorTest {

	private JMXRemoteMonitor jmxRemoteMonitor;

	@BeforeEach
	public void setUp() throws Exception {
		jmxRemoteMonitor = new JMXRemoteMonitor();
	}

	@RepeatedTest(value = 10, name = "{displayName} - {currentRepetition} of {totalRepetitions}")
	@DisplayName("test Get Free Heap Memory ")
	public void testGetFreeHeapMemory()
			throws InstanceNotFoundException, MalformedObjectNameException, IOException, ReflectionException {
		Map<String, String> jmxObjectInfo = jmxRemoteMonitor.getJMXObjectInfo(
				new JMXConnection("service:jmx:remote+http://192.168.128.109:9990", "admin", "password"), "java.lang",
				"Memory", constructRequiredMemoryAttributes());
		System.out.println(jmxObjectInfo);
		assertFalse(jmxObjectInfo.isEmpty(), "Retrieved Attributes cannot be empty");
	}

	private String[] constructRequiredMemoryAttributes() {
		return new String[] { "FreeHeapMemory", "FreeNonHeapMemory", "HeapMemoryUsage", "HeapMemoryUsagePercent",
				"NonHeapMemoryUsage", "ObjectName", "ObjectPendingFinalizationCount" };
	}

}
