/**
 * 
 */
package com.handson.generics;

/**
 * @author veera
 *
 */
public class GenericsTest3NameClashWithObjectType {

	/*
	 * Below code will fail to compile due to method name clash with Object Type
	 * after erasure.We cann't override Object methods using generics, this will
	 * causes name clashes after erasure.
	 */
	/*
	 * public class Twin<T> {
	 * 
	 * public boolean equals(T obj) {
	 * 
	 * return true; } }
	 */
}
