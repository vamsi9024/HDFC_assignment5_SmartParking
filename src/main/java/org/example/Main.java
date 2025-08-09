package org.example;

import org.example.exceptions.InvalidMobileNumberException;
import org.example.exceptions.InvalidVehicleNumberException;
import org.example.exceptions.ParkingLotFullException;
import org.example.exceptions.VehicleNotFoundException;
import org.example.service.Admin;
import org.example.service.ParkingManager;
import org.example.service.User;

import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException  {

        ParkingManager parkingSystem = new ParkingManager();
        User regularUser = parkingSystem;
        Admin adminUser = parkingSystem;

        try {
            // User parks a vehicle
            regularUser.parkVehicle("Vamsi", "AP12AB1234", "9876543210", "Four-wheeler");
            regularUser.parkVehicle("Krishna", "DL05CD5678", "9988776655", "Two-wheeler");
            regularUser.parkVehicle("Reddy", "KA01EF9012", "9123456789", "Electric");

            adminUser.viewAllParkedVehicles();

            System.out.println("\n--- One hour later ---");
            try {
                Thread.sleep(3000); // Simulate 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            regularUser.unparkVehicle("AP12AB1234");
            //regularUser.unparkVehicle("KA01EF9012");
            regularUser.unparkVehicle("TS02AB4321");

        } catch (InvalidVehicleNumberException | InvalidMobileNumberException | ParkingLotFullException |
                 VehicleNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Final Status and Reports ---");
        adminUser.viewAllParkedVehicles();
        adminUser.generateReports();


    }
}