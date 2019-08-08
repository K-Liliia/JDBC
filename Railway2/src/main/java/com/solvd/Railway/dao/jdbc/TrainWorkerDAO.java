package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITrainWorker;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.TrainWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainWorkerDAO implements ITrainWorker {
    public static final String getById = "SELECT * FROM TrainWorkers WHERE id = ?";
    public static final String getAll = "SELECT * FROM TrainWorkers";
    public static final String add = "INSERT INTO TrainWorkers VALUES (?,?)";
    public static final String remove = "DELETE FROM TrainWorkers WHERE id =?";
    public static final String update = "UPDATE TrainWorkers SET workerId = ?, trainId = ? WHERE id=?";

    @Override
    public TrainWorker add(TrainWorker trainWorker) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, trainWorker.getId());
            preparedStatement.setLong(2, trainWorker.getWorkerId().getId());
            preparedStatement.setLong(3, trainWorker.getTrainId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return trainWorker;
    }

    @Override
    public List<TrainWorker> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TrainWorker> trainWorkers = new ArrayList<>();

            while (resultSet.next()) {
                TrainWorker trainWorker = new TrainWorker();
                trainWorker.setId(resultSet.getLong("id"));
                trainWorker.getWorkerId().setId(resultSet.getLong("workerId"));
                trainWorker.getTrainId().setId(resultSet.getLong("trainId"));

                trainWorkers.add(trainWorker);

            }

            return trainWorkers;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public TrainWorker getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                TrainWorker station = new TrainWorker();
                station.setId(resultSet.getLong("id"));
                station.getWorkerId().setId(resultSet.getLong("workerId"));
                station.getTrainId().setId(resultSet.getLong("trainId"));

                return station;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(TrainWorker trainWorker) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, trainWorker.getId());
            preparedStatement.setLong(2, trainWorker.getWorkerId().getId());
            preparedStatement.setLong(3, trainWorker.getTrainId().getId());
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
