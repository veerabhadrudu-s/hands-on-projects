/**
 * 
 */
package org.javaee7.jaxws.arthematic;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.javaee7.jaxws.arthematic.exception.ArithmeticCalculationException;

/**
 * @author sveera
 *
 */
@WebService
public interface ArithmeticCalculator {

	@WebMethod
	double calculateExpression(String arithmeticExpression) throws ArithmeticCalculationException;

}
