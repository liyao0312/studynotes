# 快手一面

自我介绍，项目介绍

最后一道编程题，好序列

## 问题1.数组和链表的区别

答：数组存储是一整块存储的，链表是用指针一个一个关联起来的不是整块的。

数组查询速度快，时间复杂度o（1）；链表查询时间复杂度是o（N）；

问：java里面，数组里面存储的对象是连续的吗

答：对象的数组，数组里面存储的是对象的引用，引用是连续的，对象本身不是连续的

## 问题2：java里面有个ArrayList，这个数据类型怎么实现变长的

答：是不是说扩容

问：不用说扩容，说下怎么实现的

答：动态数组，初始容量是10，容量不够时，会扩1.5倍

问：那扩是怎么扩的？说一下扩容的过程

答：添加元素时使用 `ensureCapacityInternal()` 方法来保证容量足够，如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 `oldCapacity + (oldCapacity >> 1)`，也就是旧容量的 1.5 倍。

## 问题3：hashmap是怎么样的数据结构

答：hashmap是数组和链表的数据结构，1.8还加入了红黑树

问：什么情况下会用到红黑树

答：数组和链表的数据结构，每次hash计算出来数组的位置，然后链表里面存储value，链表长度大于8的时候就会转换成红黑树

问：红黑树的查找时间复杂度是多少

答：logN

问：红黑树其实是一个类平衡二叉树，优化hashmap的时候，为什么用红黑树不用平衡二叉树，平衡二叉树的时间复杂度也是logN

答：红黑树不用判断是否平衡，平衡二叉树对平衡的要求更高，每次判断是否平衡（左右子树的高度差不能超过1），插入的时候就会频繁的旋转

## 问题4：hashset了解过吗

答：了解过

问：hashset跟hashmap有什么关系

答：set是在map基础上实现的，比如treeset就是在treemap基础上实现的

## 问题5：hashtable了解过吗

答：线程安全的

问：怎么实现线程安全的

答：...说了ConCurrentHashMap发现不是一回事

问：是用synchronized关键字来实现的



## 问题6：什么是线程安全，能给我讲一下吗

答：balabala瞎扯了一堆，反正不对

问：A更改了B知道，怎么知道的

答：

## 问题7：计算机网路的五层结构

答：物理层、数据链路层、网络层、传输层、应用层

问：除了物理层以外，其他的每一层举一个协议，用了什么协议

答：数据链路层，主要协议PPP，数据单元帧

网络层主要协议IP，数据单元IP数据报

传输层主要协议UDP、TCP，数据单元报文段

应用层主要协议HTTP、DNS、SMTP、FTP，数据单元报文

问：IP中的子网掩码是做什么用的

答：想不起来

问：TCP和UDP的区别

TCP是面向连接的，UDP是无连接的

TCP是可靠的，UDP是不可靠的

TCP面向字节流，UDP面向报文

TCP传输速度慢，UDP传输速度快

问：TCP为什么传输速度慢（打断问）

因为TCP要保证可靠性，TCP有拥塞控制，UDP没有

问;TCP是一个可靠连接，是怎么保证可靠传输的。

答：三次握手建立连接，四次挥手建立连接

问：那中间传输数据的时候是怎么保证可靠性的

答：保证所有的包都到达，有重传机制

问：什么时候会有重传呢

答：发送数据包在一定的时间周期内没有收到相应的反应，等待一定的时间，超时之后就认为这个数据包丢失，就会重新发送。

问：网络有可能堵塞，会丢包，知道数据丢包的情况下，超时了，那重传之后的包怎么办。

答：...

问：重传之后的包，以后的包都收到了，中间的某一个包没有收到，这个包怎么办

答；....

问：TCP 有个慢启动，了解过吗，拥塞控制的一部分

答：刚开始传输的数据量很小，然后慢慢的把报文的数据量增大

问：它是以什么样的速率去扩大的

答：2倍

## 问题8：数据库

问：数据库里面有两个表，表A里面有7条数据，表B里面有10条数据，select count from  A left join B on A.x = B.x

答：7条，left join ，左连接，返回包括左表中的所有记录和右表中连接字段相等的记录

问：7是最小的，其实是个范围值。 A.x表示x是A的一个字段

问：如果A里面有1条数据，B里面有2条数据，如果A里面x字段的值是123，B里面X字段的值是123都能连接上，查询结果是几条。以左边为基表，连接右边的表 符合的字段

答：2条

问：所以刚才的答案是多少

答：7-10

问：还能更多吗

答：17

问; 还可能更多吗，考虑极端的情况，是个笛卡尔积

答：70

问： select count(1) count(x)     ,这里面的count(1)和count(x)有区别吗

答：不知道，觉得是一样的

问：count(x) count字段的话不会把空值给统计出来。count(1)的话count的是第一列，count的是索引，所以也不为空。count(*)的是count的所有整个表。

问：现在很多开发规范里面其实是禁止使用表连接的，考虑为什么不允许使用表连接，表连接有什么不好的地方。

答：数据看的少，

## 问题9：java代码

问： 下面这段代码的返回值是多少？

```java
int i=0; 
try{ 
  i++; return i；
} catch(Exception e){
  i++;
} finally{
  i++;
}
```

答：返回值是1

问：为什么是1

答：finally中的i++虽然执行了，但是对try中的return返回值没有影响

问：为什么不影响

答：只是记住了，不知道为什么

问：java虚拟机中有一部分叫栈，栈里面的基本元素时栈帧，一个栈帧什么时候会创建，什么时候会销毁，说一下

答：呃...

问：或者说栈里面存的什么东西了解过吗

答：呃...  栈里面存基本数据 数组

问：栈和堆有什么不一样吗

答：堆中存的是对象的实例，栈中存的是方法和基本数据

问：听说过栈帧这个概念吗

答：没有

问：栈帧其实就是调用一个方法的时候，就会往栈里面压一个栈帧，然后栈帧里面存的是局部变量表，里面包括了一些基础类型，方法执行完了会弹栈，弹栈的话，就会把这些信息给返回出来，栈帧的这块区域里面就存的返回值。这就是这个地方不会改变的原因，因为i的地址，和return返回的地址不是同一个地址，return的时候已经把返回值的地址写了，后面再写i的地址不会对return有影响。

问:堆里面你刚才说了存的是对象的实例，堆什么情况下会溢出？

答：堆里面溢出，堆的结构是新生代，老年代，然后内存大小超过一个值就溢出

问：比如说我创建了一个特别大的对象，然后堆溢出了，从创建对象到堆溢出发生了什么事情。

答：呃...特别大就不往新生代里面放，往老年代里面放，

## 问题10：代码题

问：判断一个字符串是否是回文的。例如 aba，abccba

答：思路是高低指针，遍历头尾是否相等，while循环接受条件是头大于尾

问：你把这个思路实现一下吧

答：

```java
public class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      
        System.out.println(solution(s));
    }
    
    public static boolean solution(String s){
        int p1=0,p2=s.length()-1;
        while(p1<p2){
            if(s.charAt(p1)!=s.charAt(p2)){
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }
}
```

问：  charAt() 刚开始没用对

答：idea 会自动出现提示，这个在线编译没有提示

问：判断一个字符串，最多删除一个字符，能否变成回文的

答：

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    static int flag=0;
    public static boolean solution(String s){
        if(flag>1) return false;
        int p1=0,p2=s.length-1;
        while(p1<p2){
            if(s.charAt(p1)!=s.charAt(p2)){
                flag+=1;
                boolean b1=solution(s.substring(p1+1,p2+1));
                boolean b2=solution(s.substring(p1,p2));
                return b1||b2;
            }
            p1++;p2--;
        }
        return true;
    }
}
```

问： substring 第一次写的时候没对，第二个结束索引（不包括）

答；好的谢谢提醒

问：你用了static，static有什么含义

答：在类加载的时候就被加载，不被实例化也会被加载。还有一个执行顺序，父类静态代码块，子类静态代码块，父类非静态代码块，父类构造方法，子类非静态代码块，子类构造方法。

问：类实例化两个对象A和B。静态变量在A中修改对B有影响吗

答：有影响，



# 快手二面

## 问题1：SQL语句

问：一个表内有两个属性，名字，对应负责项目。 怎么找负责两个以上项目的人。

答：呃....最近没怎么看mysql数据库，用的都是mongodb

```sql
select username from table1 group by username having count >=2
```

问；mongoDB的主从模式，是单机模式还是集群模式

答：集群模式

问：那MongoDB是主从模式吗

答：是

问：分布式存储知道吗

答：不知道

## 问题2：代码题

问： 实现整数反转（提示注意整数的临界值）

答：

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int max = sc.nextInt();
        int result = reverse(i,max);
        System.out.println(result);
    }
    
    public static int reverse(int i,int max){
        if(i/10==0) return i;
        int result=0;
        
        while(i/10!=0){
            int temp=i%10;
            i=i/10;
            result=result*10+temp;
        }
        result=result*10+i;
        if(result<0) return -1;
        return result;
    }
    
}
```

问：整数是怎么存的，超过存储容量后会有什么结果

答：（怎么存不知道），超过存储容量后，会把符号位也覆盖，编程负数。

问：那result判断一下就可以了，小于0就是大于临界值了

答：好的