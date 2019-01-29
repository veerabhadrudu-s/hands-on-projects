/**
 * 
 */
package com.handson.spring.boot.arithmetic;

/**
 * @author sveera
 *
 */
public class ArithmeticOperationNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param reasonForException
	 */
	public ArithmeticOperationNotSupportedException(String reasonForException) {
		super(reasonForException);
	}

}
