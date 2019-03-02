/**
 * 
 */
package com.handson.jmx.main;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author veera
 *
 */
public class JMXRemoteMoniterMainTest {

	@DisplayName("test Read JMX Memory Object")
	@ParameterizedTest(name = "test Read JMX Memory Object {0}")
	@ValueSource(strings = { "HeapMemoryUsage",
			"HeapMemoryUsage,HeapMemoryUsagePercent,NonHeapMemoryUsage,ObjectName,ObjectPendingFinalizationCount",
			"FreeHeapMemory,FreeNonHeapMemory,HeapMemoryUsage,HeapMemoryUsagePercent,NonHeapMemoryUsage,ObjectName,ObjectPendingFinalizationCount" })
	public void testReadJMXMemoryObject(String attributes) {
		JMXRemoteMoniterMain.main(new String[] { "-c", "service:jmx:remote+http://192.168.128.109:9990", "-u", "admin",
				"-p", "password", "-d", "java.lang", "-t", "Memory", "-a", attributes });
	}

}
