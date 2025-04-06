package org.example.response;

public class ParkingResult {
    String status;
    String vehicleNumber;
    String ticketId;


    public ParkingResult(String status, String vehicleNumber, String ticketId) {
        this.status = status;
        this.vehicleNumber = vehicleNumber;
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "ParkingResult{" +
                "status='" + status + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", ticketId='" + ticketId + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
