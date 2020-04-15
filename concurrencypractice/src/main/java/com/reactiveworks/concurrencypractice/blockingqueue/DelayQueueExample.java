package com.reactiveworks.concurrencypractice.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedElement implements Delayed {
	long time;

	public DelayedElement(long time) {
		this.time = System.currentTimeMillis() + time;
		;
	}

	public long getDelay(TimeUnit unit) {

		long diff = time - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	public int compareTo(Delayed obj) {
		if (this.time < ((DelayedElement) obj).time) {
			return -1;
		}
		if (this.time > ((DelayedElement) obj).time) {
			return 1;
		}
		return 0;
	}
}

public class DelayQueueExample {
	public static void main(String args[]) {
		DelayQueue<Delayed> delayQueueObj = new DelayQueue<Delayed>();
		Delayed element1 = new DelayedElement(5);
		delayQueueObj.put(element1);
	}
}
