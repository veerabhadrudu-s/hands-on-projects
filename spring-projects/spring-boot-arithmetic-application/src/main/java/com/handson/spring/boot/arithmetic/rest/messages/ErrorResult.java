/**
 * 
 */
package com.handson.spring.boot.arithmetic.rest.messages;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author sveera
 *
 */
public class ErrorResult {

	@JsonView(Views.Public.class)
	private final String errorMessage;

	public ErrorResult(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorResult [errorMessage=" + errorMessage + "]";
	}

}
