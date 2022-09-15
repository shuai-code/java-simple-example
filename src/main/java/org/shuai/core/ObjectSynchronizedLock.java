package org.shuai.core;

/**
 * @author Yangs
 */
public class ObjectSynchronizedLock {

    public static void main(String[] args) {
        ObjectSynchronizedLock synchronizedLock = new ObjectSynchronizedLock();
        synchronizedLock.executeSynchronizedStaticMethodNotSameObject();

        while (true) {
        }
    }

    /**
     * 对于同一个对象, synchronized代码块的锁仅对当前对象生效，锁生效, 串行处理
     * */
    public void executeSynchronizedCodeSameObject() {
        Client client = new Client();

        Thread thread1 = new Thread(client::doSynchronizedCode);
        thread1.start();

        Thread thread2 = new Thread(client::doSynchronizedCode);
        thread2.start();
    }

    /**
     * 对于不同对象, synchronized代码块的锁仅对当前对象生效. 两个线程间不发生锁争抢, 并行处理
     * */
    public void executeSynchronizedCodeNotSameObject() {
        Client client1 = new Client();
        Thread thread1 = new Thread(client1::doSynchronizedCode);
        thread1.start();

        Client client2 = new Client();
        Thread thread2 = new Thread(client2::doSynchronizedCode);
        thread2.start();
    }

    /**
     * 对于同一个对象, synchronized修饰方法, 锁仅对当前对象生效，锁生效, 串行处理
     * */
    public void executeSynchronizedMethodSameObject() {
        Client client = new Client();

        Thread thread1 = new Thread(client::doSynchronizedMethod);
        thread1.start();

        Thread thread2 = new Thread(client::doSynchronizedMethod);
        thread2.start();
    }

    /**
     * 对于不同对象, synchronized修饰方法. 锁仅对当前对象生效, 并行处理
     * */
    public void executeSynchronizedMethodNotSameObject() {
        Client client1 = new Client();
        Thread thread1 = new Thread(client1::doSynchronizedMethod);
        thread1.start();

        Client client2 = new Client();
        Thread thread2 = new Thread(client2::doSynchronizedMethod);
        thread2.start();
    }

    /**
     * synchronized修饰静态方法, 与对象无关, 锁对当前类生效, 锁对这个类的所有对象的synchronized静态方法都会生效, 其他方法不受影响
     * */
    public void executeSynchronizedStaticMethod() {
        Thread thread1 = new Thread(Client::doSynchronizedStaticMethod);
        thread1.start();

        Thread thread2 = new Thread(Client::doSynchronizedStaticMethod);
        thread2.start();
    }

    /**
     * synchronized修饰静态方法, 与对象无关, 锁对当前类生效, 锁对这个类的所有对象的synchronized静态方法都会生效, 其他方法不受影响
     * */
    public void executeSynchronizedStaticMethodNotSameObject() {
        Client client1 = new Client();
        Thread thread1 = new Thread(client1::executeSynchronizedStaticMethod);
        thread1.start();

        Client client2 = new Client();
        Thread thread2 = new Thread(client2::executeSynchronizedStaticMethod);
        thread2.start();
    }
}
