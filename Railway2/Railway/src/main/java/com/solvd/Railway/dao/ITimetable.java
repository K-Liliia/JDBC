package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Timetable;

import java.sql.SQLException;
import java.util.List;

public interface ITimetable {
    Timetable add(Timetable timetable) throws SQLException, InterruptedException;

    List<Timetable> getAll() throws SQLException, InterruptedException;

    Timetable getById(Long id) throws SQLException, InterruptedException;

    void update(Timetable timetable) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
