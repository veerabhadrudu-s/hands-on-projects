package com.sample.entity;

public class Marchant extends User {

	private String shopName;
	private String shopAddress;
	private int contactNumber;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(final String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(final String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(final int contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + contactNumber;
		result = prime * result + ((shopAddress == null) ? 0 : shopAddress.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
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
		final Marchant other = (Marchant) obj;
		if (contactNumber != other.contactNumber)
			return false;
		if (shopAddress == null) {
			if (other.shopAddress != null)
				return false;
		} else if (!shopAddress.equals(other.shopAddress))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Marchant [shopName=" + shopName + ", shopAddress=" + shopAddress + ", contactNumber=" + contactNumber
				+ ", getEmailAddress()=" + getEmailAddress() + ", getName()=" + getName() + ", getAge()=" + getAge()
				+ ", getAddress()=" + getAddress() + ", getSsn()=" + getSsn() + ", getUserId()=" + getUserId()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}



}
