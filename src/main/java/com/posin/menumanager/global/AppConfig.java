package com.posin.menumanager.global;

import android.icu.lang.UScript;

/**
 * Created by Greetty on 2018/4/27.
 * <p>
 * 全局常量
 */
public class AppConfig {

    /**
     * 广告系统SOCKET连接地址
     *
     * M50,M30B：localhost
     *
     * M50L：1.1.1.2
     *
     */
    public static final String TARGET_DISPLAY_ADDRESS = "localhost";

    /**
     * 广告系统SOCKET端口
     */
    public static final int TARGET_DISPLAY_PORT =9999;

    /**
     * 心跳周期 (单位：毫秒）
     */
    public volatile static long ACTIVE_CYCLE_TIME = 2000;
}
