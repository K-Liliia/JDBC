package com.solvd.Railway.dao;

import com.solvd.Railway.entity.TrainType;

import java.sql.SQLException;
import java.util.List;

public interface ITrainType {
    TrainType add(TrainType trainType) throws SQLException, InterruptedException;

    List<TrainType> getAll() throws SQLException, InterruptedException;

    TrainType getById(Long id) throws SQLException, InterruptedException;

    void update(TrainType trainType) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
