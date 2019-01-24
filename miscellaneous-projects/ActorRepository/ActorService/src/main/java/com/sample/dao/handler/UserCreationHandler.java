package com.sample.dao.handler;

import com.sample.entity.User;

public interface UserCreationHandler {

	int createUserForType(User user);

	Class<? extends User> getForType();

}
