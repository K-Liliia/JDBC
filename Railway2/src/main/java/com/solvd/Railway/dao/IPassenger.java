package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface IPassenger {
    Passenger add(Passenger passenger) throws SQLException, InterruptedException;

    List<Passenger> getAll() throws SQLException, InterruptedException;

    Passenger getById(Long id) throws SQLException, InterruptedException;

    void update(Passenger passenger) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
