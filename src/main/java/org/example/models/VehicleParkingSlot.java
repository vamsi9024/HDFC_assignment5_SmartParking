package org.example.models;

public class VehicleParkingSlot {

    private String slotNumber;
    private boolean isOccupied;
    private String assignedVehicleNumber;

    public VehicleParkingSlot(String slotNumber) {
        this.slotNumber = slotNumber;
        this.isOccupied = false;

    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getAssignedVehicleNumber() {
        return assignedVehicleNumber;
    }

    public void setAssignedVehicleNumber(String assignedVehicleNumber) {
        this.assignedVehicleNumber = assignedVehicleNumber;
    }
}
