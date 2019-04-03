/**
 * 
 */
package com.handson.miscellaneous;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author veera
 *
 */
public class ThreadWaitNotify {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadWaitNotify threadWaitNotify = new ThreadWaitNotify();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Data data = threadWaitNotify.new Data();
		executor.execute(threadWaitNotify.new Producer(data));
		/*
		 * If you uncomment below line , will causes IllegalMonitorStateException in
		 * Producer2 Thread run method. This is due to Thread which calls wait on an
		 * object should first acquire lock on that object. For more on this refer
		 * Object class wait method Documentation.
		 */
		// executor.execute(threadWaitNotify.new Producer2(data));
		executor.execute(threadWaitNotify.new Consumer(data));
		System.out.println("After executor service started threads");
		executor.shutdown();
	}

	private class Data {

	}

	private class Producer implements Runnable {

		private final Data data;

		public Producer(Data data) {
			super();
			this.data = data;
		}

		@Override
		public void run() {
			try {
				// Thread.sleep(5000);
				synchronized (data) {
					System.out
							.println("Inside run method of " + this.getClass().getSimpleName() + " before wait method");
					data.wait();
					System.out
							.println("Inside run method of " + this.getClass().getSimpleName() + " after wait method");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Inside run method of " + this.getClass().getSimpleName() + " after synchronized block");
		}

	}

	private class Producer2 implements Runnable {

		private final Data data;

		public Producer2(Data data) {
			super();
			this.data = data;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
				System.out.println("Inside run method of " + this.getClass().getSimpleName());
				data.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private class Consumer implements Runnable {

		private final Data data;

		public Consumer(Data data) {
			super();
			this.data = data;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
				synchronized (data) {
					System.out.println(
							"Inside run method of " + this.getClass().getSimpleName() + " before notify method");
					data.notify();
					System.out.println(
							"Inside run method of " + this.getClass().getSimpleName() + " after notify method");
				}
				System.out.println(
						"Inside run method of " + this.getClass().getSimpleName() + " after synchronized block");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
