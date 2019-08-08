package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITruck;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Truck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TruckDAO implements ITruck {
    public static final String getById = "SELECT * FROM Trucks WHERE id = ?";
    public static final String getAll = "SELECT * FROM Trucks";
    public static final String add = "INSERT INTO Trucks VALUES (?,?,?)";
    public static final String remove = "DELETE FROM Trucks WHERE id =?";
    public static final String update = "UPDATE Trucks SET seatsAmount = ?, truckTypeId = ?, trainId = ? WHERE id=?";

    @Override
    public Truck add(Truck truck) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, truck.getId());
            preparedStatement.setInt(2, truck.getSeatsAmount());
            preparedStatement.setLong(3, truck.getTruckTypeId().getId());
            preparedStatement.setLong(4, truck.getTrainId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return truck;
    }

    @Override
    public List<Truck> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Truck> trucks = new ArrayList<>();

            while (resultSet.next()) {
                Truck truck = new Truck();
                truck.setId(resultSet.getLong("id"));
                truck.setSeatsAmount(resultSet.getInt("seatsAmount"));
                truck.getTruckTypeId().setId(resultSet.getLong("truckTypeId"));
                truck.getTrainId().setId(resultSet.getLong("trainId"));

                trucks.add(truck);

            }

            return trucks;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Truck getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Truck truck = new Truck();
                truck.setId(resultSet.getLong("id"));
                truck.setSeatsAmount(resultSet.getInt("seatsAmount"));
                truck.getTruckTypeId().setId(resultSet.getLong("truckTypeId"));
                truck.getTrainId().setId(resultSet.getLong("trainId"));

                return truck;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Truck truck) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, truck.getId());
            preparedStatement.setInt(2, truck.getSeatsAmount());
            preparedStatement.setLong(3, truck.getTruckTypeId().getId());
            preparedStatement.setLong(4, truck.getTrainId().getId());
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
