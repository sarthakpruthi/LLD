package org.example.service;

import org.example.entity.Spot;
import org.example.entity.Vehicle;

public interface SpotManager {
    Spot allocateSpot(int floor, Vehicle vehicle);

    void releaseSpot(Spot vehicle);
}
