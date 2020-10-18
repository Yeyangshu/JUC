volatile作用
1. volatile，稍弱同步机制，使一个变量在多个线程之间可见
A运行时会在堆内存进行复制，B修改变量A不一定知道，使用volatile就可以强制使所有线程去堆内存读取这个变量
http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
Java内存模型（即Java Memory Model，简称JMM）中规定了所有的变量都存储在主内存中，每条线程还有自己的工作内存（栈空间），线程的工作内存中保存了该线程使用到的变量到主内存副本拷贝，线程对变量的所有操作（读取、赋值）都必须在工作内存中进行，而不能直接读写主内存中的变量。不同线程之间无法直接访问对方工作内存中的变量，线程间通信（变量值的传递）均需要在主内存来完成。

2. 禁止指令重排序
- DCL单例(Double Check Lock)，双重检查锁

volatile正确使用方式：
- 确保他们自身状态的可见性
- 确保它们所引用对象的状态的可见性
- 标识一些重要的程序生命周期事件的发生
典型用法：状态标记
```
volatile boolean asleep;
...
    while (asleep) {
        countSomeSheep();
    }
```

- VolatileTest01：不加volatile，死循环
- VolatileTest02_DCL01：DCL单例，不加volatile可能出现指令重排序
- VolatileTest03_DCL02：DCL单例，加volatile禁止指令重排序
- VolatileTest04：volatile只能保证线程的可见性，不能保证原子性。与VolatileTest05进行对比
- VolatileTest05：synchronize能保证线程的可见性，也能保证原子性。与VolatileTest04进行对比