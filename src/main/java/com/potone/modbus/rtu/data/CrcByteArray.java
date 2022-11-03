package com.potone.modbus.rtu.data;

import com.potone.modbus.exeception.CrcException;
import com.potone.modbus.util.ModbusUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CrcByteArray extends ByteArray {

    private ByteArray data;

    private ByteArray crc;

    public CrcByteArray(byte[] bytes) {
        super(bytes);
        build();
    }

    public CrcByteArray(String hexString) {
        super(hexString);
        build();
    }

    private void build() {
        int size = size();
        if (size < 3) {
            throw new CrcException("invalid data format");
        }
        byte[] dataBytes = Arrays.copyOf(bytes, size - 2);
        byte[] crcBytes = Arrays.copyOfRange(bytes, size - 2, size);
        if (!Arrays.equals(crcBytes, computeCrc(dataBytes))) {
            throw new CrcException("CRC validation failed");
        }
        this.data = new ByteArray(dataBytes);
        this.crc = new ByteArray(crcBytes);
    }

    public ByteArray getData() {
        return data;
    }

    public ByteArray getCrc() {
        return crc;
    }

    public static byte[] computeCrc(byte[] bs) {
        String s = ModbusUtils.getCRC3(bs);
        return ModbusUtils.hexStringToByteArray(s);
    }

    public static CrcByteArray buildByNoCrcData(byte[] bytes) {
        int size = Array.getLength(bytes);
        if (size < 1) {
            throw new CrcException("invalid data format");
        }
        byte[] newBytes = Arrays.copyOf(bytes, size + 2);
        byte[] crcBytes = computeCrc(bytes);
        if (Array.getLength(crcBytes) == 0) {
            throw new CrcException("invalid data format");
        }
        newBytes[size] = crcBytes[0];
        newBytes[size + 1] = crcBytes[1];
        return new CrcByteArray(newBytes);
    }

    public static CrcByteArray buildByNoCrcData(String hex) {
        return buildByNoCrcData(ModbusUtils.hexStringToByteArray(hex));
    }

}
