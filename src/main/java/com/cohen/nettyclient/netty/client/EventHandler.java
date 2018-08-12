package com.cohen.nettyclient.netty.client;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 林金成
 * @date 2018/8/1211:54
 */
public class EventHandler implements com.lmax.disruptor.EventHandler<Event> {

    @Override
    public void onEvent(Event event, long l, boolean b) throws Exception {

    }

    private RingBuffer<Event> ringBuffer;
    public EventHandler(){
        Executor executor = Executors.newCachedThreadPool();
        EventFactory factory = new EventFactory();
        int bufferSize = 1024;
        Disruptor<Event> disruptor = new Disruptor(factory, bufferSize, executor);
        disruptor.handleEventsWith(new com.lmax.disruptor.EventHandler[]{new EventHandler()});
        disruptor.start();
        this.ringBuffer = disruptor.getRingBuffer();
    }
}
