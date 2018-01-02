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

import com.sample.entity.Employee;
import com.sample.entity.User;

@Repository
public class EmployeeUserCreationHandler extends AbstractUserCreationHandler {

	@Inject
	public EmployeeUserCreationHandler(final JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int createUserForType(final User user) {
		checkIsValidUserType(user);
		final Employee employee = (Employee) user;
		final int userId = super.createUserForType(user);
		final String INSERT_SQL = "insert into world.employee_tbl (userId,employeeId,organizationName,corporateEmail,designation,department) values (?,?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
				ps.setInt(1, userId);
				ps.setInt(2, employee.getEmployeeId());
				ps.setString(3, employee.getOrganizationName());
				ps.setString(4, employee.getCorporateEmail());
				ps.setString(5, employee.getDesignation());
				ps.setString(6, employee.getDepartment());
				return ps;
			}
		});
		return userId;
	}

	@Override
	protected void checkIsValidUserType(final User user) {
		if (!(user instanceof Employee)) {
			throw new RuntimeException("Invalid user type requested");
		}

	}

	@Override
	public Class<? extends User> getForType() {
		return Employee.class;
	}

}
