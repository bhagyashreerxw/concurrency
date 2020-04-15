package com.reactiveworks.stocktrade.db;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.OperationNotSupportedException;
import com.reactiveworks.stocktrade.model.StockTrade;

public class InsertionJob  implements Runnable{

	StockTrade stockTradeObj;
	
	public InsertionJob(StockTrade stockTradeObj) {
		this.stockTradeObj=stockTradeObj;
	}
	
	@Override
	public void run() {
		
		StockTradeDaoFactory stockTradeDaoFactoryObj=new StockTradeDaoFactory();
		try {
			IStockTradeDao stockTradeDaoObj=stockTradeDaoFactoryObj.getStockTradeDaoInstance();
			stockTradeDaoObj.createStockTradeRecord(stockTradeObj);
		} catch (DBOperationFailureException e) {
			e.printStackTrace();
		} catch (DataBaseAccessException e) {
			e.printStackTrace();
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}	
	}
}