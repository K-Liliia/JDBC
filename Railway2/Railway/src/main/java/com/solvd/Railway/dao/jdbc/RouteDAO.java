package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.IRoute;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements IRoute {

    public static final String getById = "SELECT * FROM Route WHERE id = ?";
    public static final String getAll = "SELECT * FROM Route";
    public static final String add = "INSERT INTO Route VALUES (?,?,?)";
    public static final String remove = "DELETE FROM Route WHERE id =?";
    public static final String update = "UPDATE Route SET name = ?, distance = ? WHERE id=?";

    @Override
    public Route add(Route route) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, route.getId());
            preparedStatement.setString(2, route.getName());
            preparedStatement.setLong(3, route.getDistance());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return route;
    }

    @Override
    public List<Route> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Route> routes = new ArrayList<>();

            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getLong("id"));
                route.setName(resultSet.getString("name"));
                route.setDistance(resultSet.getInt("distance"));
                routes.add(route);

            }

            return routes;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Route getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Route passenger = new Route();
                passenger.setId(resultSet.getLong("id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setDistance(resultSet.getInt("distance"));
                return passenger;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }


    @Override
    public void update(Route route) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, route.getId());
            preparedStatement.setString(2, route.getName());
            preparedStatement.setInt(3, route.getDistance());
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
