package com.reactiveworks.stocktrade.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.concurrency.InsertionJob;
import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * creates stockTrade DB.
 */
public class StockTradeExecutorService {
	private static Logger LOGGER_OBJ = Logger.getLogger("StockTradeDBCreator.class");
    private static String CSV="csv";
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
		ExecutorService service = Executors.newFixedThreadPool(10);
		StockTradeDaoFactory stockTradeDaoFactoryObj=new StockTradeDaoFactory();
		IStockTradeDao stockTradeDaoObj = stockTradeDaoFactoryObj.getInstance(CSV);
		List<StockTrade> stockTradeObjList = stockTradeDaoObj.getStockTradeRecords();
		
		for (int index = 0; index < stockTradeObjList.size(); index++) {
			InsertionJob job = new InsertionJob(stockTradeObjList.get(index));
			service.submit(job);
		}

		service.shutdown();
		LOGGER_OBJ.debug("exection of stockTradeExecutor() method completed");
	}
}