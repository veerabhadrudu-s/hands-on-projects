/**
 * 
 */
package com.handson.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sveera
 *
 */
public class ConcurrencyExample03 {

	private volatile int counter = 0;

	/*
	 * This is a solution to previous example . Example on volatile keyword.Volatile
	 * keyword will makes always variables to always to read/write from RAM(no cpu
	 * cache).
	 * 
	 */

	public static void main(String[] args) {

		ConcurrencyExample03 concurrencyExample01 = new ConcurrencyExample03();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Callable<Void> hellos = concurrencyExample01.getHellosThread();
		Callable<Void> goodbyes = concurrencyExample01.getGoodByesThread();
		executor.submit(hellos);
		executor.submit(goodbyes);
		executor.shutdown();
	}

	private Callable<Void> getGoodByesThread() {
		return () -> {
			int localcounter = 0;
			System.out.println("Starting goodbye thread !!!");
			while (counter != 1000)
				localcounter++;
			System.out.println("Goodbye final count " + localcounter);
			return null;
		};
	}

	private Callable<Void> getHellosThread() {
		return () -> {
			System.out.println("Starting hello thread !!!");
			while (counter < 2000) {
				counter++;
				Thread.sleep(1);
			}
			System.out.println("Hello final count " + counter);
			return null;
		};
	}

}
