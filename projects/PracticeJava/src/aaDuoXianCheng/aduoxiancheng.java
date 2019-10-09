/*
Thread：

1.创建多线程的第一个方式

2.Thread类你的常用的方法：
   2.1 getName();
   2.2 setName();
   2.3 start()：开启一个线程并调用响应的run()执行
   2.4 run();
   2.5 yield(); “贡献”出当前线程对cpu的获取
   2.6 join(); 在a线程中调用b线程的join()方法，那么当b线程完全执行完以后，a线程才可能开始执行

 */



//1. 声明类继承Thread类
class myThread extends Thread{

    //2.重写run()方法，这是创建的多线程的执行的主体
    public void run(){
        for(int i=1;i<101;i++){
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
    }
}

public class aduoxiancheng {
    public static void main(String[] args) {
        //3.创建继承Thread类的子类的实例
        myThread m1 = new myThread();
        myThread m2 = new myThread();
        //线程命名
        //myThread m1 = new myThread("线程1");
        //myThread m2 = new myThread();
        m2.setName("线程2");

        //4.调用对象的start()方法： ①启动线程  ②调用响应线程对应类的run()方法
        m1.start();  //再次 m1.start(); 就会返回非法的线程状态异常
        m2.start();
        //不可以直接用run()方法，并没有新开线程、

        for (int i=1;i<=100;i++){
            if(i%10==0){
                Thread.yield();  //yield(); 贡献出当前线程的执行权
            }
            System.out.println(Thread.currentThread().getName()+": "+i);
        }

    }

}
