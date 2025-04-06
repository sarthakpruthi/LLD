package org.example.entity;

public class Floor {
    Spot[] spots;

    public Floor(Spot[] spots) {
        this.spots = spots;
    }

    public Spot[] getSpots() {
        return spots;
    }

    public void setSpot(Spot[] spots) {
        this.spots = spots;
    }
}
