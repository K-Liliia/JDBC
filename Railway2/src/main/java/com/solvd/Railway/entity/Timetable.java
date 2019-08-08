package com.solvd.Railway.entity;

public class Timetable {
    private Long id;
    private int railwayNumber;
    private String departureDate;
    private Route routeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRailwayNumber() {
        return railwayNumber;
    }

    public void setRailwayNumber(int railwayNumber) {
        this.railwayNumber = railwayNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

}
