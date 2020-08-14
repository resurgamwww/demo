package com.wht.demo.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * desc.
 *
 * @author wht
 */
public class NettyNioServer {
    public static void main(String[] args) throws InterruptedException {
        NettyNioServer server = new NettyNioServer();
        server.server(8080);

        ByteBuf buf = Unpooled.copiedBuffer("test", StandardCharsets.UTF_8);
        buf.markReaderIndex();
        buf.slice();

    }

    public void server(int port) throws InterruptedException {

        ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!Nio!\r\n", StandardCharsets.UTF_8));

        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                    ByteBufAllocator alloc = ctx.alloc();
                                    ByteBuf buffer = alloc.buffer();
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    super.exceptionCaught(ctx, cause);
                                }
                            })
                            .addLast(new ChannelOutboundHandlerAdapter(){
                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    super.write(ctx, msg, promise);
                                    ctx.write("");
                                    ChannelPipeline pipeline = ctx.pipeline();
                                    pipeline.channel().write("");
                                }

                            });
                        }
                    });

            ChannelFuture f = bootstrap.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
