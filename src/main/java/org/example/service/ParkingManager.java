package org.example.service;

import org.example.exceptions.InvalidMobileNumberException;
import org.example.exceptions.InvalidVehicleNumberException;
import org.example.exceptions.ParkingLotFullException;
import org.example.exceptions.VehicleNotFoundException;
import org.example.models.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingManager implements User,Admin{

    private static final int PARKING_CAPACITY = 20;
    private final Map<String, VehicleParkingDetails> parkedVehicles = new HashMap<>();
    private final Queue<VehicleParkingSlot> availableSlots = new LinkedList<>();

    public ParkingManager() {
        IntStream.rangeClosed(1, PARKING_CAPACITY)
                .forEach(i -> availableSlots.add(new VehicleParkingSlot("P" + i)));
    }

    @Override
    public void parkVehicle(String ownerName, String vehicleNumber, String mobileNumber, String vehicleType) throws InvalidVehicleNumberException, InvalidMobileNumberException, ParkingLotFullException, InvalidMobileNumberException {
        if (!vehicleNumber.matches("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$")) {
            throw new InvalidVehicleNumberException("Invalid vehicle number format.");
        }
        if (!mobileNumber.matches("^\\d{10}$")) {
            throw new InvalidMobileNumberException("Invalid mobile number format.");
        }

        if (parkedVehicles.size() >= PARKING_CAPACITY) {
            throw new ParkingLotFullException("Parking lot is full.");
        }

        if (parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("Vehicle " + vehicleNumber + " is already parked.");
            return;
        }

        VehicleParkingSlot slot = availableSlots.poll();
        Vehicle vehicle;
        switch (vehicleType.toLowerCase()) {
            case "two-wheeler":
                vehicle = new TwoWheelerVehicle(ownerName, vehicleNumber, mobileNumber);
                break;
            case "four-wheeler":
                vehicle = new FourWheelerVehicle(ownerName, vehicleNumber, mobileNumber);
                break;
            case "electric":
                vehicle = new ElectricVehicle(ownerName, vehicleNumber, mobileNumber);
                break;
            default:
                System.out.println("Invalid vehicle type.");
                availableSlots.add(slot);
                return;
        }

        slot.setOccupied(true);
        slot.setAssignedVehicleNumber(vehicleNumber);
        parkedVehicles.put(vehicleNumber, new VehicleParkingDetails(vehicle, slot));
        System.out.printf("Vehicle added: %s | Slot: %s | Entry Time: %s%n",
                vehicleNumber, slot.getSlotNumber(), vehicle.getEntryTime());
    }

    @Override
    public void unparkVehicle(String vehicleNumber) throws VehicleNotFoundException {
        if (!parkedVehicles.containsKey(vehicleNumber)) {
            throw new VehicleNotFoundException("Vehicle not found in the parking lot.");
        }

        VehicleParkingDetails details = parkedVehicles.remove(vehicleNumber);
        if (details != null) {
            LocalDateTime exitTime = LocalDateTime.now().plusHours(1);
            Duration duration = Duration.between(details.getEntryTime(), exitTime);
            double fee = details.getVehicle().calculateFee(duration.toMinutes());

            System.out.printf("Vehicle exited: %s | Duration: %s | Fee: ₹%.2f%n",
                    vehicleNumber, formatDuration(duration), fee);

            details.getParkingSlot().setOccupied(false);
            details.getParkingSlot().setAssignedVehicleNumber(null);
            availableSlots.add(details.getParkingSlot());
        }
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%d hours %d mins", hours, minutes);
    }

    @Override
    public void viewParkingHistory(String mobileNumber) {
        System.out.println("Viewing parking history for mobile number: " + mobileNumber);
    }

    @Override
    public void viewAllParkedVehicles() {
        if (parkedVehicles.isEmpty()) {
            System.out.println("No vehicles are currently parked.");
            return;
        }
        System.out.println("Vehicles currently parked:");
        parkedVehicles.values().stream()
                .forEach(details -> System.out.printf("- %s (%s) - Slot: %s - Since: %s%n",
                        details.getVehicle().getVehicleNumber(),
                        details.getVehicle().getType(),
                        details.getParkingSlot().getSlotNumber(),
                        details.getEntryTime().toString()));
    }

    @Override
    public void generateReports() {
        System.out.println("[Admin Report]");
        System.out.println("- Total Vehicles Parked: " + parkedVehicles.size());

        Map<String, Long> vehicleCounts = parkedVehicles.values().stream()
                .map(details -> details.getVehicle().getType())
                .collect(Collectors.groupingBy(type -> type, Collectors.counting()));

        vehicleCounts.forEach((type, count) -> System.out.printf("- %s Vehicles: %d%n", type, count));

        long twoWheelersOver3Hours = parkedVehicles.values().stream()
                .filter(details -> details.getVehicle().getType().equals("Two-wheeler"))
                .filter(details -> Duration.between(details.getEntryTime(), LocalDateTime.now()).toHours() > 3)
                .count();
        System.out.println("- 2-Wheelers > 3 hours: " + twoWheelersOver3Hours);
    }
}
