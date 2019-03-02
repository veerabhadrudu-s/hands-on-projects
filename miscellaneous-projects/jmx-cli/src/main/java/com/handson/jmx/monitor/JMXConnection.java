/**
 * 
 */
package com.handson.jmx.monitor;

/**
 * @author veera
 *
 */
public class JMXConnection {

	private final String connectionURL;
	private final String username;
	private final String password;

	/**
	 * @param connectionURL
	 * @param username
	 * @param password
	 */
	public JMXConnection(String connectionURL, String username, String password) {
		super();
		this.connectionURL = connectionURL;
		this.username = username;
		this.password = password;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionURL == null) ? 0 : connectionURL.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JMXConnection other = (JMXConnection) obj;
		if (connectionURL == null) {
			if (other.connectionURL != null)
				return false;
		} else if (!connectionURL.equals(other.connectionURL))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMXConnection [connectionURL=" + connectionURL + ", username=" + username + ", password=" + password
				+ "]";
	}

}
