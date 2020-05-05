/**
 * 
 */
package com.handson.listener;

/**
 * @author sveera
 *
 */
public interface ResponseConfiguration {

	String REQUEST_IDENTIFIER_FROM_REQUEST = "REQUEST_IDENTIFIER_FROM_REQUEST";

	String getContentType();

	String getRequestIdentifier();

	String getResponseCode();

}
