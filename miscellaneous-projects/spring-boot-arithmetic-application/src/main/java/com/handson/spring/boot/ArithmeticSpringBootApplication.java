/**
 * 
 */
package com.handson.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author sveera
 *
 */
@SpringBootApplication(scanBasePackages = { "com.handson.spring.boot.rest" })
@ImportResource(locations = { "classpath:spring-config.xml" })
public class ArithmeticSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArithmeticSpringBootApplication.class, args);
	}
}
