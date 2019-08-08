package com.solvd.Railway.service;

import com.solvd.Railway.dao.ITicket;
import com.solvd.Railway.dao.ITrailer;
import com.solvd.Railway.dao.ITrain;
import com.solvd.Railway.dao.ITrainType;
import com.solvd.Railway.dao.jdbc.*;
import com.solvd.Railway.entity.Train;

import java.sql.SQLException;

public class TrainService {
    private ITrain trainDAO = new TrainDAO();
    private ITicket ticketDAO = new TicketDAO();
    private ITrainType trainTypeDAO = new TrainTypeDAO();
    private ITrailer trailerDAO = new TrailerDAO();

    public Train getTrain(Long id) throws SQLException, InterruptedException {

        Train train = trainDAO.getById(id);
        train.setTrainTypeId(trainTypeDAO.getById(train.getTrainTypeId().getId()));
        train.setTrailerId(trailerDAO.getById(train.getTrailerId().getId()));
        train.setTicketList(ticketDAO.getAllFromId(id));
        return train;
    }

}
