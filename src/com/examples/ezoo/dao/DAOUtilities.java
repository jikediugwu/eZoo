package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to retrieve DAO Implementations. Serves as a factory.
 * 
 * @author anon
 *
 */
public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "postgres";
	private static final String CONNECTION_PASSWORD = "gringo007";
	private static final String URL = "jdbc:postgresql://localhost:5432/eZoo";

	private static AnimalDaoImpl animalDaoImpl;
	private static Connection connection;

	public static synchronized AnimalDAO getAnimalDao() {

		if (animalDaoImpl == null) {
			animalDaoImpl = new AnimalDaoImpl();
		}
		return animalDaoImpl;
	}

	static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}

		if (connection.isClosed()) {
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
}