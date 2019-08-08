package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITrainType;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.TrainType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainTypeDAO implements ITrainType {
    public static final String getById = "SELECT * FROM TrainTypes WHERE id = ?";
    public static final String getAll = "SELECT * FROM TrainTypes";
    public static final String add = "INSERT INTO TrainTypes VALUES (?)";
    public static final String remove = "DELETE FROM TrainTypes WHERE id =?";
    public static final String update = "UPDATE TrainTypes SET name = ? WHERE id=?";

    @Override
    public TrainType add(TrainType trainType) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, trainType.getId());
            preparedStatement.setString(2, trainType.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return trainType;
    }

    @Override
    public List<TrainType> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TrainType> trainTypes = new ArrayList<>();

            while (resultSet.next()) {
                TrainType trainType = new TrainType();
                trainType.setId(resultSet.getLong("id"));
                trainType.setName(resultSet.getString("trainType"));

                trainTypes.add(trainType);

            }

            return trainTypes;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public TrainType getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                TrainType trainType = new TrainType();
                trainType.setId(resultSet.getLong("id"));
                trainType.setName(resultSet.getString("trainType"));

                return trainType;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(TrainType trainType) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, trainType.getId());
            preparedStatement.setString(2, trainType.getName());
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
