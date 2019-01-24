/**
 * 
 */
package org.javaee7.jaxws.arthematic.impl;

import javax.jws.WebService;

import org.javaee7.jaxws.arthematic.ArithmeticCalculator;
import org.javaee7.jaxws.arthematic.exception.ArithmeticCalculationException;

/**
 * @author sveera
 *
 */
@WebService(targetNamespace = "http://arithmetic.calculator/calculate", 
endpointInterface = "org.javaee7.jaxws.arthematic.ArithmeticCalculator", serviceName = "arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	@Override
	public double calculateExpression(String arithmeticExpression) throws ArithmeticCalculationException {
		return 0;
	}

}
