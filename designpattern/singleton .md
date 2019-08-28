# 单例模式

### **什么是单例设计模式？**

单例模式，是一种常用的软件设计模式。在它的核心结构中只包含一个被称为单例的特殊类。通过单例模式可以保证系统中，应用该模式的类一个类只有一个实例。即一个类只有一个对象实例。

 

类结构图

 ![img](https://images2018.cnblogs.com/blog/1371573/201804/1371573-20180420101148045-181684396.png)

 

 

### **具体实现**

需要：

（1）将构造方法私有化，使其不能在类的外部通过new关键字实例化该类对象。

（2）在该类内部产生一个唯一的实例化对象，并且将其封装为private static类型。

（3）定义一个静态方法返回这个唯一对象。

 

实现一：立即加载 / “饿汉模式”

立即加载就是使用类的时候已经将对象创建完毕（不管以后会不会使用到该实例化对象，先创建了再说。很着急的样子，故又被称为“饿汉模式”），常见的实现办法就是直接new实例化。

```java
public class Singleton {

    // 将自身实例化对象设置为一个属性，并用static、final修饰
    private static final Singleton instance = new Singleton();
    
    // 构造方法私有化
    private Singleton() {}
    
    // 静态方法返回该实例
    public static Singleton getInstance() {
        return instance;
    }
}
```

“饿汉模式”的优缺点：

优点：实现起来简单，没有多线程同步问题。

缺点：当类SingletonTest被加载的时候，会初始化static的instance，静态变量被创建并分配内存空间，从这以后，这个static的instance对象便一直占着这段内存（即便你还没有用到这个实例），当类被卸载时，静态变量被摧毁，并释放所占有的内存，因此在某些特定条件下会耗费内存。

 

实现二：延迟加载 / “懒汉模式”

延迟加载就是调用get()方法时实例才被创建（先不急着实例化出对象，等要用的时候才给你创建出来。不着急，故又称为“懒汉模式”），常见的实现方法就是在get方法中进行new实例化。

```java
public class Singleton {

    // 将自身实例化对象设置为一个属性，并用static修饰
    private static Singleton instance;
    
    // 构造方法私有化
    private Singleton() {}
    
    // 静态方法返回该实例
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

“懒汉模式”的优缺点：

优点：实现起来比较简单，当类SingletonTest被加载的时候，静态变量static的instance未被创建并分配内存空间，当getInstance方法第一次被调用时，初始化instance变量，并分配内存，因此在某些特定条件下会节约了内存。

缺点：在多线程环境中，这种实现方法是完全错误的，根本不能保证单例的状态。

 

实现三：线程安全的“懒汉模式”



```java
public class Singleton {

    // 将自身实例化对象设置为一个属性，并用static修饰
    private static Singleton instance;
    
    // 构造方法私有化
    private Singleton() {}
    
    // 静态方法返回该实例，加synchronized关键字实现同步
    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```



优点：在多线程情形下，保证了“懒汉模式”的线程安全。

缺点：众所周知在多线程情形下，synchronized方法通常效率低，显然这不是最佳的实现方案。

 

实现四：DCL双检查锁机制（DCL：double checked locking）



```java
public class Singleton {

    // 将自身实例化对象设置为一个属性，并用static修饰
    private static Singleton instance;
    
    // 构造方法私有化
    private Singleton() {}
    
    // 静态方法返回该实例
    public static Singleton getInstance() {
        // 第一次检查instance是否被实例化出来，如果没有进入if块
        if(instance == null) {
            synchronized (Singleton.class) {
                // 某个线程取得了类锁，实例化对象前第二次检查instance是否已经被实例化出来，如果没有，才最终实例出对象
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```



方法四算是单例模式的最佳实现方式。内存占用率高，效率高，线程安全，多线程操作原子性。

 

参考
1、高洪岩，Java多线程编程核心技术，机械工业出版社

2、https://www.cnblogs.com/yinxiaoqiexuxing/p/5605338.html 