/**
 * 
 */
package com.handson.jmx.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

import org.apache.commons.cli.ParseException;

import com.handson.jmx.cli.JMXCliParser;
import com.handson.jmx.monitor.JMXRemoteMonitor;
import com.handson.jmx.monitor.JMXRequest;

/**
 * @author veera
 *
 */
public class JMXRemoteMoniterMain {

	public static void main(String args[]) {
		try {
			JMXCliParser jmxCliParser = new JMXCliParser();
			JMXRequest jmxRequest = jmxCliParser.parse(args);
			Map<String, String> readings = new JMXRemoteMonitor().getJMXObjectInfo(jmxRequest.getJmxConnection(),
					jmxRequest.getDomainName(), jmxRequest.getType(), jmxRequest.getAttributes().split(","));
			System.out.println("Required Attributes are " + Arrays.deepToString(jmxRequest.getAttributes().split(","))
					+ " and readings are" + readings);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException | MalformedObjectNameException | IOException | ReflectionException e) {
			e.printStackTrace();
		}
	}

}
