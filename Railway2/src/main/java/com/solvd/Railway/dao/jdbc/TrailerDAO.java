package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITrailer;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Trailer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrailerDAO implements ITrailer {
    public static final String getById = "SELECT * FROM Trailers WHERE id = ?";
    public static final String getAll = "SELECT * FROM Trailers";
    public static final String add = "INSERT INTO Trailers VALUES (?)";
    public static final String remove = "DELETE FROM Trailers WHERE id =?";
    public static final String update = "UPDATE Trailers SET name = ? WHERE id=?";

    @Override
    public Trailer add(Trailer trailer) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, trailer.getId());
            preparedStatement.setString(2, trailer.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return trailer;
    }

    @Override
    public List<Trailer> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trailer> trailers = new ArrayList<>();

            while (resultSet.next()) {
                Trailer trailer = new Trailer();
                trailer.setId(resultSet.getLong("id"));
                trailer.setName(resultSet.getString("name"));

                trailers.add(trailer);

            }

            return trailers;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Trailer getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Trailer trailer = new Trailer();
                trailer.setId(resultSet.getLong("id"));
                trailer.setName(resultSet.getString("name"));

                return trailer;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Trailer trailer) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, trailer.getId());
            preparedStatement.setString(2, trailer.getName());
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
