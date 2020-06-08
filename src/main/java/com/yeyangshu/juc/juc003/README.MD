1. volatile
使一个变量在多个线程之间可见
A运行时会在堆内存进行复制，B修改变量A不一定知道，使用volatile就可以强制使所有线程去堆内存读取这个变量
http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
