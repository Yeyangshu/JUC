# 线程池前置知识
- T01_Executor：顶层接口Executor，思想：将任务提交和任务执行进行解耦。
- T02_ExecutorService：在Executor上提供了一些能力，解决执行服务的生命周期问题，添加了管理生命周期的方法
- T03_Callable：一般是配合线程池和 Future 来使用的
- T04_Future：Future接口，提供了一些方法
- T05_FutureTask：既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值
- T06_CompletableFuture：可以组合不同的任务
# Java中的线程池
- T07_SingleThreadPool：创建一个单线程的线程池。
- T08_CachedThreadPool：创建一个可缓存的线程池。
- T09_FixedThreadPool：创建固定大小的线程池。
- T10_ScheduledThreadPool：创建一个支持定时以及周期性执行任务的大小无限的线程池。
- 
- 
- 