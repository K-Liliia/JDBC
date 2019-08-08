package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITimetable;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO implements ITimetable {
    public static final String getById = "SELECT * FROM Timetables WHERE id = ?";
    public static final String getAll = "SELECT * FROM Timetables";
    public static final String add = "INSERT INTO Timetables VALUES (?,?,?)";
    public static final String remove = "DELETE FROM Timetables WHERE id =?";
    public static final String update = "UPDATE Timetables SET railwayNumber = ?, departureDate = ?, routeId = ?" +
            " WHERE id=?";

    @Override
    public Timetable add(Timetable timetable) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, timetable.getId());
            preparedStatement.setInt(2, timetable.getRailwayNumber());
            preparedStatement.setString(3, timetable.getDepartureDate());
            preparedStatement.setLong(3, timetable.getRouteId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return timetable;
    }

    @Override
    public List<Timetable> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Timetable> timetables = new ArrayList<>();

            while (resultSet.next()) {
                Timetable timetable = new Timetable();
                timetable.setId(resultSet.getLong("id"));
                timetable.setRailwayNumber(resultSet.getInt("railwayNumber"));
                timetable.setDepartureDate(resultSet.getString("departureDate"));
                timetable.getRouteId().setId(resultSet.getLong("routeId"));

                timetables.add(timetable);

            }

            return timetables;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Timetable getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Timetable timetable = new Timetable();
                timetable.setId(resultSet.getLong("id"));
                timetable.setRailwayNumber(resultSet.getInt("railwayNumber"));
                timetable.setDepartureDate(resultSet.getString("departureDate"));
                timetable.getRouteId().setId(resultSet.getLong("routeId"));

                return timetable;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Timetable timetable) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, timetable.getId());
            preparedStatement.setInt(2, timetable.getRailwayNumber());
            preparedStatement.setString(3, timetable.getDepartureDate());
            preparedStatement.setLong(3, timetable.getRouteId().getId());
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
