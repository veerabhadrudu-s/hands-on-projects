package com.sample.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.boundary.UserCreationService;
import com.sample.entity.Employee;

@Controller
@RequestMapping(path = "/user")
public class ActorContoller {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UserCreationService userCreationService;

	@Inject
	public ActorContoller(final UserCreationService userCreationService) {
		super();
		this.userCreationService = userCreationService;
	}

	@RequestMapping(path = "/addUser")
	@ResponseBody
	public String addUSer(@ModelAttribute final Employee user) {
		logger.debug("Received User Data is " + user);
		final int userId = userCreationService.createUser(user);
		return "User Sucessfully created with used id :" + userId;
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(final Exception exception) {
		exception.printStackTrace();
		return "Exception occured while creating the user " + exception.toString();
	}

}
