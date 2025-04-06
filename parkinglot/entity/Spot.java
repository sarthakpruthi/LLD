package org.example.entity;

import org.example.enums.VehicleType;

public class Spot {
    int id;
    int floor;
    VehicleType spotType;
    Vehicle vehicle;
    boolean occupied;

    public Spot(int id, int floor, VehicleType type) {
        this.id = id;
        this.floor = floor;
        this.spotType = type;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public void setSpotType(VehicleType spotType) {
        this.spotType = spotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
