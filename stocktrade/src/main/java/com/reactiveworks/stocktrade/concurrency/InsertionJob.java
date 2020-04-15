package com.reactiveworks.stocktrade.concurrency;


import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.OperationNotSupportedException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * Represents the job of insertion of stocktrade data into the database.
 */
public class InsertionJob implements Runnable {
	private static Logger LOGGER_OBJ = Logger.getLogger("InsertionJob.class");
	StockTrade stockTradeObj;

	public InsertionJob(StockTrade stockTradeObj) {
		this.stockTradeObj = stockTradeObj;
	}

	@Override
	public void run() {


		try {
			StockTradeDaoFactory stockTradeDaoFactoryObj=new StockTradeDaoFactory();
			IStockTradeDao stockTradeDaoObj = stockTradeDaoFactoryObj.getInstance();
			stockTradeDaoObj.createStockTradeRecord(stockTradeObj);
		} catch (DBOperationFailureException dbOperationfailExp) {
			LOGGER_OBJ.error("operation on the database failed");
			dbOperationfailExp.printStackTrace();
		} catch (DataBaseAccessException dbAccessExp) {
			LOGGER_OBJ.error("unable to access the database");
			dbAccessExp.printStackTrace();
		} catch (OperationNotSupportedException unSupportedOperationExp) {
			LOGGER_OBJ.error("operation on the database failed");
			unSupportedOperationExp.printStackTrace();
		} 
	}
}