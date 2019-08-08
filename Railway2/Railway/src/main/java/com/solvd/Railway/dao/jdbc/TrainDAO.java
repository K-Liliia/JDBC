package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITrain;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO implements ITrain {
    public static final String getById = "SELECT * FROM Trains WHERE id = ?";
    public static final String getAll = "SELECT * FROM Trains";
    public static final String add = "INSERT INTO Trains VALUES (?,?,?)";
    public static final String remove = "DELETE FROM Trains WHERE id =?";
    public static final String update = "UPDATE Trains SET routeId = ?, trailerId = ?, trainTypeId = ? WHERE id=?";
    private static final String getAllByIdRoute = "SELECT * FROM Trains WHERE RouteId = ?";

    @Override
    public Train add(Train train) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, train.getId());
            preparedStatement.setLong(2, train.getRouteId().getId());
            preparedStatement.setLong(2, train.getTrailerId().getId());
            preparedStatement.setLong(2, train.getTrainTypeId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return train;
    }

    @Override
    public List<Train> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Train> trains = new ArrayList<>();

            while (resultSet.next()) {
                Train train = new Train();
                train.setId(resultSet.getLong("id"));
                train.getRouteId().setId(resultSet.getLong("routeId"));
                train.getTrailerId().setId(resultSet.getLong("trailerId"));
                train.getTrainTypeId().setId(resultSet.getLong("trainTypeId"));

                trains.add(train);

            }

            return trains;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Train getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Train train = new Train();
                train.setId(resultSet.getLong("id"));
                train.getRouteId().setId(resultSet.getLong("routeId"));
                train.getTrailerId().setId(resultSet.getLong("trailerId"));
                train.getTrainTypeId().setId(resultSet.getLong("trainTypeId"));

                return train;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Train train) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, train.getId());
            preparedStatement.setLong(2, train.getRouteId().getId());
            preparedStatement.setLong(3, train.getTrailerId().getId());
            preparedStatement.setLong(4, train.getTrainTypeId().getId());
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
    public List<Train> getAllFromId(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAllByIdRoute);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Train> trains = new ArrayList<>();

            while (resultSet.next()) {
                Train train = new Train();
                train.setId(resultSet.getLong("id"));
                train.getRouteId().setId(resultSet.getLong("routeId"));
                train.getTrailerId().setId(resultSet.getLong("trailerId"));
                train.getTrainTypeId().setId(resultSet.getLong("trainTypeId"));

                trains.add(train);

            }

            return trains;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }
}
