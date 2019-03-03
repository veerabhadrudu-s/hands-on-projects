/**
 * 
 */
package com.handson.spring.boot;

import org.springframework.boot.SpringApplication;

/**
 * @author veera
 *
 */
/*
 * This is a valid spring boot application even though we didn't annotated this
 * class with @SpringBootApplicaiton or any other spring specific
 * annotations.This is because spring boot by default doesn't enforce you to
 * implement/extend any interface or class .However,If you run this application
 * it will throw exception.This behavior is due enabling of
 * spring-boot-starter-web dependency in pom.xml file.Comment it out and run the
 * application to see successful execution of program.
 */
public class SpringBootEmptyApplication {

	public static void main(String args[]) {
		SpringApplication.run(SpringBootEmptyApplication.class, args);
	}
}
