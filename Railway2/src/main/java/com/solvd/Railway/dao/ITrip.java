package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Trip;

import java.sql.SQLException;
import java.util.List;

public interface ITrip {
    Trip add(Trip trip) throws SQLException, InterruptedException;

    List<Trip> getAll() throws SQLException, InterruptedException;

    Trip getById(Long id) throws SQLException, InterruptedException;

    void update(Trip trip) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;

    List<Trip> getAllFromId(Long id) throws SQLException, InterruptedException;
}
