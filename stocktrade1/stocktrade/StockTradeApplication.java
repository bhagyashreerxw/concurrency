package com.reactiveworks.stocktrade;

import com.reactiveworks.stocktrade.service.StockTradeDBCreator;

public class StockTradeApplication {

	public static void main(String[] args)
			throws Exception {
		
		StockTradeDBCreator stockTradeDBCreatorObj=new StockTradeDBCreator();
		stockTradeDBCreatorObj.createStockTradeDB();
	}
}