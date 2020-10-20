# java.util.concurrent.atomic.AtomicXXX(CAS)、JUC同步锁
 
## 1 CAS类使用
- AtomicTest01：AtomicInteger使用，对比synchronized，VolatileTest05
- AtomicTest02：三种自增方式的对比
  - THREAD_COUNTS = 1
  ```
    -------------synchronized------------------
    synchronized：100000 time 75
    -------------AtomicLong------------------
    AtomicLong：100000 time 8
    -------------LongAdder------------------
    LongAdder：100000 time 11
  ```
  - THREAD_COUNTS = 100
  ```
    -------------synchronized------------------
    synchronized：10000000 time 136
    -------------AtomicLong------------------
    AtomicLong：10000000 time 161
    -------------LongAdder------------------
    LongAdder：10000000 time 120
  ```
  - THREAD_COUNTS = 10000
  ```
    -------------synchronized------------------
    synchronized：1000000000 time 13596
    -------------AtomicLong------------------
    AtomicLong：1000000000 time 25056
    -------------LongAdder------------------
    LongAdder：1000000000 time 5048
  ```
  - THREAD_COUNTS = 1000000
  ```
    太久
  ```
## 2 AtomicXXX.png：Atomic包的图片

## 3 基于CAS的新类型锁
- CasLock01_ReentrantLock01：复习synchronized，可重入锁
- CasLock01_ReentrantLock02：ReentrantLock替代 synchronized，lock.lock()/lock.unlock()
- CasLock01_ReentrantLock03：tryLock尝试锁定
- CasLock01_ReentrantLock04：lock.lockInterruptibly()可以被打断
- CasLock01_ReentrantLock05：公平锁
- CasLock02_CountDownLatch：门栓
- CasLock03_CyclicBarrier：循环栅栏
- CasLock04_Phaser：阶段器
- CasLock05_ReadWriteLock：读写锁
- CasLock06_Semaphore：信号灯，限流作用
- CasLock07_Exchanger：交换器
- CasLock08_LockSupport：线程阻塞工具类
- CasLock08_LockSupport01：park方法线程阻塞
- CasLock08_LockSupport02：unpark可以优先于park执行
- CasLock08_LockSupport03：调用两次park无法唤醒

## 4 CAS（compare and swap）无锁优化，乐观锁
### 4.1 CAS原理
AtomicInteger.incrementAndGet()调用getAndAddInt()方法
```
public final int incrementAndGet() {
    return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
}
```
getAndAddInt()方法调用compareAndSwap
```
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}
```
CAS，这个方法的实现需要涉及底层的unsafe类

假设目前的value=1，excepted应该也是1，newValue=2，先判断expected是不是等于1，是1才去改值，如果在改的过程中expected变成了2，说明有另一个线程改了这个值，那么compareAndSwap会再重试一次，此时value=2，excepted应该也是2，newValue=3，在修改的过程中expected还是2，那就没有其它线程去修改这个值，把value改成3。

当我判断新值还没有设定新值时发生了改变怎么办？
cas是cpu原语支持，是cpu指令级别上的支持，不会被打断。
```
compareAndSwap(value, excepted, newValue) {
  if (value == excepted) { // 判断是不是期望值，如果是新值赋给value
    value = newValue; //CPU原语支持，中间不能被打断
  }
  otherwise try again or fail
}
```
unsafe类：java不能直接访问操作系统底层，而是通过本地方法来访问。Unsafe类提供了硬件级别的原子操作
### 4.2 CAS问题
1. ABA问题。
因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是如果一个值原来是A，变成了B，又变成了A，那么使用CAS进行检查时会发现它的值没有发生变化，但是实际上却变化了。
ABA问题的解决思路就是使用版本号。在变量前面追加上版本号，每次变量更新的时候把版本号加一，那么A－B－A 就会变成1A-2B-3A。
从Java1.5开始JDK的atomic包里提供了一个类AtomicStampedReference来解决ABA问题。

2. CPU开销较大。
循环时间长开销大。自旋CAS如果长时间不成功，会给CPU带来非常大的执行开销。

3. 只能保证一个共享变量的原子操作。
当对一个共享变量执行操作时，我们可以使用循环CAS的方式来保证原子操作，但是对多个共享变量操作时，循环CAS就无法保证操作的原子性，这个时候就可以用锁，或者有一个取巧的办法，就是把多个共享变量合并成一个共享变量来操作。比如有两个共享变量i＝2,j=a，合并一下ij=2a，然后用CAS来操作ij。从Java1.5开始JDK提供了AtomicReference类来保证引用对象之间的原子性，你可以把多个变量放在一个对象里来进行CAS操作。

