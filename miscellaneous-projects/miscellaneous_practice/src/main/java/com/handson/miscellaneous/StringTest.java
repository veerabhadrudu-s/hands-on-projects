/**
 * 
 */
package com.handson.miscellaneous;

import java.util.HashMap;
import java.util.Map;

/**
 * @author veera
 *
 */
public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put(new String("a"), "audi");
		hashMap.put(new String("a"), "ferrari");
		System.out.println(hashMap);
		System.out.println(new String("a") == new String("a"));
		System.out.println(new String("a").hashCode());
		System.out.println(new String("a").hashCode());

	}

}
