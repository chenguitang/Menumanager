package com.posin.menumanager.socket;

import android.util.Log;

import com.posin.menumanager.global.AppConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * created by Greetty at 2018/4/27 10:35
 * <p>
 * SOCKET连接管理
 */
public class SocketConnection implements Runnable {

    private static final String TAG = "SocketConnection";

    private Socket socket = null;
    //最后一次发送心跳包的时间
    private long lastActTime = 0;


    @Override
    public void run() {
        conn();
        if (socket != null && !socket.isClosed()) {
            ConnManager.getConnManager().setSocket(socket);
            new Thread(readRunnable).start();
            new Thread(heartBeatRunnable).start();
        }
    }

    private void conn() {
        try {
            socket = new Socket(AppConfig.TARGET_DISPLAY_ADDRESS, AppConfig.TARGET_DISPLAY_PORT);
            Log.d(TAG, "conn: 连接socket成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "conn: socket连接异常！");
        }
    }

    private Runnable heartBeatRunnable = new Runnable() {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                long time = System.currentTimeMillis();
                if (time - getLastActTime() >= AppConfig.ACTIVE_CYCLE_TIME) {
                    Log.d(TAG, "发送心跳包。。。");
                    //发送心跳包
                    boolean isSuccess = sendHeartBeat("ok");
                    if (!isSuccess) {
                        //心跳包失败重连socket服务
                        Log.d(TAG, "正在重连socket...");
                        releaseLastSocket(socket);
                        resetSocket();
                    }

                }
            }
        }
    };

    /**
     * 获取最后发送心跳包的时间
     *
     * @return long
     */
    public long getLastActTime() {
        return lastActTime;
    }

    /**
     * 重连socket服务
     */
    public void resetSocket() {
        try {
            socket = new Socket(AppConfig.TARGET_DISPLAY_ADDRESS, AppConfig.TARGET_DISPLAY_PORT);
            ConnManager.getConnManager().setSocket(socket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, "resetSocket: 正在重连....");
            e.printStackTrace();
        }
    }

    /**
     * 判断是否断开连接，断开返回true,没有返回false
     * <p>
     * 发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
     *
     * @param socket Socket
     * @return Boolean
     */
    public static Boolean isServerClose(Socket socket) {
        boolean b = false;
        try {
            socket.sendUrgentData(0);
        } catch (Exception se) {
            b = true;
        }
        return b;
    }


    /**
     * 获取socket心跳回执信息
     */
    private Runnable readRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    Log.d(TAG, "run: 接收到消息但没处理");
                    InputStream in = ConnManager.getConnManager().getSocket().getInputStream();
                    byte[] buffer = new byte[1024];
                    int length = 0;
                    while ((length = in.read(buffer)) != -1) {
                        if (length > 0) {
                            Log.d(TAG, "接收到服务端信息！");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 向socket服务端发送心跳包
     *
     * @param msg
     * @return
     */
    public boolean sendHeartBeat(String msg) {
        if (socket == null) {
            Log.d(TAG, "sendHeartBeat: socket = null");
            return false;
        }
        try {
            if (!socket.isClosed() || !socket.isOutputShutdown()) {
                Log.d(TAG, "sendHeartBeat: 允许发送");
                OutputStream out = socket.getOutputStream();
                String message = msg;
                out.write(message.getBytes());
                out.flush();
                lastActTime = System.currentTimeMillis();
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 关闭客户端socket端
     *
     * @param socket Socket
     */
    private void releaseLastSocket(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
