package com.reactiveworks.concurrencypractice.locks.readwritelocks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static int num = 0;
	private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
	private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

	public static void readOperation() {
		readLock.lock();
		System.out.println("num: "+num);
		
		readLock.unlock();

	}

	public static void writeOperation() {
		writeLock.lock();
		num = num + 1;
		System.out.println("write holdcount: " + lock.getWriteHoldCount());
		writeLock.unlock();
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable() {

			public void run() {
				readOperation();
			}

		});

		Thread thread2 = new Thread(new Runnable() {

			public void run() {
				readOperation();
			}

		});

		Thread thread3 = new Thread(new Runnable() {

			public void run() {
				writeOperation();
			}

		});

		thread1.start();
		thread2.start();
		thread3.start();

	}

}
