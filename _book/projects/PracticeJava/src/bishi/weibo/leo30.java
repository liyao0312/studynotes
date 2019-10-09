package bishi.weibo;

import java.util.Scanner;

public class leo30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.print(s);
        thread3.start();
        thread2.start();
        thread1.start();
    }

    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.print("_A");
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("_B");
        }
    });
    static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("_C");
        }
    });
}