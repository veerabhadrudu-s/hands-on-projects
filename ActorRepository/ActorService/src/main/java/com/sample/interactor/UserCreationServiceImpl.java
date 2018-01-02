package com.sample.interactor;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sample.boundary.UserCreationService;
import com.sample.dao.UserCreationDao;
import com.sample.entity.User;

@Service
public class UserCreationServiceImpl implements UserCreationService {

	private final UserCreationDao userCreationDao;

	@Inject
	public UserCreationServiceImpl(final UserCreationDao userCreationDao) {
		super();
		this.userCreationDao = userCreationDao;
	}

	@Override
	public int createUser(final User user) {
		return userCreationDao.createUser(user);
	}

}
