/**
 * 
 */
package com.handson.linux.runtime;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author sveera
 *
 */
public class RuntimeExecuteTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("dir");
		int exitValue = process.waitFor();
		System.out.println(exitValue);
	}

}
