package com.sample.dao.handler.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.sample.dao.handler.UserCreationHandler;
import com.sample.dao.handler.UserCreationHandlerFactory;
import com.sample.entity.User;

@Named
public class UserCreationHandlerFactoryImpl implements UserCreationHandlerFactory {

	private final Map<Class<? extends User>, UserCreationHandler> userCreationHandlerRegistory = new HashMap<>();

	@Inject
	public UserCreationHandlerFactoryImpl(final List<? extends UserCreationHandler> userCreationHandlers) {
		for (final UserCreationHandler userCreationHandler : userCreationHandlers) {
			userCreationHandlerRegistory.put(userCreationHandler.getForType(), userCreationHandler);
		}
	}

	@Override
	public UserCreationHandler findUserCreationHandler(final User user) {
		return userCreationHandlerRegistory.get(user.getClass());
	}

	@Override
	public void registerUserHandler(final Class<? extends User> userType,
			final UserCreationHandler userCreationHandler) {
		userCreationHandlerRegistory.put(userType, userCreationHandler);
	}

}
