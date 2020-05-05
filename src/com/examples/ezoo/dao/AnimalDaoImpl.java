package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;

public class AnimalDaoImpl implements AnimalDAO {

	@Override
	public List<Animal> getAllAnimals() {
		List<Animal> animals = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM ANIMALS";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Animal a = new Animal();

				a.setAnimalID(rs.getLong("animalid"));
				a.setName(rs.getString("name"));

				a.setTaxKingdom(rs.getString("taxkingdom"));
				a.setTaxPhylum(rs.getString("taxphylum"));
				a.setTaxClass(rs.getString("taxclass"));
				a.setTaxOrder(rs.getString("taxorder"));
				a.setTaxFamily(rs.getString("taxfamily"));
				a.setTaxGenus(rs.getString("taxgenus"));
				a.setTaxSpecies(rs.getString("taxspecies"));

				a.setHeight(rs.getDouble("height"));
				a.setWeight(rs.getDouble("weight"));

				a.setType(rs.getString("type"));
				a.setHealthStatus(rs.getString("healthstatus"));
				a.setScheduleID(rs.getInt("feeding_schedule"));

				animals.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return animals;
	}

	@Override
	public void saveAnimal(Animal animal) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO ANIMALS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			stmt = connection.prepareStatement(sql);

			stmt.setLong(1, animal.getAnimalID());
			stmt.setString(2, animal.getName());

			stmt.setString(3, animal.getTaxKingdom());
			stmt.setString(4, animal.getTaxPhylum());
			stmt.setString(5, animal.getTaxClass());
			stmt.setString(6, animal.getTaxOrder());
			stmt.setString(7, animal.getTaxFamily());
			stmt.setString(8, animal.getTaxGenus());
			stmt.setString(9, animal.getTaxSpecies());

			stmt.setDouble(10, animal.getHeight());
			stmt.setDouble(11, animal.getWeight());

			stmt.setString(12, animal.getType());
			stmt.setString(13, animal.getHealthStatus());
			stmt.setInt(14, animal.getScheduleID());

			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			throw new Exception("Insert animal failed: " + animal);
		}

	}

	public Animal getAnimal(long id) {
		Animal animal = new Animal();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Animals WHERE animalid = ?";

		try {
			Class.forName("org.postgresql.Driver");

			connection = DAOUtilities.getConnection();

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				long animalid = resultSet.getLong(1);
				String name = resultSet.getString(2);
				String kingdom = resultSet.getString(3);
				String phylum = resultSet.getString(4);
				String aclass = resultSet.getString(5);
				String order = resultSet.getString(6);
				String family = resultSet.getString(7);
				String genus = resultSet.getString(8);
				String species = resultSet.getString(9);

				double height = resultSet.getDouble(10);
				double weight = resultSet.getDouble(11);
				String type = resultSet.getString(12);
				String healthStatus = resultSet.getString(13);
				int scheduleID = resultSet.getInt(14);

				animal.setAnimalID(animalid);
				animal.setName(name);
				animal.setTaxKingdom(kingdom);
				animal.setTaxPhylum(phylum);
				animal.setTaxClass(aclass);
				animal.setTaxOrder(order);
				animal.setTaxFamily(family);
				animal.setTaxGenus(genus);
				animal.setTaxSpecies(species);
				animal.setHeight(height);
				animal.setWeight(weight);
				animal.setType(type);
				animal.setHealthStatus(healthStatus);
				animal.setScheduleID(scheduleID);

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

		return animal;

	}
}