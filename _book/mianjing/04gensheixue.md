# 跟谁学

自我介绍

项目介绍

## 问题1：反射机制

问：解释一下反射机制

问：为什么要用反射机制

答：可以给我讲一下吗

问：因为它是一个运行时的状态，如果你用new调用的话，那它就是在编译区，写代码的时候就确定了。反射它是在运行时可以动态获取，这样就可以在运行时做一些操作。如果说是编码的时候，用new那种方式，就把它写死了，等运行的时候就没法做一些调整。

## 问题2：http协议里面get post 

问：http协议里面请求方式有get post ，还有一些别的方式，你知道这个get和post有什么区别吗

答：get是从指定的资源请求数据，post是向指定的资源提交要被处理的数据

> [HTTP 方法：GET 对比 POST]([https://www.w3school.com.cn/tags/html_ref_httpmethods.asp](https://www.w3school.com.cn/tags/html_ref_httpmethods.asp))
>
> [GET和POST的区别](https://www.cnblogs.com/liliuguang/p/10416267.html)
>
> [面试题思考：GET和POST两种基本请求方法的区别](https://www.cnblogs.com/songanwei/p/9387815.html)

问：那post也可以用来获取数据吗

答：可以用来获取

问：那么他们请求带的参数有什么区别吗

答：post提交的数据不会显示在URL里面

问：那post的数据是在哪一块

答：e....

问：http协议的消息体分为哪几部分，就是http协议分为哪几部分

答：通用头域、请求消息、响应消息和主体信息。（当时打错了，面试官没有继续往下问）

## 问题3：单例模式

问：设计模式中的单例模式，什么时候会使用单例模式

> [设计模式之单例模式经典总结（实现方式、应用场景、优缺点、注意事项）]([https://blog.csdn.net/qq_18769269/article/details/93602672](https://blog.csdn.net/qq_18769269/article/details/93602672))

答：保证一个类中，有且只有一个实例存在并提供一个访问点供全局访问，该实例可以被所有的程序来访问。
一般有在，在这种情况下用：
​        1、当要用一个类时，又要用该类中的一个实例；
​        2、new 来创建实例时会给程序造成资源的浪费，而且实例越多也不好控制。
​        3、不同的线程调用时，可能会引起不同步的现象。

问:那怎么实现一个单例模式，实现的时候应该注意什么

答：有饿汉式的，懒汉式的，一般使用双重校验

》》》》》》》》》

a） 将被实现的类的构造方法设计成private的。

b） 添加此类引用的静态成员变量，并为其实例化。

c） 在被实现的类中提供公共的CreateInstance函数，返回实例化的此类,就是b中的静态成员变量。

1.使用时不能用反射模式创建单例，否则会实例化一个新的对象 
2.使用懒单例模式时注意线程安全问题 
3.单例模式和懒单例模式构造方法都是私有的，因而是不能被继承的，有些单例模式可以被继承（如登记式模式）

《《《《《《《《《

问：为什么要使用双重校验

答：第一个 if 语句用来避免 uniqueInstance 已经被实例化之后的加锁操作，而第二个 if 语句进行了加锁，所以只能有一个线程进入，就不会出现 uniqueInstance == null 时两个线程同时进行实例化操作。

问：第一能不能保证只有一个线程进入吗

答：e.....不能保证

问：那它校验中间需要用到锁吗

答：需要用到，用synchronized把类锁起来

## 问题4：java的锁

问：那除了synchronized，你接触过的java的锁还有哪些

答：volatile，volatile进行写操作。JVM会向处理器发送一条Lock前缀的指令（后面不会说了）。 保证内存的可见性（e....）

问：那它每次读都是从主内存读，还是更新的时候从主内从更新到各个线程里面

答：e...

问：那它各自的线程里面有变量的备份吗

答:  有....但是有个机制，能保证读取到的是最新的数据

## 问题5：java并发

问：java并发里面有个叫CAS，你知道这个是干什么的吗

答：comper adn swap ，三个参数，一个当前内存值 V、旧的预期值 A、即将更新的值 B，当 且仅当预期值 A 和内存值 V 相同时，将内存值修改为 B 并返回 true，否则什么都不做，并 返回 false。 

问：值刚开始是A，之间变成了B，后来比较的时候又变成了A。中间有变成过程，但是你比较的时候比较的是最终的值，你以为它没有变化，其实它已经变化了，那这个问题该怎么解决

答：e....

## 问题6：JVM

问：JVM运行时的区域大致分为哪几块？

答：Java虚拟机的运行时区域构成图如下所示：![img](https://github.com/liyao0312/studynotes/blob/master/imag/mianjing/jvm10.png?raw=true)

>[JVM运行时区域由哪几部分组成？分别介绍一下]([https://blog.csdn.net/x4609883/article/details/79759419](https://blog.csdn.net/x4609883/article/details/79759419))

问：java的类什么时候会被加载，加载的一个过程

答：e...面经上写的看不懂，没回答清楚

> [jvm学习一：类加载过程详解](https://www.cnblogs.com/fanjie/p/6916784.html)

## 问题7：数据结构

问：我看你项目中用到了mongoDB，你知道MongoDB的索引用到了什么数据结构吗？

答：B+树，mysql用的是B树。B+树和b树的区别就是，b树存储的时候，非叶子节点也会存储信息；B+数存储的时候，非叶子节点只存储路径，所有的值都存在叶子节点里面。所以b+的索引速度会更快一点。

问：那MongoDB和mysql那个索引块

答：MongoDB快，而且它还是非关系型的

问：那MongoDB之间能join吗，表连接，就是一个表的A字段，等于另一个表的B字段

答：能join，但是不符合MongoDB的nosql的特性了 

>[在MongoDB中使用JOIN操作](https://www.cnblogs.com/duhuo/p/6068879.html)

## 问题8：网络编程

问：java中的网络编程，应该怎么写一个服务端的程序

答：额....

>[socket技术详解（看清socket编程）]([https://blog.csdn.net/weixin_39634961/article/details/80236161](https://blog.csdn.net/weixin_39634961/article/details/80236161))

## 问题9：多线程

问：java的多线程都知道什么

答：常见的方法，wait() sleep() notify() notifyAll()

问：那wait的作用是什么

答：

问：那java线程之间的通信都有哪些方式

答：wait，notify等机制

问：notify的话，比如A线程想要notify B线程，那A怎么指定唤醒的是B，而不是其他线程。这个是什么指令

答： e.....不知道

## 问题10：数据库

问：数据库中的left join，它的意义是什么

答：返回包括左表中的所有记录和右表中联结字段相等的记录

问：left join 和 inner join 有什么区别

答：额....  inner join(等值连接、内连接) 只返回两个表中联结字段相等的行

## 问题11：算法

问：你知道哪些常用的字符串匹配的算法，比如一个字符串在不在另一个字符串里面

答：e...  

> [java中判断一个字符串是否包含另外一个字符串，如果包含，计算出字符串的开始位置]([https://blog.csdn.net/u010838785/article/details/72820802](https://blog.csdn.net/u010838785/article/details/72820802))

问：时间复杂度是多少

答;  e...

## 问题12：网络

问：  ping 这个命令，其实走的是什么协议，属于网络协议的那一层

答：  ping使用的是[ICMP协议](https://www.baidu.com/s?wd=ICMP%E5%8D%8F%E8%AE%AE&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)，是“Internet Control Message Protocol”（Internet控制消息协议）的缩写，是[TCP/IP](https://www.baidu.com/s?wd=TCP%2FIP&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)协议族的一个子协议，用于在IP主机、路由器之间传递控制消息。

问：TCP为了支持可靠连接，他都有哪些策略

答： 三次握手建立连接，四次挥手断开连接，

问： 那这样就能保证它的可靠性吗，如果UDP也实现三次握手和四次挥手

答：e...

> [TCP/IP协议-为什么说TCP是可靠连接]([https://blog.csdn.net/baidu_35692628/article/details/78255476](https://blog.csdn.net/baidu_35692628/article/details/78255476))

问：就是假设一个数据包，在传输过程中丢失了，发送端和客户端都是怎么知道的，它会重试吗

答：e...

>该看什么资料还不知道

问：TCP是怎么进行拥塞控制的

答：e... 说了滑动窗口

> [tcp的拥塞控制](https://www.cnblogs.com/bewolf/p/11077721.html)

问：那窗口的大小时固定的吗？

答：e....

>[十年架构师教你：如何搞定计算机网络面试（终结篇）]([https://blog.csdn.net/qq_42894896/article/details/82883737](https://blog.csdn.net/qq_42894896/article/details/82883737))

## 问题13：操作系统

问：操作系统里面的进程和线程都有什么区别和联系

答：balabala 背了一通，不知道背的对不对，需要重点背一下，每次必问我的

> [操作系统中进程与线程的区别与联系]([https://blog.csdn.net/cry_admin/article/details/89317095](https://blog.csdn.net/cry_admin/article/details/89317095))

问：CPU分配的时间片是分配给线程还是进程

答:  随便说了个 线程

> 网上没找到相关资料

问：一个进程大概能访问多大的内存

答：没概念

问：操作系统里面虚拟内存的作用是什么

答：不知道

> 这个是第二次问我了，还是不知道

问：操作系统里面的中断有什么作为，为什么要有中断这个东西，比如说CPU中断

答：e...

问：为什么我们线程之间，我们的代码程序之间的访问会有中断这个东西

答：e...

问：有学过操作系统这门课吗，听说过中断这个概念吗，什么原因造成中断

答：内存溢出吗

## 问题14：linux

问：怎么查看一台机器的网络状况

答：ipconfig

问：都能看到哪些信息

答：ip地址，子网掩码，网关

问：网关的作用是什么

答：e...

> [什么是网关，网关的作用是什么？]([https://www.jianshu.com/p/122ac247f772](https://www.jianshu.com/p/122ac247f772))

问：子网掩码的作用是什么

答：e...

问：局域网和广域网有什么区别

答：e....

## 问题15：jvm

问：java里面jvm怎么判断一个对象是否还存活

答：引用计数法，引用链法（可达性分析法），可达性分析法，就是从根节点开始标记，没被标记到的就是不存活的

问：根节点是从哪里

答：GC Roots

问：什么可以作为GC Root，

答：e.....    balabala

>[可达性分析算法]([https://liyao0312.github.io/studynotes/javacore/jvm/jvm-gc.html#%E5%8F%AF%E8%BE%BE%E6%80%A7%E5%88%86%E6%9E%90%E7%AE%97%E6%B3%95](https://liyao0312.github.io/studynotes/javacore/jvm/jvm-gc.html#%E5%8F%AF%E8%BE%BE%E6%80%A7%E5%88%86%E6%9E%90%E7%AE%97%E6%B3%95))

问：GC Root 可能会被回收吗

答：e......   会

问：JVM什么时候会触发它的gc

答; MinorGC,FullGC   判断老年代最大的可用连续空间大于新生代的所有对象总空间,直接执行minorGC

老年代空间不足 触发Full GC

> [什么时候触发MinorGC?什么时候触发FullGC?]([https://blog.csdn.net/varyall/article/details/82527070](https://blog.csdn.net/varyall/article/details/82527070))

问：那GC的过程你把它分为哪几步 

答：e..

问：或者说你知道哪种垃圾回收器 说下他的过程

答：e...

## 问题16：有什么想要问我的

问：

答：字符串算法，最优的那个算法是什么

问：最优算法就是，你要把之前匹配的信息记录下来，这样当下一个不匹配时，不用从头在匹配，这样下一次匹配就不用重复匹配。

