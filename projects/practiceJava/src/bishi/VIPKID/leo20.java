package bishi.VIPKID;

import java.util.Scanner;

/**
 * Created by artsing on 2019/8/29.
 */
public class leo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int count=0;
        while(a!=0){
            count++;
            a=(a-1)&a;
        }
        System.out.println(count);
    }
}
