/**
 * 
 */
package com.handson.spring.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.spring.dao.entity.User;
import com.handson.spring.service.UserService;

/**
 * @author sveera
 *
 */
@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping(value = "/getallusers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(value = "/createuser", produces = { MediaType.APPLICATION_JSON_VALUE })
	public int createUser(@ModelAttribute User user) {
		return userService.createUser(user);

	}

	@PutMapping(value = "/updateuser", produces = { MediaType.APPLICATION_JSON_VALUE })
	public int updateUser(@ModelAttribute User user) {
		return userService.updateUser(user);

	}

	@DeleteMapping(value = "/deleteuser/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String deleteUser() {
		return null;
	}

}
