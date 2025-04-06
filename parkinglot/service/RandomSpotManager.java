package org.example.service;

import org.example.entity.Floor;
import org.example.entity.Parking;
import org.example.entity.Spot;
import org.example.entity.Vehicle;
import org.example.enums.VehicleType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RandomSpotManager implements SpotManager {
    Map<Integer, ConcurrentHashMap<VehicleType, Queue<Spot>>> floorFreeSpots;

    RandomSpotManager(Parking parking){
        floorFreeSpots = new ConcurrentHashMap<>();
        Floor[] floors = parking.getFloors();
        for(int i=0;i<floors.length;i++) {
            Spot[] spots = floors[i].getSpots();
            floorFreeSpots.put(i, new ConcurrentHashMap<>());
            ConcurrentHashMap<VehicleType, Queue<Spot>> floorSpotMap = floorFreeSpots.get(i);
            for(int j=0;j<spots.length;j++) {
                Spot spot = spots[j];
                if(spot.isOccupied()) continue;
                floorSpotMap.putIfAbsent(spot.getSpotType(), new LinkedList<>());
                floorSpotMap.get(spot.getSpotType()).add(spot);
            }
        }
    }

    Lock lock = new ReentrantLock();

    @Override
    public Spot allocateSpot(int floor, Vehicle vehicle) {
        lock.lock();
        try {
            ConcurrentHashMap<VehicleType, Queue<Spot>> floorSpotMap = floorFreeSpots.getOrDefault(floor, null);
            if(floorSpotMap == null) return null;
            Queue<Spot> spots = floorSpotMap.getOrDefault(vehicle.getType(), null);
            if(spots == null || spots.isEmpty()) return null;
            Spot freeSpot = spots.poll();
            floorSpotMap.get(vehicle.getType()).remove(freeSpot);
            return freeSpot;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void releaseSpot(Spot spot) {
        lock.lock();
        try {
            VehicleType type = spot.getSpotType();
            int floor = spot.getFloor();
            floorFreeSpots.get(floor).get(type).add(spot);
        }
        finally {
            lock.unlock();
        }
    }
}
