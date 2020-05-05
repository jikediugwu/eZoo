package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingAnimal;
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

			Class.forName("org.postgresql.Driver");

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
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
				Class.forName("org.postgresql.Driver");

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
				} catch (Exception e) {
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
			Class.forName("org.postgresql.Driver");

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
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
			Class.forName("org.postgresql.Driver");

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
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
					Class.forName("org.postgresql.Driver");

					connection = DriverManager.getConnection(url, username, password);

					statement = connection.createStatement();
					statement.execute(query);

				}

				catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (statement != null)
							statement.close();
						if (connection != null)
							connection.close();
					} catch (Exception e) {
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
					Class.forName("org.postgresql.Driver");

					connection = DriverManager.getConnection(url, username, password);

					statement = connection.createStatement();
					statement.execute(query);

				}

				catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (statement != null)
							statement.close();
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				deleteFeeding(fs.getScheduleID());

			}
		}

	}

	@Override
	public void updateFeeding(long id, FeedingSchedule fs) {

		FeedingScheduleDaoImpl feedingScheduleDaoImpl = new FeedingScheduleDaoImpl();
		FeedingSchedule feedingSchedule = feedingScheduleDaoImpl.getFeeding(id);

		int success = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		if (feedingSchedule.getScheduleID() == id) {

			try {
				Class.forName("org.postgresql.Driver");

				connection = DriverManager.getConnection(url, username, password);

				String query = "UPDATE feeding_schedules SET schedule_id = ?, feeding_time=?, recurrence=?, food=?, notes=? WHERE schedule_id= "
						+ id + "";

				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setLong(1, fs.getScheduleID());
				preparedStatement.setString(2, fs.getFeedingTime());
				preparedStatement.setString(3, fs.getRecurrence());
				preparedStatement.setString(4, fs.getFood());
				preparedStatement.setString(5, fs.getNotes());

				success = preparedStatement.executeUpdate();
				System.out.println(success + " rows updated...");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		if (feedingSchedule.getScheduleID() != id) {
			// inform user feeding schedule not in DB
		}

	}

	public List<FeedingAnimal> showAssignedAnimalFeeding() {

		List<FeedingAnimal> list = new ArrayList<FeedingAnimal>();
		Connection connection = null;
		Statement statement = null;
		String query = "SELECT animalid, name, feeding_schedule, food\n" + "FROM animals\n" + "JOIN Feeding_schedules\n"
				+ "ON animals.feeding_schedule=Feeding_schedules.schedule_id";

		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(url, username, password);

			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				FeedingAnimal feedingAnimal = new FeedingAnimal();

				feedingAnimal.setAnimalID(resultSet.getLong(1));
				feedingAnimal.setAnimalName(resultSet.getString(2));
				feedingAnimal.setScheduleID(resultSet.getLong(3));
				feedingAnimal.setFood(resultSet.getString(4));

				list.add(feedingAnimal);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	public Map<Integer, Integer> getDBView() {
		Map<Integer, Integer> map = new HashMap<>();
		Connection connection = null;
		Statement statement = null;
		String query = "select * from \"viewIDs\"";

		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(url, username, password);

			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int aid = resultSet.getInt(1);
				int afs = resultSet.getInt(2);
				map.put(aid, afs);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return map;
	}

}