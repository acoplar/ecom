package com.ecom.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtil {

    public static void main(String[] args) {
        System.out.println(getStringToDouble("1,2345.05"));
    }

    public static double getStringToDouble(String input) {
        double output = 0.0;
        if (input != null) {
            output = Double.parseDouble(input.replace(",", ""));
        }
        return output;
    }
    
    public static BigDecimal getBigDecimal(BigDecimal input) {
        BigDecimal output = new BigDecimal(BigInteger.ZERO);
        if (input != null) {
            return input;
        }
        return output;
    }
    
    public static BigDecimal getBigDecimal(Double input) {
        BigDecimal output = new BigDecimal(BigInteger.ZERO);
        if (input != null) {
            return BigDecimal.valueOf(input);
        }
        return output;
    }

    public static double getDouble(BigDecimal input) {
        double output = 0.0;
        if (input != null) {
            output = input.doubleValue();
        }
        return output;
    }

    public static double getDouble(Double input) {
        double output = 0.0;
        if (input != null) {
            output = input.doubleValue();
        }
        return output;
    }

    public static float getFloat(Double input) {
        float output = 0;
        if (input != null) {
            output = input.floatValue();
        }
        return output;
    }

    public static int getInteger(Integer input) {
        int output = 0;
        if (input != null) {
            output = input.intValue();
        }
        return output;
    }

    public static int getInteger(BigInteger input) {
        if (input != null) {
            return input.intValue();
        }
        return 0;
    }
}
