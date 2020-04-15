package com.reactiveworks.concurrencypractice.signalling;

class Display {

	public boolean signal = false;

	public synchronized void wish() {

		try {
			if (!this.signal)
				this.wait();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("hello");
	}

}

class MyThread extends Thread {
	public void run() {
		Display displayObj = new Display();
		displayObj.wish();
	}
}

public class SignallingExample {

	public static void main(String[] args) {
		Display dispObj = new Display();
		MyThread thread1 = new MyThread();
		MyThread thread2 = new MyThread();
		thread1.start();
		thread2.start();
		synchronized (dispObj) {
			dispObj.signal = true;
			dispObj.notify();
		}

	}

}