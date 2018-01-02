package com.sample.dao.handler.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.handler.UserCreationHandler;
import com.sample.entity.User;

public abstract class AbstractUserCreationHandler implements UserCreationHandler {

	protected final JdbcTemplate jdbcTemplate;

	public AbstractUserCreationHandler(final JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int createUserForType(final User user) {
		final String INSERT_SQL = "insert into world.user_tbl (name,age,address,ssn,emailId) values (?,?,?,?,?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "userId" });
				ps.setString(1, user.getName());
				ps.setInt(2, user.getAge());
				ps.setString(3, user.getAddress());
				ps.setString(4, user.getSsn());
				ps.setString(5, user.getEmailAddress());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	protected abstract void checkIsValidUserType(final User user);
}
