# 容器

- [Java容器概述](#容器)
  - [容器框架](#容器框架)
- [Java容器collection之List](#java容器collection之list)
  - [ArrayList 类](#arraylist-类)
  - [LinkedList 类](#linkedlist-类)
  - [ArrayList, Vector和LinkedList有什么区别](#arrayList,-vector和linkedlist有什么区别)
- [Java容器collection之Set](#java容器collection之set)
  - [HashSet 类](#hashset-类)
  - [TreeSet 类](#treeset-类)
  - [LinkedHashSet 类](#linkedhashset-类)
  - [EnumSet 类](#enumset-类)
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
- [Java容器Map](#java容器之map)
  - [Map架构](#map架构)
  - [HashMap类](#hashmap类)
  - [HashTable](#hashtable)
  - [TreeMap](#treemap)
  - [WeakHashMap](#weakhashmap)
  - [HashMap、Hashtable、TreeMap和WeakHashMap有什么区别](#hashmap、hashtable、treemap和weakhashmap有什么区别)
  - [用自定义类型作为HashMap或Hashtable的key需要注意哪些问题](#用自定义类型作为hashmap或hashtable的key需要注意哪些问题)

##  Java容器概述

### 容器框架

![img](https://gitee.com/turnon/images/raw/master/images/java/container/java-container-structure.png)

##  Java容器collection之List

### List 概述

`List` 接口定义：

```java
public interface List<E> extends Collection<E>
```

`List` 主要方法：

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/list-api.png" width="400"/>
</div>

`List` 常见子类：

- `ArrayList` - 动态数组。
- `LinkedList` - 双链表。

### ArrayList 类

#### ArrayList要点

#### ArrayList原理

##### 1.概览

##### 2.序列化

ArrayList 具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。ArrayList 重写了 `writeObject()` 和 `readObject()` 来控制只序列化数组中有元素填充那部分内容。

##### 3.扩容

添加元素时使用 `ensureCapacityInternal()` 方法来保证容量足够，如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 `oldCapacity + (oldCapacity >> 1)`，也就是旧容量的 1.5 倍。

扩容操作需要调用 `Arrays.copyOf()` 把原数组整个复制到新数组中，因此最好在创建 ArrayList 对象时就指定大概的容量大小，减少扩容操作的次数。

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}

private void ensureCapacityInternal(int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }

    ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```
##### 4.删除元素

需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，复制的代价很高。

```java
public E remove(int index) {
    rangeCheck(index);

    modCount++;
    E oldValue = elementData(index);

    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work

    return oldValue;
}
```
##### 5. Fail-Fast

modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

```java
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}
```
### LinkedList 类

#### LinkedList要点

LinkedList 基于双向链表实现。由于是双向链表，那么它的**顺序访问会非常高效，而随机访问效率比较低。**

LinkedList 定义：

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```
从 LinkedList 的定义，可以得出 LinkedList 的一些基本特性：

- LinkedList 是一个继承于 AbstractSequentialList 的双向链表。它也可以被当作堆栈、队列或双端队列进行操作。
- LinkedList 实现 List 接口，能对它进行队列操作。
- LinkedList 实现 Deque 接口，即能将 LinkedList 当作双端队列使用。
- LinkedList 实现了 Cloneable 接口，即覆盖了函数 clone()，能被克隆。
- LinkedList 实现 java.io.Serializable 接口，这意味着 LinkedList 支持序列化。
- LinkedList 是非线程安全的。

#### LinkedList原理

LinkedList 包含两个重要的成员：first 和 last。

```java
// 链表长度
transient int size = 0;

// 链表头节点
transient Node<E> first;

// 链表尾节点
transient Node<E> last;
```
- size 表示双链表中节点的个数，初始为 0。
- first 和 last 分别是双链表的头节点和尾节点。

Node 则表示链表中的实例。Node 中包含三个元素：prev, next, item。其中，prev 是该节点的上一个节点，next 是该节点的下一个节点，item 是该节点所包含的值。

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
    ...
}
```
### ArrayList, Vector和LinkedList有什么区别

- ArrayList 基于动态数组实现，LinkedList 基于双向链表实现；
- ArrayList 支持随机访问，所以访问速度更快；LinkedList 在任意位置添加删除元素更快；
- ArrayList 基于数组实现，存在容量限制，当元素数超过最大容量时，会自动扩容；LinkedList 基于双链表实现，不存在容量限制；
- ArrayList 和 LinkedList 都不是线程安全的。
- Vector是线程安全的。当在多线程中使用容器时（即多个线程会同时访问该容器），选用Vector较为安全。

## 资料

- [Java 编程思想（第 4 版）](https://item.jd.com/10058164.html)
- https://www.cnblogs.com/skywang12345/p/3308556.html
- http://www.cnblogs.com/skywang12345/p/3308807.html

##  Java容器collection之Set

### Set架构

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/Set-diagrams.png" width="400" />
</div>

- Set 继承了 Collection 的接口。实际上 Set 就是 Collection，只是行为略有不同：Set 集合不允许有重复元素。
- SortedSet 继承了 Set 的接口。SortedSet 中的内容是排序的唯一值，排序的方法是通过比较器(Comparator)。
- NavigableSet 继承了 SortedSet 的接口。相比于 NavigableSet 有一系列的导航方法；如"获取大于/等于某值的元素"、“获取小于/等于某值的元素”等等。
- AbstractSet 是一个抽象类，它继承于 AbstractCollection，AbstractCollection 实现了 Set 中的绝大部分函数，为 Set 的实现类提供了便利。
- HashSet 类依赖于 HashMap，它实际上是通过 HashMap 实现的。HashSet 中的元素是无序的。
- TreeSet 类依赖于 TreeMap，它实际上是通过 TreeMap 实现的。TreeSet 中的元素是有序的。
- LinkedHashSet 类具有 HashSet 的查找效率，且内部使用链表维护元素的插入顺序。
- EnumSet 中所有元素都必须是指定枚举类型的枚举值。

### Set 接口

Set 接口定义如下：

```java
public interface Set<E> extends Collection<E> {}
```

Set 继承了 Collection 的接口。实际上，Set 就是 Collection，二者提供的方法完全相同。

### SortedSet 接口

SortedSet 接口定义如下：

```java
public interface SortedSet<E> extends Set<E> {}
```

SortedSet 接口新扩展的方法：

- comparator - 返回 Comparator
- subSet - 返回指定区间的子集
- headSet - 返回小于指定元素的子集
- tailSet - 返回大于指定元素的子集
- first - 返回第一个元素
- last - 返回最后一个元素
- spliterator

### NavigableSet 接口

NavigableSet 接口定义如下：

```java
public interface NavigableSet<E> extends SortedSet<E> {}
```

NavigableSet 接口新扩展的方法：

- lower - 返回小于指定值的元素中最接近的元素
- higher - 返回大于指定值的元素中最接近的元素
- floor - 返回小于或等于指定值的元素中最接近的元素
- ceiling - 返回大于或等于指定值的元素中最接近的元素
- pollFirst - 检索并移除第一个（最小的）元素
- pollLast - 检索并移除最后一个（最大的）元素
- descendingSet - 返回反序排列的 Set
- descendingIterator - 返回反序排列的 Set 的迭代器
- subSet - 返回指定区间的子集
- headSet - 返回小于指定元素的子集
- tailSet - 返回大于指定元素的子集

### AbstractSet 抽象类

AbstractSet 抽象类定义如下：

```
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {}
```

AbstractSet 类提供 Set 接口的骨干实现，以最大限度地减少实现 Set 接口所需的工作。

事实上，主要的实现已经在 AbstractCollection 中完成。

### HashSet 类

采用散列函数，查找速度较快

HashSet 类定义如下：

```java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable {}
```
#### HashSet 要点

1.  HashSet 类通过继承 AbstractSet 实现了 Set 接口中的骨干方法。
2.  HashSet 实现了 Cloneable，所以支持克隆。
3.  HashSet 实现了 Serializable，所以支持序列化。
4.  HashSet 中存储的元素是无序的。
5.  HashSet 允许 null 值的元素。
6.  HashSet 不是线程安全的。

#### HashSet 原理

```java
// HashSet 的核心，通过维护一个 HashMap 实体来实现 HashSet 方法
private transient HashMap<E,Object> map;

// PRESENT 是用于关联 map 中当前操作元素的一个虚拟值
private static final Object PRESENT = new Object();
}
```
**HashSet 是基于 HashMap 实现的。**

HashSet 中维护了一个 HashMap 对象 map，HashSet 的重要方法，如 add、remove、iterator、clear、size 等都是围绕 map 实现的。

PRESENT 是用于关联 map 中当前操作元素的一个虚拟值。

HashSet 类中通过定义 `writeObject()` 和 `readObject()` 方法确定了其序列化和反序列化的机制。

### TreeSet 类

对元素进行排序，使用红黑树

TreeSet 类定义如下：

```java
public class TreeSet<E> extends AbstractSet<E>
    implements NavigableSet<E>, Cloneable, java.io.Serializable {}
```
#### TreeSet 要点

1.  TreeSet 类通过继承 AbstractSet 实现了 NavigableSet 接口中的骨干方法。
2.  TreeSet 实现了 Cloneable，所以支持克隆。
3.  TreeSet 实现了 Serializable，所以支持序列化。
4.  TreeSet 中存储的元素是有序的。排序规则是自然顺序或比较器（Comparator）中提供的顺序规则。
5.  TreeSet 不是线程安全的。

#### TreeSet 源码

```java
// TreeSet 的核心，通过维护一个 NavigableMap 实体来实现 TreeSet 方法
private transient NavigableMap<E,Object> m;

// PRESENT 是用于关联 map 中当前操作元素的一个虚拟值
private static final Object PRESENT = new Object();
```

**TreeSet 是基于 TreeMap 实现的。**

TreeSet 中维护了一个 NavigableMap 对象 map（实际上是一个 TreeMap 实例），TreeSet 的重要方法，如 add、remove、iterator、clear、size 等都是围绕 map 实现的。

PRESENT 是用于关联 map 中当前操作元素的一个虚拟值。TreeSet 中的元素都被当成 TreeMap 的 key 存储，而 value 都填的是 PRESENT。

### LinkedHashSet 类

既保证了顺序，又保证查找的速度

LinkedHashSet 类定义如下：

```java
public class LinkedHashSet<E>
    extends HashSet<E>
    implements Set<E>, Cloneable, java.io.Serializable {}
```
#### LinkedHashSet 要点

1.  LinkedHashSet 类通过继承 HashSet 实现了 Set 接口中的骨干方法。
2.  LinkedHashSet 实现了 Cloneable，所以支持克隆。
3.  LinkedHashSet 实现了 Serializable，所以支持序列化。
4.  LinkedHashSet 中存储的元素是按照插入顺序保存的。
5.  LinkedHashSet 不是线程安全的。

#### LinkedHashSet 原理

LinkedHashSet 有三个构造方法，无一例外，都是调用父类 HashSet 的构造方法。

```java
public LinkedHashSet(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor, true);
}
public LinkedHashSet(int initialCapacity) {
    super(initialCapacity, .75f, true);
}
public LinkedHashSet() {
    super(16, .75f, true);
}
```

需要强调的是：**LinkedHashSet 构造方法实际上调用的是父类 HashSet 的非 public 构造方法。**

```java
HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}
```

不同于 HashSet public 构造方法中初始化的 HashMap 实例，这个构造方法中，初始化了 LinkedHashMap 实例。

也就是说，实际上，LinkedHashSet 维护了一个双链表。由双链表的特性可以知道，它是按照元素的插入顺序保存的。所以，这就是 LinkedHashSet 中存储的元素是按照插入顺序保存的原理。

### EnumSet 类

EnumSet 类定义如下：

```java
public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>
    implements Cloneable, java.io.Serializable {}
```

#### EnumSet 要点

1.  EnumSet 类继承了 AbstractSet，所以有 Set 接口中的骨干方法。
2.  EnumSet 实现了 Cloneable，所以支持克隆。
3.  EnumSet 实现了 Serializable，所以支持序列化。
4.  EnumSet 通过 `<E extends Enum<E>>` 限定了存储元素必须是枚举值。
5.  EnumSet 没有构造方法，只能通过类中的 static 方法来创建 EnumSet 对象。
6.  EnumSet 是有序的。以枚举值在 EnumSet 类中的定义顺序来决定集合元素的顺序。
7.  EnumSet 不是线程安全的。

### 资料

- [Java 编程思想（Thinking in java）](https://item.jd.com/10058164.html)

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

PriorityQueue 类定义如下：

```java
public class PriorityQueue<E> extends AbstractQueue<E>
    implements java.io.Serializable {}
```

#### PriorityQueue 要点

1.  PriorityQueue 实现了 Serializable，支持序列化。
2.  PriorityQueue 类是基于优先级堆实现的无界优先级队列。
3.  PriorityQueue 中的元素根据自然顺序或 Comparator 提供的顺序排序。
4.  PriorityQueue 不接受 null 值元素。
5.  PriorityQueue 不是线程安全的。

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

##  Java容器之Map

### Map架构

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/Map-diagrams.png" />
</div>

1.  Map 是映射接口，Map 中存储的内容是键值对(key-value)。
2.  AbstractMap 是继承于 Map 的抽象类，它实现了 Map 中的大部分 API。其它 Map 的实现类可以通过继承 AbstractMap 来减少重复编码。
3.  SortedMap 是继承于 Map 的接口。SortedMap 中的内容是排序的键值对，排序的方法是通过比较器(Comparator)。
4.  NavigableMap 是继承于 SortedMap 的接口。相比于 SortedMap，NavigableMap 有一系列的导航方法；如"获取大于/等于某对象的键值对"、“获取小于/等于某对象的键值对”等等。
5.  TreeMap 继承于 AbstractMap，且实现了 NavigableMap 接口；因此，TreeMap 中的内容是“有序的键值对”！
6.  HashMap 继承于 AbstractMap，但没实现 NavigableMap 接口；因此，HashMap 的内容是“键值对，但不保证次序”！
7.  Hashtable 虽然不是继承于 AbstractMap，但它继承于 Dictionary(Dictionary 也是键值对的接口)，而且也实现 Map 接口；因此，Hashtable 的内容也是“键值对，也不保证次序”。但和 HashMap 相比，Hashtable 是线程安全的，而且它支持通过 Enumeration 去遍历。
8.  WeakHashMap 继承于 AbstractMap。它和 HashMap 的键类型不同，WeakHashMap 的键是**弱键**。

### HashMap类

#### HashMap 要点

HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。

基于哈希表的 Map 接口实现。该实现提供了所有可选的 Map 操作，并允许使用空值和空键。 （HashMap 类大致等同于 Hashtable，除了它是不同步的并且允许为空值。）这个类不保序；特别是，它的元素顺序可能会随着时间的推移变化。

HashMap 的一个实例有两个影响其性能的参数：初始容量和负载因子。

容量是哈希表中桶的数量，初始容量就是哈希表创建时的容量。

加载因子是散列表在其容量自动扩容之前被允许的最大饱和量。当哈希表中的 entry 数量超过负载因子和当前容量的乘积时，散列表就会被重新映射（即重建内部数据结构），一般散列表大约是存储桶数量的两倍。

通常，默认加载因子（0.75）在时间和空间成本之间提供了良好的平衡。较高的值会减少空间开销，但会增加查找成本（反映在大部分 HashMap 类的操作中，包括 get 和 put）。在设置初始容量时，应考虑映射中的条目数量及其负载因子，以尽量减少重新运行操作的次数。如果初始容量大于最大入口数除以负载因子，则不会发生重新刷新操作。

如果许多映射要存储在 HashMap 实例中，使用足够大的容量创建映射将允许映射存储的效率高于根据需要执行自动重新散列以增长表。请注意，使用多个具有相同 hashCode() 的密钥是降低任何散列表性能的一个可靠方法。为了改善影响，当键是 Comparable 时，该类可以使用键之间的比较顺序来帮助断开关系。

HashMap 不是并发安全的。

#### HashMap 源码

##### HashMap 定义

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {

    // 该表在初次使用时初始化，并根据需要调整大小。分配时，长度总是2的幂。
    transient Node<K,V>[] table;
    // 保存缓存的 entrySet()。请注意，AbstractMap 字段用于 keySet() 和 values()。
    transient Set<Map.Entry<K,V>> entrySet;
    // map 中的键值对数
    transient int size;
    // 这个HashMap被结构修改的次数结构修改是那些改变HashMap中的映射数量或者修改其内部结构（例如，重新散列）的修改。
    transient int modCount;
    // 下一个调整大小的值（容量*加载因子）。
    int threshold;
    // 散列表的加载因子
    final float loadFactor;
}
```

##### 构造方法

```java
public HashMap(); // 默认加载因子0.75
public HashMap(int initialCapacity); // 默认加载因子0.75；以 initialCapacity 初始化容量
public HashMap(int initialCapacity, float loadFactor); // 以 initialCapacity 初始化容量；以 loadFactor 初始化加载因子
public HashMap(Map<? extends K, ? extends V> m) // 默认加载因子0.75
```

##### put 方法的实现

put 方法大致的思路为：

1.  对 key 的 hashCode()做 hash，然后再计算 index;

2.  如果没碰撞直接放到 bucket 里；

3.  如果碰撞了，以链表的形式存在 buckets 后；

4.  如果碰撞导致链表过长(大于等于 TREEIFY_THRESHOLD)，就把链表转换成红黑树；

5.  如果节点已经存在就替换 old value(保证 key 的唯一性)

6.  如果 bucket 满了(超过 load factor \* current capacity)，就要 resize。

具体代码的实现如下：

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // tab 为空则创建
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 计算 index，并对 null 做处理
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        // 节点存在
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 该链为树
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // 该链为链表
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        // 写入
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

##### get 方法的实现

在理解了 put 之后，get 就很简单了。大致思路如下：

1.  bucket 里的第一个节点，直接命中；

2.  如果有冲突，则通过 key.equals(k)去查找对应的 entry

    - 若为树，则在树中通过 key.equals(k)查找，O(logn)；

    - 若为链表，则在链表中通过 key.equals(k)查找，O(n)。

具体代码的实现如下：

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        // 直接命中
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        // 未命中
        if ((e = first.next) != null) {
            // 在树中 get
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            // 在链表中 get
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

##### hash 方法的实现

在 get 和 put 的过程中，计算下标时，先对 hashCode 进行 hash 操作，然后再通过 hash 值进一步计算下标，如下图所示：

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/HashMap-hash.png" />
</div>

在对 hashCode() 计算 hash 时具体实现是这样的：

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

可以看到这个方法大概的作用就是：高 16bit 不变，低 16bit 和高 16bit 做了一个异或。

在设计 hash 方法时，因为目前的 table 长度 n 为 2 的幂，而计算下标的时候，是这样实现的(使用&位操作，而非%求余)：

```java
(n - 1) & hash
```

设计者认为这方法很容易发生碰撞。为什么这么说呢？不妨思考一下，在 n - 1 为 15(0x1111) 时，其实散列真正生效的只是低 4bit 的有效位，当然容易碰撞了。

因此，设计者想了一个顾全大局的方法(综合考虑了速度、作用、质量)，就是把高 16bit 和低 16bit 异或了一下。设计者还解释到因为现在大多数的 hashCode 的分布已经很不错了，就算是发生了碰撞也用 O(logn)的 tree 去做了。仅仅异或一下，既减少了系统的开销，也不会造成的因为高位没有参与下标的计算(table 长度比较小时)，从而引起的碰撞。

如果还是产生了频繁的碰撞，会发生什么问题呢？作者注释说，他们使用树来处理频繁的碰撞(we use trees to handle large sets of collisions in bins)，在 [JEP-180](http://openjdk.java.net/jeps/180) 中，描述了这个问题：

> Improve the performance of java.util.HashMap under high hash-collision conditions by using balanced trees rather than linked lists to store map entries. Implement the same improvement in the LinkedHashMap class.

之前已经提过，在获取 HashMap 的元素时，基本分两步：

1.  首先根据 hashCode()做 hash，然后确定 bucket 的 index；

2.  如果 bucket 的节点的 key 不是我们需要的，则通过 keys.equals()在链中找。

在 JDK8 之前的实现中是用链表解决冲突的，在产生碰撞的情况下，进行 get 时，两步的时间复杂度是 O(1)+O(n)。因此，当碰撞很厉害的时候 n 很大，O(n)的速度显然是影响速度的。

因此在 JDK8 中，利用红黑树替换链表，这样复杂度就变成了 O(1)+O(logn)了，这样在 n 很大的时候，能够比较理想的解决这个问题，在 JDK8：HashMap 的性能提升一文中有性能测试的结果。

##### resize 的实现

当 put 时，如果发现目前的 bucket 占用程度已经超过了 Load Factor 所希望的比例，那么就会发生 resize。在 resize 的过程，简单的说就是把 bucket 扩充为 2 倍，之后重新计算 index，把节点再放到新的 bucket 中。

当超过限制的时候会 resize，然而又因为我们使用的是 2 次幂的扩展(指长度扩为原来 2 倍)，所以，元素的位置要么是在原位置，要么是在原位置再移动 2 次幂的位置。

怎么理解呢？例如我们从 16 扩展为 32 时，具体的变化如下所示：

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/HashMap-resize-01.png" />
</div>

因此元素在重新计算 hash 之后，因为 n 变为 2 倍，那么 n-1 的 mask 范围在高位多 1bit(红色)，因此新的 index 就会发生这样的变化：

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/HashMap-resize-02.png" />
</div>

因此，我们在扩充 HashMap 的时候，不需要重新计算 hash，只需要看看原来的 hash 值新增的那个 bit 是 1 还是 0 就好了，是 0 的话索引没变，是 1 的话索引变成“原索引+oldCap”。可以看看下图为 16 扩充为 32 的 resize 示意图：

<div align="center">
<img src="https://gitee.com/turnon/images/raw/master/images/java/container/HashMap-resize-03.png" />
</div>

这个设计确实非常的巧妙，既省去了重新计算 hash 值的时间，而且同时，由于新增的 1bit 是 0 还是 1 可以认为是随机的，因此 resize 的过程，均匀的把之前的冲突的节点分散到新的 bucket 了。

```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        // 超过最大值就不再扩充了，就只好随你碰撞去吧
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 没超过最大值，就扩充为原来的 2 倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }

    // 计算新的 resize 上限
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        // 把每个 bucket 都移动到新的 buckets 中
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        // 原索引
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        // 原索引+oldCap
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    // 原索引放到bucket里
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    // 原索引+oldCap放到bucket里
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

#### 小结

我们现在可以回答开始的几个问题，加深对 HashMap 的理解：

1.  什么时候会使用 HashMap？他有什么特点？

    是基于 Map 接口的实现，存储键值对时，它可以接收 null 的键值，是非同步的，HashMap 存储着 Entry(hash, key, value, next)对象。

2. 你知道 HashMap 的工作原理吗？

    通过 hash 的方法，通过 put 和 get 存储和获取对象。存储对象时，我们将 K/V 传给 put 方法时，它调用 hashCode 计算 hash 从而得到 bucket 位置，进一步存储，HashMap 会根据当前 bucket 的占用情况自动调整容量(超过 Load Facotr 则 resize 为原来的 2 倍)。获取对象时，我们将 K 传给 get，它调用 hashCode 计算 hash 从而得到 bucket 位置，并进一步调用 equals()方法确定键值对。如果发生碰撞的时候，Hashmap 通过链表将产生碰撞冲突的元素组织起来，在 Java 8 中，如果一个 bucket 中碰撞冲突的元素超过某个限制(默认是 8)，则使用红黑树来替换链表，从而提高速度。

3. 你知道 get 和 put 的原理吗？equals()和 hashCode()的都有什么作用？

    通过对 key 的 hashCode()进行 hashing，并计算下标( n-1 & hash)，从而获得 buckets 的位置。如果产生碰撞，则利用 key.equals()方法去链表或树中去查找对应的节点

4. 你知道 hash 的实现吗？为什么要这样实现？

    在 Java 1.8 的实现中，是通过 hashCode()的高 16 位异或低 16 位实现的：(h = k.hashCode()) ^ (h >>> 16)，主要是从速度、功效、质量来考虑的，这么做可以在 bucket 的 n 比较小的时候，也能保证考虑到高低 bit 都参与到 hash 的计算中，同时不会有太大的开销。

5. 如果 HashMap 的大小超过了负载因子(load factor)定义的容量，怎么办？

    如果超过了负载因子(默认 0.75)，则会重新 resize 一个原来长度两倍的 HashMap，并且重新调用 hash 方法。

### HashTable

### TreeMap

#### TreeMap 要点

- TreeMap 基于红黑树实现。
- TreeMap 是有序的。它的排序规则是：根据 map 中的 key 的自然顺序或提供的比较器的比较顺序。
- TreeMap 不是并发安全的。

#### TreeMap 源码

##### put 方法

```java
public V put(K key, V value) {
    Entry<K,V> t = root;
    // 如果根节点为 null，插入第一个节点
    if (t == null) {
        compare(key, key); // type (and possibly null) check

        root = new Entry<>(key, value, null);
        size = 1;
        modCount++;
        return null;
    }
    int cmp;
    Entry<K,V> parent;
    // split comparator and comparable paths
    Comparator<? super K> cpr = comparator;
    // 每个节点的左孩子节点的值小于它；右孩子节点的值大于它
    // 如果有比较器，使用比较器进行比较
    if (cpr != null) {
        do {
            parent = t;
            cmp = cpr.compare(key, t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else
                return t.setValue(value);
        } while (t != null);
    }
    // 没有比较器，使用 key 的自然顺序进行比较
    else {
        if (key == null)
            throw new NullPointerException();
        @SuppressWarnings("unchecked")
            Comparable<? super K> k = (Comparable<? super K>) key;
        do {
            parent = t;
            cmp = k.compareTo(t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else
                return t.setValue(value);
        } while (t != null);
    }
    // 通过上面的遍历未找到 key 值，则新插入节点
    Entry<K,V> e = new Entry<>(key, value, parent);
    if (cmp < 0)
        parent.left = e;
    else
        parent.right = e;
    // 插入后，为了维持红黑树的平衡需要调整
    fixAfterInsertion(e);
    size++;
    modCount++;
    return null;
}
```

##### get 方法

```java
public V get(Object key) {
    Entry<K,V> p = getEntry(key);
    return (p==null ? null : p.value);
}

final Entry<K,V> getEntry(Object key) {
    // Offload comparator-based version for sake of performance
    if (comparator != null)
        return getEntryUsingComparator(key);
    if (key == null)
        throw new NullPointerException();
    @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
    Entry<K,V> p = root;
    // 按照二叉树搜索的方式进行搜索，搜到返回
    while (p != null) {
        int cmp = k.compareTo(p.key);
        if (cmp < 0)
            p = p.left;
        else if (cmp > 0)
            p = p.right;
        else
            return p;
    }
    return null;
}
```

##### remove 方法

```java
public V remove(Object key) {
    Entry<K,V> p = getEntry(key);
    if (p == null)
        return null;

    V oldValue = p.value;
    deleteEntry(p);
    return oldValue;
}
private void deleteEntry(Entry<K,V> p) {
    modCount++;
    size--;

    // 如果当前节点有左右孩子节点，使用后继节点替换要删除的节点
    // If strictly internal, copy successor's element to p and then make p
    // point to successor.
    if (p.left != null && p.right != null) {
        Entry<K,V> s = successor(p);
        p.key = s.key;
        p.value = s.value;
        p = s;
    } // p has 2 children

    // Start fixup at replacement node, if it exists.
    Entry<K,V> replacement = (p.left != null ? p.left : p.right);

    if (replacement != null) { // 要删除的节点有一个孩子节点
        // Link replacement to parent
        replacement.parent = p.parent;
        if (p.parent == null)
            root = replacement;
        else if (p == p.parent.left)
            p.parent.left  = replacement;
        else
 D:\codes\zp\java\database\docs\redis\分布式锁.md           p.parent.right = replacement;

        // Null out links so they are OK to use by fixAfterDeletion.
        p.left = p.right = p.parent = null;

        // Fix replacement
        if (p.color == BLACK)
            fixAfterDeletion(replacement);
    } else if (p.parent == null) { // return if we are the only node.
        root = null;
    } else { //  No children. Use self as phantom replacement and unlink.
        if (p.color == BLACK)
            fixAfterDeletion(p);

        if (p.parent != null) {
            if (p == p.parent.left)
                p.parent.left = null;
            else if (p == p.parent.right)
                p.parent.right = null;
            p.parent = null;
        }
    }
}
```
#### TreeMap 示例

```java
public class TreeMapDemo {

    private static final String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            treeMap.put(i, chars[i]);
        }
        System.out.println(treeMap);
        Integer low = treeMap.firstKey();
        Integer high = treeMap.lastKey();
        System.out.println(low);
        System.out.println(high);
        Iterator<Integer> it = treeMap.keySet().iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) { low = it.next(); }
            if (i == 6) { high = it.next(); } else { it.next(); }
        }
        System.out.println(low);
        System.out.println(high);
        System.out.println(treeMap.subMap(low, high));
        System.out.println(treeMap.headMap(high));
        System.out.println(treeMap.tailMap(low));
    }
}
```
### WeakHashMap

WeakHashMap 的定义如下：

```java
public class WeakHashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V> {}
```

WeakHashMap 继承了 AbstractMap，实现了 Map 接口。

和 HashMap 一样，WeakHashMap 也是一个散列表，它存储的内容也是键值对(key-value)映射，而且键和值都可以是 null。

不过 WeakHashMap 的键是**弱键**。在 WeakHashMap 中，当某个键不再正常使用时，会被从 WeakHashMap 中被自动移除。更精确地说，对于一个给定的键，其映射的存在并不阻止垃圾回收器对该键的丢弃，这就使该键成为可终止的，被终止，然后被回收。某个键被终止时，它对应的键值对也就从映射中有效地移除了。

这个**弱键**的原理呢？大致上就是，通过 WeakReference 和 ReferenceQueue 实现的。

WeakHashMap 的 key 是**弱键**，即是 WeakReference 类型的；ReferenceQueue 是一个队列，它会保存被 GC 回收的**弱键**。实现步骤是：

1.  新建 WeakHashMap，将**键值对**添加到 WeakHashMap 中。
    实际上，WeakHashMap 是通过数组 table 保存 Entry(键值对)；每一个 Entry 实际上是一个单向链表，即 Entry 是键值对链表。
2.  当某**弱键**不再被其它对象引用，并被 GC 回收时。在 GC 回收该**弱键**时，这个**弱键**也同时会被添加到 ReferenceQueue(queue)队列中。
3.  当下一次我们需要操作 WeakHashMap 时，会先同步 table 和 queue。table 中保存了全部的键值对，而 queue 中保存被 GC 回收的键值对；同步它们，就是删除 table 中被 GC 回收的键值对。

这就是**弱键**如何被自动从 WeakHashMap 中删除的步骤了。

和 HashMap 一样，WeakHashMap 是不同步的。可以使用 Collections.synchronizedMap 方法来构造同步的 WeakHashMap。

### HashMap、Hashtable、TreeMap和WeakHashMap有什么区别

### 用自定义类型作为HashMap或Hashtable的key需要注意哪些问题



### 