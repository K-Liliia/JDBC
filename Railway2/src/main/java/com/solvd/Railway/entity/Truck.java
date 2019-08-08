package com.solvd.Railway.entity;

public class Truck {
    private Long id;
    private int seatsAmount;
    private TruckType truckTypeId;
    private Train trainId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(int seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    public TruckType getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(TruckType truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Train getTrainId() {
        return trainId;
    }

    public void setTrainId(Train trainId) {
        this.trainId = trainId;
    }
}
