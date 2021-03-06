/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.core;

import java.sql.*;
import samples.util.DebugUtil;

/**
 *
 * @author Pathos
 */
public class SqlDatabase {

	private static SqlDatabase instance;
	private Connection connection;
	private String databaseName = "tsn_prototype";
	private String username = "root";
	private String password = "";
	private String host = "localhost:3306";

	/**
	 * Makes the constructor private to enable the singleton pattern. The
	 * constructor loads the sql driver.
	 */
	private SqlDatabase() {
		try {
			// load driver
			Class.forName ("com.mysql.jdbc.Driver").newInstance(); 
		} catch (InstantiationException ex) {
			DebugUtil.log(ex);
		} catch (IllegalAccessException ex) {
			DebugUtil.log(ex);
		} catch (ClassNotFoundException ex) {
			DebugUtil.log(ex);
		}
	}
	
	/**
	 * Returns the connection.
	 * If the connection is closed, a new one is opened.
	 * 
	 * @return the connection
	 */
	protected Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + databaseName, username, password);
		}
			
		return connection;
	}

	/**
	 * Returns the current instance of the database object. If there is none,
	 * one will be created and returned.
	 *
	 * @return the database object
	 */
	public static SqlDatabase getInstance() {
		if (instance == null) {
			instance = new SqlDatabase();
		}

		return instance;
	}

	/**
	 * Returns a result set after executing that query.
	 *
	 * @param query the executing query
	 * @return the result set
	 */
	public ResultSet fetch(String query) throws SQLException {
		Statement statement = getConnection().createStatement();
		return statement.executeQuery(query);
	}
	
	/**
	 * Returns a result set after executing the prepared statement.
	 * 
	 * @param query the executing query
	 * @param params the parameters, used for the prepared statement
	 * @return the result set
	 */
	public ResultSet fetch(String query, String... params) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement(query);
		
		// set parameters
		if (params != null) {
			for (int i = 0; i < params.length; ++i) {
				statement.setString(i+1, params[i]);
			}
		}
		
		// return result list
		return statement.executeQuery();
	}

	/**
	 * Executes a manipulating query, e.g. containing INSERT, UPDATE or DELETE
	 * statements.
	 *
	 * @param query the query
	 * @param params the parameters, used for the prepared statement
	 * @return the last inserted key.
	 */
	public int execute(String query, Object... params) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		// set parameters
		if (params != null) {
			for (int i = 0; i < params.length; ++i) {
				if (params[i] instanceof String) statement.setString(i+1, (String) params[i]);
				else statement.setObject(i+1, params[i]);
			}
		}

		// execute statement
		statement.executeUpdate();
		
		// fetch keys and return first one
		ResultSet keys = statement.getGeneratedKeys();
		if (keys.first()) return keys.getInt(1);
		return -1;
	}
}
