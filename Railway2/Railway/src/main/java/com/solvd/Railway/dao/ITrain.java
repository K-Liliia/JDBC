package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Ticket;
import com.solvd.Railway.entity.Train;

import java.sql.SQLException;
import java.util.List;

public interface ITrain {
    Train add(Train train) throws SQLException, InterruptedException;

    List<Train> getAll() throws SQLException, InterruptedException;

    Train getById(Long id) throws SQLException, InterruptedException;

    void update(Train train) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;

    List<Train> getAllFromId(Long id) throws SQLException, InterruptedException;
}
