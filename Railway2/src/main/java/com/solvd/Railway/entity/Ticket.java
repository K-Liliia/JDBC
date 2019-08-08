package com.solvd.Railway.entity;

public class Ticket {
    private Long id;
    private int price;
    private boolean linens;
    private Passenger passengerId;
    private Train trainId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isLinens() {
        return linens;
    }

    public void setLinens(boolean linens) {
        this.linens = linens;
    }

    public Passenger getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Passenger passengerId) {
        this.passengerId = passengerId;
    }

    public Train getTrainId() {
        return trainId;
    }

    public void setTrainId(Train trainId) {
        this.trainId = trainId;
    }

}
