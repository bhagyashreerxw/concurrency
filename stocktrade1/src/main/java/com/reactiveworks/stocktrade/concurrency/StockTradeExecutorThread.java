package com.reactiveworks.stocktrade.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class StockTradeExecutorThread extends Thread {
    private static LinkedBlockingQueue<InsertionJob> jobList;
    private static AtomicInteger threadCount;
    private static StockTradeExecutorThread stockTradeExecutorThreads[];
    
    public void insertJob(InsertionJob insertionJob) {
    	jobList.add(insertionJob);
    }
	
	public StockTradeExecutorThread(int threadCount) {
		StockTradeExecutorThread.threadCount.addAndGet(threadCount);
		stockTradeExecutorThreads=new StockTradeExecutorThread[threadCount];
	}
	
	public StockTradeExecutorThread() {
		
	}
	
	@Override
	public void run() {
		int value;
		while((value=threadCount.decrementAndGet())>=0)
		{
			stockTradeExecutorThreads[value].start();
		
		}
		
		while(jobList.size()!=0) {
			
			jobList.remove().run();
		
		}
	}

}