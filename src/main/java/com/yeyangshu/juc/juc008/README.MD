并发容器

- c01_HashTableToConcurrentHashMap：Map演变过程
  - Map01_HashTable：自带锁，线程安全
  - Map02_HashMap：删除了锁
  - Map03_SynchronizedHashMap：加了锁的HashMap
  - Map04_ConcurrentHashMap：读效率快
- c02_VectorToQueue：Collection演变过程
  - TicketSeller01：ArrayList
  - TicketSeller02：Vector自带锁，线程安全
  - TicketSeller03：LinkedList，加锁，不会出现问题，但是效率慢
  - TicketSeller04_ConcurrentLinkedQueue：高并发

#### 需要根据实际情况压测进行容器选择！！！
一个线程，简单的用HashMap、ArrayList、LinkedList
高并发执行时间短用ConcurrentHashMap、ConcurrentLinkedQueue
线程并发量不高，代码执行时间长用Synchronized

- c03_Concurrent：常用高并发容器
  - Concurrent01_ConcurrentMap：ConcurrentHashMap高并发无序、ConcurrentSkipListMap高并发有序
  - Concurrent02_CopyOnWrite：写时复制，适用于读多写少
  - Concurrent02_SynchronizedList：与CopyOnWrite对比
  - Concurrent03_BlockingQueue01_ConcurrentQueue
  - Concurrent03_BlockingQueue02_LinkedBlockingQueue
  - Concurrent03_BlockingQueue03_ArrayBlockingQueue
  - Concurrent03_BlockingQueue04_DelayQueue
  - Concurrent03_BlockingQueue05_SynchronousQueue
  - Concurrent03_BlockingQueue06_TransferQueue
#### 面试题：Queue和List区别？
  - Queue添加了很多对线程友好的API offer peek poll
  - Queue子类型BlockingQueue又添加了put 和 take
  - put take -> 实现了阻塞操作