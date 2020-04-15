package com.reactiveworks.concurrencypractice.locks;

import java.util.concurrent.locks.ReentrantLock;

class Display {
	int count = 0;
	ReentrantLock lock = new ReentrantLock();

	public void wish() {
		lock.lock();
		for (int index = 0; index < 10; index++) {
			System.out.print("hello: ");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(count++);
		}
		lock.unlock();

	}
}

class Mythread extends Thread {
	Display dispObj;

	Mythread(Display dispObj) {
		this.dispObj = dispObj;
	}

	public void run() {

		dispObj.wish();
	}

}

public class ReentrantLockExample {

	public static void main(String[] args) {

		Display displayObj = new Display();
		Mythread thread1 = new Mythread(displayObj);
		Mythread thread2 = new Mythread(displayObj);

		thread1.start();
		thread2.start();

	}

}
