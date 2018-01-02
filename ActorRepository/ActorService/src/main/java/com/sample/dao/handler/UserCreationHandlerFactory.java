package com.sample.dao.handler;

import com.sample.entity.User;

public interface UserCreationHandlerFactory {

	UserCreationHandler findUserCreationHandler(User user);

	void registerUserHandler(final Class<? extends User> userType,
			final UserCreationHandler userCreationHandler);

}
