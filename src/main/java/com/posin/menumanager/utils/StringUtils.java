package com.posin.menumanager.utils;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by Greetty on 2018/5/9.
 */
public class StringUtils {

    public static String formatMaxLength(String message, int maxLength) {
        if (message.length() <= maxLength) {
            return message;
        } else {
            return message.substring(0, maxLength - 1) + "...";
        }
    }

    /**
     * 格式化double类型
     *
     * @param number 数值
     * @param digits 保留小数位数
     * @return 格式后的数值(String类型)
     */
    public static String decimalFormat(double number, int digits) {
        Log.e("decimalFormat", "number: " + number);
        if (number == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("0.");
            for (int i = 0; i < digits; i++) {
                sb.append("0");
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("#.");
            for (int i = 0; i < digits; i++) {
                sb.append("#");
            }
            DecimalFormat df = new DecimalFormat(sb.toString());
            return df.format(number);
        }


    }


}
