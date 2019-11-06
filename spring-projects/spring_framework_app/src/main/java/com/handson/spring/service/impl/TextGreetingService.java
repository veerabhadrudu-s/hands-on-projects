/**
 * 
 */
package com.handson.spring.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.handson.spring.service.GreetingService;

/**
 * @author sveera
 *
 */
//@Component
@Service
@Primary
public class TextGreetingService implements GreetingService {

	@Override
	public String getGreetingMesg(String name) {
		return String.format("Hello %s . Have a Good day !!!", name);
	}

}
