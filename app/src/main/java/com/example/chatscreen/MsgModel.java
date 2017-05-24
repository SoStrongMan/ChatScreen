package com.example.chatscreen;

import java.io.Serializable;

/**
 * 作者：徐欣
 * 日期：2017/5/23
 * 描述：聊天信息的实体类
 */

public class MsgModel implements Serializable {
    public static final int TYPE_SEND = 0x01; //发送的内容
    public static final int TYPE_RECEIVE = 0x02; //接收的内容

    private String content;
    private int type;

    public MsgModel(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
