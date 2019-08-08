package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.IPassenger;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Passenger;

import java.sql.*;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

public class PassengerDAO implements IPassenger {

    public static final String getById = "SELECT * FROM Passenger WHERE id = ?";
    public static final String getAll = "SELECT * FROM Passenger";
    public static final String add = "INSERT INTO Passenger VALUES (?,?)";
    public static final String remove = "DELETE FROM Passenger WHERE id =?";
    public static final String update = "UPDATE Passenger SET name = ?, surname = ? WHERE id=?";

    @Override
    public Passenger add(Passenger passenger) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, passenger.getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getSurname());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return passenger;
    }

    @Override
    public List<Passenger> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Passenger> passengers = new ArrayList<>();

            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong("id"));
                passenger.setName(resultSet.getString("passenger_name"));
                passenger.setSurname(resultSet.getString("passenger_surname"));

                passengers.add(passenger);

            }

            return passengers;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Passenger getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong("id"));
                passenger.setName(resultSet.getString("passenger_name"));
                passenger.setSurname(resultSet.getString("passenger_surname"));

                return passenger;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Passenger passenger) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, passenger.getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
    }

    @Override
    public void remove(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(remove);

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
    }
}
