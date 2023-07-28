package com.shuai.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 非阻塞IO, 通过手动来设置建立连接和read读取数据都是非阻塞操作。虽然两个操作是非阻塞，程序会继续向下执行循环，但是程序会无限循环进行空转
 * */
public class NonBlockingServer {
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
        // 建立连接为非阻塞
        serverSocketChannel.configureBlocking(false);
        while (true) {
            // accept建立与客户端连接通道, 非阻塞。如果没有建立成功则返回null
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                // 将通道设置为非阻塞
                socketChannel.configureBlocking(false);
                socketChannels.add(socketChannel);
            }
            socketChannels.add(socketChannel);
            // 当成功建立与客户端的连接通道, 程序才能执行到此处理所有通道。
            NonBlockingServer nonBlockingServer = new NonBlockingServer();
            nonBlockingServer.processChannels();
        }
    }

    public void processChannels() throws IOException {
        for (SocketChannel channel : socketChannels) {
            read(channel);
        }
    }

    public void read(SocketChannel channel) throws IOException {
        // 从通道读取数据, 非阻塞。如果未读取到数据则不返回数据
        int index = channel.read(buffer);
        if (index <= 0) {
            return;
        }
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
