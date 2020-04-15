package com.reactiveworks.stocktrade.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.db.InsertionJob;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * creates stockTrade DB.
 */
public class StockTradeDBCreator {
	private static Logger LOGGER_OBJ = Logger.getLogger("StockTradeDBCreator.class");

	/**
	 * Creates stockTrade records in the database.
	 * 
	 * @throws DBOperationFailureException    when DB operation fails.
	 * @throws DataBaseAccessException        when unable to access the database.
	 * @throws InvalidDBRecordFormatException when the format of the record is
	 *                                        invalid.
	 */
	public void createStockTradeDB()
			throws DBOperationFailureException, DataBaseAccessException, InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("exection of createStockTradeDB() method started");
		ExecutorService service = Executors.newFixedThreadPool(10);
		StockTradeDaoFactory stockTradeFactoryObj = new StockTradeDaoFactory();
		IStockTradeDao stockTradeDaoObj = stockTradeFactoryObj.getStockTradeDaoInstance();
		List<StockTrade> stockTradeObjList = stockTradeDaoObj.getStockTradeRecords();
		InsertionJob insertionjobs[] = new InsertionJob[stockTradeObjList.size()];

		for (int index = 0; index < stockTradeObjList.size(); index++) {
			InsertionJob job = new InsertionJob(stockTradeObjList.get(index));
			insertionjobs[index] = job;
		}

		for (InsertionJob job : insertionjobs) {
			service.submit(job);
		}

		service.shutdown();
		LOGGER_OBJ.debug("exection of createStockTradeDB() method completed");

	}

}
