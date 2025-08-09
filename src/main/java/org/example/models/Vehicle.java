package org.example.models;

import java.time.LocalDateTime;

public abstract class Vehicle {
    private String vehicleOwnerName;
    private String vehicleNumber;
    private String mobileNumber;
    private LocalDateTime entryTime;



    public Vehicle(String vehicleOwnerName, String vehicleNumber, String mobileNumber) {
        this.vehicleOwnerName = vehicleOwnerName;
        this.vehicleNumber = vehicleNumber;
        this.mobileNumber = mobileNumber;
        this.entryTime=LocalDateTime.now();

    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public abstract double calculateFee(long durationInMinutes);
    public abstract String getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleNumber.equals(vehicle.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return vehicleNumber.hashCode();
    }


}
