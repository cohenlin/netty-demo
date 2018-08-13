package com.cohen.nettyclient.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 林金成
 * @date 2018/8/128:55
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        System.out.println("client has connected the server!");
        byte[] bytes = "hello".getBytes(CharsetUtil.UTF_8);
        ByteBuf message = Unpooled.buffer(bytes.length + 2);
        message.writeBytes(bytes);
        message.writeBytes("\n\r".getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String message = ((ByteBuf) msg).toString(CharsetUtil.UTF_8).replaceAll("\\n", "");
            System.out.println("server received: " + message);
            ChannelFuture future = ctx.writeAndFlush("I have received message: " + message);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("response success!");
                    }else{
                        System.out.println("response error!");
                    }
                }
            });
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
