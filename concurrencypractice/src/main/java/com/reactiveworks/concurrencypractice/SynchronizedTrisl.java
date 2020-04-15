package com.reactiveworks.concurrencypractice;

public class SynchronizedTrisl {

	public static void main(String[] args) throws InterruptedException {

		final SharedObject sharedObject = new SharedObject();

		Thread t1 = new Thread(new Runnable() {
			public void run() {

				sharedObject.count();
			}

		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {

				System.out.println(sharedObject.counter);
			}

		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		// System.out.println(sharedObject.counter);

	}
}

class SharedObject {

	int counter = 0;

	public void count() {
		counter = 90;
	}
}
