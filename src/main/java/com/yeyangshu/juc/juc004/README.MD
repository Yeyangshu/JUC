##java.util.concurrent.atomic.AtomicXXX(CAS)、JUC同步锁
1. CAS（compare and swap）无锁优化，乐观锁
- AtomicTest01：AtomicInteger使用，对比synchronized，VolatileTest05
  - CAS
    - ABA问题
  - 自增方式
    - synchronized、atomicXXX、LongAdder
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
- AtomicXXX.png：Atomic包的图片
2. 基于CAS的新类型锁
- CasLock01_ReentrantLock01：复习synchronized，可重入锁
- CasLock01_ReentrantLock02：ReentrantLock替代 synchronized，lock.lock()/lock.unlock()
- CasLock01_ReentrantLock03：tryLock尝试锁定
- CasLock01_ReentrantLock04：lock.lockInterruptibly()可以被打断
- CasLock01_ReentrantLock05：公平锁

CAS
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
```
compareAndSwap(value, excepted, newValue) {
  if (value == excepted) { // 判断是不是期望值，如果是新值赋给value
    value = newValue; //CPU原语支持，中间不能被打断
  }
  otherwise try again or fail
}

假设目前的value=1，excepted应该也是1，newValue=2，先判断expected是不是等于1，是1才去改值，如果在改的过程中expected变成了2，
说明有另一个线程改了这个值，那么compareAndSwap会再重试一次，此时value=2，excepted应该也是2，newValue=3，在修改的过程中
expected还是2，那就没有其它线程去修改这个值，把value改成3。
```
unsafe类：java不能直接访问操作系统底层，而是通过本地方法来访问。Unsafe类提供了硬件级别的原子操作

