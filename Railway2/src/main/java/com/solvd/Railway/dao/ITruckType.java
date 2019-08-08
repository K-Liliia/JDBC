package com.solvd.Railway.dao;

import com.solvd.Railway.entity.TruckType;

import java.sql.SQLException;
import java.util.List;

public interface ITruckType {
    TruckType add(TruckType truckType) throws SQLException, InterruptedException;

    List<TruckType> getAll() throws SQLException, InterruptedException;

    TruckType getById(Long id) throws SQLException, InterruptedException;

    void update(TruckType truckType) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
