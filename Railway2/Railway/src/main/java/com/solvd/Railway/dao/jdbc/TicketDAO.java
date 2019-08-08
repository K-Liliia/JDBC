package com.solvd.Railway.dao.jdbc;

import com.solvd.Railway.dao.ITicket;
import com.solvd.Railway.dao.connection.MyPool;
import com.solvd.Railway.entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements ITicket {
    public static final String getById = "SELECT * FROM Tickets WHERE id = ?";
    public static final String getAll = "SELECT * FROM Tickets";
    public static final String add = "INSERT INTO Tickets VALUES (?,?,?,?)";
    public static final String remove = "DELETE FROM Tickets WHERE id =?";
    public static final String update = "UPDATE Tickets SET price = ?, linens = ?, passengerId = ?," +
            "trainId = ? WHERE id=?";
    private static final String getAllByIdTrain = "SELECT * FROM Tickets WHERE TrainId = ?";

    @Override
    public Ticket add(Ticket ticket) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setLong(1, ticket.getId());
            preparedStatement.setInt(2, ticket.getPrice());
            preparedStatement.setBoolean(3, ticket.isLinens());
            preparedStatement.setLong(4, ticket.getPassengerId().getId());
            preparedStatement.setLong(5, ticket.getTrainId().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {

        } finally {
            connection.close();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setPrice(resultSet.getInt("price"));
                ticket.setLinens(resultSet.getBoolean("linens"));
                ticket.getPassengerId().setId(resultSet.getLong("passengerId"));
                ticket.getTrainId().setId(resultSet.getLong("trainId"));

                tickets.add(ticket);

            }

            return tickets;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Ticket getById(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setPrice(resultSet.getInt("price"));
                ticket.setLinens(resultSet.getBoolean("linens"));
                ticket.getPassengerId().setId(resultSet.getLong("passengerId"));
                ticket.getTrainId().setId(resultSet.getLong("ticketId"));

                return ticket;
            }
        } catch (SQLException ex) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public void update(Ticket ticket) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setLong(1, ticket.getId());
            preparedStatement.setInt(2, ticket.getPrice());
            preparedStatement.setBoolean(3, ticket.isLinens());
            preparedStatement.setLong(4, ticket.getPassengerId().getId());
            preparedStatement.setLong(5, ticket.getTrainId().getId());
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
    public List<Ticket> getAllFromId(Long id) throws SQLException, InterruptedException {
        Connection connection = MyPool.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAllByIdTrain);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setPrice(resultSet.getInt("price"));
                ticket.setLinens(resultSet.getBoolean("linens"));
                ticket.getPassengerId().setId(resultSet.getLong("passengerId"));
                ticket.getTrainId().setId(resultSet.getLong("trainId"));

                tickets.add(ticket);

            }

            return tickets;

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        return null;
    }


}
