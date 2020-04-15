package com.reactiveworks.concurrencypractice.customlock;

class Counter {
	Lock lock = new Lock();
	public int count = 0;

	public void Count() {
		try {
			lock.lock();
			count++;
			lock.unLock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

public class CustomLockExample {

	public static void main(String[] args) throws InterruptedException {
		final Counter countObj = new Counter();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				for (int index = 0; index < 10; index++)
					countObj.Count();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				for (int index = 0; index < 10; index++)
					countObj.Count();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("count: " + countObj.count);

	}

}

class Lock {
	private boolean isLocked = false;
	private Thread lockedThread = null;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
		lockedThread = Thread.currentThread();
	}

	public synchronized void unLock() {
		if (this.lockedThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		isLocked = false;
		lockedThread = null;

		notify();

	}

}