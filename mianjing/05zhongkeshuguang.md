## 二面准备

自我介绍

### 1围绕简历问

你的项目为什么用这种算法，这种算法的优势

能否画一个框图，分模块地介绍你的项目？

### 2数据结构和算法

##### 2.1 二叉树和B+树的区别

```
B-树：
多路搜索树，每个结点存储M/2到M个关键字，非叶子结点存储指向关键字范围的子结点；
所有关键字在整颗树中出现，且只出现一次，非叶子结点可以命中；

B+树：
在B-树基础上，为叶子结点增加链表指针，所有关键字都在叶子结点中出现，非叶子结点作为叶子结点的索引；B+树总是到叶子结点才命中；

```



二叉树

```
或者是一棵空树；
或者是具有下列性质的二叉树：
（1）若它的左子树不空，则左子树上所有结点的值均小于它的根节点的值；
（2）若它的右子树上所有结点的值均大于它的根节点的值；
（3）它的左、右子树也分别为二叉排序树。
```

![img](https://img-blog.csdn.net/20181004102705475?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d5cXdpbGxpYW0=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

B树

```
B-树是一种多路搜索树（并不一定是二叉的）
所有的叶子结点都位于同一层。
B-树的特性：
1.关键字集合分布在整颗树中；
2.任何一个关键字出现且只出现在一个结点中；
3.搜索有可能在非叶子结点结束；
4.其搜索性能等价于在关键字全集内做一次二分查找；
5.自动层次控制；
```

![è¿éåå¾çæè¿°](https://img-blog.csdn.net/20160805191715603)

B+树

```
B+ 树通常用于数据库和操作系统的文件系统中。
B+ 树的特点是能够保持数据稳定有序，其插入与修改拥有较稳定的对数时间复杂度。B+ 树元素自底向上插入。

B+树是应文件系统所需而出的一种B-树的变型树。一棵m阶的B+树和m阶的B-树的差异在于：
1.有n棵子树的结点中含有n个关键字，每个关键字不保存数据，只用来索引，所有数据都保存在叶子节点。
2.所有的叶子结点中包含了全部关键字的信息，及指向含这些关键字记录的指针，且叶子结点本身依关键字的大小自小而大顺序链接。
3.所有的非终端结点可以看成是索引部分，结点中仅含其子树（根结点）中的最大（或最小）关键字。 
通常在B+树上有两个头指针，一个指向根结点，一个指向关键字最小的叶子结点。

B+的特性：
1.所有关键字都出现在叶子结点的链表中（稠密索引），且链表中的关键字恰好是有序的；
2.不可能在非叶子结点命中；
3.非叶子结点相当于是叶子结点的索引（稀疏索引），叶子结点相当于是存储（关键字）数据的数据层；
4.更适合文件索引系统；

```

![è¿éåå¾çæè¿°](https://img-blog.csdn.net/20160805192039968)

##### 2.2二叉树的中序遍历（不用迭代）

```
private static <V> void dfs(TreeNode<V> root, int depth) {
    if (root.getLeft() != null){
        dfs(root.getLeft(), depth + 1);
    }
    if (root.getRight() != null){
        dfs(root.getRight(), depth + 1);
    }
    //打印节点值以及深度
    System.out.println(d.getValue().toString() + ",   " + depth);
}
```



> [java二叉树遍历——深度优先(DFS)与广度优先(BFS) 递归版与非递归版](https://www.cnblogs.com/liyao0312/p/11401019.html)

### 3编程题

##### 3.1手写矩阵相乘

```java
/**
 * 矩阵乘法
 * a点乘b，当矩阵a的列数x与矩阵b的行数y相等时可进行相乘
 * a乘b得到的新矩阵c，c的行数y等于a的行数，c的列数x等于b的列数
 * Created by Queena on 2017/8/19.
 */
public class MatrixMultiplication {
    public static int[][] matrix(int a[][], int b[][]) {
        //当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
        if (a[0].length != b.length)
            return null;
        //c矩阵的行数y，与列数x
        int y = a.length;
        int x = b[0].length;
        int c[][] = new int[y][x];
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++)
                //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
                for (int k = 0; k < b.length; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }
}
```

##### 3.2矩阵相加

##### 3.3查找一段英文文字中，某个单词或字母或数字出现的次数

```java
import java.util.*;
public class danci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String s = sc.nextLine();
        int sum = foo(str,s);
        System.out.println(sum);
    }
    private static int foo(String str, String s) {
        int sum=0;
        int sLen=s.length();
        int temp=0;
        while(str.contains(s)){
            sum++;
            temp = str.indexOf(s);
            str=str.substring(temp+sLen);
        }
        return sum;
    }
}
```

##### 3.4单向链表：P指向单向链表某一项；要求删除P指向的项，保证链表不断。

```java
 算法思想：我们无法得到p所指结点的前驱，但是其后继是知道的，当前结点和后继结点的区别是data的不同，我们可以将p所指向结点的后继的值赋给p所指向的结点，将p所指向结点的后继删除，将该后继的后继地址赋给p的next。即
      q=p.next;
      p.data=q.data;
      p.next=q.next;
```

### 4java基础

##### 4.1JAVA可以跨平台的原理，平台指的是什么

```
所谓的跨平台就是JAVA写的一份代码可以在任意的操作系统平台上运行。
JAVA源代码->JAVA字节码->JVM解释执行


```

![javaçè·¨å¹³å°åçï¼](https://s1.51cto.com/images/blog/201908/15/40064dd903bf5049d1077b7f28d63bb3.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

##### 4.2了解java的泛型吗

泛型接口   泛型类    泛型方法

> [java泛型（泛型接口、泛型类、泛型方法）](https://www.cnblogs.com/jpfss/p/9928698.html)

##### 4.3静态函数可以调用非静态成员变量吗 

静态方法是不需要初始化就可以调用的

没有实例化，就不能被直接使用的

##### 4.4简单的描述一下spring框架 



##### 4.5了解jvm的优化吗



##### 4.6枚举类

图纸的类型



### 5网络相关

##### 5.1网络有几层，分别是哪几层，什么什么位于哪一层   主要是网络层较多   问了几个网络协议  

##### 5.2问了电话是怎么通信的

##### 5.3 ios七层协议，大数据云计算，

##### 5.4问到了三种类型的网络模型（7层，4层，5层） 

##### 5.5IP属于网络层，TCP/UDP属于传输层，HTTP、NFS、FTP属于应用层。

##### 5.6问到了ARP地址解析协议。

>[IP地址、MAC地址、ARP地址解析协议]([https://www.cnblogs.com/418ks/p/7196696.html](https://www.cnblogs.com/418ks/p/7196696.html))
>
>[ARP地址解析协议原理]([https://www.cnblogs.com/csguo/p/7542944.html](https://www.cnblogs.com/csguo/p/7542944.html))



### 6linux

##### 6.1线程和进程，以及索引号

```
什么是进程？
    简言之，进程可视为一个正在运行的程序。
    进程是具有一定独立功能的程序关于某个数据集合上的一次运行活动。进程是操作系统进行资源分配的基本单位。
什么是线程？
    线程是操作系统进行调度的基本单位。
进程 vs. 线程
    一个程序至少有一个进程，一个进程至少有一个线程。
    线程比进程划分更细，所以执行开销更小，并发性更高。
    进程是一个实体，拥有独立的资源；而同一个进程中的多个线程共享进程的资源。
```

##### 6.2线程通信方式  同步方式.

线程间的通信方式

```
使用全局变量
使用消息实现通信
使用事件CEvent类实现线程间通信
```

线程间的同步方式

```\
 临界区
 互斥量
 信号量
 事件
```

> [线程间的通信、同步方式与进程间通信方式]([https://blog.csdn.net/weixin_42187898/article/details/93796820](https://blog.csdn.net/weixin_42187898/article/details/93796820))

##### 6.3信号量机制

> [并发编程基础-信号量机制]([https://blog.csdn.net/weixin_34378045/article/details/91471618](https://blog.csdn.net/weixin_34378045/article/details/91471618))

##### 6.4进程间的通信方式

```
管道( pipe )
有名管道 (named pipe) 
信号量( semophore )
消息队列( message queue )
信号 ( signal ) 
共享内存( shared memory )
套接字( socket )
```



##### 6.5Linux中的文件系统是否了解？ 

> [Linux下的文件系统]([https://blog.csdn.net/qq_42197548/article/details/89299615](https://blog.csdn.net/qq_42197548/article/details/89299615))

##### 6.6索引号

```
索引节点是一个结构，它包含了一个文件的长度、创建及修改时间、权限、所属关系、磁盘中的位置等信息。一个文件系统维护了一个索引节点的数组，每个文件或目录都与索引节点数组中的唯一一个元素对应。系统给每个索引节点分配了一个号码，也就是该节点在数组中的索引号，称为索引节点号。
```

##### 

##### 6.7Linux中查看进程以及查看进程中的线程的命令？ 

查看进程

```
ps命令，或者top命令，它能显示当前运行中进程的相关信息，包括进程的PID。

ps命令能提供一份当前进程的快照。如果想状态可以自动刷新，可以使用top命令。
```

查看线程

```
一、查看进程pid下的线程

命令：ps hH p pid

二、查看进程pid下的线程个数

命令：ps hH p pid | wc -l
```



##### 6.8问到了Linux和其他操作系统文件共享的问题，提到了Samba服务器。 

> [linux与其他操作系统文件共享方法]([https://blog.csdn.net/a271917994/article/details/72973152](https://blog.csdn.net/a271917994/article/details/72973152))

### 其他

1、研究生期间最得意的事情是什么

2、研究生期间最失落的事情是什么

3、你做过什么项目，难点是什么

4、你对我们公司有什么提问的吗

​      （公司附近住宿方便吗，我习惯晚上迟点回去）

5、就是聊了聊家庭情况，没什么技术问题。 



## 一面

自我介绍

项目介绍

### 数据库

sql语句，写出A表的BID字段等于B表中ID的所有数据， leftjoin 没写对



### 算法

写了一个冒泡排序，带有标志位



### Linux

命令都知道哪些

### 网络

ipconfig

tracert x.x.x.x

### JAVA

Go语言和Java语言都有什么区别

java集合类都有哪些  （我给把集合类的种类给画了一个图）

### JAVAWEB

我有说我自己在看微服务的一套

SpringBoot+Spring Cloud +Docker



### 其他

工作地点

怎么学习 （看视频，然后手动实操一遍）

