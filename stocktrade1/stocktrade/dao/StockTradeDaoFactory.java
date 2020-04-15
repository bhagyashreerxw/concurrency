package com.reactiveworks.stocktrade.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.reactiveworks.stocktrade.dao.implementation.StockTradeMysqlDaoImpl;
import com.reactiveworks.stocktrade.db.DBUtil;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;

/**
 * Factory class for StockTradeDao.
 */
public class StockTradeDaoFactory {
	private static final String PROPERTY_FILE = "dbtype.properties";
	private static final Logger LOGGER_OBJ = Logger.getLogger("StockTradeDaoFactory.class");

	public static Properties properties = null;

	/**
	 * Creates the object StockTradeDao implementation class.
	 * 
	 * @return the StockTradeDao implementation class object.
	 * @throws DBOerationFailureException when operation on the database fails.
	 */
	public IStockTradeDao getStockTradeDaoInstance() throws DBOperationFailureException {

		if (properties == null) {
			try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {

				properties = new Properties();
				properties.load(input);

			} catch (IOException exp) {
				LOGGER_OBJ.error("not able to read the properties file " + PROPERTY_FILE);
				throw new DBOperationFailureException("not able to read the file " + PROPERTY_FILE, exp);
			}
		}

		return new StockTradeMysqlDaoImpl();
	}

}