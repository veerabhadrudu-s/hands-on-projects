/**
 * 
 */
package com.handson.listener;

/**
 * @author sveera
 *
 */
public class ListenerConfigurationBuilder {

	private int port = 8080;
	private String notificationEndPointPath = "/notification";
	private String contentType = "text/html";
	private String requestIdentifier = ListenerConfiguration.REQUEST_IDENTIFIER_FROM_REQUEST;
	private String responseCode = "2000";

	public ListenerConfigurationBuilder port(int port) {
		this.port = port;
		return this;
	}

	public ListenerConfigurationBuilder notificationEndPointPath(String notificationEndPointPath) {
		this.notificationEndPointPath = notificationEndPointPath;
		return this;
	}

	public ListenerConfigurationBuilder contentType(String contentType) {
		this.contentType = contentType.equalsIgnoreCase("null") ? null : contentType;
		return this;
	}

	public ListenerConfigurationBuilder requestIdentifier(String requestIdentifier) {
		this.requestIdentifier = requestIdentifier.equalsIgnoreCase("null") ? null : requestIdentifier;
		return this;
	}

	public ListenerConfigurationBuilder responseCode(String responseCode) {
		this.responseCode = responseCode.equalsIgnoreCase("null") ? null : responseCode;
		return this;
	}

	public ListenerConfiguration build() {
		return new ListenerConfiguration(this.port, this.notificationEndPointPath, this.contentType,
				this.requestIdentifier, this.responseCode);
	}

}
