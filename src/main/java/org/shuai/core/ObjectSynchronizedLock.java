package org.shuai.core;

/**
 * @author Yangs
 */
public class ObjectSynchronizedLock {

    public static void main(String[] args) {
        ObjectSynchronizedLock synchronizedLock = new ObjectSynchronizedLock();
        synchronizedLock.executeFiveSameObject();
        while (true) {

        }
    }

    public void executeOneSameObject() {
        Client client = new Client();
        // doRunOne方法使用synchronized修饰代码块, 指定锁定this当前对象
        // 所有线程使用同一个对象, 10个线程串行处理(等待锁)
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(client::doRunOne);
            thread.start();
        }
    }

    public void executeOneAloneObject() {
        // doRunOne方法使用synchronized修饰代码块, 指定锁定this当前对象
        // 但是每个线程都创建了一个新的对象, 锁针对于每个对象. 10个线程并行处理
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            Thread thread = new Thread(client::doRunOne);
            thread.start();
        }
    }

    public void executeTwoSameObject() {
        Client client = new Client();
        // doRunOne方法和doRunTwo使用synchronized修饰
        // 会导致互相抢锁, 两个方法只能同时有1个执行, 20个线程串行执行
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(client::doRunOne);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(client::doRunTwo);
            thread.start();
        }
        // doRunFour方法是一个没有synchronized修饰的正常方法, 不受锁影响
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(client::doRunFour);
            thread.start();
        }
    }

    public void executeThreeSameObject() {
        // doRunOne方法和doRunTwo使用synchronized修饰
        // 会导致互相抢锁, 两个方法只能同时有1个执行, 20个线程串行执行
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            Thread thread = new Thread(client::doRunOne);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            Thread thread = new Thread(client::doRunTwo);
            thread.start();
        }
        // doRunFour方法是一个没有synchronized修饰的正常方法, 不受锁影响
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            Thread thread = new Thread(client::doRunFour);
            thread.start();
        }
    }

    public void executeFiveSameObject() {
        // doRunThree和doRunFive使用synchronized修饰的静态方法
        // 锁是类锁, 20个线程串行执行
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(Client::doRunThree);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(Client::doRunFive);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            Thread thread = new Thread(client::doRunFour);
            thread.start();
        }
    }
}
