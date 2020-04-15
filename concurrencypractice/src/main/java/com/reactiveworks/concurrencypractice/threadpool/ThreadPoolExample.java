package com.reactiveworks.concurrencypractice.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Print implements Runnable{
	String printStr;

	public Print(String strPrint) {
		printStr = strPrint;
	}

	public void run() {

		System.out.println(Thread.currentThread().getName() + " printing " + printStr);

	}
}

public class ThreadPoolExample {

	public static void main(String[] args) {

		Print jobs[] = { new Print("weqwe"),
				new Print("erttyy"), 
				new Print("yuuiio"),
				new Print("rtyui")};

		ExecutorService executorServiceObj = Executors.newFixedThreadPool(3);
		for (Print printJob : jobs) {
			executorServiceObj.submit(printJob);
		}
		executorServiceObj.shutdown();
	}
}