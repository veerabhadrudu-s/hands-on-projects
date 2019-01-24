/**
 * 
 */
package org.handson.arthematic;

import org.handson.arthematic.PrefixArithmeticCalculator.PrefixExpressionCannotBeInvalid;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sveera
 *
 */
public class PrefixArithmeticCalculatorTest {

	private PrefixArithmeticCalculator prefixArithmeticCalculator;

	@Before
	public void setUp() {
		prefixArithmeticCalculator = new PrefixArithmeticCalculator();
	}

	@Test(expected = PrefixExpressionCannotBeInvalid.class)
	public void calculateForNull() {
		prefixArithmeticCalculator.calculateExpression(null);
	}

	@Test(expected = PrefixExpressionCannotBeInvalid.class)
	public void calculateForEmpty() {
		prefixArithmeticCalculator.calculateExpression("");
	}

	@Test(expected = PrefixExpressionCannotBeInvalid.class)
	public void calcluateForInvalidExpression() {
		prefixArithmeticCalculator.calculateExpression("2+3");
	}

}
