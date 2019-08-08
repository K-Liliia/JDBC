package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Worker;

import java.sql.SQLException;
import java.util.List;

public interface IWorker {
    Worker add(Worker worker) throws SQLException, InterruptedException;

    List<Worker> getAll() throws SQLException, InterruptedException;

    Worker getById(Long id) throws SQLException, InterruptedException;

    void update(Worker worker) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
