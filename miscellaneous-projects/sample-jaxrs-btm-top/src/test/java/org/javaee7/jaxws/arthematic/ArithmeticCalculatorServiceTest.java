/**
 * 
 */
package org.javaee7.jaxws.arthematic;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.javaee7.jaxws.arthematic.exception.ArithmeticCalculationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sveera
 *
 */
public class ArithmeticCalculatorServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateExpression() throws ArithmeticCalculationException, MalformedURLException {
		final Service calculatorService = Service.create(
				new URL("http://185.170.48.161/sample-jaxrs-btm-top/arithmeticCalculator?wsdl"),
				new QName("http://arithmetic.calculator/calculate", "arithmeticCalculator"));
		assertNotNull(calculatorService);
		final ArithmeticCalculator calculator = calculatorService.getPort(ArithmeticCalculator.class);
		Assert.assertEquals(0.0, calculator.calculateExpression("1+2"), 0.000);
	}

}
