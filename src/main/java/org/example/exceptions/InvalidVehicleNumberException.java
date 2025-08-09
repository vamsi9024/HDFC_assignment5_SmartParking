package org.example.exceptions;

public class InvalidVehicleNumberException extends Exception{

    private String msg;

    public InvalidVehicleNumberException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
