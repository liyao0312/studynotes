
/**
 * 单例模式，懒汉式,双重校验
 */
public class bTestSingleton {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1==s2);
    }
}


class Singleton{
    private  Singleton(){

    }

    private static Singleton instance = null;

    public static Singleton getInstance(){
        if(instance==null) {   //为了不让其他的做无谓的等待，不是空的话直接返回
            synchronized (Singleton.class) {  //线程安全
                if (instance == null) {       //是空的时候 创建
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}