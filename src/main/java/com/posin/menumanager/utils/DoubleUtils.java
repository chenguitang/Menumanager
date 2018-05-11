package com.posin.menumanager.utils;

import java.math.BigDecimal;

/**
 * Created by Greetty on 2018/5/11.
 * <p>
 * Double类型工具类
 */
public class DoubleUtils {


    /**
     * 加法
     *
     * @param b1 double数值1
     * @param b2 double数值1
     * @return double类型数值，保留两位小数
     */
    public static double add(double b1, double b2) {
        BigDecimal bignum1 = new BigDecimal(b1);
        BigDecimal bignum2 = new BigDecimal(b2);
        return bignum1.add(bignum2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 减法
     *
     * @param b1 double数值1
     * @param b2 double数值1
     * @return double类型数值，保留两位小数
     */
    public static double subtract(double b1, double b2) {
        BigDecimal bignum1 = new BigDecimal(b1);
        BigDecimal bignum2 = new BigDecimal(b2);
        return bignum1.subtract(bignum2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 乘法
     *
     * @param b1 double数值1
     * @param b2 double数值1
     * @return double类型数值，保留两位小数
     */
    public static double multiply(double b1, double b2) {
        BigDecimal bignum1 = new BigDecimal(b1);
        BigDecimal bignum2 = new BigDecimal(b2);
        return bignum1.multiply(bignum2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法
     *
     * @param b1 double数值1
     * @param b2 double数值1
     * @return double类型数值，保留两位小数
     */
    public static double divide(double b1, double b2) {
        BigDecimal bignum1 = new BigDecimal(b1);
        BigDecimal bignum2 = new BigDecimal(b2);
        return bignum1.divide(bignum2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
