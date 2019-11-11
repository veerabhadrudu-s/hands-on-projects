/**
 * 
 */
package com.handson.generics;

import java.io.Serializable;
import java.util.List;

/**
 * @author veera
 *
 */
public class GenericsTest2ArrayStoreException {

	public static void main(String args[]) {

		/*
		 * This example throws ArrayStoreException as arrays in java remembers their
		 * initial created type
		 */
		DummyClass[] dummies = new DummyClass[10];
		Object[] obj = dummies;
		obj[4] = "";

		List<Integer> ints = null;
		List<Number> numbers = null;
		List<Object> objs = null;
		consumeList(ints);
		consumeList(numbers);
		consumeList(objs);
	}

	private static class DummyClass implements Serializable, Cloneable {

		private static final long serialVersionUID = 1L;

	}

	private static void consumeList(List<? super Integer> superList) {

	}

}
