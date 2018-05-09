package com.posin.menumanager.utils;

/**
 * Created by Greetty on 2018/5/9.
 */
public class StringUtils {

    public static String formatMaxLength(String message, int maxLength) {
        if (message.length() <= maxLength) {
            return message;
        } else {
            return message.substring(0,maxLength - 1) + "...";
        }
    }




}
