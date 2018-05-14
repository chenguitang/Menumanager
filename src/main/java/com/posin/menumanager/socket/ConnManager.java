package com.posin.menumanager.socket;

import android.util.Log;


import com.posin.menumanager.utils.Json;
import com.posin.menumanager.utils.ThreadManage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * created by Greetty at 2018/4/27 10:16
 * <p>
 * 连接管理
 */
public class ConnManager {

    private static final String TAG = "ConnManager";
    private static ConnManager connManager = null;
    private Socket socket = null;

    /**
     * 获取ConnManager实例
     *
     * @return ConnManager
     */
    public static ConnManager getConnManager() {
        if (connManager == null) {
            new Thread(new SocketConnection()).start();
            connManager = new ConnManager();
        }
        return connManager;
    }

    /**
     * 获取Socket
     *
     * @return Socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * 设置Socket值
     *
     * @param socket Socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 发送指令，动态修改副屏菜单栏显示
     *
     * @param commands 指令集
     */
    public void sendViewCode(final String[][] commands) {

        ThreadManage.getSinglePool().execute(new Runnable() {
            @Override
            public void run() {
                if (socket != null && !socket.isClosed()) {
                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        String msg = "@*#&0@*#&#&*@" + Json.listToString(
                                formatCommand(commands)) + "#&*@";
                        outputStream.write(msg.getBytes());
                        outputStream.flush();
                        Log.d(TAG, "send: 发送完成:" + msg);
                    } catch (IOException e) {
                        Log.d(TAG, "socket 断开连接，等待重连！");
                        e.printStackTrace();

                    }
                }else{
                    Log.e(TAG, "socket ==null or socket is closed");
                }
            }
        });
    }


    /**
     * 格式化指令
     *
     * @param commands 指令集
     * @return 格式后的指令集
     */
    private List<Map<String, String>> formatCommand(String[][] commands) {
        List<Map<String, String>> lst = new LinkedList<Map<String, String>>();
        for (String[] c :
                commands) {
            Map<String, String> m = new HashMap<String, String>();
            for (int i = 0; i < c.length; i += 2) {
                m.put(c[i], c[i + 1]);
            }
            lst.add(m);
        }
        return lst;
    }

}
