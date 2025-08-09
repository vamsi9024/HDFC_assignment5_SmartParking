package org.example.exceptions;

public class InvalidOwnerNameException extends Exception{

    private String msg;

    public InvalidOwnerNameException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
