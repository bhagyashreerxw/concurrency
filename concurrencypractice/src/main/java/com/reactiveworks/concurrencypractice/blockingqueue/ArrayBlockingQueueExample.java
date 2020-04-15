package com.reactiveworks.concurrencypractice.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerConsumer {
	BlockingQueue<Integer> blockigQueueObj = new ArrayBlockingQueue<Integer>(4);

	public void produce() {
		System.out.println("producer thread started its execution");
		try {
			for (int index = 0; index < 4; index++) {
				blockigQueueObj.put(1);

			}
			// System.out.println(blockigQueueObj);
			System.out.println("producer thread completed its execution");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consume() {
		System.out.println("consumer thread started its execution");
		for (int index = 0; index < 3; index++) {
			try {
				//System.out.println(blockigQueueObj);
				blockigQueueObj.take();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("consumer thread completed its execution");
	}

	public void getQueueEntries() {
		System.out.println(blockigQueueObj);
	}

}

public class ArrayBlockingQueueExample {

	public static void main(String[] args) {
		final ProducerConsumer producerConsumerObj = new ProducerConsumer();

		Thread thread1 = new Thread(new Runnable() {

			public void run() {

				producerConsumerObj.produce();
			}

		});

		Thread thread2 = new Thread(new Runnable() {

			public void run() {

				producerConsumerObj.produce();
			}

		});

		Thread thread3 = new Thread(new Runnable() {

			public void run() {

				producerConsumerObj.consume();
			}

		});
		thread3.setPriority(6);
		thread1.start();
		// thread2.start();
		thread3.start();
	}

}
