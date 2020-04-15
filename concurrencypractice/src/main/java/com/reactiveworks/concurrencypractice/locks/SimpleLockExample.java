package com.reactiveworks.concurrencypractice.locks;

class Counter {
	Lock lock = new Lock();
	public int count = 0;

	public void count() throws InterruptedException {
		lock.lock();
		count++;
		lock.unlock();
	}
}

class Lock {
	private boolean isLocked = false;

	public void lock() throws InterruptedException {

		while (isLocked) {

			this.wait();

		}
		isLocked = true;
	}

	public void unlock() {
		isLocked = false;
		synchronized (this) {
			this.notify();
		}

	}

}

public class SimpleLockExample {

	public static void main(String[] args) throws InterruptedException {

		final Counter counterObj = new Counter();
		Thread thread1 = new Thread(new Runnable() {

			public void run() {

				try {
					counterObj.count();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			public void run() {

				try {
					for (int index = 0; index < 10; index++)
						counterObj.count();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		});

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
		System.out.println(counterObj.count);
	}

}
