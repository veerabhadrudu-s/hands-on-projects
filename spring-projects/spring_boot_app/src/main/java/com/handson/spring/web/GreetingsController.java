/**
 * 
 */
package com.handson.spring.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sveera
 *
 */
@RestController
public class GreetingsController {

	/**
	 * This method will handle uri request defined at controller level.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getControllerInfo() {
		return createJSONResponse("This is a greetings Controller !!!");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/greetings/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendGreetings(@PathVariable(name = "name") String userName) {
		return createJSONResponse(String.format("Hello %s . Have a Good day !!!", userName));
	}

	/*
	 * In order to Spring MVC properly work with spring boot , following changes
	 * need to be added in application.properties and pom.xml. In
	 * application.properties below properties need to be added
	 * spring.mvc.view.prefix=/ spring.mvc.view.suffix=.jsp In pom.xml
	 * ,tomcat-embed-jasper dependency need to be added. This is a JSP page
	 * translator.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/greetings_html/{name}")
	public ModelAndView getDynamicPage(@PathVariable(name = "name") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("name", userName);
		modelAndView.setViewName("dynamic_page");
		return modelAndView;

	}

	private String createJSONResponse(String message) {
		return "{\"msg\" :\"" + message + "\"}";
	}

}
