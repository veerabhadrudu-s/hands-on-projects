/**
 * 
 */
package com.handson.generics;

import java.io.Serializable;

/**
 * @author veera
 *
 */
public class GenericsTest2ArrayStoreException {

	public static void main(String args[]) {

		DummyClass[] ints = new DummyClass[10];
		Object[] obj = ints;
		obj[4] = "";
	}

	private static class DummyClass implements Serializable, Cloneable {

		private static final long serialVersionUID = 1L;

	}

}
