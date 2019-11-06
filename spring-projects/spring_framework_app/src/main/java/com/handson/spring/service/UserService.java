/**
 * 
 */
package com.handson.spring.service;

import java.util.List;

import com.handson.spring.dao.entity.User;

/**
 * @author sveera
 *
 */
public interface UserService {

	List<User> getAllUsers();

	int createUser(User user);

	int updateUser(User user);

	void deleteUser(String name);

}
