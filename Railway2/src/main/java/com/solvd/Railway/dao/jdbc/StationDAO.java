package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.IStation;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAO implements IStation {
    public static final String getById = "SELECT * FROM Stations WHERE id = ?";
    public static final String getAll = "SELECT * FROM Stations";
    public static final String add = "INSERT INTO Stations VALUES (?,?)";
    public static final String remove = "DELETE FROM Stations WHERE id =?";
    public static final String update = "UPDATE Stations SET name = ?, routeId = ? WHERE id=?";
    private static final String getAllByIdRoute = "SELECT * FROM Stations WHERE RouteId = ?";

    @Override
    public Station add(Station station) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, station.getId());
            preparedStatement.setString(2, station.getName());
            preparedStatement.setLong(3, station.getRoute().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return station;
    }

    @Override
    public List<Station> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Station> stations = new ArrayList<>();

            while (resultSet.next()) {
                Station station = new Station();
                station.setId(resultSet.getLong("id"));
                station.setName(resultSet.getString("station_name"));
                station.getRoute().setId(resultSet.getLong("routeId"));

                stations.add(station);

            }

            return stations;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Station getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Station station = new Station();
                station.setId(resultSet.getLong("id"));
                station.setName(resultSet.getString("station_name"));
                station.getRoute().setId(resultSet.getLong("routeId"));

                return station;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Station station) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, station.getId());
            preparedStatement.setString(2, station.getName());
            preparedStatement.setLong(3, station.getRoute().getId());
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
    public List<Station> getAllFromId(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAllByIdRoute);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Station> stations = new ArrayList<>();

            while (resultSet.next()) {
                Station station = new Station();
                station.setId(resultSet.getLong("id"));
                station.setName(resultSet.getString("name"));
                station.getRoute().setId(resultSet.getLong("routeId"));

                stations.add(station);

            }

            return stations;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }
}
