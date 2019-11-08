/**
 * 
 */
package com.handson.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author veera
 *
 */
public class GenericsTest5 {

	public static void main(String args[]) {

		/*
		 * Use lower bound i.e super to behave like consumer. In the below example add
		 * method behaves like consumer method/operation. However, we cannot call get
		 * method to get Number object.
		 */
		List<? super Number> numbers = new ArrayList<>();
		numbers.add(new Integer(5));
		// Number value = numbers.get(0);

		/*
		 * Use upper bound i.e extends to behave like producer. In the below example get
		 * method behaves like producer method/operation. However, we cannot call get
		 * method to get Number object.
		 */
		List<? extends Number> numbers1 = new ArrayList<>();
		// numbers1.add(1);
		Number value = numbers1.get(0);

	}
}
