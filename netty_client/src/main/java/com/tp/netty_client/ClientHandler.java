package com.tp.netty_client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/24
 * Description:
 * Author: zl
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    private ReceiveData<String> receiveData;

    public ClientHandler(ReceiveData receiveData){
        this.receiveData = receiveData;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        receiveData.onReceive(msg);
    }

}
