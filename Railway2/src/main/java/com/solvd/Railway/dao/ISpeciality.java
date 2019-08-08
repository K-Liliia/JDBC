package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Speciality;

import java.sql.SQLException;
import java.util.List;

public interface ISpeciality {
    Speciality add(Speciality speciality) throws SQLException, InterruptedException;

    List<Speciality> getAll() throws SQLException, InterruptedException;

    Speciality getById(Long id) throws SQLException, InterruptedException;

    void update(Speciality speciality) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;
}
