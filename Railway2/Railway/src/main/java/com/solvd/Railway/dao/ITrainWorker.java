package com.solvd.Railway.dao;

import com.solvd.Railway.entity.TrainWorker;

import java.sql.SQLException;
import java.util.List;

public interface ITrainWorker {
    TrainWorker add(TrainWorker trainWorker) throws SQLException, InterruptedException;

    List<TrainWorker> getAll() throws SQLException, InterruptedException;

    TrainWorker getById(Long id) throws SQLException, InterruptedException;

    void update(TrainWorker trainWorker) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
