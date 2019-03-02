/**
 * 
 */
package com.handson.jmx.monitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author sveera
 *
 */
public class JMXRemoteMonitor {

	/**
	 * @param objectType
	 * @param key
	 * @param fullDomainName
	 * @return
	 * @throws IOException
	 * @throws MalformedObjectNameException
	 * @throws InstanceNotFoundException
	 * @throws ReflectionException
	 */
	public Map<String, String> getJMXObjectInfo(JMXConnection jmxConnection, String fullDomainName, String objectType,
			String... requiredAttributes)
			throws IOException, InstanceNotFoundException, MalformedObjectNameException, ReflectionException {
		final Map<String, String> result = new HashMap<>();
		MBeanServerConnection mBeanServerConnection = connectToJMXMBeanServer(jmxConnection);
		ObjectName objectName = new ObjectName(fullDomainName, "type", objectType);
		AttributeList list = mBeanServerConnection.getAttributes(objectName, requiredAttributes);
		for (Attribute a : list.asList())
			result.put(a.getName(), a.getValue().toString());
		return result;

	}

	private MBeanServerConnection connectToJMXMBeanServer(JMXConnection jmxConnection)
			throws MalformedURLException, IOException {
		final String[] credentials = new String[2];
		final Map<String, String[]> environment = new HashMap<>();
		credentials[0] = jmxConnection.getUsername();
		credentials[1] = jmxConnection.getPassword();
		environment.put(JMXConnector.CREDENTIALS, credentials);
		JMXServiceURL url = new JMXServiceURL(jmxConnection.getConnectionURL());
		JMXConnector jmxc = JMXConnectorFactory.connect(url, environment);
		return jmxc.getMBeanServerConnection();
	}

}
