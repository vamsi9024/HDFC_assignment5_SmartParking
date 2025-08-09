package org.example.models;

public class TwoWheelerVehicle extends Vehicle{


    public TwoWheelerVehicle(String vehicleOwnerName, String vehicleNumber, String mobileNumber) {
        super(vehicleOwnerName, vehicleNumber, mobileNumber);
    }

    @Override
    public double calculateFee(long durationInMinutes) {
        return Math.ceil(durationInMinutes / 60.0) * 20;
    }

    @Override
    public String getType() {
        return "Two-Wheeler";
    }
}
