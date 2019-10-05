/**
 * 设计模式：设计模式是在大量的实践中总结和理论化之后优选的代码结构、编程风格、以及解决问题的思考方式。
 * 23种设计模式
 *
 * 单例的设计模式：
 * 1.解决的问题：使得一个类只能够创建一个对象。
 * 2.如何实现？见如下的4步
 */

//饿汉式的   （上来就创建好，很饿）
public class TestSingleton1 {
    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.out.println(s1==s2);
    }
}

//只能创建mySingleton的单个实例
class Singleton1 {

    //1.私有化构造器,使得在类的外部不能够调用此构造器
    private Singleton1(){

    }
    //2.在类的内部创建一个类的实例
    private static Singleton1 instance = new Singleton1();
    //3.私有化此对象，通过公共的方法来调用
    //4.此公共的方法，只能通过类来调用，因此设置为static，同时类的实例也必须为static声明的
    public static Singleton1 getInstance(){
        return  instance;
    }
}
