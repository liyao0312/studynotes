/**
 *  开启三个窗口买票
 *
 *  可能出现线程安全问题
 *  1. 休闲线程安全问题的原因：当多个线程有共享数据的时候，由于一个线程对共享数据的操作尚未完成，其他线程就参与出来
 *  2.如何解决线程的安全问题： 要保证党一个线程操作共享数据的时候，其他线程必须在外面等候。知道操作共享数据的线程执行完，其他线程才可以操作贡献数据
 *  3.如何来操作：①java如何实现的  ②什么是共享数据
 *
 *
 *  创建多线程的第二种方式：实现Runnalble接口
 */

//1. 创建实现Runnable接口的类
class Ticket1 implements  Runnable{
    int ticket =100;
    Object obj = new Object(); //必须定义到run方法以外。类似wc的灯
    //2. 重写Runnable接口中的抽象方法
    public  void run(){
        while(true){
            show();
        }
    }

    //同步方法的方式  加synchronized 关键字
    public synchronized void show(){   //同步方法默认的锁是当前对象
        if (ticket > 0) {
            try{
                Thread.currentThread().sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "售票，票号位：" + ticket--);
        }
    }
}
public class TestTicket1 {
    public static void main(String[] args) {
        //3. 创建一个实现接口的子类对象
        Ticket1 t = new Ticket1();
        //4. 将创建的子类对象作为形参传递给Thread类的构造器
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);

        t1.setName("t1窗口");
        t2.setName("t2窗口");
        t3.setName("t3窗口");

        t1.start();
        t2.start();
        t3.start();
    }
}
