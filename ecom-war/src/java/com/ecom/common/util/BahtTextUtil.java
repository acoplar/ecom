/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class BahtTextUtil {

    private static final String[] NUMBER_SCALES = {
        "ล้าน", "สิบ", "ร้อย",
        "พัน", "หมื่น", "แสน", ""
    };
    private static final String[] DIGIT_WORDS = {
        "ศูนย์", "หนึ่ง", "สอง",
        "สาม", "สี่", "ห้า", "หก",
        "เจ็ด", "แปด", "เก้า"
    };

    public static void main(String[] args) {
        System.out.println(getBahtTextUtil(BigDecimal.ZERO));
    }

    public static String getBahtTextUtil(BigDecimal amount) {
        StringBuilder buffer = new StringBuilder();
        BigDecimal absolute = amount.abs();
        int precision = absolute.precision();
        int scale = absolute.scale();
        int rounded_precision = ((precision - scale) + 2);
        MathContext mc = new MathContext(rounded_precision, RoundingMode.HALF_UP);
        BigDecimal rounded = absolute.round(mc);
        BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
        boolean negative_amount = (-1 == amount.compareTo(BigDecimal.ZERO));

        compound[0] = compound[0].setScale(0);
        compound[1] = compound[1].movePointRight(2);

        if (negative_amount) {
            buffer.append("ลบ");
        }

        buffer.append(getNumberText(compound[0].toBigIntegerExact()));
        buffer.append("บาท");

        if (0 == compound[1].compareTo(BigDecimal.ZERO)) {
            buffer.append("ถ้วน");
        } else {
            buffer.append(getNumberText(compound[1].toBigIntegerExact()));
            buffer.append("สตางค์");
        }

        return buffer.toString();
    }

    private static String getNumberText(BigInteger number) {
        StringBuilder buffer = new StringBuilder();
        char[] digits = number.toString()
                .toCharArray();

        for (int index = digits.length; index > 0; --index) {
            int digit = Integer.parseInt(String.valueOf(digits[digits.length
                    - index]));
            String digit_text = DIGIT_WORDS[digit];
            int scale_idx = ((1 < index)
                    ? ((index - 1) % 6)
                    : 6);

            if ((1 == scale_idx) && (2 == digit)) {
                digit_text = "ยี่";
            }

            if (1 == digit) {
                switch (scale_idx) {
                    case 0:
                    case 6:
                        buffer.append((index < digits.length)
                                ? "เอ็ด"
                                : digit_text);

                        break;

                    case 1:
                        break;

                    default:
                        buffer.append(digit_text);

                        break;
                }
            } else if (0 == digit) {
                if (0 == scale_idx) {
                    buffer.append(NUMBER_SCALES[scale_idx]);
                }

                continue;
            } else {
                buffer.append(digit_text);
            }

            buffer.append(NUMBER_SCALES[scale_idx]);
        }

        return buffer.toString();
    }
}
