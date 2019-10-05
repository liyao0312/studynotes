/**
 * 死锁
 * 两个线程同时运行了，线程1中s1拿到了s2的锁  线程2中s2要拿s1的锁。就僵持住了，程序无法继续运行
 */
public class TestDeadLock {
    public static void main(String[] args) {
        final  StringBuffer s1 = new StringBuffer();
        final  StringBuffer s2 = new StringBuffer();

        new Thread(){
            public  void run(){
                synchronized (s1){
                    s2.append("a");
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (s2){
                        s1.append("b");
                        System.out.print(s1);
                        System.out.print(s2);
                    }
                }
            }
        }.start();

        new Thread(){
            public  void run(){
                synchronized (s2){
                    s1.append("c");
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (s1){
                        s1.append("d");
                        System.out.print(s1);
                        System.out.print(s2);
                    }
                }
            }
        }.start();
    }
}
