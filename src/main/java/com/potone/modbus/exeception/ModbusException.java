package com.potone.modbus.exeception;

public class ModbusException extends RuntimeException {

    public ModbusException(String message) {
        super(message);
    }

    public ModbusException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModbusException(Throwable cause) {
        super(cause);
    }
}