/**
 * 懒汉式 ：可能存在线程安全
 */
public class TestSingleton2 {
    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        System.out.println(s1==s2);
    }
}

class Singleton2{
    //1.
    private Singleton2(){

    }
    //2.
    private static  Singleton2 instance = null;
    //3.
    //4.
    public  static  Singleton2 getInstance(){
        if(instance==null){
            instance = new Singleton2();
        }
        return instance;
    }
}