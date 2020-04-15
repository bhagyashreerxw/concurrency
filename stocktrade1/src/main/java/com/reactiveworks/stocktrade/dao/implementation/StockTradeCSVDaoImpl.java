package com.reactiveworks.stocktrade.dao.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.db.exceptions.DBOperationFailureException;
import com.reactiveworks.stocktrade.db.exceptions.DataBaseAccessException;
import com.reactiveworks.stocktrade.db.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.db.exceptions.OperationNotSupportedException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * CSV implementation of StockTradeDao interface.
 */
public class StockTradeCSVDaoImpl implements IStockTradeDao {
	private static final Logger LOGGER_OBJ = Logger.getLogger("StockTradeCSVDaoImpl.class");
	private static final String FILE_NAME = "CISCO.txt";

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
	@Override
	public List<StockTrade> getStockTradeRecords()
			throws DataBaseAccessException, InvalidDBRecordFormatException, DBOperationFailureException {
		LOGGER_OBJ.debug("execution of getStockTradeRecords() method started.");
		File file = new File(getClass().getClassLoader().getResource("CISCO.txt").getFile());

		List<StockTrade> stockTrdList = new ArrayList<StockTrade>();
		try {

			BufferedReader buffReader = new BufferedReader(new FileReader(file));
			String lineStr;

			buffReader.readLine();

			while ((lineStr = buffReader.readLine()) != null) {

				stockTrdList.add(parseStockCSVLine(lineStr));

			}
			buffReader.close();

		} catch (FileNotFoundException fnexp) {
			LOGGER_OBJ.error(FILE_NAME + " file is not found");
			throw new DataBaseAccessException(FILE_NAME + " file is not found", fnexp);
		} catch (IOException ioexp) {
			LOGGER_OBJ.error("not able to read the file " + FILE_NAME);
			throw new DBOperationFailureException("not able to read the file " + FILE_NAME, ioexp);
		}
		LOGGER_OBJ.debug("getStockTradeRecords() method execution completed.");
		return stockTrdList;
	}

	/**
	 * converts one line in csv file to the stockTrade object.
	 * 
	 * @param strLine string in each line of the csv file.
	 * @return the stockTrade object.
	 * @throws InvalidDBRecordFormatException when the format of the stockTrade is
	 *                                        invalid.
	 */
	private StockTrade parseStockCSVLine(String strLine) throws InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("execution of parseStockCSVLine() method started.");
		StockTrade stockTrade = new StockTrade();
		String stockInfo[] = strLine.split(",");
		stockTrade.setSecurity(stockInfo[0]);

		Date date;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(stockInfo[1]);
			stockTrade.setDate(date);
			stockTrade.setOpen(Double.parseDouble(stockInfo[2]));
			stockTrade.setHigh(Double.parseDouble(stockInfo[3]));
			stockTrade.setLow(Double.parseDouble(stockInfo[4]));
			stockTrade.setClose(Double.parseDouble(stockInfo[5]));
			stockTrade.setVolume(Double.parseDouble(stockInfo[6]));
			stockTrade.setAdjClose(Double.parseDouble(stockInfo[7]));
		} catch (ParseException pexp) {
			LOGGER_OBJ.error("date format of the stocktrade is invalid.");
			throw new InvalidDBRecordFormatException("invalid date format.", pexp);
		} catch (NumberFormatException nfexp) {
			LOGGER_OBJ.error("number format of the stocktrade field is invalid.");
			throw new InvalidDBRecordFormatException("invalid number format.", nfexp);
		}
		LOGGER_OBJ.debug("parseStockCSVLine() method execution completed.");
		return stockTrade;

	}

	/**
	 * implementation of IStockTradeDao method createStockTradeRecord().
	 * 
	 * @throws OperationNotSupportedException when createStockTradeRecord() method
	 *                                        is called.
	 */
	@Override
	public void createStockTradeRecord(StockTrade stockTradeObj) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("createStockTradeRecord() method is not supprted");
	}

	/**
	 * finds the number of records in the stocktrade database
	 * 
	 * @return the number of records in the database
	 * @throws DataBaseAccessException     when unable to access the database.
	 * @throws DBOperationFailureException  when DB operation fails.
	 */
	@Override
	public int getDBRecordsCount()
			throws DataBaseAccessException, DBOperationFailureException, InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("getDBRecordsCount() method started it's execution");
		int recordsCount;
		List<StockTrade> stockTradeObjList = getStockTradeRecords();
		recordsCount = stockTradeObjList.size();
		LOGGER_OBJ.debug("getDBRecordsCount() method completed it's execution");
		return recordsCount;

	}

}
