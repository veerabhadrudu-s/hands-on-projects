/**
 * 
 */
package com.handson.jmx.monitor;

/**
 * @author veera
 *
 */
public class JMXRequest {

	private final JMXConnection jmxConnection;
	private final String domainName;
	private final String type;
	private final String attributes;

	/**
	 * @param jmxConnection
	 * @param domainName
	 * @param type
	 * @param attributes
	 */
	public JMXRequest(JMXConnection jmxConnection, String domainName, String type, String attributes) {
		super();
		this.jmxConnection = jmxConnection;
		this.domainName = domainName;
		this.type = type;
		this.attributes = attributes;
	}

	public JMXConnection getJmxConnection() {
		return jmxConnection;
	}

	public String getDomainName() {
		return domainName;
	}

	public String getType() {
		return type;
	}

	public String getAttributes() {
		return attributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((domainName == null) ? 0 : domainName.hashCode());
		result = prime * result + ((jmxConnection == null) ? 0 : jmxConnection.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		JMXRequest other = (JMXRequest) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (domainName == null) {
			if (other.domainName != null)
				return false;
		} else if (!domainName.equals(other.domainName))
			return false;
		if (jmxConnection == null) {
			if (other.jmxConnection != null)
				return false;
		} else if (!jmxConnection.equals(other.jmxConnection))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMXRequest [jmxConnection=" + jmxConnection + ", domainName=" + domainName + ", type=" + type
				+ ", attributes=" + attributes + "]";
	}

}
