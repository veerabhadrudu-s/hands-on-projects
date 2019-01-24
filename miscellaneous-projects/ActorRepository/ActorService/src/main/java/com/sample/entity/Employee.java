package com.sample.entity;

public class Employee extends User {

	private int employeeId;
	private String organizationName;
	private String corporateEmail;
	private String designation;
	private String department;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	public String getCorporateEmail() {
		return corporateEmail;
	}

	public void setCorporateEmail(final String corporateEmail) {
		this.corporateEmail = corporateEmail;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(final String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(final String department) {
		this.department = department;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(final int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((corporateEmail == null) ? 0 : corporateEmail.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Employee other = (Employee) obj;
		if (corporateEmail == null) {
			if (other.corporateEmail != null)
				return false;
		} else if (!corporateEmail.equals(other.corporateEmail))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", organizationName=" + organizationName + ", corporateEmail="
				+ corporateEmail + ", designation=" + designation + ", department=" + department
				+ ", getEmailAddress()=" + getEmailAddress() + ", getName()=" + getName() + ", getAge()=" + getAge()
				+ ", getAddress()=" + getAddress() + ", getSsn()=" + getSsn() + ", getUserId()=" + getUserId()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

}
