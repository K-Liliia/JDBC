package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITruckType;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.TruckType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TruckTypeDAO implements ITruckType {
    public static final String getById = "SELECT * FROM TruckTypes WHERE id = ?";
    public static final String getAll = "SELECT * FROM TruckTypes";
    public static final String add = "INSERT INTO TruckTypes VALUES (?)";
    public static final String remove = "DELETE FROM TruckTypes WHERE id =?";
    public static final String update = "UPDATE TruckTypes SET name = ? WHERE id=?";

    @Override
    public TruckType add(TruckType truckType) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, truckType.getId());
            preparedStatement.setString(2, truckType.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return truckType;
    }

    @Override
    public List<TruckType> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TruckType> truckTypes = new ArrayList<>();

            while (resultSet.next()) {
                TruckType truckType = new TruckType();
                truckType.setId(resultSet.getLong("id"));
                truckType.setName(resultSet.getString("name"));

                truckTypes.add(truckType);

            }

            return truckTypes;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public TruckType getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                TruckType truckType = new TruckType();
                truckType.setId(resultSet.getLong("id"));
                truckType.setName(resultSet.getString("name"));

                return truckType;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(TruckType truckType) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, truckType.getId());
            preparedStatement.setString(2, truckType.getName());
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
