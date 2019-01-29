/**
 * 
 */
package com.handson.spring.boot.arithmetic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.handson.spring.boot.arithmetic.ArithmeticCalculator;
import com.handson.spring.boot.arithmetic.ArithmeticOperationNotSupportedException;

/**
 * @author sveera
 *
 */
public class ArithmeticCalculatorTest {

	private ArithmeticCalculator arithmeticCalculator;

	@BeforeEach
	public void setUp() throws Exception {
		arithmeticCalculator = new ArithmeticCalculator();
	}

	@Test
	public void testTwoNumbersAdditionOperationAndExpectSumAsResult() {
		assertEquals(3, arithmeticCalculator.performArithematicOperation(1, 2, "+"),
				"Expected and Actual Results are not equal");
	}

	@Test
	public void testTwoNumbersSubtractionOperationAndExpectDifferenceAsResult() {
		assertEquals(10, arithmeticCalculator.performArithematicOperation(20, 10, "-"),
				"Expected and Actual Results are not equal");
	}

	@Test
	public void testTwoNumbersMultiplicationOperationAndExpectProductAsResult() {
		assertEquals(625, arithmeticCalculator.performArithematicOperation(25, 25, "*"),
				"Expected and Actual Results are not equal");
	}

	@Test
	public void testTwoNumbersDivisionOperationAndExpectQuotientAsResult() {
		assertEquals(10, arithmeticCalculator.performArithematicOperation(50, 5, "/"),
				"Expected and Actual Results are not equal");
	}

	@Test
	public void testTwoNumbersInvalidOperationAndExpectException() {
		assertThrows(ArithmeticOperationNotSupportedException.class,
				() -> arithmeticCalculator.performArithematicOperation(50, 5, "?"));
	}

}
