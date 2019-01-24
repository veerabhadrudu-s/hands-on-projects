package com.sample.dao.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.sample.dao.UserCreationDao;
import com.sample.dao.handler.UserCreationHandler;
import com.sample.dao.handler.UserCreationHandlerFactory;
import com.sample.entity.User;

@Named
public class UserCreationDaoImpl implements UserCreationDao {

	private final UserCreationHandlerFactory userCreationHandlerFactory;

	@Inject
	public UserCreationDaoImpl(final UserCreationHandlerFactory userCreationHandlerFactory) {
		super();
		this.userCreationHandlerFactory = userCreationHandlerFactory;
	}

	@Override
	public int createUser(final User user) {
		final UserCreationHandler userCreationHandler = userCreationHandlerFactory.findUserCreationHandler(user);
		return userCreationHandler.createUserForType(user);
	}

}
