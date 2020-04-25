package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDaoImpl implements FeedingScheduleDAO<FeedingSchedule, Animal> {

	private String url = "jdbc:postgresql://localhost:5432/eZoo";
	private String username = "jikediugwu";
	private String password = "gringo007";

	@Override
	public void addFeeding(FeedingSchedule feedingSchedule) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int success = 0;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO FEEDING_SCHEDULES VALUES (?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setLong(1, feedingSchedule.getScheduleID());
			preparedStatement.setString(2, feedingSchedule.getFeedingTime());
			preparedStatement.setString(3, feedingSchedule.getRecurrence());
			preparedStatement.setString(4, feedingSchedule.getFood());
			preparedStatement.setString(5, feedingSchedule.getNotes());

			success = preparedStatement.executeUpdate();
			System.out.println(success + " rows added...");
		} catch (SQLException e) {
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

		if (success == 0) {
			System.out.println(new Exception("Insert feedingSchedule failed: " + feedingSchedule));
		}
	}

	@Override
	public void deleteFeeding(long id) {

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

	@Override
	public List<FeedingSchedule> getAllFeeding() {
		List<FeedingSchedule> allFeedingSchedule = new ArrayList<FeedingSchedule>();
		Connection connection = null;
		Statement statement = null;
		String query = "SELECT * FROM FEEDING_SCHEDULES";

		try {
			connection = DriverManager.getConnection(url, username, password);

			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				FeedingSchedule feedingSchedule = new FeedingSchedule();

				feedingSchedule.setScheduleID(resultSet.getLong(1));
				feedingSchedule.setFeedingTime(resultSet.getString(2));
				feedingSchedule.setRecurrence(resultSet.getString(3));
				feedingSchedule.setFood(resultSet.getString(4));
				feedingSchedule.setNotes(resultSet.getString(5));
				allFeedingSchedule.add(feedingSchedule);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return allFeedingSchedule;
	}

	@Override
	public FeedingSchedule getFeeding(long id) {
		FeedingSchedule feedingSchedule = new FeedingSchedule();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM FEEDING_SCHEDULES WHERE SCHEDULE_ID = ?";

		try {
			connection = DriverManager.getConnection(url, username, password);

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				long scheduleID = resultSet.getLong(1);
				String feedingTime = resultSet.getString(2);
				String recurrence = resultSet.getString(3);
				String food = resultSet.getString(4);
				String notes = resultSet.getString(5);

				feedingSchedule.setScheduleID(scheduleID);
				feedingSchedule.setFeedingTime(feedingTime);
				feedingSchedule.setRecurrence(recurrence);
				feedingSchedule.setFood(food);
				feedingSchedule.setNotes(notes);

			}
		} catch (SQLException e) {
			e.printStackTrace();
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

		return feedingSchedule;

	}

	@Override
	public void assignAnimalFeeding(FeedingSchedule fs, Animal animal) {

		if (fs instanceof FeedingSchedule && fs != null && animal instanceof Animal && animal != null) {

			FeedingSchedule f = new FeedingScheduleDaoImpl().getFeeding(fs.getScheduleID());

			if (f != null) {

				Connection connection = null;
				Statement statement = null;

				String query = "UPDATE animals SET feeding_schedule = " + fs.getScheduleID() + "WHERE animalid= "
						+ animal.getAnimalID() + "";

				try {
					connection = DriverManager.getConnection(url, username, password);

					statement = connection.createStatement();
					statement.execute(query);

				}

				catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (statement != null)
							statement.close();
						if (connection != null)
							connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

	@Override
	public void unassignAnimalFeeding(FeedingSchedule fs, Animal animal) {

		if (fs instanceof FeedingSchedule && fs != null && animal instanceof Animal && animal != null) {

			FeedingSchedule f = new FeedingScheduleDaoImpl().getFeeding(fs.getScheduleID());

			if (f != null) {

				Connection connection = null;
				Statement statement = null;

				String query = "UPDATE animals SET feeding_schedule = null WHERE animalid= " + animal.getAnimalID()
						+ "";

				try {
					connection = DriverManager.getConnection(url, username, password);

					statement = connection.createStatement();
					statement.execute(query);

				}

				catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (statement != null)
							statement.close();
						if (connection != null)
							connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				deleteFeeding(fs.getScheduleID());

			}
		}

	}

}