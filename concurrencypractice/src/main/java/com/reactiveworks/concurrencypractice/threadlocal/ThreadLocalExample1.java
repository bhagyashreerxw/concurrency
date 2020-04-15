package com.reactiveworks.concurrencypractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

class MyDateFormatter{
	
	ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocalObj=new  ThreadLocal<SimpleDateFormat>();
	
	  public String format(Date date) {
	        SimpleDateFormat simpleDateFormat = getThreadLocalSimpleDateFormat();
	        return simpleDateFormat.format(date);
	    }
	    
	    
	    private SimpleDateFormat getThreadLocalSimpleDateFormat() {
	        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocalObj.get();
	        if(simpleDateFormat == null) {
	            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            simpleDateFormatThreadLocalObj.set(simpleDateFormat);
	        }
	        return simpleDateFormat;
	    }
	
}

public class ThreadLocalExample1 {
	

	public static void main(String[] args) {
		final MyDateFormatter myDateFormatterObj=new MyDateFormatter();
		Thread thread1=new Thread(new Runnable() {

			public void run() {
				
				System.out.println(myDateFormatterObj.format(new Date()));
			}
			
		});
		
		thread1.start();
		
	}

}
