package com.handson.miscellaneous.ldap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.password.PasswordDetails;
import org.apache.directory.api.ldap.model.password.PasswordUtil;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

public class LdapClient {

	public static void main(final String args[]) {
		List<UserInfo> users = new LdapClient().readLdapUsersData();

	}

	public List<UserInfo> readLdapUsersData() {
		final List<UserInfo> allusers = new ArrayList<>();
		try (final LdapConnection connection = new LdapNetworkConnection("10.3.239.70", 10389)) {
			connection.bind();
			final EntryCursor cursor = connection.search("ou=People,dc=example,dc=com", "(objectclass=*)",
					SearchScope.ONELEVEL, "*");

			while (cursor.next()) {
				final Entry entry = cursor.get();
				final Collection<Attribute> attributes = entry.getAttributes();
				final UserInfo userInfo = readUserInformation(attributes);
				allusers.add(userInfo);
			}
			connection.unBind();
		} catch (final Throwable t) {
			t.printStackTrace();
		}

		return allusers;
	}

	private UserInfo readUserInformation(final Collection<Attribute> attributes) {
		String firstName = null, lastName = null, usedId = null;
		byte[] passwordBytes = null;
		for (final Attribute attribute : attributes) {
			
			if (attribute.getId().equals("sn")) {
				//firstName = (String) attribute.get();
			} else if (attribute.getId().equals("cn")) {
				//lastName = (String) attribute.get().getNormValue();
			} else if (attribute.getId().equals("uid")) {
				//usedId = (String) attribute.get().getNormValue();
			} else if (attribute.getId().equals("userpassword")) {
				/*final PasswordDetails passwordDetails = PasswordUtil
						.splitCredentials((byte[]) attribute.get().getNormValue());
				passwordBytes = passwordDetails.getPassword();*/
			}
		}

		return new UserInfo(firstName, lastName, usedId, passwordBytes);
	}

	private class UserInfo {

		private final String firstName;
		private final String lastName;
		private final String usedId;
		private final byte[] hashCodedPassword;

		public UserInfo(final String firstName, final String lastName, final String usedId,
				final byte[] passwordBytes) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.usedId = usedId;
			this.hashCodedPassword = passwordBytes;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getUsedId() {
			return usedId;
		}

		public byte[] getHashCodedPassword() {
			return hashCodedPassword;
		}

		@Override
		public String toString() {
			return "UserInfo [firstName=" + firstName + ", lastName=" + lastName + ", usedId=" + usedId
					+ ", hashCodedPassword=" + hashCodedPassword + "]";
		}

	}

}
