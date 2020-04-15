package com.reactiveworks.concurrencypractice.semaphore;

import java.util.concurrent.Semaphore;

class MyThread extends Thread{
	private static Semaphore semaphore=new Semaphore(4);
	String name="";
	
	public MyThread(String name) {
		this.name=name;
	}
	
	public void run() {
		 
		try {
			System.out.println(name+" available permits are " + semaphore.availablePermits());
			semaphore.acquire();
			System.out.println(name+" got the permit");
			
			for(int index=0;index<5;index++) {
				System.out.println(name + " : is performing operation " + index 
						+ ", available Semaphore permits : "
						+ semaphore.availablePermits());

				// sleep 1 second
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(name + " : releasing lock...");
			semaphore.release();
			System.out.println(name + " : available Semaphore permits now: " 
						+ semaphore.availablePermits());

		}
		
	}
	
}

public class SemaphoreExample {

	public static void main(String[] args) {
		
		MyThread thread1=new MyThread("A");
		MyThread thread2=new MyThread("b");
		MyThread thread3=new MyThread("c");
		MyThread thread4=new MyThread("d");
		MyThread thread5=new MyThread("e");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		

	}

}
