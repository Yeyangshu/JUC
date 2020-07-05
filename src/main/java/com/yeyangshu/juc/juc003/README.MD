1. volatile，稍弱同步机制
使一个变量在多个线程之间可见
A运行时会在堆内存进行复制，B修改变量A不一定知道，使用volatile就可以强制使所有线程去堆内存读取这个变量
http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html

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
- VolatileTest04：volatile只能保证线程的可见性，不能保证原子性。与VolatileTest05进行对比
- VolatileTest05：synchronize能保证线程的可见性，也能保证原子性。与VolatileTest04进行对比