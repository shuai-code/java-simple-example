package org.shuai.core;

import lombok.SneakyThrows;

/**
 * @author Yangs
 */
public class Client {

    @SneakyThrows
    public void doSynchronizedCode() {
        synchronized (this) {
            System.out.println("synchronized同步代码块方法开始执行, 线程：" + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("synchronized同步代码块方法执行完成, 线程：" + Thread.currentThread().getName());
        }
    }

    @SneakyThrows
    public synchronized void doSynchronizedMethod() {
        System.out.println("synchronized同步非静态方法开始执行, 线程：" + Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("synchronized同步非静态方法执行完成, 线程：" + Thread.currentThread().getName());
    }

    @SneakyThrows
    public synchronized static void doSynchronizedStaticMethod() {
        System.out.println("synchronized同步静态方法执行完成, 线程：" + Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("synchronized同步静态方法执行完成, 线程：" + Thread.currentThread().getName());
    }

    /**
     * 为了模拟创建对象, 用这个方法包装下静态方法
     * */
    public void executeSynchronizedStaticMethod() {
        doSynchronizedStaticMethod();
    }
}
