/**
 * 
 */
package com.handson.spring.service;

/**
 * @author sveera
 *
 */
public interface GreetingService {

	default String getGreetingMesg(String name) {
		return "";
	};

}
