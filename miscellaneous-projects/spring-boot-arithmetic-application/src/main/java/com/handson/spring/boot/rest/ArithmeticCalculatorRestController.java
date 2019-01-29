/**
 * 
 */
package com.handson.spring.boot.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.spring.boot.arithmetic.ArithmeticCalculator;
import com.handson.spring.boot.arithmetic.ArithmeticOperationNotSupportedException;

/**
 * @author sveera
 *
 */
@RestController
public class ArithmeticCalculatorRestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final ArithmeticCalculator arithmeticCalculator;

	/**
	 * @param arithmeticCalculator
	 */
	@Autowired
	public ArithmeticCalculatorRestController(ArithmeticCalculator arithmeticCalculator) {
		super();
		this.arithmeticCalculator = arithmeticCalculator;
	}

	@RequestMapping("/performArthOperation")
	@JsonView(Views.Public.class)
	public OutputResult performArithematicOperation(@RequestBody String requestBody)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		final InputRequest inputRequest = objectMapper.readValue(requestBody, InputRequest.class);
		return constructOutputresult(inputRequest.getFirstOperand(), inputRequest.getSecondOperand(),
				inputRequest.getOperation(), arithmeticCalculator.performArithematicOperation(
						inputRequest.getFirstOperand(), inputRequest.getSecondOperand(), inputRequest.getOperation()));
	}

	/**
	 * @param performArithematicOperation
	 * @return
	 */
	private OutputResult constructOutputresult(double firstOperand, double secondOperand, String operation,
			double result) {
		return new OutputResult(firstOperand, secondOperand, operation, result);
	}

	@ExceptionHandler
	@JsonView(Views.Public.class)
	public ErrorResult handleException(HttpServletResponse httpServletResponse, Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stringWriter));
		logger.error("Failed to process input request ");
		logger.error(stringWriter.toString());
		if (throwable instanceof ArithmeticOperationNotSupportedException)
			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return throwable instanceof ArithmeticOperationNotSupportedException ? new ErrorResult(throwable.getMessage())
				: new ErrorResult("Oops! Something went wrong check server logs");
	}

	public static class InputRequest {

		private double firstOperand;
		private double secondOperand;
		private String operation;

		public InputRequest() {
			super();
		}

		/**
		 * @param firstOperand
		 * @param secondOperand
		 * @param operation
		 */
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

	public class OutputResult {

		@JsonView(Views.Public.class)
		private final double firstOperand;
		@JsonView(Views.Public.class)
		private final double secondOperand;
		@JsonView(Views.Public.class)
		private final String operation;
		@JsonView(Views.Public.class)
		private final double result;

		/**
		 * @param firstOperand
		 * @param secondOperand
		 * @param operation
		 * @param result
		 */
		public OutputResult(double firstOperand, double secondOperand, String operation, double result) {
			super();
			this.firstOperand = firstOperand;
			this.secondOperand = secondOperand;
			this.operation = operation;
			this.result = result;
		}

		public double getFirstOperand() {
			return firstOperand;
		}

		public double getSecondOperand() {
			return secondOperand;
		}

		public String getOperation() {
			return operation;
		}

		public double getResult() {
			return result;
		}

		@Override
		public String toString() {
			return "OutputResult [firstOperand=" + firstOperand + ", secondOperand=" + secondOperand + ", operation="
					+ operation + ", result=" + result + "]";
		}

	}

	public class ErrorResult {

		@JsonView(Views.Public.class)
		private final String errorMessage;

		/**
		 * @param errorMessage
		 */
		public ErrorResult(String errorMessage) {
			super();
			this.errorMessage = errorMessage;
		}

		@Override
		public String toString() {
			return "ErrorResult [errorMessage=" + errorMessage + "]";
		}

	}

}
