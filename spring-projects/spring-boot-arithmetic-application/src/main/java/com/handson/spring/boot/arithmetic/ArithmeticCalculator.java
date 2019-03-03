/**
 * 
 */
package com.handson.spring.boot.arithmetic;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sveera
 *
 */
public class ArithmeticCalculator {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Map<String, Operation> supportedOperations = new HashMap<>();
	private final String supportedOperaionsString;

	public ArithmeticCalculator() {
		super();
		this.supportedOperations.put("+", (firstOparand, secondOparand) -> {
			return firstOparand + secondOparand;
		});
		this.supportedOperations.put("-", (firstOparand, secondOparand) -> firstOparand - secondOparand);
		this.supportedOperations.put("*", (firstOparand, secondOparand) -> firstOparand * secondOparand);
		this.supportedOperations.put("/", (firstOparand, secondOparand) -> firstOparand / secondOparand);
		supportedOperaionsString = this.supportedOperations.keySet().toString();
	}

	/**
	 * @param firstOperand
	 * @param secondOperand
	 * @param operation
	 * @return
	 */
	public double performArithematicOperation(double firstOperand, double secondOperand, String operation) {
		logInputRequest(firstOperand, secondOperand, operation);
		if (!supportedOperations.containsKey(operation))
			throw new ArithmeticOperationNotSupportedException(
					"Supported Operations at present are " + supportedOperaionsString);
		return supportedOperations.get(operation).performOperation(firstOperand, secondOperand);
	}

	/**
	 * @param firstOperand
	 * @param secondOperand
	 * @param operation
	 */
	private void logInputRequest(double firstOperand, double secondOperand, String operation) {
		logger.trace("Requested {} operation with first firstOperand {} and secondOperand {} ", operation, firstOperand,
				secondOperand);
	}

	interface Operation {
		double performOperation(double firstOparand, double secondOparand);
	}

}
