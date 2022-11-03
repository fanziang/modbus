package com.potone.modbus.rtu.data;

import com.potone.modbus.exeception.ModbusException;

import java.util.Arrays;

public class ModbusRtuMessage implements Bytes {

    private byte address;

    private ModbusFunction function;

    private ByteArray data;

    private ByteArray crc;

    private CrcByteArray crcByteData;

    public ModbusRtuMessage(byte[] bytes) {
        crcByteData = new CrcByteArray(bytes);
        build();
    }

    public ModbusRtuMessage(String hex) {
        crcByteData = new CrcByteArray(hex);
        build();
    }

    public ModbusRtuMessage(byte address, ModbusFunction function, ByteArray data) {
        if (null == function || null == data) {
            throw new ModbusException("invalid data format");
        }
        byte[] bytes = new byte[data.size() + 2];
        bytes[0] = address;
        bytes[1] = function.getBytes()[0];
        System.arraycopy(data.getBytes(), 0, bytes, 2, data.size());
        crcByteData = CrcByteArray.buildByNoCrcData(bytes);
        build();
    }

    public byte getAddress() {
        return address;
    }

    public ModbusFunction getFunction() {
        return function;
    }

    public ByteArray getData() {
        return data;
    }

    public ByteArray getCrc() {
        return crc;
    }

    private void build() {
        int size = size();
        if (size < 5) {
            throw new ModbusException("invalid data format");
        }
        byte[] bytes = crcByteData.getBytes();
        address = bytes[0];
        function = ModbusFunction.getByValue(bytes[1]);
        data = new ByteArray(Arrays.copyOfRange(bytes, 2, size - 2));
        crc = new ByteArray(Arrays.copyOfRange(bytes, size - 2, size));
    }

    public int size() {
        return crcByteData.size();
    }

    @Override
    public byte[] getBytes() {
        return crcByteData.getBytes();
    }

    @Override
    public String getHex() {
        return crcByteData.getHex();
    }

    @Override
    public String toString() {
        return getHex();
    }

    public static void main(String[] args) {
        ModbusRtuMessage data = new ModbusRtuMessage((byte) 0x56, ModbusFunction.C_04, new ByteArray("0001"));
        System.out.println(data.toString());
    }
}
