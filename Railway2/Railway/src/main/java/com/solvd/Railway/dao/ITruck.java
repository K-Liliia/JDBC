package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Truck;

import java.sql.SQLException;
import java.util.List;

public interface ITruck {
    Truck add(Truck truck) throws SQLException, InterruptedException;

    List<Truck> getAll() throws SQLException, InterruptedException;

    Truck getById(Long id) throws SQLException, InterruptedException;

    void update(Truck truck) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
