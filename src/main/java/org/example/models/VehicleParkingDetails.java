package org.example.models;

import java.time.LocalDateTime;

public class VehicleParkingDetails{

    private Vehicle vehicle;
    private VehicleParkingSlot parkingSlot;
    private LocalDateTime entryTime;

    public VehicleParkingDetails(Vehicle vehicle, VehicleParkingSlot parkingSlot) {
        this.vehicle = vehicle;
        this.parkingSlot = parkingSlot;
        this.entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle() { return vehicle; }
    public VehicleParkingSlot getParkingSlot() { return parkingSlot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
