- [HashMap](#hashmap)

  - [什么是HashMao](#什么是hashmap)
  - [HashMap的工作原理？](#hashmap的工作原理)
  - [怎样计算key在数组中的位置(hash算法)？根据HashCode计算数组下标？](#怎样计算key在数组中的位置hash算法？根据hashcode计算数组下标？)
  - [碰撞，hash冲突](#碰撞，hash冲突)
  - [HashMap的扩容resize()？](#hashmap的扩容resize？)
  - [HashMap长度为什么等于2的幂？ ？](#hashmap长度为什么等于2的幂？-？)
  - [JDK1.8的HashMap的优化？](#jdk18的hashmap的优化？)
  - [为什么JDK1.8中HashMap链表长度超过8会转成树结构？？](#为什么jdk18中hashmap链表长度超过8会转成树结构？？)
  - [HashMap为什么是线程不安全的？](#hashmap为什么是线程不安全的？)
  - [为什么String, Interger这样的wrapper类适合作为键？？](#为什么string-interger这样的wrapper类适合作为键？？)
  - [为什么java中在重写equals()方法后必须对hashCode()方法进行重写](#已经重写了equals()和hashCode()方法)
  - [我们可以使用自定义的对象作为键吗？？](#我们可以使用自定义的对象作为键吗？？)

- [HashTable](hashtable)

  - [什么是HashTable](#什么是hashtable)
  - [HashTable的参数？](#hashtable的参数？)
  - [HashTable的扩容](#hashtable的扩容)

- [HashTable和HashMap的区别](#hashtable和hashmap的区别)

- [ConcurrHashMap](#concurrhashmap)

  - [什么是ConcurrentHashMap?](#什么是concurrenthashmap)

  - [为什么用ConcurrentHashMap?](#为什么用concurrenthashmap)

  - [JDK1.7 ConcurrentHashMap?](#jdk17-concurrenthashmap)

    -  [1.7结构](#17结构)
      - [Segment](#segment)
      - [什么是分段锁技术？](#什么是分段锁技术？)
    - [ConcurrentHashMap的工作原理？](#concurrenthashmap的工作原理？)
    - [初始化](#初始化)
      - [三个参数](#三个参数)
      - [初始化ConcurrentHashMap](#初始化concurrenthashmap)
      - [初始化Segment分段](#初始化segment分段)
      - [三次hash](#三次hash)
    - [Put操作？](#put操作？)
    - [Get操作？](#get操作？)
    - [Remove操作？](#remove操作？)
    - [size操作？](#size操作？)

  - [JDK1.8 ConcurrentHashMap?](#jdk18-concurrenthashmap)

    - [1.8结构](#18结构)
      - [CAS思想和实现原理？](#cas思想和实现原理？)
    - [ConcurrentHashMap1.8的工作原理？](#concurrenthashmap18的工作原理？)
    - [数据结构](#数据结构)
      - [1.8初始化ConcurrentHashMap](#18初始化concurrenthashmap)
    - [Put操作](#put操作)
    - [Get操作](#get操作)
    - [size操作](#size操作)

  - [ConcurrentHashMap JDK1.7  1.8区别?](#concurrenthashmap-jdk17-18区别)

  - [JDK1.8为什么使用内置锁synchronized来代替重入锁ReentrantLock?](#jdk18为什么使用内置锁synchronized来代替重入锁reentrantlock)

  - [ConcurrentHashMap和HashMap区别](#jdk18为什么使用内置锁synchronized来代替重入锁reentrantlock)

  - [ConcurrentHashMap和HashTable区别](#concurrenthashmap和hashtable区别)

    ​



# 面试问题HashMap/HashTable/ConcurrHashMap

## HashMap

### 什么是HashMap？

- 基于哈希表的 Map 接口的实现。
- HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。
- HashMap 继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口。
- HashMap 的实现不是同步的，这意味着它不是线程安全的。它的key、value都可以为null。此外，HashMap中的映射不是有序的。

### HashMap的工作原理？

- HashMap是基于hashing的原理，我们使用put(key, value)存储对象到HashMap中，使用get(key)从HashMap中获取对象。
  - Put: 当我们往hashmap中put元素的时候，先根据key的hash值得到这个元素在数组中的位置（即下标），如果下标对应的链表为空，则直接把键值对作为链表头节点，如果不为空，发生哈希冲突，则equals方法在对应位置的链表中找到需要的元素，有就把value替换，没有那么在同一个位子上的元素将以链表的形式存放，新加入的放在链头，最先加入的放在链尾。（JDK1.7之所以放在头节点，是因为HashMap的发明者认为后插入的Entry被查找的可能性更大。）
  - Get: 从hashmap中get元素时，首先计算key的hashcode，找到数组中对应位置的某一元素，然后通过key的equals方法在对应位置的链表中找到需要的元素。

### 怎样计算key在数组中的位置(hash算法)？根据HashCode计算数组下标？

- 我们当然希望这个hashmap里面的元素位置尽量的分布均匀些，尽量使得每个位置上的元素数量只有一个，那么当我们用hash算法求得这个位置的时候，马上就可以知道对应位置的元素就是我们要的，而不用再去遍历链表

- JDK1.7

  - h&(length-1) 就相当于对length取模，而且在速度、效率上比直接取模要快得多

- JDK1.8优化

  - 高16位异或低16位，然后再h&(length-1)，目的：混合原始哈希码的高位和低位，以此来加大低位的随机性。而且混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留下来。

  - [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/Hash%E7%AE%97%E6%B3%95JDK8.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/Hash%E7%AE%97%E6%B3%95JDK8.png)

    ​

### 碰撞，hash冲突

- 哈希表为解决 Hash 冲突，可以采用开放地址法和链地址法来解决问题。HashMap 采用了链地址法。链地址法，简单来说，就是数组加链表的结合。在每个数组元素上都一个链表结构，当数据被 Hash 后，得到数组下标，把数据放在对应下标元素的链表上。
- JDK1.7 链表头插。JDK1.8 链表尾插。

### HashMap的扩容resize()？

- JDK 1.7 先扩容后插入

  - hashmap使用一个容量更大的数组来代替旧的数组，transfer（）方法将原有Entry数组里的元素拷贝到新的Entry数组里，扩容之后，原数组中的数据必须重新计算其在新数组中的位置，并放进去，这就是resize。
  - 新容量=旧容量*2；默认旧容量=16*0.75=12
  - 在扩容后插入的过程中，存储在LinkedList中的元素的次序会反过来，因为移动到新的bucket位置的时候，HashMap并不会将元素放在LinkedList的尾部，而是放在头部，这是为了避免尾部遍历(tail traversing)。

- JDK 1.8 先插入后扩容

  - 在JDK1.8的时候直接用了JDK1.7的时候计算的规律，也就是扩容前的原始位置+扩容的大小值=JDK1.8的计算方式，而不再是JDK1.7的那种与计算的方法。但是这种方式就相当于只需要判断Hash值的新增参与运算的位是0还是1就直接迅速计算出了扩容后的储存方式。

  - [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/HashMapJDK1.8%E6%89%A9%E5%AE%B9.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/HashMapJDK1.8%E6%89%A9%E5%AE%B9.png)

    ​

- JDK 1.7/1.8扩容区别

  - [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/hashMap1.7%E5%92%8C1.8%E6%89%A9%E5%AE%B9%E5%8C%BA%E5%88%AB.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/hashMap1.7%E5%92%8C1.8%E6%89%A9%E5%AE%B9%E5%8C%BA%E5%88%AB.png)

    ​

### HashMap长度为什么等于2的幂？ ？

- hashmap中默认的数组大小为16，加载因子0.75。并且每次自动扩展或手动初始化时，长度必须是2的幂。

- 举例：

  - 看下图，左边两组是数组长度为16（2的4次方），右边两组是数组长度为15。两组的hashcode均为8和9，但是很明显，当它们和1110“与”的时候，产生了相同的结果，也就是说它们会定位到数组中的同一个位置上去，这就产生了碰撞，8和9会被放到同一个链表上，那么查询的时候就需要遍历这个链表，得到8或者9，这样就降低了查询的效率。

  - 同时，我们也可以发现，当数组长度为15的时候，hashcode的值会与14（1110）进行“与”，那么最后一位永远是0，而0001，0011，0101，1001，1011，0111，1101这几个位置永远都不能存放元素了，显然不符合Hash算法均匀分布的原则，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！

  - [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/HashMap%E7%9A%84%E9%95%BF%E5%BA%A6%E4%B8%BA%E4%BB%80%E4%B9%88%E6%98%AF16%E6%88%96%E6%98%AF2%E7%9A%84%E5%B9%82%E6%AC%A1%E6%96%B9.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/HashMap%E7%9A%84%E9%95%BF%E5%BA%A6%E4%B8%BA%E4%BB%80%E4%B9%88%E6%98%AF16%E6%88%96%E6%98%AF2%E7%9A%84%E5%B9%82%E6%AC%A1%E6%96%B9.png)

    ​

- 所以，长度16或者其他2的幂，Length-1的值是所有二进制位全为1，这种情况下，index的结果等同于HashCode后几位的值。只要输入的HashCode本身分布均匀，Hash算法的结果就是均匀的。

### JDK1.8的HashMap的优化？

- 数据结构变化：
  - 数组+链表+红黑树，当链表的深度达到8的时候，也就是默认阈值，就会自动扩容把链表转成红黑树的数据结构来把时间复杂度从O（N）变成O（logN）提高了效率
- Hash算法计算方式改变：
  - 高16位异或低16位，后&(length-1)
- 扩容后不是重新计算所有元素在数组的位置：
  - 新的位置=旧的位置 OR 旧的位置+旧容量
- JDK1.7用的是头插法，而JDK1.8及之后使用的都是尾插法，
  - 因为JDK1.7是用单链表进行的纵向延伸，当采用头插法就是能够提高插入的效率，但是也会容易出现逆序且环形链表死循环问题。但是在JDK1.8之后是因为加入了红黑树，使用尾插法能够避免出现逆序且链表死循环的问题。
- 链表死循环问题 [https://coolshell.cn/articles/9606.html/comment-page-1#comments](https://coolshell.cn/articles/9606.html/comment-page-1#comments)

### 为什么JDK1.8中HashMap链表长度超过8会转成树结构？？

- 1.若桶中链表元素个数大于等于8时，链表转换成树结构；若桶中链表元素个数小于等于6时，树结构还原成链表。因为红黑树的平均查找长度是log(n)，长度为8的时候，平均查找长度为3，如果继续使用链表，平均查找长度为8/2=4，这才有转换为树的必要。链表长度如果是小于等于6，6/2=3，虽然速度也很快的，但是转化为树结构和生成树的时间并不会太短。

- 2.还有选择6和8，中间有个差值7可以有效防止链表和树频繁转换。假设一下，如果设计成链表个数超过8则链表转换成树结构，链表个数小于8则树结构转换成链表，如果一个HashMap不停的插入、删除元素，链表个数在8左右徘徊，就会频繁的发生树转链表、链表转树，效率会很低。

- 1. 在这里简单解释一下，理想情况下，在随机哈希代码下，桶中的节点频率遵循泊松分布，文中给出了桶长度k的频率表。由频率表可以看出，桶的长度超过8的概率非常非常小。所以作者应该是根据概率统计而选择了8作为阀值，由此可见，这个选择是非常严谨和科学的。

- [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/hashMap1.8%E9%93%BE%E8%A1%A8%E8%BD%AC%E6%8D%A2%E4%B8%BA%E7%BA%A2%E9%BB%91%E6%A0%91%E7%9A%84%E7%95%8C%E9%99%908%E7%AC%A6%E5%90%88%E6%B3%8A%E6%9D%BE%E5%88%86%E5%B8%83.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/hashMap1.8%E9%93%BE%E8%A1%A8%E8%BD%AC%E6%8D%A2%E4%B8%BA%E7%BA%A2%E9%BB%91%E6%A0%91%E7%9A%84%E7%95%8C%E9%99%908%E7%AC%A6%E5%90%88%E6%B3%8A%E6%9D%BE%E5%88%86%E5%B8%83.png)

  ​

### HashMap为什么是线程不安全的？

- HashMap resize而引起死循环.（条件竞争）。扩容的的时候可能会形成环形链表， 造成死循环. [https://coolshell.cn/articles/9606.html/comment-page-1#comments](https://coolshell.cn/articles/9606.html/comment-page-1#comments)
- put的时候导致的多线程数据不一致
  - 在多线程下put操作时,产生哈希碰撞，最后的写入操作会覆盖先前的写入操作----写入不一致。最后的修改操作会覆盖先前的修改---修改不一致。
- 注意：要想实现线程安全，那么需要调用 collections 类的静态方法

### 为什么String, Interger这样的wrapper类适合作为键？？

- 1.不可变性： String是不可变的，也是final的
  - 计算hashCode()，就要防止键值改变，如果键值在放入时和获取时返回不同的hashcode的话，那么就不能从HashMap中找到你想要的对象。
  - 不可变性还有其他的优点如线程安全。
- 2.已经重写了equals()和hashCode()方法
  - 为什么要重写equals()方法？
    - 1．Object类中equals方法比较的是两个对象的引用地址，只有对象的引用地址指向同一个地址时，才认为这两个地址是相等的，否则这两个对象就不相等。
    - 2.如果有两个对象，他们的属性是相同的，但是地址不同，这样使用equals()比较得出的结果是不相等的，而我们需要的是这两个对象相等，因此默认的equals()方法是不符合我们的要求的，这个时候我们就需要对equals()方法进行重写以满足我们的预期结果。
    - 3.在java的集合框架中需要用到equals()方法进行查找对象，如果集合中存放的是自定义类型，并且没有重写equals()方法，则会调用Object父类中的equals()方法按照地址比较，往往会出现错误的结果，此时我们应该根据业务需求重写equals()方法。
    - 如果不重写equals，那么比较的将是对象的引用是否指向同一块内存地址，重写之后目的是为了比较两个对象的value值是否相等。
  - 为什么java中在重写equals()方法后必须对hashCode()方法进行重写？
    - 1.为了维护hashCode()方法的equals协定，该协定指出：相等的对象必须具有相等的散列码（hashCode）；而两个hashCode()返回的结果相等，两个对象的equals()方法不一定相等。
    - 2.如果不重写hashCode()，就会造成相等对象，不同的hashCode();在hashMap中就会存储两个值一样的对象。导致混淆。

### 我们可以使用自定义的对象作为键吗？？

- 当然你可能使用任何对象作为键，只要它遵守了equals()和hashCode()方法的定义规则，并且当对象插入到Map中之后将不会再改变了。如果这个自定义对象时不可变的，那么它已经满足了作为键的条件，因为当它创建之后就已经不能改变了。

## HashTable

### 什么是HashTable

- Hashtable 也是一个散列表，它存储的内容是键值对(key-value)映射。
- Hashtable 继承于Dictionary，实现了Map、Cloneable、java.io.Serializable接口。
- Hashtable 的函数都是同步的，这意味着它是线程安全的。它的key、value都不可以为null。此外，Hashtable中的映射不是有序的。

### HashTable的参数？

- （1） table 是一个 Entry[]数组类型， 而 Entry 实际上就是一个单向链表。 哈希表的"key-value 键值对"都是存储在 Entry 数组中的。
- （2） count 是 Hashtable 的大小， 它是 Hashtable 保存的键值对的数量。
- （3） threshold 是 Hashtable 的阈值， 用于判断是否需要调整 Hashtable 的容量。 threshold 的值="容量*加载因子"。
- （4） loadFactor 就是加载因子。
- （5） modCount 是用来实现 fail-fast 机制的

### HashTable的扩容

- 默认初始容量为 11
- 线程安全， 但是速度慢， 不允许 key/value 为 null
- 加载因子为 0.75： 即当元素个数超过容量长度的0.75倍时， 进行扩容
  - 扩容增量： 2*原数组长度+1
    - 如 HashTable 的容量为 11， 一次扩容后是容量为 23

## HashTable和HashMap的区别

- 1.继承的父类不同
  - Hashtable继承自Dictionary类，而HashMap继承自AbstractMap类。但二者都实现了Map接口。
- 2.线程安全性不同
  - Hashtable 同步-线程安全。HashMap 非同步-不是线程安全的。
- 3.是否提供contains方法
  - HashMap把Hashtable的contains方法去掉了，改成containsValue和containsKey
  - Hashtable则保留了contains，containsValue和containsKey三个方法，其中contains和containsValue功能相同。
- 4.key和value是否允许null值
  - Hashtable不允许，HashMap允许
- 5.两个遍历方式的内部实现上不同
  - Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。
- 6.哈希值的使用不同
  - Hashtable直接使用对象的hashCode、HashMap重新计算hash值，而且用于代替求模
- 7.内部实现使用的数组初始化和扩容方式不同，
  - Hashtable默认容量为11，不要求底层数组的容量一定要为2的整数次幂，扩容时将容量变为原来的2倍加1，
  - HashMap默认容量为16，要求底层数组的容量一定要为2的整数次幂，扩容时将容量变为原来的2倍

## ConcurrHashMap

### 什么是ConcurrentHashMap?

- ConcurrentHashMap就是多线程编程中可以使用的一种高性能的线程安全HashMap方案。

### 为什么用ConcurrentHashMap?

- HashMap是线程不安全的，在并发环境下，可能会形成环状链表（扩容时可能造成，具体原因自行百度google或查看源码分析），导致get操作时，cpu空转
- HashTable线程安全的策略实现代价却太大了，简单粗暴，get/put所有相关操作都是synchronized的，这相当于给整个哈希表加了一把大锁，多线程访问时候，只要有一个线程访问或操作该对象，那其他线程只能阻塞，相当于将所有的操作串行化，在竞争激烈的并发场景中性能就会非常差。

### JDK1.7 ConcurrentHashMap?

#### 1.7结构

- [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/ConcurrenHashMap1.7%E7%BB%93%E6%9E%84%E5%9B%BE.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/ConcurrenHashMap1.7%E7%BB%93%E6%9E%84%E5%9B%BE.png)

  ​

##### Segment

- Segment的结构和HashMap类似，是一种数组和链表结构。
- 同HashMap一样，Segment包含一个HashEntry数组，数组中的每一个HashEntry既是一个键值对，也是一个链表的头节点。
- 每个Segment守护着一个HashEntry数组里的元素,当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁。
- Segment是一种可重入锁ReentrantLock

##### 什么是分段锁技术？

- 容器里有多把锁，每一把锁用于锁容器其中一部分数据，那么当多线程访问容器里不同数据段的数据时，线程间就不会存在锁竞争，从而可以有效的提高并发访问效率

#### ConcurrentHashMap的工作原理？

- ConcurrentHashMap所使用的锁分段技术，首先将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。能够实现真正的并发访问。
- 有一些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁。这里“按顺序”是很重要的，否则极有可能出现死锁

#### 初始化

- [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/ConcurrentHashMap%E7%9A%84%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%841.7.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/ConcurrentHashMap%E7%9A%84%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%841.7.png)

  ​

##### 三个参数

- initialCapacity
  - initialCapacity表示新创建的这个ConcurrentHashMap的初始容量，也就是上面的结构图中的Entry数量。默认值为static final int DEFAULT_INITIAL_CAPACITY = 16;
- loadFactor
  - loadFactor表示负载因子，就是当ConcurrentHashMap中的元素个数大于loadFactor * 最大容量时就需要rehash，扩容。默认值为static final float DEFAULT_LOAD_FACTOR = 0.75f;
- concurrencyLevel
  - concurrencyLevel表示并发级别，这个值用来确定Segment的个数，Segment的个数是大于等于concurrencyLevel的第一个2的n次方的数。比如，如果concurrencyLevel为12，13，14，15，16这些数，则Segment的数目为16(2的4次方)。默认值为static final int DEFAULT_CONCURRENCY_LEVEL = 16;。理想情况下ConcurrentHashMap的真正的并发访问量能够达到concurrencyLevel，因为有concurrencyLevel个Segment，假如有concurrencyLevel个线程需要访问Map，并且需要访问的数据都恰好分别落在不同的Segment中，则这些线程能够无竞争地自由访问（因为他们不需要竞争同一把锁），达到同时访问的效果。这也是为什么这个参数起名为“并发级别”的原因。
- concurrencyLevel主要用来初始化segments、segmentShift和segmentMask等；而initialCapacity和loadFactor则主要用来初始化每个Segment分段。

##### 初始化ConcurrentHashMap

- ssize(segments数组的长度) sshift(计算ssize时进行移位操作的次数)
- segmentShift 段偏移量
- segmentMask 段掩码
- 例子：

##### 初始化Segment分段

- ssize 2的N次方大于等于ConcurrentHashMapLevel
- sshift (计算ssize时进行移位操作的次数)log(ssize)
- segmentShift = 32 - sshift; //之所以用32是因为ConcurrentHashMap里的hash()方法输出的最大数是32位的
- segmentMask = ssize - 1;
- 例如LconcurrencyLevel等于16，则ssize=16,sshift=4，则segmentShift为28,。segmentMask=15;hash值是一个32位的整数，将其向右移动28位就变成这个样子：0000 0000 0000 0000 0000 0000 0000 xxxx，然后再用这个值与segmentMask做&运算，也就是取最后四位的值。这个值确定Segment的索引。

##### 三次hash

- Segment下面包含很多个HashEntry列表数组。对于一个key，需要经过三次（为什么要hash三次下文会详细讲解）hash操作，才能最终定位这个元素的位置，这三次hash分别为：
  - 对于一个key，先进行一次hash操作，得到hash值h1，也即h1 = hash1(key)；
  - 将得到的h1的高几位进行第二次hash，得到hash值h2，也即h2 = hash2(h1高几位)，通过h2能够确定该元素的放在哪个Segment；
  - 将得到的h1进行第三次hash，得到hash值h3，也即h3 = hash3(h1)，通过h3能够确定该元素放置在哪个HashEntry。

#### Put操作？

put操作是要加锁的。

- 判断value是否为null，如果为null，直接抛出异常。
- key通过一次hash运算得到一个hash值。(这个hash运算下文详说)
- 将得到hash值向右按位移动segmentShift位，然后再与segmentMask做&运算得到segment的索引j。
- 在初始化的时候我们说过segmentShift的值等于32-sshift，例如concurrencyLevel等于16，则sshift等于4，则segmentShift为28。hash值是一个32位的整数，将其向右移动28位就变成这个样子： 0000 0000 0000 0000 0000 0000 0000 xxxx，然后再用这个值与segmentMask做&运算，也就是取最后四位的值。这个值确定Segment的索引。
- 使用Unsafe的方式从Segment数组中获取该索引对应的Segment对象。
- 向这个Segment对象中put值，这个put操作也基本是一样的步骤（通过&运算获取HashEntry的索引，然后set）。

#### Get操作？

- JDK1.7的ConcurrentHashMap的get操作是不加锁的，因为在每个Segment中定义的HashEntry数组和在每个HashEntry中定义的value和next HashEntry节点都是volatile类型的，volatile类型的变量可以保证其在多线程之间的可见性，因此可以被多个线程同时读，从而不用加锁。
- 而其get操作步骤也比较简单，定位Segment –> 定位HashEntry –> 通过getObjectVolatile()方法获取指定偏移量上的HashEntry –> 通过循环遍历链表获取对应值。
  - 定位Segment：(((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE
  - 定位HashEntry：(((tab.length - 1) & h)) << TSHIFT) + TBASE

#### Remove操作？

#### size操作？

- 计算ConcurrentHashMap的元素大小是一个有趣的问题，因为他是并发操作的，就是在你计算size的时候，他还在并发的插入数据，可能会导致你计算出来的size和你实际的size有相差（在你return size的时候，插入了多个数据），要解决这个问题，JDK1.7版本用两种方案
- 第一种方案他会使用不加锁的模式去尝试多次计算ConcurrentHashMap的size，最多三次，比较前后两次计算的结果，结果一致就认为当前没有元素加入，计算的结果是准确的
- 第二种方案是如果第一种方案不符合，他就会给每个Segment加上锁，然后计算ConcurrentHashMap的size返回
- 举例：
  - 一个Map有4个Segment，标记为S1，S2，S3，S4，现在我们要获取Map的size。
  - 计算过程是这样的：
    - 第一次计算，不对S1，S2，S3，S4加锁，遍历所有的Segment，假设每个Segment的大小分别为1，2，3，4，更新操作次数分别为：2，2，3，1，则这次计算可以得到Map的总大小为1+2+3+4=10，总共更新操作次数为2+2+3+1=8；
    - 第二次计算，不对S1,S2,S3,S4加锁，遍历所有Segment，假设这次每个Segment的大小变成了2，2，3，4，更新次数分别为3，2，3，1，因为两次计算得到的Map更新次数不一致(第一次是8，第二次是9)则可以断定这段时间Map数据被更新，则此时应该再试一次；
    - 第三次计算，不对S1，S2，S3，S4加锁，遍历所有Segment，假设每个Segment的更新操作次数还是为3，2，3，1，则因为第二次计算和第三次计算得到的Map的更新操作的次数是一致的，就能说明第二次计算和第三次计算这段时间内Map数据没有被更新，此时可以直接返回第三次计算得到的Map的大小。
    - 最坏的情况：第三次计算得到的数据更新次数和第二次也不一样，则只能先对所有Segment加锁再计算最后解锁。

### JDK1.8 ConcurrentHashMap?

#### 1.8结构

- ConcurrentHashMap在1.8版本摒弃了Segment（锁段）的概念，而是启用了一种全新的CAS算法的方式实现，Node + CAS + Synchronized。数据结构沿用了与它同时期的HashMap版本的思想，底层依然由数组+链表+红黑树的方式思想。

- [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/ConcurrHashMap1.8%E7%BB%93%E6%9E%84.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/ConcurrHashMap1.8%E7%BB%93%E6%9E%84.png)

  ​

##### CAS思想和实现原理？

- 乐观锁：认为数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则让用户返回错误的信息。让用户决定如何去做。

- 悲观锁:就是对数据的冲突采取一种悲观的态度，也就是说假设数据肯定会冲突，所以在数据开始读取的时候就把数据锁定住。【数据锁定：数据将暂时不会得到修改】

- 思想：

  - CAS（Compare And Swap，比较交换）：CAS有三个操作数，内存值V、预期值A、要修改的新值B，当且仅当A和V相等时才会将V修改为B，否则什么都不做。
  - 实现CAS最重要的一点，就是比较和交换操作的一致性，否则就会产生歧义。
    - 比如当前线程比较成功后，准备更新共享变量值的时候，这个共享变量值被其他线程更改了，那么CAS函数必须返回false。

- 实现原理

  - CAS通过调用JNI的代码实现的。JNI:Java Native Interface-JAVA本地接口，允许java与其他语言交互。
  - 要实现这个需求，java中提供了Unsafe类，它提供了三个函数，分别用来操作基本类型int和long，以及引用类型Object。方法的实现位于unsafe.cpp中

  ```
  public final native boolean compareAndSwapObject
      (Object obj, long valueOffset, Object expect, Object update);

      public final native boolean compareAndSwapInt
      (Object obj, long valueOffset, int expect, int update);

      public final native boolean compareAndSwapLong
      (Object obj, long valueOffset, long expect, long update);
  ```

  - Java中CAS操作通过JNI本地方法实现，在JVM中程序会根据当前处理器的类型来决定是否为cmpxchg指令添加lock前缀。如果程序是在多处理器上运行，就为cmpxchg指令加上lock前缀（Lock Cmpxchg）；反之，如果程序是在单处理器上运行，就省略lock前缀。
  - lock 前缀有两个特性 1， 禁止该指令与前面和后面的读写指令重排序 2， 把写缓冲区的所有数据刷新到内存中
  - 这两点保证了内存屏障效果， 保证了 CAS 同时具有 volatile 读和 volatile 写的内存语义。

- 缺点

  - 不过CAS操作也存在一些缺点：1. 存在ABA问题，其解决思路是使用版本号；2. 循环时间长，开销大；3. 只能保证一个共享变量的原子操作。

#### ConcurrentHashMap1.8的工作原理？

- 在JDK1.7之前，ConcurrentHashMap是通过分段锁机制来实现的，所以其最大并发度受Segment的个数限制。因此，在JDK1.8中，ConcurrentHashMap的实现原理摒弃了这种设计，而是选择了与HashMap类似的数组+链表+红黑树的方式实现，而加锁则采用CAS和synchronized实现。
- 首先，在table中添加一个元素时，如果添加元素的链表节点个数超过8，则会触发链表向红黑树结构转换。
  - 首先会检查hash表的大小是否大于等于MIN_TREEIFY_CAPACITY，默认值为64，如果小于该值，则表示不需要转化为红黑树结构，直接将hash表扩容即可。
  - 如果当前table的长度大于64，则使用CAS获取指定的Node节点，然后对该节点通过synchronized加锁，由于只对一个Node节点加锁，因此该操作并不影响其他Node节点的操作，因此极大的提高了ConcurrentHashMap的并发效率。
  - 加锁之后，便是将这个Node节点所在的链表转换为TreeBin结构的红黑树。
  - 然后，在table中删除元素时，如果元素所在的红黑树节点个数小于6，则会触发红黑树向链表结构转换。

#### 数据结构

- [![img](https://github.com/Zds501710271/-Java/raw/master/notes/pics/ConcurrentHashMap%E7%9A%84%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%841.8.png)](https://github.com/Zds501710271/-Java/blob/master/notes/pics/ConcurrentHashMap%E7%9A%84%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%841.8.png)

  ​

- ConcurrentHashMap中包含一个table数组，其类型是一个Node数组；而Node是一个继承自Map.Entry<K, V>的链表，而当这个链表结构中的数据大于8，则将数据结构升级为TreeBin类型的红黑树结构。

- JDK1.8中的ConcurrentHashMap中还包含一个重要属性sizeCtl，其是一个控制标识符，不同的值代表不同的意思：

  - 其为0时，表示hash表还未初始化
  - 而为正数时这个数值表示初始化或下一次扩容的大小，相当于一个阈值；即如果hash表的实际大小>=sizeCtl，则进行扩容，默认情况下其是当前ConcurrentHashMap容量的0.75倍；
  - 而如果sizeCtl为-1，表示正在进行初始化操作；
  - 而为-N时，则表示有N-1个线程正在进行扩容。

##### 1.8初始化ConcurrentHashMap

- 首先会判断sizeCtl的值，如果其小于0，则说明其正在进行初始化或扩容操作，则不执行任何操作，调用yield()方法使当前线程返回等待状态；
- 而如果sizeCtl大于等于0，则使用CAS操作比较sizeCtl的值是否是-1，如果是-1则进行初始化。
  - 初始化时，如果sizeCtl的值为0，则创建默认容量的table；否则创建大小为sizeCtl的table；
  - 然后重置sizeCtl的值为0.75n，即当前table容量的0.75倍，并返回创建的table，此时初始化hash表完成。

#### Put操作

- 计算key的hash值，即调用speed()方法计算hash值；
- 获取hash值对应的Node节点位置，此时通过一个循环实现。有以下几种情况：
  - 如果table表为空，则首先进行初始化操作，初始化之后再次进入循环获取Node节点的位置；
  - 如果table不为空，但没有找到key对应的Node节点，则直接调用casTabAt()方法插入一个新节点，此时不用加锁；
  - 如果table不为空，且key对应的Node节点也不为空，但Node头结点的hash值为MOVED(-1)，则表示需要扩容，此时调用helpTransfer()方法进行扩容；
  - 其他情况下，则直接向Node中插入一个新Node节点，此时需要对这个Node链表或红黑树通过synchronized加锁。
- 插入元素后，判断对应的Node结构是否需要改变结构，如果需要则调用treeifyBin()方法将Node链表升级为红黑树结构；
- 最后，调用addCount()方法记录table中元素的数量。

#### Get操作

- 通过get获取hash表中的值时，首先需要获取key值的hash值。而在JDK1.8的ConcurrentHashMap中通过spread()方法获取。
- speed()方法将key的hash值进行再hash，让hash值的高位也参与hash运算，从而减少哈希冲突。然后再查询对应的value值。
- 查询时，首先通过tabAt()方法找到key对应的Node链表或红黑树，然后遍历该结构便可以获取key对应的value值。其中，tabAt()方法主要通过Unsafe类的getObjectVolatile()方法获取value值，通过volatile读获取value值，可以保证value值的可见性，从而保证其是当前最新的值。

#### size操作

- JDK1.8的ConcurrentHashMap中保存元素的个数的记录方法也有不同，首先在添加和删除元素时，会通过CAS操作更新ConcurrentHashMap的baseCount属性值来统计元素个数。但是CAS操作可能会失败，因此，ConcurrentHashMap又定义了一个CounterCell数组来记录CAS操作失败时的元素个数。
- 因此，ConcurrentHashMap中元素的个数则通过如下方式获得：元素总数 = baseCount + sum(CounterCell)
- size只能获取int范围内的ConcurrentHashMap元素个数；而如果hash表中的数据过多，超过了int类型的最大值，则推荐使用mappingCount()方法获取其元素个数。

### ConcurrentHashMap JDK1.7 1.8区别?

JDK 1.7 ReentrantLock+Segment+HashEntry JDK1.8 synchronized+CAS+HashEntry+红黑树

- 1.JDK1.8的实现降低锁的粒度，JDK1.7版本锁的粒度是基于Segment的，包含多个HashEntry，而JDK1.8锁的粒度就是HashEntry（首节点）
- 2.JDK1.8版本的数据结构变得更加简单，使得操作也更加清晰流畅，因为已经使用synchronized来进行同步，所以不需要分段锁的概念，也就不需要Segment这种数据结构了，由于粒度的降低，实现的复杂度也增加了
- 3.JDK1.8使用红黑树来优化链表，基于长度很长的链表的遍历是一个很漫长的过程，而红黑树的遍历效率是很快的，代替一定阈值的链表，这样形成一个最佳拍档

### JDK1.8为什么使用内置锁synchronized来代替重入锁ReentrantLock?

- 1.因为粒度降低了，在相对而言的低粒度加锁方式，synchronized并不比ReentrantLock差，在粗粒度加锁中ReentrantLock可能通过Condition来控制各个低粒度的边界，更加的灵活，而在低粒度中，Condition的优势就没有了
- 2.JVM的开发团队从来都没有放弃synchronized，而且基于JVM的synchronized优化空间更大，使用内嵌的关键字比使用API更加自然
- 3.在大量的数据操作下，对于JVM的内存压力，基于API的ReentrantLock会开销更多的内存，虽然不是瓶颈，但是也是一个选择依据

## ConcurrentHashMap和HashMap区别

- 1.HashMap非线程安全 ConcurrentHashMap线程安全
- 2.ConcurrentHashMap将整个Hash桶进行了分段segment，每个segment上都有锁
- 3.ConcurrentHashMap让锁的粒度更精细，并发性能更好。

## ConcurrentHashMap和HashTable区别

- 1.底层数据结构
  - ConcurrentHashMap1.7分段数组+链表
  - ConcurrentHashMap1.8数组+链表/红黑二叉树
  - HashTable数组+链表
- 2.实现线程安全的方式
  - Hashtable的所有操作都会锁住整个对象，虽然能够保证线程安全，但是性能较差；
  - ConcurrentHashMap内部使用Segment数组，每个Segment类似于Hashtable，在“写”线程或者部分特殊的“读”线程中锁住的是某个Segment对象，其它的线程能够并发执行其它的Segment对象。