package com.reactiveworks.concurrencypractice.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableThread implements Callable<Integer> {
	int num;

	public CallableThread(int num) {
		this.num = num;

	}

	public Integer call() throws Exception {
		int sum = 0;
		System.out.println(Thread.currentThread().getName() + " calculates sum of " + num + " natural numbers");
		for (int index = 1; index <= num; index++) {
			sum += index;
		}
		return sum;
	}

}

public class CallableExample {

	public static void main(String[] args) {
		CallableThread jobs[] = { new CallableThread(10), new CallableThread(10), new CallableThread(20),
				new CallableThread(30), new CallableThread(5), new CallableThread(40), new CallableThread(3) };

		ExecutorService serviceObj = Executors.newFixedThreadPool(3);
		for (CallableThread job : jobs) {
			Future<Integer> value = serviceObj.submit(job);
			try {
				System.out.println(value.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
