package com.example.user.service;

import com.example.user.vo.RE;

import javax.websocket.Session;

public interface ChatService {
    /**
     * 连接建立成功调用的方法
     */
    RE onOpen(Session session);
    /**
     * 连接关闭调用的方法
     */
    RE onClose();
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    RE onMessage(String message);
    /**
     * 发生错误时调用的方法
     *
     * @OnError
     **/
    RE onError(Throwable error);

    RE sendMessage(String message);
    /**
     * 单聊
     * message ： 消息内容，输入的实际内容，不是拼接后的内容
     * recUser : 消息接收者
     * sendUser : 消息发送者
     */
    RE sendInfo( String message , String recUser,String sendUser);
    /**
     * 群聊
     * message ： 消息内容，输入的实际内容，不是拼接后的内容
     * sendUser : 消息发送者
     */
    RE sendGroupInfo(String message,String sendUser);
    /**
     * Map/Set的key为自定义对象时，必须重写hashCode和equals。
     * 关于hashCode和equals的处理，遵循如下规则：
     * 1）只要重写equals，就必须重写hashCode。
     * 2）因为Set存储的是不重复的对象，依据hashCode和equals进行判断，所以Set存储的对象必须重写这两个方法。
     * 3）如果自定义对象做为Map的键，那么必须重写hashCode和equals。
     *
     * @param o
     * @return
     */
    @Override
    boolean equals(Object o);
    @Override
    int hashCode();
}
