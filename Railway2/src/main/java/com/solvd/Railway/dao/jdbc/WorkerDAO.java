package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.IWorker;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO implements IWorker {
    public static final String getById = "SELECT * FROM Workers WHERE id = ?";
    public static final String getAll = "SELECT * FROM Workers";
    public static final String add = "INSERT INTO Workers VALUES (?,?,?,?)";
    public static final String remove = "DELETE FROM Workers WHERE id =?";
    public static final String update = "UPDATE Workers SET name = ?, surname = ?, salary = ?," +
            "specialityId = ? WHERE id=?";

    @Override
    public Worker add(Worker worker) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, worker.getId());
            preparedStatement.setString(2, worker.getName());
            preparedStatement.setString(3, worker.getSurname());
            preparedStatement.setInt(4, worker.getSalary());
            preparedStatement.setLong(5, worker.getSpecialityId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return worker;

    }

    @Override
    public List<Worker> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Worker> workers = new ArrayList<>();

            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setId(resultSet.getLong("id"));
                worker.setName(resultSet.getString("name"));
                worker.setSurname(resultSet.getString("surname"));
                worker.setSalary(resultSet.getInt("salary"));
                worker.getSpecialityId().setId(resultSet.getLong("specialityId"));

                workers.add(worker);

            }

            return workers;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Worker getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Worker station = new Worker();
                station.setId(resultSet.getLong("id"));
                station.setName(resultSet.getString("name"));
                station.setSurname(resultSet.getString("surname"));
                station.setSalary(resultSet.getInt("salary"));
                station.getSpecialityId().setId(resultSet.getLong("specialityId"));

                return station;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Worker worker) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, worker.getId());
            preparedStatement.setString(2, worker.getName());
            preparedStatement.setString(3, worker.getSurname());
            preparedStatement.setInt(4, worker.getSalary());
            preparedStatement.setLong(5, worker.getSpecialityId().getId());
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