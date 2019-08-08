package com.solvd.Railway.service;

import com.solvd.Railway.dao.IRoute;
import com.solvd.Railway.dao.IStation;
import com.solvd.Railway.dao.ITrain;
import com.solvd.Railway.dao.ITrip;
import com.solvd.Railway.dao.jdbc.RouteDAO;
import com.solvd.Railway.dao.jdbc.StationDAO;
import com.solvd.Railway.dao.jdbc.TrainDAO;
import com.solvd.Railway.dao.jdbc.TripDAO;
import com.solvd.Railway.entity.Route;

import java.sql.SQLException;

public class RouteService {
    private IRoute routeDAO = new RouteDAO();
    private ITrain trainDAO = new TrainDAO();
    private ITrip tripDAO = new TripDAO();
    private IStation stationDAO = new StationDAO();

    public Route getRoute(Long id) throws SQLException, InterruptedException {

        Route route = routeDAO.getById(id);
        route.setTrainList(trainDAO.getAllFromId(id));
        route.setTripList(tripDAO.getAllFromId(id));
        route.setStationList(stationDAO.getAllFromId(id));

        return route;
    }


}
