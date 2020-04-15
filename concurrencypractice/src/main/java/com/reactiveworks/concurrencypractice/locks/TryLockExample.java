package com.reactiveworks.concurrencypractice.locks;

import java.util.concurrent.locks.ReentrantLock;

class Display1 {
	int count = 0;
	ReentrantLock lock = new ReentrantLock();

	public void wish() {
		if (lock.tryLock()) {
			for (int index = 0; index < 10; index++) {
				System.out.print("hello: ");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(count++);

			}
			System.out.println(Thread.currentThread() + " completed execution");
		} else {
			System.out.println(Thread.currentThread() + " could not acquire the lock");
		}

	}
}

class Mythread1 extends Thread {
	Display1 dispObj;

	Mythread1(Display1 dispObj) {
		this.dispObj = dispObj;
	}

	public void run() {

		dispObj.wish();
	}

}

public class TryLockExample {

	public static void main(String[] args) {

		Display1 displayObj = new Display1();
		Mythread1 thread1 = new Mythread1(displayObj);
		Mythread1 thread2 = new Mythread1(displayObj);

		thread1.start();
		thread2.start();

	}

}
