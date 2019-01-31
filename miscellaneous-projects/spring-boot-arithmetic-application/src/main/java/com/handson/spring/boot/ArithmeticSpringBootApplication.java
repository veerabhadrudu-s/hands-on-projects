/**
 * 
 */
package com.handson.spring.boot;

import static org.springframework.boot.WebApplicationType.SERVLET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author sveera
 */
/*
 * Migrated from Spring XML based configuration to Spring bean based
 * configuration. XML based configuration is still kept for reference purpose.
 */
@SpringBootApplication
//@SpringBootApplication(scanBasePackages = { "com.handson.spring.boot.rest" })
//@ImportResource(locations = { "classpath:spring-config.xml" })
public class ArithmeticSpringBootApplication {

	private static final Logger logger = LoggerFactory.getLogger(ArithmeticSpringBootApplication.class);

	public static void main(String[] args) {
		customizeAndRunSpringBootApplication(args);
	}

	private static void runSpringApplicationWithDefaultOptions(String[] args) {
		SpringApplication.run(ArithmeticSpringBootApplication.class, args);
	}

	private static void customizeAndRunSpringBootApplication(String[] args) {
		SpringApplication springApplication = new SpringApplication(ArithmeticSpringBootApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.addListeners(createApplicaitonListener());
		springApplication.setWebApplicationType(SERVLET);
		springApplication.run(args);
	}

	/**
	 * Spring Application Listener is called even before bean configuration or
	 * ApplicationContext is created by spring boot. It sends different types of
	 * ApplicationEvent's during spring boot life cycle. To know more about these
	 * events refer spring reference doc.
	 * 
	 * @return
	 */
	private static ApplicationListener<ApplicationEvent> createApplicaitonListener() {
		return (ApplicationEvent event) -> {
			logger.debug(String.format("Invoked Spring Application Listener %s ", event.getClass().getName()));
		};
	}
}
