/**
 * 
 */
package com.handson.generics;

import java.util.List;

/**
 * @author veera
 *
 */
public class GenericsTest8Super {

	public static void main(String args[]) {

		List<Integer> ints = null;
		List<Number> numbers = null;
		List<Object> objs = null;
		consumeList(ints);
		consumeList(numbers);
		consumeList(objs);
	}

	private static void consumeList(List<? super Integer> superList) {

	}

}
