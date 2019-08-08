package com.solvd.Railway.dao;

import com.solvd.Railway.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicket {

    Ticket add(Ticket ticket) throws SQLException, InterruptedException;

    List<Ticket> getAll() throws SQLException, InterruptedException;

    Ticket getById(Long id) throws SQLException, InterruptedException;

    void update(Ticket ticket) throws SQLException, InterruptedException;

    void remove(Long id) throws SQLException, InterruptedException;

    List<Ticket> getAllFromId(Long id) throws SQLException, InterruptedException;

}
