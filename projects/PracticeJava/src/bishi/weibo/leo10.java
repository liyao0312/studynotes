package bishi.weibo;

import java.util.*;

public class leo10 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String input = sc.nextLine();
        System.out.print(input);
        threadThree.start();
        threadTwo.start();
        threadOne.start();

    }


    static Thread threadOne = new Thread(new Runnable() {

        @Override
        public void run() {
            System.out.print("_A");
        }
    });
    static Thread threadTwo = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                threadOne.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("_B");
        }
    });
    static Thread threadThree = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                threadTwo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("_C");
        }
    });



}
