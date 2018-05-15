package com.posin.menumanager.socket.listener;

/**
 * Created by Greetty on 2018/5/14.
 *
 * Socket操作结果接口
 */
public interface SendCallback {

    /**
     * socket操作成功
     */
    void success() ;

    /**
     * socket操作失败
     *
     * @param e Exception
     */
    void failure(Exception e);
}
