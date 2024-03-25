package com.example.user.vo;

import java.io.Serializable;

public class MyMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String message;//消息内容
    private String messageType;//消息类型  1 代表单聊 2 代表群聊

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
