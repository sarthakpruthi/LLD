package org.example;

import org.example.entity.*;
import org.example.enums.VehicleType;
import org.example.response.ParkingResult;
import org.example.service.ParkingManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Create floors with spots
        int totalFloors = 2;
        int spotsPerFloor = 5;
        Floor[] floors = new Floor[totalFloors];

        for (int i = 0; i < totalFloors; i++) {
            Spot[] spots = new Spot[spotsPerFloor];
            for (int j = 0; j < spotsPerFloor; j++) {
                VehicleType type = (j % 2 == 0) ? VehicleType.TWO : VehicleType.FOUR;
                spots[j] = new Spot(j, i, type);
            }
            floors[i] = new Floor(spots);
        }

        // Create Parking object
        Parking parking = new Parking(floors);

        // Create ParkingManager with the parking object
        ParkingManager parkingManager = new ParkingManager(parking);

        // Create multiple vehicles
        Vehicle bike1 = new Vehicle("KA-01-1234", VehicleType.TWO);
        Vehicle bike2 = new Vehicle("KA-01-5678", VehicleType.TWO);
        Vehicle car1 = new Vehicle("KA-02-9101", VehicleType.FOUR);
        Vehicle car2 = new Vehicle("KA-02-1122", VehicleType.FOUR);

        // Multi-threaded parking
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable parkBike1 = () -> checkParkingResult(parkingManager.parkVehicle(bike1), "Bike1");
        Runnable parkBike2 = () -> checkParkingResult(parkingManager.parkVehicle(bike2), "Bike2");
        Runnable parkCar1 = () -> checkParkingResult(parkingManager.parkVehicle(car1), "Car1");
        Runnable parkCar2 = () -> checkParkingResult(parkingManager.parkVehicle(car2), "Car2");

        executor.execute(parkBike1);
        executor.execute(parkBike2);
        executor.execute(parkCar1);
        executor.execute(parkCar2);

        executor.shutdown();
        while (!executor.isTerminated()) {}

        // Multi-threaded unparking
        ExecutorService unparkExecutor = Executors.newFixedThreadPool(2);

        Runnable unparkBike1 = () -> checkUnparkingResult(parkingManager, bike1, "Bike1");
        Runnable unparkCar1 = () -> checkUnparkingResult(parkingManager, car1, "Car1");

        unparkExecutor.execute(unparkBike1);
        unparkExecutor.execute(unparkCar1);

        unparkExecutor.shutdown();
        while (!unparkExecutor.isTerminated()) {}

        System.out.println("Final Parking State:");
        System.out.println(parkingManager);
    }

    private static void checkParkingResult(ParkingResult result, String vehicleName) {
        if (result.getStatus().equals("201")) {
            System.out.println(Thread.currentThread().getName() + " - " + vehicleName + " parked successfully at " + result.getTicketId());
        } else {
            System.out.println(Thread.currentThread().getName() + " - " + vehicleName + " parking failed");
        }
    }

    private static void checkUnparkingResult(ParkingManager parkingManager, Vehicle vehicle, String vehicleName) {
        parkingManager.unparkVehicle(vehicle);
        System.out.println(Thread.currentThread().getName() + " - " + vehicleName + " unparked successfully");
    }
}
