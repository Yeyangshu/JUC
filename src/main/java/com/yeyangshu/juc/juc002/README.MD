每个Java对象都可以用作一个实现同步的锁，这写锁被称为内置锁或监视器锁。线程在进入同步代码块之前会自动获得锁，并且在退出同步代码块时自动释放锁。
获得锁的唯一途径就是进入由这个锁保护的同步代码块或方法。
Java内置锁相当于一种互斥锁，最多只有一个线程能持有这种锁。
- SynchronizedTest01:对某个对象加锁，synchronized (o)
- SynchronizedTest02:对当前对象加锁，synchronized(this)
- SynchronizedTest03:对方法加锁，等价于synchronized(this)，方法调用所在的对象
- SynchronizedTest04:synchronized(SynchronizedTest04.class)，锁的是SynchronizedTest04的对象（Class对象）
- SynchronizedTest05:synchronized既保证原子性，又保证可见性。
- SynchronizedTest06:同步方法和非同步方法可以同时调用
- SynchronizedTest07:synchronized银行案例
- SynchronizedTest08:synchronized是可重入锁，实现方式是为锁关联一个计数值和所有者线程。
- SynchronizedTest09:异常锁，t1异常，t2抢到锁


- synchronized底层实现
  - 早期（jdk 1.6之前）
    - 重量级锁：需要操作系统去申请锁
  - 后期（jdk 1.6之后）
    - 偏向锁：第一个访问锁的线程在其Java对象头(Mark Word)上被标记，记录偏向锁的线程ID
    - 自旋锁：当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。
    - 重量级锁：自旋锁循环结束未获取到锁，升级为重量级锁
- 对比
  - 执行时间短，线程数量少，自旋锁
  - 执行时间长，线程数量多，重量级锁
  
> Java对象头里的Mark Word里默认存储对象的HashCode、分代年龄和锁位标记
> Mark Word里存储的数据会随着锁标志位的变化而变化