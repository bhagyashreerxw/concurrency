package com.reactiveworks.stocktrade.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;

/**
 *class for testing the StockTradeDBCreator 
 */
public class StockTradeExecutorServiceTest {
	private static final String CSV = "csv";
	private static Logger LOGGER_OBJ = Logger.getLogger("StockTradeDBCreatorTest.class");
	
	/**
	 * Checks whether the createStockTradeDB() method creates a stocktrade database.
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws DBOperationFailureException when database operation fails.
	 * @throws InvalidDBRecordFormatException when database record is invalid.
	 */
	@Test
	public void stockTradeExecutorVerification() throws DataBaseAccessException, DBOperationFailureException, InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("execution of stockTradeExecutorVerification() started");
		StockTradeDaoFactory stockTradeDaoFactoryObj=new StockTradeDaoFactory();
		int actualSize=stockTradeDaoFactoryObj.getInstance().getDBRecordsCount();
		int expectedSize=stockTradeDaoFactoryObj.getInstance(CSV).getStockTradeRecords().size();		
		assertEquals(expectedSize, actualSize);
		LOGGER_OBJ.debug("execution of stockTradeExecutorVerification() completed");
	}
	
	/**
	 * Checks the working of createStockTradeDB() in negative scenario.
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws DBOperationFailureException when operation on the database fails.
	 * @throws InvalidDBRecordFormatException when format of db record is invalid.
	 */
	@Test
	public void stockTradeExecutorFailureTest() throws DataBaseAccessException, DBOperationFailureException, InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("execution of stockTradeExecutorFailureTest() started");
		StockTradeDaoFactory stockTradeDaoFactoryObj=new StockTradeDaoFactory();
		int actualSize=stockTradeDaoFactoryObj.getInstance().getDBRecordsCount();
		int expectedSize=300;		
		assertFalse(expectedSize==actualSize);
		LOGGER_OBJ.debug("execution of stockTradeExecutorFailureTest() completed");
	}
}