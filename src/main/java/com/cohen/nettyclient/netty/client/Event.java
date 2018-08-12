package com.cohen.nettyclient.netty.client;

/**
 * @author 林金成
 * @date 2018/8/1211:55
 */
public class Event {

    public Event() {
    }

    public Event(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
