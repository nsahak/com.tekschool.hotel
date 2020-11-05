package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVWriter;

public class DataBaseUtility {

	// Java provide JDBC API for connecting to Database.
	// JDBC : Java Data Base Connectivity
	// 1. JDBC provides a Class named Connection: In order to connect with DataBase.
	// 2. JDBC provides a Class named Statement: In order to executed or send a
	// Query.
	// 3. JDBC provides a Class named Result Set: Result Set will store retrieved
	// data.

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	// JDBC URL, and this URL will connect FrameWork or Java Classes to the DataBase
	// Syntax for pstgresql: (jdbc:postgresql://host:port/databaseName)
	private static String url = "jdbc:postgresql://localhost:5432/dvdrental2";

	// UserName for DataBase
	private static String userName = "postgres";
	// Password for DataBase
	private static String password = "sdet";

	/**
	 * This Method will establish a connection between JAVA and DataBase using
	 * Object of Connection Class and DriverManager.getConnection Method.
	 */
	public static void setupConnection() {

		try {
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method executes query and return outcome or result set as part of
	 * execution result.
	 * 
	 * @Param query
	 * 
	 * @return
	 */
	public static ResultSet runQuery(String query) {

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;

	}

	/**
	 * This method will Write all output data from result set to a CSV File. We are
	 * creating new csv file using file class object We are Writing to that file
	 * using File Writer class.
	 */

	public static void returnResult() {

		try {
			while (resultSet.next()) {
				CSVWriter csvWriter;
				// provide path for output data file
				String output = ".\\output\\book.csv";
				File file = new File(output);

				// we need to use FileWriter class to write ResultSet data from DataBase to
				// CsvFile
				FileWriter fileWriter = new FileWriter(file);
				csvWriter = new CSVWriter(fileWriter);
				csvWriter.writeAll(resultSet, true);
				csvWriter.close();
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method will close connection to DataBase
	 */
	public static void closeConnection() {

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
