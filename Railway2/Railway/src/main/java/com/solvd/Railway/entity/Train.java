package com.solvd.Railway.entity;

import java.util.List;

public class Train {
    private Long id;
    private Route routeId;
    private Trailer trailerId;
    private TrainType trainTypeId;
    private List<Ticket> ticketList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public Trailer getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(Trailer trailerId) {
        this.trailerId = trailerId;
    }

    public TrainType getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(TrainType trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

}
