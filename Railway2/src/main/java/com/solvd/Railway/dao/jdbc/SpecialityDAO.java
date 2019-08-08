package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ISpeciality;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Speciality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAO implements ISpeciality {
    public static final String getById = "SELECT * FROM Speciality WHERE id = ?";
    public static final String getAll = "SELECT * FROM Speciality";
    public static final String add = "INSERT INTO Speciality VALUES (?)";
    public static final String remove = "DELETE FROM Speciality WHERE id =?";
    public static final String update = "UPDATE Speciality SET name = ? WHERE id=?";


    @Override
    public Speciality add(Speciality speciality) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, speciality.getId());
            preparedStatement.setString(2, speciality.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return speciality;
    }

    @Override
    public List<Speciality> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Speciality> specialities = new ArrayList<>();

            while (resultSet.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(resultSet.getLong("id"));
                speciality.setName(resultSet.getString("name"));

                specialities.add(speciality);

            }

            return specialities;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Speciality getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(resultSet.getLong("id"));
                speciality.setName(resultSet.getString("name"));

                return speciality;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Speciality speciality) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, speciality.getId());
            preparedStatement.setString(2, speciality.getName());
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
