package com.sample.dao.handler.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.entity.Marchant;
import com.sample.entity.User;

@Repository
public class MarchantUserCreationHandler extends AbstractUserCreationHandler {

	@Inject
	public MarchantUserCreationHandler(final JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int createUserForType(final User user) {
		checkIsValidUserType(user);
		final Marchant marchant = (Marchant) user;
		final int userId = super.createUserForType(user);
		final String INSERT_SQL = "insert into world.marchant_tbl (userId,shopName,shopAddress,contactNumber) values (?,?,?,?);";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
				ps.setInt(1, userId);
				ps.setString(2, marchant.getShopName());
				ps.setString(3, marchant.getShopAddress());
				ps.setInt(4, marchant.getContactNumber());
				return ps;
			}
		});
		return userId;
	}

	@Override
	protected void checkIsValidUserType(final User user) {
		if (!(user instanceof Marchant)) {
			throw new RuntimeException("Invalid user type requested");
		}

	}

	@Override
	public Class<? extends User> getForType() {
		return Marchant.class;
	}

}
