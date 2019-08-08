package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Trailer;

import java.sql.SQLException;
import java.util.List;

public interface ITrailer {
    Trailer add(Trailer trailer) throws SQLException, InterruptedException;

    List<Trailer> getAll() throws SQLException, InterruptedException;

    Trailer getById(Long id) throws SQLException, InterruptedException;

    void update(Trailer trailer) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
