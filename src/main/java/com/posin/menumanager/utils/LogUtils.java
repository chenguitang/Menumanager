package com.posin.menumanager.utils;

import android.util.Log;

/**
 * Created by Greetty on 2018/5/9.
 */
public class LogUtils {

    public static void Error(String TAG, String message) {
        Log.e(TAG, "========================================================");
        Log.e(TAG, "========================================================");
        Log.e(TAG, "======    " + message + "   ======");
        Log.e(TAG, "========================================================");
        Log.e(TAG, "========================================================");
    }
}
