package com.reactiveworks.concurrencypractice.exchanger;

import java.util.concurrent.Exchanger;

class MyThread extends Thread {
	String obj = "";
	Exchanger<String> exchangerObj = null;

	public MyThread(Exchanger<String> exchangerObj, String obj) {
		this.obj = obj;
		this.exchangerObj = exchangerObj;
	}

	public void run() {
		try {
			Object previous = this.obj;

			this.obj = this.exchangerObj.exchange(this.obj);

			System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.obj);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ExchngerExample {

	public static void main(String[] args) {

		Exchanger<String> exchanger = new Exchanger<String>();

		MyThread exchangerRunnable1 = new MyThread(exchanger, "A");

		MyThread exchangerRunnable2 = new MyThread(exchanger, "B");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();

	}

}
