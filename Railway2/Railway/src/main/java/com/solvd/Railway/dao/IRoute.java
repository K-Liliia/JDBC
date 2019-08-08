package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Route;

import java.sql.SQLException;
import java.util.List;

public interface IRoute {
    Route add(Route route) throws SQLException, InterruptedException;

    List<Route> getAll() throws SQLException, InterruptedException;

    Route getById(Long id) throws SQLException, InterruptedException;

    void update(Route route) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
