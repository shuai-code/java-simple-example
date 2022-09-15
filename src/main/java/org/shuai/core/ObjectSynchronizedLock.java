package org.shuai.core;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yangs
 */
public class ObjectSynchronizedLock {

    public static void main(String[] args) {
        // 所有线程使用同一个对象, 对象锁生效, 10个线程串行处理(等待锁)
        Client client = new Client();
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(client::doRun);
        }

        // 每个线程单独创建了对象, 所以对象锁不生效, 10个线程并行处理
        for (int i = 0; i < 10; i++) {
            Client aloneClient = new Client();
            CompletableFuture.runAsync(aloneClient::doRun);
        }

        while (true) {

        }
    }

    public static class Client {
        public void doRun() {
            // 对象锁, 锁定Client对象
            synchronized (this) {
                System.out.println("do...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("end...");
            }
        }
    }
}
