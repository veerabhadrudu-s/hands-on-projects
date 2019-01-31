/**
 * 
 */
package com.handson.spring.boot.arithmetic.rest.messages;

/**
 * @author sveera
 *
 */
public  class InputRequest {

	private double firstOperand;
	private double secondOperand;
	private String operation;

	public InputRequest() {
		super();
	}

	public InputRequest(double firstOperand, double secondOperand, String operation) {
		super();
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
		this.operation = operation;
	}

	public double getFirstOperand() {
		return firstOperand;
	}

	public void setFirstOperand(double firstOperand) {
		this.firstOperand = firstOperand;
	}

	public double getSecondOperand() {
		return secondOperand;
	}

	public void setSecondOperand(double secondOperand) {
		this.secondOperand = secondOperand;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
