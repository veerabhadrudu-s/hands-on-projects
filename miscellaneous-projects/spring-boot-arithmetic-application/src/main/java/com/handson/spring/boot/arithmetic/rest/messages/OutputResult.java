/**
 * 
 */
package com.handson.spring.boot.arithmetic.rest.messages;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author sveera
 *
 */
public class OutputResult {

	@JsonView(Views.Public.class)
	private final double firstOperand;
	@JsonView(Views.Public.class)
	private final double secondOperand;
	@JsonView(Views.Public.class)
	private final String operation;
	@JsonView(Views.Public.class)
	private final double result;

	public OutputResult(double firstOperand, double secondOperand, String operation, double result) {
		super();
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
		this.operation = operation;
		this.result = result;
	}

	public double getFirstOperand() {
		return firstOperand;
	}

	public double getSecondOperand() {
		return secondOperand;
	}

	public String getOperation() {
		return operation;
	}

	public double getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "OutputResult [firstOperand=" + firstOperand + ", secondOperand=" + secondOperand + ", operation="
				+ operation + ", result=" + result + "]";
	}

}
