/**
 * 
 */
package com.handson.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.handson.spring.service.GreetingService;

/**
 * @author sveera
 *
 */
//@Component
@Service
@Qualifier("greetingService")
public class JSONGreetingService implements GreetingService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JSONResponseFormatter jsonResponseFormatter;

	@Autowired
	public JSONGreetingService(JSONResponseFormatter jsonResponseFormatter) {
		super();
		this.jsonResponseFormatter = jsonResponseFormatter;
	}

	@Override
	public String getGreetingMesg(String name) {
		logger.info(
				"Printing JSON Response Formatter instance hashcode first time - " + jsonResponseFormatter.hashCode());
		logger.info(
				"Printing JSON Response Formatter instance hashcode second time - " + jsonResponseFormatter.hashCode());
		jsonResponseFormatter.createJSONResponse(String.format("Hello %s . Have a Good day !!!", name));
		return jsonResponseFormatter.createJSONResponse(String.format("Hello %s . Have a Good day !!!", name));
	}

}
