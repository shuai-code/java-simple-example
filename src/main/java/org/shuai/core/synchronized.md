Synchronized在JVM中有个专用名字: 对象监视器(Object Monitor)
主要作用:
1. 原子性
   确保线程互斥的访问同步代码
2. 可见性
   保证共享变量的修改能够及时可见
   对一个变量unlock操作之前, 必须同步到主内存中
   对一个变量lock操作之前, 必须清空工作内存中此变量的值, 在使用此变量前, 需要重新从主内存中load加载
3. 有序性
   解决重排序问题