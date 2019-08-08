package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITrip;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDAO implements ITrip {
    public static final String getById = "SELECT * FROM Trips WHERE id = ?";
    public static final String getAll = "SELECT * FROM Trips";
    public static final String add = "INSERT INTO Trips VALUES (?,?)";
    public static final String remove = "DELETE FROM Trips WHERE id =?";
    public static final String update = "UPDATE Trips SET number = ?, routeId = ? WHERE id=?";
    private static final String getAllByIdRoute = "SELECT * FROM Trips WHERE RouteId = ?";

    @Override
    public Trip add(Trip trip) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, trip.getId());
            preparedStatement.setString(2, trip.getNumber());
            preparedStatement.setLong(3, trip.getRouteId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return trip;
    }

    @Override
    public List<Trip> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId(resultSet.getLong("id"));
                trip.setNumber(resultSet.getString("number"));
                trip.getRouteId().setId(resultSet.getLong("routeId"));

                trips.add(trip);

            }

            return trips;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Trip getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId(resultSet.getLong("id"));
                trip.setNumber(resultSet.getString("number"));
                trip.getRouteId().setId(resultSet.getLong("routeId"));

                return trip;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Trip trip) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, trip.getId());
            preparedStatement.setString(2, trip.getNumber());
            preparedStatement.setLong(3, trip.getRouteId().getId());
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

    @Override
    public List<Trip> getAllFromId(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAllByIdRoute);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId(resultSet.getLong("id"));
                trip.setNumber(resultSet.getString("number"));
                trip.getRouteId().setId(resultSet.getLong("routeId"));

                trips.add(trip);

            }

            return trips;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }
}
