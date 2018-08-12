package com.cohen.nettyclient.netty.client;

/**
 * @author 林金成
 * @date 2018/8/1211:57
 */
public class EventFactory implements com.lmax.disruptor.EventFactory<Event> {
    public EventFactory() {
    }

    public Event newInstance() {
        return new Event();
    }
}
