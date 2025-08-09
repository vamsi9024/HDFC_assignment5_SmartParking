package org.example.service;

import org.example.exceptions.InvalidMobileNumberException;
import org.example.exceptions.InvalidVehicleNumberException;
import org.example.exceptions.ParkingLotFullException;
import org.example.exceptions.VehicleNotFoundException;

public interface User {

    void parkVehicle(String ownerName, String vehicleNumber, String mobileNumber, String vehicleType) throws InvalidVehicleNumberException, InvalidMobileNumberException, ParkingLotFullException;
    void unparkVehicle(String vehicleNumber) throws VehicleNotFoundException;
    void viewParkingHistory(String mobileNumber);
}
