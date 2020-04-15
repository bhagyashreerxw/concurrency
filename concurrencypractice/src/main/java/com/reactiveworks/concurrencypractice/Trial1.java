package com.reactiveworks.concurrencypractice;

public class Trial1 {
	
	public static void main(String args[]) {
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println("hello");
			}
		}).start();
		
	}

}
