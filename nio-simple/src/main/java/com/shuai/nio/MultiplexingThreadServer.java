package com.shuai.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * IO多路复用, 多线程版本。在主线程中处理建立连接。在多线程Worker中处理客户端数据。
 * */
public class MultiplexingThreadServer {
    /**
     * 缓存数据Buffer
     * */
    private static final ByteBuffer buffer = ByteBuffer.allocate(16);
    /**
     * 初始化处理数据的线程
     * */
    private static final Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
    /**
     * 用来选择处理数据的线程
     * */
    private static final AtomicInteger index = new AtomicInteger();

    public MultiplexingThreadServer() {
        // 根据服务器CPU核数配置worker线程数
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建一个Selector
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 建立连接为非阻塞操作
        serverSocketChannel.configureBlocking(false);
        // 将通道注册到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null);
        while (true) {
            // 等待新的事件, 阻塞操作
            selector.select();
            // 遍历事件集合, 每处理完一个事件后需要移除, 使用迭代器
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                // 如果事件是可建立连接
                processAcceptEvent(iterator.next());
                iterator.remove();
            }
        }
    }

    public static void processAcceptEvent(SelectionKey selectionKey) throws IOException {
        // 如果事件是可建立连接
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            // 建立连接
            SocketChannel socketChannel = channel.accept();
            // 处理通道数据为非阻塞
            socketChannel.configureBlocking(false);
            // 通道注册到工作线程
            workers[index.getAndIncrement() % workers.length].register(socketChannel);
        }
    }

    public static class Worker implements Runnable {
        private Thread thread;
        private String name;
        private Selector selector;

        private Boolean start;

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel socketChannel) throws IOException {
            if (!start) {
                thread = new Thread(this, name);
                thread.start();
                selector = Selector.open();
                start = true;
            }
            // 唤醒Selector, 可以在select方法前或者后调用都可以唤醒
            selector.wakeup();
            socketChannel.register(selector, SelectionKey.OP_READ, null);
        }

        @Override
        public void run() {
            while (true) {
                // 等待新的事件, 阻塞操作
                try {
                    selector.select();
                    // 遍历事件集合, 每处理完一个事件后需要移除, 使用迭代器
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        processEvent(iterator.next());
                        iterator.remove();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void processEvent(SelectionKey selectionKey) throws IOException {
            if (!selectionKey.isReadable()) {
                return;
            }
            read((SocketChannel) selectionKey.channel());
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
}
