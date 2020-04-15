package com.reactiveworks.stocktrade.dao;

import java.util.List;

import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.db.exceptions.OperationNotSupportedException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * Interface for StockTradeDao implementation.
 */
public interface IStockTradeDao {

	/**
	 * Reads the information from the CSV file and creates the list of stockTrade
	 * objects.
	 * 
	 * @return the list of stockTrade objects.
	 * @throws DataBaseAccessException        when there is problem in accessing the
	 *                                        CSV file.
	 * @throws InvalidDBRecordFormatException when the format of the CSV record is
	 *                                        invalid.
	 * @throws DBOperationFailureException    when operation on csv file fails.
	 */

	public List<StockTrade> getStockTradeRecords()
			throws DataBaseAccessException, InvalidDBRecordFormatException, DBOperationFailureException;

	/**
	 * Creates the stockTrade record in the database.
	 * 
	 * @param stockTradeObj stockTrade object which has to be inserted into the
	 *                      database.
	 * @throws DataBaseAccessException        when there is problem in accessing the
	 *                                        database.
	 * @throws DBOperationFailureException    when there is problem while operating
	 *                                        on the database.
	 * @throws OperationNotSupportedException when the operation is not supported by
	 *                                        the database.
	 */
	public void createStockTradeRecord(StockTrade stockTradeObj)
			throws DataBaseAccessException, DBOperationFailureException, OperationNotSupportedException;

	/**
	 * finds the number of records in the stocktrade database
	 * 
	 * @return the number of records in the database
	 * @throws DataBaseAccessException     when unable to access the database.
	 * @throws DBOperationFailureException when the database operation fails.
	 */
	public int getDBRecordsCount()
			throws DataBaseAccessException, DBOperationFailureException, InvalidDBRecordFormatException;
}
