package org.example.entity;

import org.example.enums.VehicleType;

public class Vehicle {
    String number;
    VehicleType type;

    public Vehicle(String s, VehicleType type) {
        this.number = s;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
