# 容器

* [一、概览](#一概览)
    * [Collection](#collection)
      - [1.List](#1list)
      - [2.Set](#2set)
      - [3.Queue](#3queue)
    * [Map](#map)
* [二、容器中的设计模式](#二容器中的设计模式)
    * [迭代器模式](#迭代器模式)
    * [适配器模式](#适配器模式)

##  一、Java容器概述

容器主要包括 Collection 和 Map 两种，Collection 存储着对象的集合，而 Map 存储着键值对（两个对象）的映射表。

### Collection

<div align="center"> <img src="https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/73403d84-d921-49f1-93a9-d8fe050f3497.png" width="800px"> </div><br>

#### 1.List

- ArrayList：基于动态数组实现，支持随机访问。
- Vector：和ArrayList类似，但它是线程安全的。
- LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList还可以用做栈、队列和双向队列。

#### 2.Set

- TreeSet: 基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如HashSet，HashSet查找的时间复杂度为O(1)，TreeSet则为O(logN)。
- HashSet：基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用Iterator遍历HashSet得到的结果是不确定的。
- LinkedHashSet：具有HashSet的查找效果，且内部使用双向链表维护元素的插入顺序。

#### 3.Queue

- LinkedList：可以用它来实现双向队列。
- PriorityQueue：基于堆结构实现，可以用它来实现优先队列。

### Map

<div align="center"> <img src="https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/774d756b-902a-41a3-a3fd-81ca3ef688dc.png" width="500px"> </div><br>

- TreeMap：基于红黑树实现。
- HashMap：基于哈希表实现。
- HashTable： 和HashMap类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入HashTable并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用ConcurrentHashMap来支持线程安全，并且ConcurrentHashMap的效率会更高，因为ConcurrentHashMap引入了分段锁。
- LinkedHashMap：使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。



## 二、容器框架

![img](https://gitee.com/turnon/images/raw/master/images/java/container/java-container-structure.png)

## 三、容器中的设计模式

###迭代器模式

 <div align="center"> <img src="https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/93fb1d38-83f9-464a-a733-67b2e6bfddda.png" width="600px"> </div><br>

Collection 继承了 Iterable接口，其中的iterator()方法能够产生一个Iterator对象，通过这个对象就可以迭代遍历Collection中的元素。

从 JDK 1.5 之后可以使用 foreach 方法来遍历实现 Iterable 接口的聚合对象。

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for(String item : list){
  System.out.println(item);  
}
```

### 适配器模式

java.util.Arrays#aslist() 可以把数组类型转换为List类型。

```java
@SafeVarargs
public static <T> List<T> asList(T... a)
```

应该注意的是asList() 的参数为泛型的变长参数，不能使用基本类型数组作为参数，只能使用相应的包装类型数组。

```java
Intege[] arr = {1,2,3};
List list = Array.asList(arr);
```

也可以使用以下方式调用 asList() :

```java
List list = Arrays.asList(1,2,3);
```





### 