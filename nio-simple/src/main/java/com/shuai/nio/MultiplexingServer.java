package com.shuai.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * IO多路复用, 引入一个Selector选择器来监听所有IO事件。事件发生时会通知Selector。
 * 不用再像非阻塞IO一样无限循环使程序空转。而是用select来阻塞，当事件发生时解除阻塞自动向下执行。
 * */
public class MultiplexingServer {
    /**
     * 缓存数据Buffer
     * */
    private static final ByteBuffer buffer = ByteBuffer.allocate(16);
    public static void main(String[] args) throws IOException {
        // 创建一个Selector
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 建立连接为非阻塞操作
        serverSocketChannel.configureBlocking(false);
        // 将通道注册到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null);

        MultiplexingServer multiplexingServer = new MultiplexingServer();
        while (true) {
            // 等待新的事件, 阻塞操作
            selector.select();
            multiplexingServer.processEvents(selector);
        }
    }

    public void processEvents(Selector selector) throws IOException {
        // 遍历事件集合, 每处理完一个事件后需要移除, 使用迭代器
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            processEvent(selector, iterator.next());
            iterator.remove();
        }
    }

    public void processEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        // 建立连接事件
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            // 建立连接
            SocketChannel socketChannel = channel.accept();
            // 处理通道数据为非阻塞
            socketChannel.configureBlocking(false);
            // 将连接通道注册到selector, 监听可读事件
            socketChannel.register(selector, SelectionKey.OP_READ, null);
        }
        // 可读事件
        else if (selectionKey.isReadable()) {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
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
