package com.examples.ezoo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.FeedingSchedule;

class FeedingScheduleDAOImplTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		createTable();
	}

	@AfterEach
	void tearDown() throws Exception {
		dropTable();
	}

	@Test
	void testAddFeeding() {

	}

	private void createTable() {

		String url = "jdbc:postgresql://localhost:5432/eZoo";
		String username = "jikediugwu";
		String password = "gringo007";
		String create = " CREATE TABLE Test_Table (\n" + "    schedule_ID int not null PRIMARY Key,\n"
				+ "    feeding_time varchar (255),\n" + "    recurrence varchar (255),\n" + "    food varchar (255),\n"
				+ "    notes varchar (255)\n" + ");";

		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			statement.execute(create);

			connection.close();
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void delete(long id) {

		String url = "jdbc:postgresql://localhost:5432/eZoo";
		String username = "jikediugwu";
		String password = "gringo007";

		FeedingScheduleDaoImpl feedingScheduleDaoImpl = new FeedingScheduleDaoImpl();
		FeedingSchedule feedingSchedule = feedingScheduleDaoImpl.getFeeding(id);
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		if (feedingSchedule.getScheduleID() == id) {

			String query = "DELETE FROM FEEDING_SCHEDULES WHERE SCHEDULE_ID = ?";

			try {

				connection = DriverManager.getConnection(url, username, password);

				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setLong(1, id);

				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void dropTable() {

		String url = "jdbc:postgresql://localhost:5432/eZoo";
		String username = "jikediugwu";
		String password = "gringo007";
		String drop = "drop table test_table;";

		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			statement.execute(drop);

			connection.close();
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}