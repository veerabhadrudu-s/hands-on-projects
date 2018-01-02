/**
 * 
 */
package org.javaee7.jaxws.arthematic.exception;

/**
 * @author sveera
 *
 */
public class ArithmeticCalculationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArithmeticCalculationException() {
		super();
	}

	public ArithmeticCalculationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ArithmeticCalculationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ArithmeticCalculationException(String arg0) {
		super(arg0);
	}

	public ArithmeticCalculationException(Throwable arg0) {
		super(arg0);
	}

}
