package com.solvd.Railway.entity;

import java.util.List;

public class Route {
    private Long id;
    private String name;
    private int distance;
    private List<Train> trainList;
    private List<Trip> tripList;
    private List<Station> stationList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Train> getTrainList() { return trainList; }

    public void setTrainList(List<Train> trainList) { this.trainList = trainList; }

    public List<Trip> getTripList() { return tripList; }

    public void setTripList(List<Trip> tripList) { this.tripList = tripList; }

    public List<Station> getStationList() { return stationList; }

    public void setStationList(List<Station> stationList) { this.stationList = stationList; }

}
