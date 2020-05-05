/**
 * 
 */
package com.handson.listener;

/**
 * @author sveera
 *
 */
public class ListenerConfiguration implements ResponseConfiguration {

	private final int port;
	private final String notificationEndPointPath;
	private final String contentType;
	private final String requestIdentifier;
	private final String responseCode;

	public ListenerConfiguration(int port, String notificationEndPointPath, String contentType,
			String requestIdentifier, String responseCode) {
		super();
		this.port = port;
		this.notificationEndPointPath = notificationEndPointPath;
		this.contentType = contentType;
		this.requestIdentifier = requestIdentifier;
		this.responseCode = responseCode;
	}

	public int getPort() {
		return port;
	}

	public String getNotificationEndPointPath() {
		return notificationEndPointPath;
	}

	public String getContentType() {
		return contentType;
	}

	public String getRequestIdentifier() {
		return requestIdentifier;
	}

	public String getResponseCode() {
		return responseCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((notificationEndPointPath == null) ? 0 : notificationEndPointPath.hashCode());
		result = prime * result + port;
		result = prime * result + ((requestIdentifier == null) ? 0 : requestIdentifier.hashCode());
		result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
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
		ListenerConfiguration other = (ListenerConfiguration) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (notificationEndPointPath == null) {
			if (other.notificationEndPointPath != null)
				return false;
		} else if (!notificationEndPointPath.equals(other.notificationEndPointPath))
			return false;
		if (port != other.port)
			return false;
		if (requestIdentifier == null) {
			if (other.requestIdentifier != null)
				return false;
		} else if (!requestIdentifier.equals(other.requestIdentifier))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListenerConfiguration [port=" + port + ", notificationEndPointPath=" + notificationEndPointPath
				+ ", contentType=" + contentType + ", requestIdentifier=" + requestIdentifier + ", responseCode="
				+ responseCode + "]";
	}

}
