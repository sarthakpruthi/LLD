package org.example.service;

import org.example.entity.Parking;
import org.example.entity.Spot;
import org.example.entity.Vehicle;
import org.example.response.ParkingResult;

import java.util.concurrent.ConcurrentHashMap;

public class ParkingManager {
    Parking parking;
    SpotManager spotManager;
    ConcurrentHashMap<String, Spot> vehicleSpot;
    int floor;

    public ParkingManager(Parking parking) {
        this.parking = parking;
        this.floor = parking.getFloors().length;
        vehicleSpot = new ConcurrentHashMap<>();
        spotManager = new RandomSpotManager(parking);
    }

    public ParkingResult parkVehicle(Vehicle vehicle) {
        for(int i=0; i<floor; i++){
            Spot freeSpot = spotManager.allocateSpot(i, vehicle);
            if(freeSpot != null) {
                vehicleSpot.put(vehicle.getNumber(), freeSpot);
                freeSpot.setVehicle(vehicle);
                freeSpot.setOccupied(true);
                return new ParkingResult("201", vehicle.getNumber(), "t>" + freeSpot.getFloor() + "-" + freeSpot.getId());
            }
        }
        return new ParkingResult("400",null, null);
    }

    public void unparkVehicle(Vehicle vehicle) {
        Spot spot = vehicleSpot.get(vehicle.getNumber());
        spotManager.releaseSpot(spot);
        spot.setOccupied(false);
        spot.setVehicle(null);
    }
}
