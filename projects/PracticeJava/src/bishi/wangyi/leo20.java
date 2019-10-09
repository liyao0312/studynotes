package wangyi;

import java.util.Scanner;

public class leo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] re = new int[t];
        for (int i = 0; i < t; i++) {
            op=0;
            int[] a =new int[4];
            a[0]=sc.nextInt();
            a[1]=sc.nextInt();
            a[2]=sc.nextInt();
            a[3]=sc.nextInt();
            so(a);
            re[i]=op;
        }
        for (int i = 0; i < t; i++) {
            System.out.println(re[i]);
        }


    }
    static int op=0;
    private static void so(int[] a) {
        if(a[0]>=a[1]) return;
        int flag1 = a[0]+a[2]+a[2];
        int flag2 = a[0]+a[2]*a[3];
        if(flag1>=flag2||a[3]==1||(a[0]+a[2]>a[1])){
            a[0]+=a[2];
            op++;
        }else{
            a[2]*=a[3];
            op++;
        }
        so(a);
    }


}
