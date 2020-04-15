package com.reactiveworks.stocktrade;

import com.reactiveworks.stocktrade.service.StockTradeJobExecutor;

public class StockTradeApplication {

	public static void main(String[] args) throws Exception {

		StockTradeJobExecutor stockTradeJobExecutorObj = new StockTradeJobExecutor();
		stockTradeJobExecutorObj.stockTradeExecutor();

	}
}