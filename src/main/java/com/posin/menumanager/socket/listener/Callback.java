package com.posin.menumanager.socket.listener;

/**
 * Created by Greetty on 2018/5/14.
 *
 * Socket操作结果接口
 */
public interface Callback {

    /**
     * socket连接成功
     */
    void connectSuccess() ;

    /**
     * socket连接失败
     *
     * @param e Exception
     */
    void connectFailure(Exception e);
}
