package com.reactiveworks.stocktrade.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.concurrency.InsertionJob;
import com.reactiveworks.stocktrade.concurrency.StockTradeExecutorThread;
import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * creates stockTrade DB.
 */
public class StockTradeJobExecutor {
	private static final Logger LOGGER_OBJ = Logger.getLogger("StockTradeDBCreator.class");
	private static final String CSV = "csv";

	/**
	 * Creates stockTrade records in the database.
	 * 
	 * @throws DBOperationFailureException    when DB operation fails.
	 * @throws DataBaseAccessException        when unable to access the database.
	 * @throws InvalidDBRecordFormatException when the format of the record is
	 *                                        invalid.
	 */
	public void stockTradeExecutor()
			throws DBOperationFailureException, DataBaseAccessException, InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("exection of stockTradeExecutor() method started");
		StockTradeDaoFactory stockTradeDaoFactoryObj = new StockTradeDaoFactory();
		IStockTradeDao stockTradeDaoObj = stockTradeDaoFactoryObj.getInstance(CSV);
		List<StockTrade> stockTradeObjList = stockTradeDaoObj.getStockTradeRecords();
//		StockTradeExecutorThread thread1;
//		StockTradeExecutorThread thread2;
//		StockTradeExecutorThread thread3;
//		StockTradeExecutorThread thread4;
		StockTradeExecutorThread thread=new StockTradeExecutorThread(4);
		//LinkedBlockingQueue<InsertionJob> insertionjobs = new LinkedBlockingQueue<InsertionJob>();
		for (int index = 0; index < stockTradeObjList.size(); index++) {
			
			InsertionJob job = new InsertionJob(stockTradeObjList.get(index));
			thread.insertJob(job);

		}
		thread.start();
//		thread1 = new StockTradeExecutorThread();
//		thread2 = new StockTradeExecutorThread();
		//thread3 = new StockTradeExecutorThread(insertionjobs);
		//thread4 = new StockTradeExecutorThread(insertionjobs);

//		thread1.start();
//		thread2.start();
//		thread3.start();
//		thread4.start();

		LOGGER_OBJ.debug("exection of stockTradeExecutor() method completed");
	}
}