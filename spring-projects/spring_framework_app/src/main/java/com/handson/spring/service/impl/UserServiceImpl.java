/**
 * 
 */
package com.handson.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.handson.spring.dao.entity.User;
import com.handson.spring.service.UserService;

/**
 * @author sveera
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<User> getAllUsers() {
		return null;
	}

	@Override
	public int createUser(User user) {
		return 0;
	}

	@Override
	public int updateUser(User user) {
		return 0;
	}

	@Override
	public void deleteUser(String name) {

	}

}
