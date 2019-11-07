/**
 * 
 */
package com.handson.generics;

/**
 * @author veera
 *
 */
public class GenericsTest2NameClashWithObjectType {

	/*
	 * Below code will fail to compile due to method name clash with Object Type
	 * after erasure
	 */
	/*
	 * public class Twin<T> {
	 * 
	 * public boolean equals(T obj) {
	 * 
	 * return true; } }
	 */
}
