package com.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sample.boundary.UserCreationService;
import com.sample.dao.UserCreationDao;
import com.sample.entity.User;
import com.sample.interactor.UserCreationServiceImpl;

//@RunWith(SpringJUnit4ClassRunner.class)
public class UserCreationServiceTest {

	private UserCreationService userCreationService;

	private UserCreationDao userCreationDao;

	@Before
	public void setUp() throws Exception {
		userCreationService = new UserCreationServiceImpl(userCreationDao);
	}

	@Test
	@Ignore
	public void testCreateUser() {
		final User user = new User();
		final int userId = userCreationService.createUser(user);
		Assert.assertSame("Failed to create User", 0, userId);

	}

}
