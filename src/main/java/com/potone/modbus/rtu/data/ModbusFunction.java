package com.potone.modbus.rtu.data;

import com.potone.modbus.util.ModbusUtils;

import java.util.HashMap;
import java.util.Map;

public class ModbusFunction extends ByteArray {

    public ModbusFunction(byte b) {
        super(new byte[]{b});
    }

    public static final ModbusFunction C_01 = new ModbusFunction((byte) 0x01);
    public static final ModbusFunction C_02 = new ModbusFunction((byte) 0x02);
    public static final ModbusFunction C_03 = new ModbusFunction((byte) 0x03);
    public static final ModbusFunction C_04 = new ModbusFunction((byte) 0x04);
    public static final ModbusFunction C_05 = new ModbusFunction((byte) 0x05);
    public static final ModbusFunction C_06 = new ModbusFunction((byte) 0x06);
    public static final ModbusFunction C_0F = new ModbusFunction((byte) 0x0F);
    public static final ModbusFunction C_10 = new ModbusFunction((byte) 0x10);

    private static Map<String, ModbusFunction> map = new HashMap<>();

    static {
        map.put("01", C_01);
        map.put("02", C_02);
        map.put("03", C_03);
        map.put("04", C_04);
        map.put("05", C_05);
        map.put("06", C_06);
        map.put("0F", C_0F);
        map.put("10", C_10);
    }

    public static ModbusFunction getByHex(String hex) {
        return map.get(hex);
    }

    public static ModbusFunction getByValue(byte b) {
        return map.get(ModbusUtils.bytes2HexString(b));
    }
}
