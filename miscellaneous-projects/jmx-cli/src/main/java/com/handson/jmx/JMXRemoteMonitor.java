/**
 * 
 */
package com.handson.jmx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author sveera
 *
 */
public class JMXRemoteMonitor {

	public static void main(String args[]) {
		String[] credentials = new String[2];
		credentials[0] = "admin";
		credentials[1] = "password";
		Map<String, String[]> environment = new HashMap<String, String[]>();
		environment.put(JMXConnector.CREDENTIALS, credentials);
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:remote+http://192.168.128.109:9990");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, environment);
			MBeanServerConnection mBeanServerConnection = jmxc.getMBeanServerConnection();
			ObjectInstance objectInstance = mBeanServerConnection
					.getObjectInstance(new ObjectName("java.lang", "type", "Memory"));
			System.out.println(objectInstance.getClassName());
			System.out.println(objectInstance.getClass().getName());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
