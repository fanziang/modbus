package com.potone.modbus.rtu.data;

import com.potone.modbus.util.ModbusUtils;
import org.apache.commons.lang3.StringUtils;

public class ByteArray implements Bytes {

    protected byte[] bytes;

    protected String hex;

    public ByteArray(byte[] bytes) {
        this.bytes = bytes;
        this.hex = ModbusUtils.bytes2HexString(bytes);
        if (StringUtils.isNotEmpty(this.hex)) {
            this.hex = this.hex.replace(" ", "");
        }
    }

    public ByteArray(String hex) {
        this.bytes = ModbusUtils.hexStringToByteArray(hex);
        this.hex = hex;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String getHex() {
        return hex;
    }

    public int size() {
        return null == bytes ? 0 : bytes.length;
    }

    @Override
    public String toString() {
        return hex;
    }

}
