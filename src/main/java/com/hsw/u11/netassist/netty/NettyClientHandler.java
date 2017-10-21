package com.hsw.u11.netassist.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class NettyClientHandler extends SimpleChannelInboundHandler<String>{
	
	@Override
	protected void channelRead0(ChannelHandlerContext context, String msg)
			throws Exception {
		System.out.println(msg);
	}
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println(ctx.name()+ctx.channel().localAddress()+"remote:"+ctx.channel().remoteAddress());
	}

}
