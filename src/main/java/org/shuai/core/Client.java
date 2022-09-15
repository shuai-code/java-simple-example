package org.shuai.core;

/**
 * @author Yangs
 */
public class Client {

    /**
     * 修饰代码块
     * */
    public void doRunOne() {
        synchronized (this) {
            System.out.println("开始执行doRunOne...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("结束执行doRunOne...");
        }
    }

    /**
     * 修饰非静态方法
     * */
    public synchronized void doRunTwo() {
        System.out.println("开始执行doRunTwo...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("结束执行doRunTwo...");
    }

    /**
     * 修饰静态方法
     * */
    public synchronized static void doRunThree() {
        System.out.println("开始执行doRunThree...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("结束执行doRunThree...");
    }

    /**
     * 修饰静态方法
     * */
    public synchronized static void doRunFive() {
        System.out.println("开始执行doRunFive...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("结束执行doRunFive...");
    }

    public void doRunFour() {
        System.out.println("开始执行doRunFour...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("结束执行doRunFour...");
    }
}
