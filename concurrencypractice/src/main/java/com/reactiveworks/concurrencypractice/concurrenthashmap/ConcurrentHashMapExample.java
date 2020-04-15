package com.reactiveworks.concurrencypractice.concurrenthashmap;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample extends Thread {
	
	private static ConcurrentHashMap< Integer, String> concurrentHashMapObj =new ConcurrentHashMap<Integer, String>();
	
	public void run() {
		
		 try {
			 Thread.sleep(1000);
		 } catch (InterruptedException e) {
			e.printStackTrace();
		}
		 System.out.println("child thread is updating the map");
		 concurrentHashMapObj.put(1, "a");
	}

	public static void main(String[] args) {
		ConcurrentHashMapExample thread=new ConcurrentHashMapExample();
		concurrentHashMapObj.put(2, "b");
		concurrentHashMapObj.put(3, "c");
		concurrentHashMapObj.put(4, "d");
		thread.start();
		Set<Integer> keySetObj=concurrentHashMapObj.keySet();
		Iterator<Integer> itr=keySetObj.iterator();
		while(itr.hasNext()) {
			Integer key=itr.next();
			System.out.println("main thread is iterating over the map current entry is key="+key+" value="+concurrentHashMapObj.get(key));
		    try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(concurrentHashMapObj);
		
	}

}
