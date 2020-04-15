package com.reactiveworks.stocktrade.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.dao.implementation.StockTradeCSVDaoImpl;
import com.reactiveworks.stocktrade.dao.implementation.StockTradeMysqlDaoImpl;
import com.reactiveworks.stocktrade.db.DBUtil;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;

/**
 * Factory class for StockTradeDao.
 */
public class StockTradeDaoFactory {
	private static final String PROPERTY_FILE = "dbtype.properties";
	private static final Logger LOGGER_OBJ = Logger.getLogger("StockTradeDaoFactory.class");
	private static final String DB_TYPE = "dbtype";
	private static final String CSV = "csv";
	private static final String MYSQL = "mysql";

	public static Properties properties = null;

	/**
	 * Creates the object StockTradeDao implementation class.
	 * 
	 * @return the StockTradeDao implementation class object.
	 * @throws DBOerationFailureException when operation on the database fails.
	 */
	public IStockTradeDao getInstance() throws DBOperationFailureException {

		IStockTradeDao stockTradeDaoObj = null;
		if (properties == null) {
			try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {

				properties = new Properties();
				properties.load(input);

			} catch (IOException exp) {
				LOGGER_OBJ.error("not able to read the properties file " + PROPERTY_FILE);
				throw new DBOperationFailureException("not able to read the file " + PROPERTY_FILE, exp);
			}
		}

		if (((String) properties.get(DB_TYPE)).equalsIgnoreCase(CSV)) {
			stockTradeDaoObj = new StockTradeCSVDaoImpl();
		} else if (((String) properties.get(DB_TYPE)).equalsIgnoreCase(MYSQL)) {
			stockTradeDaoObj = new StockTradeMysqlDaoImpl();
		} else {
			LOGGER_OBJ.debug(properties.get(DB_TYPE) + " implementation does not exist.");
			stockTradeDaoObj = new StockTradeCSVDaoImpl(); // default stockTrade Dao Object
		}

		return stockTradeDaoObj;
	}

	/**
	 * Creates the object StockTradeDao implementation class.
	 * 
	 * @return the StockTradeDao implementation class object.
	 * @throws DBOerationFailureException when operation on the database fails.
	 */
	public IStockTradeDao getInstance(String dbType) throws DBOperationFailureException {

		IStockTradeDao stockTradeDaoObj = null;

		if (dbType.equalsIgnoreCase(CSV)) {
			stockTradeDaoObj = new StockTradeCSVDaoImpl();
		} else if (dbType.equalsIgnoreCase(MYSQL)) {
			stockTradeDaoObj = new StockTradeMysqlDaoImpl();
		} else {
			LOGGER_OBJ.debug(dbType + " implementation does not exist.");
			stockTradeDaoObj = new StockTradeCSVDaoImpl(); // default stockTrade Dao Object
		}

		return stockTradeDaoObj;
	}
	
}