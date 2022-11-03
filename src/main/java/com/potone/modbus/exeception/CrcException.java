package com.potone.modbus.exeception;

public class CrcException extends RuntimeException {

    public CrcException(String message) {
        super(message);
    }

    public CrcException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrcException(Throwable cause) {
        super(cause);
    }
}