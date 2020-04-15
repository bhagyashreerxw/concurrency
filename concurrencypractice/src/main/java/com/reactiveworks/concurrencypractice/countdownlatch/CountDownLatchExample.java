package com.reactiveworks.concurrencypractice.countdownlatch;

import java.util.concurrent.CountDownLatch;

class Waiter extends Thread {

	CountDownLatch latch;

	public Waiter(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		 System.out.println("Waiter Released");
	}
}

class Decrementer extends Thread {

	CountDownLatch latch ;
	
	public Decrementer(CountDownLatch latch) {
		this.latch=latch;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
//		latch.countDown();
//		latch.countDown();
		
	}
}

public class CountDownLatchExample {

	public static void main(String[] args) {
        
		CountDownLatch latch=new CountDownLatch(3);
		Waiter waiterObj=new Waiter(latch);
		Decrementer decrementerObj1=new Decrementer(latch);
		Decrementer decrementerObj2=new Decrementer(latch);
		Decrementer decrementerObj3=new Decrementer(latch);
		waiterObj.start();
		decrementerObj1.start();
		decrementerObj2.start();
		decrementerObj3.start();
	}
}
