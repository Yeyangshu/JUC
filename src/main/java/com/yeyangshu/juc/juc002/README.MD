SynchronizedTest01:对某个对象加锁
SynchronizedTest02:对当前对象加锁
SynchronizedTest03:对方法加锁，等价于synchronized(this)
SynchronizedTest04:synchronized(SynchronizedTest04.class)，锁的是SynchronizedTest04的对象。
SynchronizedTest05:synchronized既保证原子性，又保证可见性。
SynchronizedTest06:同步方法和非同步方法可以同时调用
SynchronizedTest07:synchronized银行案例
SynchronizedTest08:synchronized是可重入锁
SynchronizedTest09:异常锁,t1异常，t2抢到锁

synchronized底层实现
  早期（jdk 1.6之前）
    重量级锁：需要操作系统去申请锁
  后期（jdk 1.6之后）
    偏向锁：第一个访问锁的线程在其头上被标记
    自旋锁：当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。
    重量级锁：自旋锁循环结束未获取到锁，升级为重量级锁

对比
  执行时间短，线程数量少，自旋锁
  执行时间长，线程数量多，重量级锁