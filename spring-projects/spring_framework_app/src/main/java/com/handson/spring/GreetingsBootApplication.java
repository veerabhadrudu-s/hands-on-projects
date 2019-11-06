/**
 * 
 */
package com.handson.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author sveera
 *
 */
@SpringBootApplication
public class GreetingsBootApplication {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplicationBuilder().build();
		springApplication.run(GreetingsBootApplication.class, args); 
	}

}
