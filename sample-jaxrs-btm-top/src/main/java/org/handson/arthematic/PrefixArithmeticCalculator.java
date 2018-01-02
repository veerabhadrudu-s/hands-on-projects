/**
 * 
 */
package org.handson.arthematic;

/**
 * @author sveera
 *
 */
public class PrefixArithmeticCalculator {

	public double calculateExpression(String prefixArithemeticExpression) {
		validatePrefixArithemeticExpression(prefixArithemeticExpression);
		return 0.0;
	}

	private void validatePrefixArithemeticExpression(String prefixArithemeticExpression) {
		if (prefixArithemeticExpression == null || prefixArithemeticExpression.isEmpty())
			throw new PrefixExpressionCannotBeInvalid("Prefix Arithemetic Expression can't be null or empty.");
		if (prefixArithemeticExpression.split(",").length == 1)
			throw new PrefixExpressionCannotBeInvalid("Invalid Arithemetic Expression");
	}

	public class PrefixExpressionCannotBeInvalid extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public PrefixExpressionCannotBeInvalid(String message) {
			super(message);
		}
	}

}
