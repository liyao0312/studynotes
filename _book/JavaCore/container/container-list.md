# 容器

- [Java容器collection之List](#java容器collection之list)
  - [ArrayList 类](#arraylist-类)
  - [LinkedList 类](#linkedlist-类)
  - [ArrayList, Vector和LinkedList有什么区别](#arrayList,-vector和linkedlist有什么区别)

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



### 