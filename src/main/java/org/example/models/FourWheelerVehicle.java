package org.example.models;

public class FourWheelerVehicle extends Vehicle{


    public FourWheelerVehicle(String vehicleOwnerName, String vehicleNumber, String mobileNumber) {
        super(vehicleOwnerName, vehicleNumber, mobileNumber);
    }

    @Override
    public double calculateFee(long durationInMinutes) {
        return Math.ceil(durationInMinutes/60)*40;
    }

    @Override
    public String getType() {
        return "Four-Wheeler";
    }
}
