package org.example.util;



import java.util.regex.Pattern;

public class VehicleValidator {


    private static final Pattern OWNER_NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    private static final Pattern VEHICLE_NUMBER_PATTERN = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");

    private static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");

    public static boolean isValidOwnerName(String name) {
        return OWNER_NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidVehicleNumber(String vehicleNumber) {
        return VEHICLE_NUMBER_PATTERN.matcher(vehicleNumber).matches();
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        return MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches();
    }

}
