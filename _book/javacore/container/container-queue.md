# 容器

- [Java容器collection之Queue](#java容器collection之queue)
  - [Queue 架构](#queue-架构)
  - [Queue 接口](#queue-接口)
  - [BlockingQueue 接口](#blockingqueue-接口)
  - [AbstractQueue 抽象类](#abstractqueue-抽象类)
  - [PriorityQueue 类](#priorityqueue-类)
  - [PriorityBlockingQueue 类](#priorityblockingqueue-类)
  - [LinkedBlockingQueue 类](#linkedblockingqueue-类)
  - [ArrayBlockingQueue 类](#arrayblockingqueue-类)
  - [SynchronousQueue](#synchronousqueue)

##  Java容器collection之Queue

### Queue 架构

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/Queue-diagrams.png" />
</div>

### Queue 接口

Queue 接口定义如下：

```java
public interface Queue<E> extends Collection<E> {}
```

### BlockingQueue 接口

BlockingQueue 接口定义如下：

```java
public interface BlockingQueue<E> extends Queue<E> {}
```

BlockingQueue 顾名思义，是一个阻塞队列。

在 BlockingQueue 中，如果获取队列元素但是队列为空时，会阻塞，等待队列中有元素再返回；如果添加元素时，如果队列已满，那么等到队列可以放入新元素时再放入。

BlockingQueue 对插入操作、移除操作、获取元素操作提供了四种不同的方法用于不同的场景中使用：

1.  抛出异常；
2.  返回特殊值（null 或 true/false，取决于具体的操作）；
3.  阻塞等待此操作，直到这个操作成功；
4.  阻塞等待此操作，直到成功或者超时指定时间。

总结如下：

|         | _Throws exception_ | _Special value_ | _Blocks_         | _Times out_          |
| ------- | ------------------ | --------------- | ---------------- | -------------------- |
| Insert  | add(e)             | offer(e)        | put(e)           | offer(e, time, unit) |
| Remove  | remove()           | poll()          | take()           | poll(time, unit)     |
| Examine | element()          | peek()          | _not applicable_ | _not applicable_     |

BlockingQueue 的各个实现类都遵循了这些规则。

BlockingQueue 不接受 null 值元素。

### AbstractQueue 抽象类

AbstractQueue 抽象类定义如下：

```java
public abstract class AbstractQueue<E>
    extends AbstractCollection<E>
    implements Queue<E> {}
```

AbstractQueue 类提供 Queue 接口的骨干实现，以最大限度地减少实现 Queue 接口所需的工作。

### PriorityQueue 类

Java中PriorityQueue（优先队列）通过二叉小顶堆实现，可以用一棵完全二叉树表示。优先队列的作用是能保证每次取出的元素都是队列中权值最小的（Java的优先队列每次取最小元素，C++的优先队列每次取最大元素）。这里牵涉到了大小关系，元素大小的评判可以通过元素本身的自然顺序（natural ordering），也可以通过构造时传入的比较器（Comparator，类似于C++的仿函数）。

PriorityQueue 类定义如下：

```java
public class PriorityQueue<E> extends AbstractQueue<E>
    implements java.io.Serializable {}
```

> [深入理解Java PriorityQueue](https://www.cnblogs.com/CarpenterLee/p/5488070.html)

#### PriorityQueue 要点

1.  PriorityQueue 实现了 Serializable，支持序列化。
2.  PriorityQueue 类是基于优先级堆实现的无界优先级队列。
3.  PriorityQueue 中的元素根据自然顺序或 Comparator 提供的顺序排序。
4.  PriorityQueue 不接受 null 值元素。
5.  PriorityQueue 不是线程安全的。
6.  入队和出 队的时间复杂度是 O(log(n))。

#### PriorityQueue 原理

Java中*PriorityQueue*实现了*Queue*接口，不允许放入`null`元素；其通过堆实现，具体说是通过完全二叉树（*complete binary tree*）实现的**小顶堆**（任意一个非叶子节点的权值，都不大于其左右子节点的权值），也就意味着可以通过数组来作为*PriorityQueue*的底层实现。

![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820155107908-1229529104.png)



上图中我们给每个元素按照层序遍历的方式进行了编号，如果你足够细心，会发现父节点和子节点的编号是有联系的，更确切的说父子节点的编号之间有如下关系：

`leftNo = parentNo*2+1`

`rightNo = parentNo*2+2`

`parentNo = (nodeNo-1)/2`

通过上述三个公式，可以轻易计算出某个节点的父节点以及子节点的下标。这也就是为什么可以直接用数组来存储堆的原因。

*PriorityQueue*的`peek()`和`element`操作是常数时间，`add()`, `offer()`, 无参数的`remove()`以及`poll()`方法的时间复杂度都是*log(N)*。

##### add()和offer()

`add(E e)`和`offer(E e)`的语义相同，都是向优先队列中插入元素，只是`Queue`接口规定二者对插入失败时的处理不同，前者在插入失败时抛出异常，后则则会返回`false`。对于*PriorityQueue*这两个方法其实没什么差别


![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820155126062-2078961640.png)


新加入的元素可能会破坏小顶堆的性质，因此需要进行必要的调整。

```java
//offer(E e)
public boolean offer(E e) {
    if (e == null)//不允许放入null元素
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length)
        grow(i + 1);//自动扩容
    size = i + 1;
    if (i == 0)//队列原来为空，这是插入的第一个元素
        queue[0] = e;
    else
        siftUp(i, e);//调整
    return true;
}
```

上述代码中，扩容函数`grow()`类似于`ArrayList`里的`grow()`函数，就是再申请一个更大的数组，并将原数组的元素复制过去，这里不再赘述。需要注意的是`siftUp(int k, E x)`方法，该方法用于插入元素`x`并维持堆的特性。

```java
//siftUp()
private void siftUp(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;//parentNo = (nodeNo-1)/2
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)//调用比较器的比较方法
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}
```

新加入的元素`x`可能会破坏小顶堆的性质，因此需要进行调整。调整的过程为：**从k指定的位置开始，将x逐层与当前点的parent进行比较并交换，直到满足x >= queue[parent]为止**。注意这里的比较可以是元素的自然顺序，也可以是依靠比较器的顺序。

##### element()和peek()

`element()`和`peek()`的语义完全相同，都是获取但不删除队首元素，也就是队列中权值最小的那个元素，二者唯一的区别是当方法失败时前者抛出异常，后者返回`null`。根据小顶堆的性质，堆顶那个元素就是全局最小的那个；由于堆用数组表示，根据下标关系，`0`下标处的那个元素既是堆顶元素。所以**直接返回数组0下标处的那个元素即可**。

![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820155145417-413367035.png)


代码也就非常简洁：

```java
//peek()
public E peek() {
    if (size == 0)
        return null;
    return (E) queue[0];//0下标处的那个元素就是最小的那个
}
```

##### remove()和poll()

`remove()`和`poll()`方法的语义也完全相同，都是获取并删除队首元素，区别是当方法失败时前者抛出异常，后者返回`null`。由于删除操作会改变队列的结构，为维护小顶堆的性质，需要进行必要的调整。

![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820155202646-205052104.png)


代码如下：

```java
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];//0下标处的那个元素就是最小的那个
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x);//调整
    return result;
}
```

上述代码首先记录`0`下标处的元素，并用最后一个元素替换`0`下标位置的元素，之后调用`siftDown()`方法对堆进行调整，最后返回原来`0`下标处的那个元素（也就是最小的那个元素）。重点是`siftDown(int k, E x)`方法，该方法的作用是**从k指定的位置开始，将x逐层向下与当前点的左右孩子中较小的那个交换，直到x小于或等于左右孩子中的任何一个为止**。

```
//siftDown()
private void siftDown(int k, E x) {
    int half = size >>> 1;
    while (k < half) {
        //首先找到左右孩子中较小的那个，记录到c里，并用child记录其下标
        int child = (k << 1) + 1;//leftNo = parentNo*2+1
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
        if (comparator.compare(x, (E) c) <= 0)
            break;
        queue[k] = c;//然后用c取代原来的值
        k = child;
    }
    queue[k] = x;
}
```

##### remove(Object o)

`remove(Object o)`方法用于删除队列中跟`o`相等的某一个元素（如果有多个相等，只删除一个），该方法不是*Queue*接口内的方法，而是*Collection*接口的方法。由于删除操作会改变队列结构，所以要进行调整；又由于删除元素的位置可能是任意的，所以调整过程比其它函数稍加繁琐。具体来说，`remove(Object o)`可以分为2种情况：1. 删除的是最后一个元素。直接删除即可，不需要调整。2. 删除的不是最后一个元素，从删除点开始以最后一个元素为参照调用一次`siftDown()`即可。此处不再赘述。

![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820155225667-361597973.png)

具体代码如下：

```java
//remove(Object o)
public boolean remove(Object o) {
    //通过遍历数组的方式找到第一个满足o.equals(queue[i])元素的下标
    int i = indexOf(o);
    if (i == -1)
        return false;
    int s = --size;
    if (s == i) //情况1
        queue[i] = null;
    else {
        E moved = (E) queue[s];
        queue[s] = null;
        siftDown(i, moved);//情况2
        ......
    }
    return true;
}
```
####  最大堆获取数组中最小的几个数

```java
public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k){
  ArrayList<Integer> result = new ArrayList<Integer>();
  int length = input.length;
  if(k>length||k==0){
    return result;
  }
  PriorityQueue<Integer> maxHeap = bew PriorityQueue<Integer>(k,new Comparator<Integer>()){
    public int compare(Integer o1, Integer o2){
      return o2.compareTo(o1);
    }
  }
  for(int i=0;i<length;i++){
    if(maxHeap.size()!=k){
      maxHeap.offer(input[i]);
    }else if(maxHeap.peek()> input[i]){
      Integer temp = maxHeap.poll();
      temp = null;
      maxHeap.offer(input[i]);
    }
  }
  for(Integer integer:maxHeap){
    System.out.println(integer);
    result.add(integer);
  }
  return result;
}
```

上述代码是构建最大堆，最大堆的栈顶是堆的最大的元素，最大的元素比数组中任意一个 元素小，说明了最大堆这些元素是数组中最小的几个元素。

**上述代码为何需要重现写比较器函数 compare()???** 

答：需要查看优先队列的源码，如下源码所示，添加元素需要比较新的元素与父节点的元 素，如果比较器比较结果大等于 0，那么结束添加过程添加完成，说明在构建最大堆时候，要想 使得元素是对父节点的元素小才结束循环，那么必须重新写比较器函数，调换两者的比较 顺序即可。

![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820162130035-313184384.png)
![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190820162223193-644380970.png)

####  最小堆获取数组中最大的几个数



### PriorityBlockingQueue 类

PriorityBlockingQueue 类定义如下：

```java
public class PriorityBlockingQueue<E> extends AbstractQueue<E>
    implements BlockingQueue<E>, java.io.Serializable {}
```

#### PriorityBlockingQueue 要点

1.  PriorityBlockingQueue 实现了 BlockingQueue，也是一个阻塞队列。
2.  PriorityBlockingQueue 实现了 Serializable，支持序列化。
3.  PriorityBlockingQueue 可以视为 PriorityQueue 的线程安全版本。
4.  PriorityBlockingQueue 不接受 null 值元素。
5.  PriorityBlockingQueue 的插入操作 put 方法不会 block，因为它是无界队列（take 方法在队列为空的时候会阻塞）。

#### PriorityBlockingQueue 原理

PriorityBlockingQueue 有两个重要成员：

```java
private transient Object[] queue;
private final ReentrantLock lock;
```

- queue 是一个 Object 数组，用于保存 PriorityBlockingQueue 的元素。
- 而可重入锁 lock 则用于在执行插入、删除操作时，保证这个方法在当前线程释放锁之前，其他线程不能访问。

PriorityBlockingQueue 的容量虽然有初始化大小，但是不限制大小，如果当前容量已满，插入新元素时会自动扩容。

### LinkedBlockingQueue 类

LinkedBlockingQueue 类定义如下：

```java
public class LinkedBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable {}
```

#### LinkedBlockingQueue 要点

1.  LinkedBlockingQueue 实现了 BlockingQueue，也是一个阻塞队列。
2.  LinkedBlockingQueue 实现了 Serializable，支持序列化。
3.  LinkedBlockingQueue 是基于单链表实现的阻塞队列，可以当做无界队列也可以当做有界队列来使用。
4.  LinkedBlockingQueue 中元素按照插入顺序保存（FIFO）。

#### LinkedBlockingQueue 原理

```java
// 队列容量
private final int capacity;

// 队列中的元素数量
private final AtomicInteger count = new AtomicInteger(0);

// 队头
private transient Node<E> head;

// 队尾
private transient Node<E> last;

// take, poll, peek 等读操作的方法需要获取到这个锁
private final ReentrantLock takeLock = new ReentrantLock();

// 如果读操作的时候队列是空的，那么等待 notEmpty 条件
private final Condition notEmpty = takeLock.newCondition();

// put, offer 等写操作的方法需要获取到这个锁
private final ReentrantLock putLock = new ReentrantLock();

// 如果写操作的时候队列是满的，那么等待 notFull 条件
private final Condition notFull = putLock.newCondition();
```

这里用了两个锁，两个 Condition，简单介绍如下：

- takeLock 和 notEmpty 搭配：如果要获取（take）一个元素，需要获取 takeLock 锁，但是获取了锁还不够，如果队列此时为空，还需要队列不为空（notEmpty）这个条件（Condition）。
- putLock 需要和 notFull 搭配：如果要插入（put）一个元素，需要获取 putLock 锁，但是获取了锁还不够，如果队列此时已满，还需要队列不是满的（notFull）这个条件（Condition）。

### ArrayBlockingQueue 类

ArrayBlockingQueue 类定义如下：

```java
public class ArrayBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable {}
```

#### ArrayBlockingQueue 要点

1.  ArrayBlockingQueue 实现了 BlockingQueue，也是一个阻塞队列。
2.  ArrayBlockingQueue 实现了 Serializable，支持序列化。
3.  ArrayBlockingQueue 是基于数组实现的无界阻塞队列。

#### ArrayBlockingQueue 原理

ArrayBlockingQueue 的重要成员如下：

```java
// 用于存放元素的数组
final Object[] items;
// 下一次读取操作的位置
int takeIndex;
// 下一次写入操作的位置
int putIndex;
// 队列中的元素数量
int count;

// 以下几个就是控制并发用的同步器
final ReentrantLock lock;
private final Condition notEmpty;
private final Condition notFull;
```

ArrayBlockingQueue 实现并发同步的原理就是，读操作和写操作都需要获取到 AQS 独占锁才能进行操作。

- 如果队列为空，这个时候读操作的线程进入到读线程队列排队，等待写线程写入新的元素，然后唤醒读线程队列的第一个等待线程。
- 如果队列已满，这个时候写操作的线程进入到写线程队列排队，等待读线程将队列元素移除，然后唤醒写线程队列的第一个等待线程。

对于 ArrayBlockingQueue，我们可以在构造的时候指定以下三个参数：

1.  队列容量，其限制了队列中最多允许的元素个数；
2.  指定独占锁是公平锁还是非公平锁。非公平锁的吞吐量比较高，公平锁可以保证每次都是等待最久的线程获取到锁；
3.  可以指定用一个集合来初始化，将此集合中的元素在构造方法期间就先添加到队列中。

### SynchronousQueue

SynchronousQueue 定义如下：

```java
public class SynchronousQueue<E> extends AbstractQueue<E>
    implements BlockingQueue<E>, java.io.Serializable {}
```

1.  SynchronousQueue 这个类，不过它在线程池的实现类 ScheduledThreadPoolExecutor 中得到了应用。
2.  SynchronousQueue 的队列其实是虚的，其不提供任何空间（一个都没有）来存储元素。数据必须从某个写线程交给某个读线程，而不是写到某个队列中等待被消费。
3.  SynchronousQueue 中不能使用 peek 方法（在这里这个方法直接返回 null），peek 方法的语义是只读取不移除，显然，这个方法的语义是不符合 SynchronousQueue 的特征的。
4.  SynchronousQueue 也不能被迭代，因为根本就没有元素可以拿来迭代的。
5.  虽然 SynchronousQueue 间接地实现了 Collection 接口，但是如果你将其当做 Collection 来用的话，那么集合是空的。
6.  当然，SynchronousQueue 也不允许传递 null 值的（并发包中的容器类好像都不支持插入 null 值，因为 null 值往往用作其他用途，比如用于方法的返回值代表操作失败）。

### 资料

[解读 Java 并发队列 BlockingQueue](http://www.importnew.com/28053.html)

### 



### 