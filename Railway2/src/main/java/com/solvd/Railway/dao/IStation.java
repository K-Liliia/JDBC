package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Station;

import java.sql.SQLException;
import java.util.List;

public interface IStation {
    Station add(Station station) throws SQLException, InterruptedException;

    List<Station> getAll() throws SQLException, InterruptedException;

    Station getById(Long id) throws SQLException, InterruptedException;

    void update(Station station) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;

    List<Station> getAllFromId(Long id) throws SQLException, InterruptedException;
}
