package com.shuai.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 阻塞IO, 在不采用线程的前提下accept建立连接和read读取数据都是阻塞操作。
 * */
@Slf4j
public class BlockingServer {
    /**
     * 客户端连接通道集合
     * */
    private static final List<SocketChannel> socketChannels = new ArrayList<>();
    /**
     * 缓存数据Buffer
     * */
    private static final ByteBuffer buffer = ByteBuffer.allocate(16);

    public static void main(String[] args) throws IOException {
        // 开启服务端Socket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定服务端端口
        serverSocketChannel.bind(new InetSocketAddress(8080));
        while (true) {
            // accept建立与客户端连接通道, 阻塞操作(直到连接建立完成)
            socketChannels.add(serverSocketChannel.accept());
            // 当成功建立与客户端的连接通道, 程序才能执行到此处理所有通道。
            BlockingServer blockingServer = new BlockingServer();
            blockingServer.processChannels();
        }
    }

    public void processChannels() throws IOException {
        for (SocketChannel channel : socketChannels) {
            read(channel);
        }
    }

    public void read(SocketChannel channel) throws IOException {
        // 从通道读取数据, 阻塞操作(此时有新的连接也会被阻塞)
        channel.read(buffer);
        // 切换到读模式
        buffer.flip();
        // 打印数据
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.println(buffer.get());
        }
        // 重置buffer
        buffer.clear();
    }
}
