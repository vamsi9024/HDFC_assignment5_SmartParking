package org.example.models;

public class ElectricVehicle extends Vehicle{

    public ElectricVehicle(String vehicleOwnerName, String vehicleNumber, String mobileNumber) {
        super(vehicleOwnerName, vehicleNumber, mobileNumber);
    }

    @Override
    public double calculateFee(long durationInMinutes) {
        return Math.ceil(durationInMinutes/60)*30;
    }

    @Override
    public String getType() {
        return "Electric";
    }
}
