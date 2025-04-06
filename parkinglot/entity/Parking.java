package org.example.entity;

public class Parking {
    Floor[] floors;

    public Parking(Floor[] floors) {
        this.floors = floors;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }
}
