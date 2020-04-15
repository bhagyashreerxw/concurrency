package com.reactiveworks.stocktrade;

import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.service.StockTradeExecutorService;

public class StockTradeApplication {

	public static void main(String[] args)
			throws DBOperationFailureException, DataBaseAccessException, InvalidDBRecordFormatException {

		StockTradeExecutorService stockTradeDBCreatorObj = new StockTradeExecutorService();
		stockTradeDBCreatorObj.stockTradeExecutor();
	}
}