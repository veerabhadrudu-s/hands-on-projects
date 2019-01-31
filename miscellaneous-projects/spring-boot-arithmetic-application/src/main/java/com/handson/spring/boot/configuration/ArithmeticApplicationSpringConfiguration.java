/**
 * 
 */
package com.handson.spring.boot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.handson.spring.boot.arithmetic.ArithmeticCalculator;

/**
 * @author sveera
 *
 */
@Configuration
@ComponentScan(basePackages = "com.handson.spring.boot.arithmetic.rest")
public class ArithmeticApplicationSpringConfiguration {

	@Bean
	public ArithmeticCalculator constructArithmeticCalculator() {
		return new ArithmeticCalculator();
	}
}
