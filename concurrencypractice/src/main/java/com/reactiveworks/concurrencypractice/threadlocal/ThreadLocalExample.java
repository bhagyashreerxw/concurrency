package com.reactiveworks.concurrencypractice.threadlocal;

class Customer extends Thread {
	static int customerId = 0;

	private static ThreadLocal<Integer> threadLocalObj = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			synchronized (threadLocalObj) {
				return ++customerId;	
			}
			
		}
	};

	Customer(String name) {
		super(name);
	}

	public void run() {
		System.out.println("id of " + Thread.currentThread().getName() + " is " + threadLocalObj.get());
	}
}

public class ThreadLocalExample {

	public static void main(String args[]) {
		Customer customerObj1=new Customer("thread1");
		Customer customerObj2=new Customer("thread2");
		Customer customerObj3=new Customer("thread3");
		customerObj1.start();
		customerObj2.start();
		customerObj3.start();
	}

}
