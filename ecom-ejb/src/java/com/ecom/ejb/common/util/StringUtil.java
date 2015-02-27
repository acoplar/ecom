package com.ecom.ejb.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtil {

    public static boolean isNullOrEmpty(String p_string) {
        boolean result = false;
        if (p_string == null || p_string.length() == 0) {
            result = true;
        }
        return result;
    }

    public static boolean isNullOrEmpty(Object oj) {
        boolean result = false;
        if (oj == null) {
            result = true;
        }
        return result;
    }

    public static boolean isNotNullOrNotEmpty(String p_string) {
        boolean result = false;
        if (p_string != null && p_string.length() != 0) {
            result = true;
        }
        return result;
    }

    public static boolean isNotNullOrNotEmpty(Object oj) {
        boolean result = false;
        if (oj != null) {
            result = true;
        }
        return result;
    }

    public static String numberFormat(int id, String patt) {
        NumberFormat formatter = new DecimalFormat(patt);
        return formatter.format(id);
    }

    public static String getIsNull(String input) {
        String output = "";
        if (input != null && input.length() != 0) {
            output = input;
        }
        return output;
    }
}
