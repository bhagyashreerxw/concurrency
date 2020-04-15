package com.reactiveworks.stocktrade.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.db.DBUtil;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * Mysql implementation of StockTradeDao interface.
 */
public class StockTradeMysqlDaoImpl implements IStockTradeDao {
	private static Logger LOGGER_OBJ = Logger.getLogger("StockTradeMysqlDaoImpl.class");
	private static final String INSERT_QUERY = "INSERT INTO stocktrade(security,date,open,high,low,close,volume,adj_close) VALUES(?,?,?,?,?,?,?,?);";
	private static final String GET_SIZE_QUERY = "SELECT COUNT(*) FROM stocktrade;";
	private static final String SELECT_QUERY = "SELECT * FROM stocktrade;";

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
			throws DataBaseAccessException, InvalidDBRecordFormatException, DBOperationFailureException {
		LOGGER_OBJ.debug("execution of getStockTradeRecords() method started.");

		List<StockTrade> stockTradeList = new ArrayList<StockTrade>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {

			connection = DBUtil.getdbconnection();
			statement = connection.prepareStatement(SELECT_QUERY);
			res = statement.executeQuery(SELECT_QUERY);
			while (res.next()) {
				StockTrade stockObj = new StockTrade();
				stockObj.setSecurity(res.getString(2));
				stockObj.setDate(res.getDate(3));
				stockObj.setOpen(res.getDouble(4));
				stockObj.setHigh(res.getDouble(5));
				stockObj.setLow(res.getDouble(6));
				stockObj.setClose(res.getDouble(7));
				stockObj.setVolume(res.getDouble(8));
				stockObj.setAdjClose(res.getDouble(9));
				stockTradeList.add(stockObj);
			}
		} catch (SQLException exp) {
			LOGGER_OBJ.error("unable to access stocktrade database");
			throw new DataBaseAccessException("unable to access stocktrade database" + exp);
		} finally {

			DBUtil.cleanupdbresources(res, statement, connection);

		}

		LOGGER_OBJ.debug("getStockTradeRecords() method execution completed.");
		return stockTradeList;
	}

	/**
	 * Creates the stockTrade record in the database.
	 * 
	 * @param stockTradeObj stockTrade object which has to be inserted into the
	 *                      database.
	 * @throws DataBaseAccessException     when there is problem in accessing the
	 *                                     database.
	 * @throws DBOperationFailureException when operation on database fails.
	 */
	public void createStockTradeRecord(StockTrade stockTradeObj)
			throws DataBaseAccessException, DBOperationFailureException {
		LOGGER_OBJ.debug("execution of createStockTradeRecord() method started.");
		
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBUtil.getdbconnection();
			statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, stockTradeObj.getSecurity());
			java.sql.Date date = new java.sql.Date(stockTradeObj.getDate().getTime());
			statement.setDate(2, date);
			statement.setDouble(3, stockTradeObj.getOpen());
			statement.setDouble(4, stockTradeObj.getHigh());
			statement.setDouble(5, stockTradeObj.getLow());
			statement.setDouble(6, stockTradeObj.getClose());
			statement.setDouble(7, stockTradeObj.getVolume());
			statement.setDouble(8, stockTradeObj.getAdjClose());
			statement.executeUpdate();

		} catch (SQLException exp) {
			LOGGER_OBJ.error("unable to access stocktrade database");
			throw new DataBaseAccessException("unable to access stocktrade database" + exp);
		} finally {

			DBUtil.cleanupdbresources(resultSet, statement, connection);

		}

		LOGGER_OBJ.debug("createStockTradeRecord() method execution completed.");
	}

	/**
	 * finds the number of records in the stocktrade database
	 * 
	 * @return the number of records in the database
	 * @throws DataBaseAccessException
	 * @throws DBOperationFailureException
	 */
	public int getDBRecordsCount() throws DataBaseAccessException, DBOperationFailureException {
		LOGGER_OBJ.debug("execution of getDBRecordsCount() method started.");
		int size;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBUtil.getdbconnection();
			statement = connection.prepareStatement(GET_SIZE_QUERY);
			resultSet = statement.executeQuery();
			resultSet.next();
			size = resultSet.getInt(1);
		} catch (SQLException exp) {
			LOGGER_OBJ.error("unable to access stocktrade database");
			throw new DataBaseAccessException("unable to access stocktrade database" + exp);
		} finally {

			DBUtil.cleanupdbresources(null, statement, connection);
		}

		LOGGER_OBJ.debug("getDBRecordsCount() method execution completed.");
		return size;
	}
	
}